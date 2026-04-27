package com.jobportal.controller;

import com.jobportal.dao.*;
import com.jobportal.model.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ApplicationServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private JobDAO jobDAO = new JobDAO();
    private SkillDAO skillDAO = new SkillDAO();
    private UserSkillDAO userSkillDAO = new UserSkillDAO();
    private ApplicationDAO applicationDAO = new ApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Job> jobs = jobDAO.getAllJobs();
        List<Skill> skills = skillDAO.getAllSkills();
        request.setAttribute("jobs", jobs);
        request.setAttribute("skills", skills);
        request.getRequestDispatcher("apply.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("apply".equals(action)) {
            handleApplication(request, response);
        } else if ("search".equals(action)) {
            handleSearch(request, response);
        } else {
            request.setAttribute("error", "Invalid action.");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

    private void handleApplication(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = trim(request.getParameter("name"));
        String email = trim(request.getParameter("email"));
        String jobIdStr = trim(request.getParameter("jobRole"));
        String[] skillsArray = request.getParameterValues("skills");

        if (name.isEmpty() || email.isEmpty() || jobIdStr.isEmpty()) {
            redirectWithError(response, "apply.jsp", "All required fields must be filled.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            redirectWithError(response, "apply.jsp", "Please enter a valid email address.");
            return;
        }

        if (skillsArray == null || skillsArray.length == 0) {
            redirectWithError(response, "apply.jsp", "Please select at least one skill.");
            return;
        }

        int skillsCount = skillsArray.length;
        int jobId = Integer.parseInt(jobIdStr);

        HttpSession session = request.getSession();
        session.setAttribute("userName", name);

        Cookie roleCookie = new Cookie("preferredJobRole", jobIdStr);
        roleCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(roleCookie);

        try {
            User user = userDAO.getUserByEmail(email);
            if (user == null) {
                user = new User(0, name, email, "");
                userDAO.insertUser(user);
                user = userDAO.getUserByEmail(email);
            }

            Application app = new Application(0, user.getId(), jobId, new Date(), "Pending");
            boolean inserted = applicationDAO.insertApplication(app);

            for (String skillIdStr : skillsArray) {
                int skillId = Integer.parseInt(skillIdStr);
                userSkillDAO.insertUserSkill(new UserSkill(user.getId(), skillId));
            }

            if (inserted) {
                request.setAttribute("message", "Application submitted successfully.");
                request.setAttribute("skillsCount", skillsCount);
            } else {
                request.setAttribute("error", "Unable to save application. Please try again.");
            }
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String searchRole = trim(request.getParameter("searchRole"));
        if (searchRole.isEmpty()) {
            redirectWithError(response, "search.jsp", "Please select a job role to search.");
            return;
        }

        try {
            List<Job> jobs = jobDAO.getAllJobs();
            Job foundJob = jobs.stream().filter(j -> j.getTitle().equalsIgnoreCase(searchRole)).findFirst().orElse(null);
            if (foundJob == null) {
                request.setAttribute("applications", List.of());
                request.setAttribute("searchRole", searchRole);
                request.setAttribute("message", "No jobs found for this role.");
                request.getRequestDispatcher("result.jsp").forward(request, response);
                return;
            }
            List<Application> apps = applicationDAO.getApplicationsByJobId(foundJob.getId());
            List<Map<String, Object>> applications = new ArrayList<>();
            for (Application app : apps) {
                User user = userDAO.getUserById(app.getUserId());
                List<UserSkill> userSkills = userSkillDAO.getSkillsByUserId(user.getId());
                List<String> skillNames = new ArrayList<>();
                for (UserSkill us : userSkills) {
                    Skill skill = skillDAO.getSkillById(us.getSkillId());
                    if (skill != null) skillNames.add(skill.getSkillName());
                }
                java.util.HashMap<String, Object> row = new java.util.HashMap<>();
                row.put("id", app.getId());
                row.put("name", user.getName());
                row.put("email", user.getEmail());
                row.put("job_role", foundJob.getTitle());
                row.put("skills", String.join(", ", skillNames));
                applications.add(row);
            }
            request.setAttribute("applications", applications);
            request.setAttribute("searchRole", searchRole);
            request.setAttribute("message", "Search completed.");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Database error: " + ex.getMessage());
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

    private void redirectWithError(HttpServletResponse response, String page, String error)
            throws IOException {
        String encodedError = URLEncoder.encode(error, StandardCharsets.UTF_8.name());
        response.sendRedirect(page + "?error=" + encodedError);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }
}