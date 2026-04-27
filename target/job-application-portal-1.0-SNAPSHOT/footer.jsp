<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<footer class="site-footer">
    <div class="container">
        <p><strong>Student IDs:</strong> <%= application.getInitParameter("studentIds") %></p>
        <p><strong>Email:</strong> <%= application.getInitParameter("contactEmail") %></p>
        <p>&copy; <%= java.time.Year.now() %> Job Application Portal. All rights reserved.</p>
    </div>
</footer>
