<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply Job - Job Application Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container card">
        <h2>Job Application Form</h2>

        <%
            String error = request.getParameter("error");
            if (error != null && !error.trim().isEmpty()) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            }
        %>

        <form action="ApplicationServlet" method="post" class="portal-form">
            <input type="hidden" name="action" value="apply">

            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" placeholder="Enter your full name" required>

            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="jobRole">Job Role</label>
            <select id="jobRole" name="jobRole" required>
                <option value="">-- Select Job Role --</option>
                <option value="Developer">Developer</option>
                <option value="Designer">Designer</option>
                <option value="Analyst">Analyst</option>
            </select>

            <fieldset>
                <legend>Skills</legend>
                <label><input type="checkbox" name="skills" value="Java"> Java</label>
                <label><input type="checkbox" name="skills" value="Python"> Python</label>
                <label><input type="checkbox" name="skills" value="SQL"> SQL</label>
                <label><input type="checkbox" name="skills" value="Web Dev"> Web Dev</label>
            </fieldset>

            <div class="form-actions">
                <button type="submit">Submit Application</button>
                <button type="reset" class="secondary-btn">Cancel</button>
            </div>
        </form>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
