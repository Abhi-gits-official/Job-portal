# Job Application Portal

A dynamic Java web application built with JSP, Servlets, JDBC, and MySQL.  
Users can submit job applications and search submitted applications by job role.

## Tech Stack

- Java (Maven WAR project)
- JSP + Jakarta Servlets (Tomcat 10+ compatible)
- JDBC + MySQL
- HTML5 + CSS3
- Apache NetBeans
- Apache Tomcat 10+ / 11

## Features

- Apply Job form with client-side and server-side validation
- Search applications by job role
- Result page with status messages and tabular data
- Session usage (`userName`) and cookie usage (`preferredJobRole`)
- Reusable `header.jsp` and `footer.jsp`
- Modern responsive UI

## Project Structure

```text
Job Application Portal/
├── pom.xml
├── job_portal.sql
├── src/
│   └── java/
│       └── com/jobportal/
│           ├── controller/
│           │   └── ApplicationServlet.java
│           └── util/
│               └── DBConnection.java
└── web/
    ├── index.jsp
    ├── apply.jsp
    ├── search.jsp
    ├── result.jsp
    ├── about.jsp
    ├── header.jsp
    ├── footer.jsp
    ├── css/
    │   └── style.css
    └── WEB-INF/
        └── web.xml
```

## Prerequisites

- JDK 17+ (JDK 25 is also fine)
- Maven 3.8+
- MySQL 8+
- Tomcat 10+ (Jakarta namespace)
- NetBeans (latest recommended)

## Database Setup

1. Open MySQL client (MySQL Workbench / CLI).
2. Execute:
   - `job_portal.sql`
3. Confirm database/table:
   - Database: `job_portal`
   - Table: `applications`

### Table Columns (Current)

- `id` (AUTO_INCREMENT, PK)
- `name`
- `email`
- `job_role`
- `skills`

## Configure Database Credentials

Update values in `src/java/com/jobportal/util/DBConnection.java` if needed:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Current defaults:

- URL: `jdbc:mysql://localhost:3306/job_portal?useSSL=false&serverTimezone=UTC`
- Username: `root`
- Password: `root`

## Run in NetBeans (Recommended)

1. Open the project folder in NetBeans.
2. Let Maven download dependencies from `pom.xml`.
3. Configure Tomcat 10+/11 as the server.
4. Run the project.
5. Open:
   - `http://localhost:8080/job-application-portal/`

## Run via Terminal (Build)

From project root:

```bash
mvn clean package
```

Generated WAR:

- `target/job-application-portal-1.0-SNAPSHOT.war`

Deploy this WAR to Tomcat `webapps` folder, then open:

- `http://localhost:8080/job-application-portal/`

## Main Pages

- Home: `/index.jsp`
- Apply Job: `/apply.jsp`
- Search: `/search.jsp`
- About: `/about.jsp`
- Servlet endpoint: `/ApplicationServlet`

## Notes

- `web.xml` uses Jakarta schema (`web-app 5.0`), so project targets Tomcat 10+.
- Group member details are currently read from `web.xml` context params (not from DB).

## Troubleshooting

- **404 on app URL**: Check WAR is deployed under correct context path.
- **DB error on submit/search**: Verify MySQL is running and credentials are correct.
- **UI not updating**: Rebuild/redeploy WAR and hard refresh browser (`Cmd/Ctrl + Shift + R`).
- **Servlet import issues**: Ensure runtime is Tomcat 10+ (Jakarta), not Tomcat 9.
