<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Job Application Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="container home-page">
        <section class="card home-hero">
            <p class="hero-tag">Smart Career Management</p>
            <h2>Welcome to the Job Application Portal</h2>
            <p class="hero-text">
                Submit job applications in minutes, organize candidate information centrally,
                and search applications by role with a clean, modern experience.
            </p>
            <div class="hero-actions">
                <a href="apply.jsp" class="cta-btn primary-link">Apply for a Job</a>
                <a href="search.jsp" class="cta-btn secondary-link">Search Applications</a>
            </div>
            <div class="hero-stats">
                <div class="stat-item">
                    <strong>Fast</strong>
                    <span>One-page submission flow</span>
                </div>
                <div class="stat-item">
                    <strong>Secure</strong>
                    <span>Servlet + JDBC data handling</span>
                </div>
                <div class="stat-item">
                    <strong>Efficient</strong>
                    <span>Role-based record search</span>
                </div>
            </div>
        </section>

        <section class="home-grid">
            <article class="card mini-card">
                <h3>Why Use This Portal?</h3>
                <ul class="feature-list">
                    <li>Simple and responsive interface</li>
                    <li>Validated form submission</li>
                    <li>Organized application records</li>
                    <li>Built with JSP, Servlets, JDBC, and MySQL</li>
                </ul>
            </article>

            <article class="card mini-card">
                <h3>Group Members</h3>
                <%
                    String members = application.getInitParameter("groupMembers");
                    if (members != null && !members.trim().isEmpty()) {
                        String[] memberList = members.split(",");
                %>
                        <ul class="member-list">
                            <%
                                for (String member : memberList) {
                            %>
                                    <li><%= member.trim() %></li>
                            <%
                                }
                            %>
                        </ul>
                <%
                    } else {
                %>
                        <p>Group member details are not configured.</p>
                <%
                    }
                %>
            </article>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
