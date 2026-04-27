<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result - Job Application Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container card">
        <h2>Operation Result</h2>

        <%
            String message = (String) request.getAttribute("message");
            String error = (String) request.getAttribute("error");
            Integer skillsCount = (Integer) request.getAttribute("skillsCount");
            String searchRole = (String) request.getAttribute("searchRole");
            List<Map<String, String>> applications = (List<Map<String, String>>) request.getAttribute("applications");
        %>

        <%
            if (error != null && !error.trim().isEmpty()) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            } else if (message != null && !message.trim().isEmpty()) {
        %>
            <p class="success-message"><%= message %></p>
            <%
                if (skillsCount != null) {
            %>
                <p>Total selected skills: <strong><%= skillsCount %></strong></p>
            <%
                }
            %>
        <%
            }
        %>

        <%
            if (applications != null) {
                if (applications.isEmpty()) {
        %>
                    <p>No results found for the selected role.</p>
        <%
                } else {
        %>
                    <h3>Search Results for: <%= searchRole %></h3>
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Job Role</th>
                                    <th>Skills</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Map<String, String> row : applications) {
                                %>
                                        <tr>
                                            <td><%= row.get("id") %></td>
                                            <td><%= row.get("name") %></td>
                                            <td><%= row.get("email") %></td>
                                            <td><%= row.get("job_role") %></td>
                                            <td><%= row.get("skills") %></td>
                                        </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
        <%
                }
            }
        %>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
