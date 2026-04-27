<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<header class="site-header">
    <div class="container header-inner">
        <h1><%= application.getInitParameter("projectTitle") %></h1>
        <nav>
            <ul class="nav-links">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="apply.jsp">Apply Job</a></li>
                <li><a href="search.jsp">Search Applications</a></li>
                <li><a href="about.jsp">About Us</a></li>
            </ul>
        </nav>
    </div>
    <div class="container members-strip">
        <strong>Group Members:</strong>
        <span><%= application.getInitParameter("groupMembers") %></span>
    </div>
</header>
