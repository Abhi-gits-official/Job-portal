<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Job Application Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container about-page">
        <section class="card about-hero">
            <p class="about-tag">About the Platform</p>
            <h2>About This Project</h2>
            <p class="about-lead">
                The Job Application Portal is a modern web application that helps candidates submit
                applications and helps administrators search role-based records efficiently.
            </p>
            <div class="tech-badges">
                <span>JSP</span>
                <span>Servlets</span>
                <span>JDBC</span>
                <span>MySQL</span>
                <span>HTML5</span>
                <span>CSS3</span>
            </div>
        </section>

        <section class="about-grid">
            <article class="card about-card">
                <h3>What This Portal Provides</h3>
                <ul>
                    <li>Clean and responsive application submission interface</li>
                    <li>Server-side validation before data persistence</li>
                    <li>Search functionality by job role</li>
                    <li>Reusable header and footer components across pages</li>
                </ul>
            </article>

            <article class="card about-card">
                <h3>Architecture Highlights</h3>
                <ul>
                    <li>JSP pages handle UI and reusable layout fragments</li>
                    <li>Servlet manages business logic and request flow</li>
                    <li>JDBC utility handles database connectivity and queries</li>
                    <li>MySQL stores structured application records</li>
                </ul>
            </article>
        </section>

        <section class="card about-future">
            <h3>Future Enhancements</h3>
            <p>
                The project is designed for extensibility and can be upgraded with authentication,
                role-based access, richer analytics dashboards, and advanced filtering without
                major structural changes.
            </p>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
