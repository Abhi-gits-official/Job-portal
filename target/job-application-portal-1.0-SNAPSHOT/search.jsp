<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Applications - Job Application Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container card">
        <h2>Search Job Applications</h2>

        <%
            String error = request.getParameter("error");
            if (error != null && !error.trim().isEmpty()) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            }
        %>

        <form action="ApplicationServlet" method="post" class="portal-form inline-form">
            <input type="hidden" name="action" value="search">

            <label for="searchRole">Job Role</label>
            <select id="searchRole" name="searchRole" required>
                <option value="">-- Select Job Role --</option>
                <option value="Developer">Developer</option>
                <option value="Designer">Designer</option>
                <option value="Analyst">Analyst</option>
            </select>

            <div class="form-actions">
                <button type="submit">Search</button>
                <button type="reset" class="secondary-btn">Reset</button>
            </div>
        </form>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
