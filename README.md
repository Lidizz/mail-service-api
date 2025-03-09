# Spring Boot Mail Service API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.4-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Status](https://img.shields.io/badge/Status-Development-red)

This project is a RESTful Mail Service API built with Spring Boot, Java, and PostgreSQL. It provides a robust backend for managing users and email records, showcasing the integration of Spring Boot with RESTful web services and Spring Data JPA for seamless database operations. This guide is for educational purposes and was made to be beginner-friendly. It will allow you to clone, run and test the project from your local environment.

## Table of Contents
- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [PostgreSQL Setup](#postgresql-setup)
  - [Setup via Command Line (CLI)](#setup-via-command-line-cli)
  - [Setup via IntelliJ IDEA](#setup-via-intellij-idea)
  - [Setup via Eclipse](#setup-via-eclipse)
- [API Endpoints](#api-endpoints)
  - [User Controller](#user-controller)
  - [Email Record Controller](#email-record-controller)
- [Testing the API](#testing-the-api)
  - [Testing via CLI with cURL](#testing-via-cli-with-curl)
  - [Testing via Postman](#testing-via-postman)
  - [Testing via Swagger UI](#testing-via-swagger-ui)
- [Additional Notes](#additional-notes)
- [Troubleshooting](#troubleshooting)

## Project Overview

The API provides functionality to manage users and email records:
- **User Management**: Create, update, retrieve, and delete users.
- **Email Management**: Send emails, retrieve emails (all, by ID, by sender, or by recipient), and delete emails.

The project uses:
- Java JDK 21 (latest LTS as of March 2025)
- Maven for dependency management
- Spring Boot 3.4.2 (current; latest stable is 3.4.3 as of March 2025)
- Spring Data JPA for database operations
- PostgreSQL 16.4 as the database (server version; driver version 42.7.3 via Spring Boot 3.4.2)
- Swagger for API documentation (via OpenAPI configuration, Springdoc 2.6.0)

## Prerequisites

Before setting up the project, ensure you have these tools installed:
- **Java JDK 21**: Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html). This is the programming language version used.
- **Maven**: Download from [Maven](https://maven.apache.org/download.cgi). It manages project dependencies and builds.
- **PostgreSQL**: Download from [PostgreSQL](https://www.postgresql.org/download/). This is the database system.
- **Optional (for IDE setups)**:
  - IntelliJ IDEA: Download from [JetBrains](https://www.jetbrains.com/idea/download/) (recommended for development).
  - Eclipse: Download from [Eclipse](https://www.eclipse.org/downloads/) (another popular IDE option).

- **Dependencies**:
  - `spring-boot-starter-web:3.4.2` (RESTful web services)
  - `spring-boot-starter-data-jpa:3.4.2` (JPA/Hibernate)
  - `postgresql:42.7.3` (PostgreSQL driver)
  - `springdoc-openapi-starter-webmvc-ui:2.6.0` (Swagger UI)
  - `lombok:1.18.34` (code generation | can be used to replace existing code)
  - `spring-boot-starter-validation:3.4.2` (input validation)
  - `h2` (runtime, optional for testing; commented out but can be used if wanted)

## Setup Instructions

### PostgreSQL Setup

Before proceeding with any setup method, install PostgreSQL and create the required database. Choose either the command-line (`psql`) or graphical (pgAdmin 4) approach below.

1. **Install PostgreSQL**:
   - Visit [postgresql.org/download](https://www.postgresql.org/download/) and select your operating system (e.g., Windows, macOS, Linux).
   - Download the installer and run it. During installation:
     - Set a password for the `postgres` user (e.g., `your_password`—store it for later use).
     - Accept the default port `5432` unless you need to change it.
   - After installation, PostgreSQL should start automatically. On Windows, check the “Services” app (search in Start menu) for “postgresql-x64-XX” (XX is the version, e.g., 16) and ensure it’s running. On Linux/macOS, it starts via the installer or use `sudo service postgresql start`.

2. **Create the Database**:
   - **Option 1: Using `psql` (Command Line)**:
     - Open a terminal (e.g., Command Prompt or PowerShell on Windows, Terminal on macOS/Linux).
     - Log in to PostgreSQL:
       ```bash
       psql -U postgres
       ```
     - You’ll see: `Password for user postgres:`. Enter your password (e.g., `your_password`, it may not show that you enter anything in the shell while you enter your password, it is normal) and press Enter.
     - At the `postgres=#` prompt, create the database:
       ```sql
       CREATE DATABASE mail_service_db;
       ```
     - Verify it exists by listing databases:
       ```sql
       \l
       ```
       Look for `mail_service_db` in the list (it might show under “Name”).
     - Exit `psql`:
       ```sql
       \q
       ```
       This quits the PostgreSQL command-line tool and returns you to your terminal.
   - **Option 2: Using pgAdmin 4 (Graphical Interface)**:
     - Open pgAdmin 4 (installed with PostgreSQL—find it in your Start menu or applications).
     - In the login window, enter your `postgres` password and click “OK”.
     - In the left sidebar, right-click “Servers” > “Register” > “Server” if not already set up.
     - Name it (e.g., “Local”), go to “Connection” tab, set “Host” to `localhost`, “Username” to `postgres`, “Password” to `your_password`, then “Save”.
     - Expand the server, right-click “Databases” > “Create” > “Database”.
     - In the “Database” field, enter `mail_service_db`, then click “Save”.
     - Verify: You’ll see `mail_service_db` under “Databases” in the sidebar.

Now, proceed to your preferred setup method below.

### Setup via Command Line (CLI)

1. **Clone the Repository**:
   - Open your terminal and navigate to where you want the project (e.g., `C:\projects` on Windows):
     ```bash
     cd C:\projects
     ```
   - Clone the GitHub repository:
     ```bash
     git clone https://github.com/Lidizz/mail-service-api.git
     ```
   - Move into the project folder:
     ```bash
     cd mail-service-api
     ```

2. **Set Environment Variables**:
   - The app needs a username and password to connect to PostgreSQL, stored in `DB_USERNAME` and `DB_PASSWORD`.
   - Set them in your terminal:
     - **Windows (PowerShell)**:
       ```powershell
       $env:DB_USERNAME = "postgres"
       $env:DB_PASSWORD = "your_password"
       ```
     - **Linux/macOS (Bash)**:
       ```bash
       export DB_USERNAME=postgres
       export DB_PASSWORD=your_password
       ```
     - Replace `your_password` with your PostgreSQL password from the installation.  
     
   - Verify that they’re set:
     - **Windows (PowerShell)**:
       ```powershell
       echo $env:DB_USERNAME
       echo $env:DB_PASSWORD
       ```
       You should see `postgres` and `your_password`.
     - **Linux/macOS (Bash)**:
       ```bash
       echo $DB_USERNAME
       echo $DB_PASSWORD
       ```
       You should see `postgres` and `your_password`.
   - To make these permanent (so you don’t set them every time):
     - **Windows**: Search “Environment Variables” in the Start menu, click “Edit the system environment variables,” add `DB_USERNAME` and `DB_PASSWORD` under “System variables,” and restart your terminal.
     - **Linux/macOS**: Add the `export` lines to `~/.bashrc` or `~/.zshrc`, then run `source ~/.bashrc`.

3. **Build the Project**:
   - In the project folder (`mail-service-api`), run:
     ```bash
     mvn clean install
     ```
     - `clean` removes old build files, and `install` downloads dependencies (like Spring Boot) and builds the project.
     - Wait for `[INFO] BUILD SUCCESS`—this might take a minute the first time.

4. **Run the Application**:
   - Start the app:
     ```bash
     mvn spring-boot:run
     ```
     - If environment variables are set, this works directly.
     - If not, use:
       ```bash
       mvn spring-boot:run "-Dspring-boot.run.arguments=--DB_USERNAME=postgres --DB_PASSWORD=your_password"
       ```
     - You’ll see logs ending with `Started MailServiceApiApplication in X.XXX seconds`. The app is now running at `http://localhost:8080`.

5. **Test the API**:
   - Open a browser and go to `http://localhost:8080/swagger-ui.html` to see the API documentation.
   - Or use tools like cURL or Postman (see [Testing the API](#testing-the-api)).

### Setup via IntelliJ IDEA

1. **Install IntelliJ IDEA**:
   - Go to [jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/), download the Community Edition (free), and install it by following the installer prompts.

2. **Clone and Open the Project**:
   - Launch IntelliJ IDEA.
   - From the welcome screen, click “Get from VCS” (Version Control System).
   - In the “URL” field, paste: `https://github.com/Lidizz/mail-service-api.git`.
   - Choose a directory (e.g., `C:\projects`), click “Clone,” and wait for it to download.
   - IntelliJ will ask to “Open or Import” the project—click “Import” and select “Maven” when prompted. Click “OK” to let it load the `pom.xml`.

3. **Configure Database Credentials**:
   - In IntelliJ, go to the top menu: `Run > Edit Configurations`.
   - Click the `+` button in the top-left corner, then select “Spring Boot” from the list.
   - Fill in:
     - **Name**: `MailServiceApi` (this names your run configuration).
     - **Main class**: Type or browse to `com.lidizz.mailserviceapi.MailServiceApiApplication`.
   - Click “Modify options” (alt + M), then check “Environment variables” or “Program arguments” (both work; arguments match CLI style, variables are production-friendly).
   - If you go with “Environment variables”, in the field, enter:
     ```plaintext
     DB_USERNAME=postgres;DB_PASSWORD=your_password
     ```
   - If you go with “Program arguments”, in the field, enter:
     ```plaintext
     --DB_USERNAME=postgres --DB_PASSWORD=your_password
     ```
     Replace `your_password` with your PostgreSQL password from the setup.
   - Click `Apply`, then `OK`.

4. **Build and Run**:
   - On the right side, open the “Maven” tab (“M” icon).
   - Expand `mail-service-api > Lifecycle`, double-click `clean`, then `install`. Wait for “BUILD SUCCESS” in the console.
   - Click the green “Run” triangle next to `MailServiceApi` in the top toolbar.
   - The app starts, and you’ll see `Started MailServiceApiApplication` in the Run window. It’s now at `http://localhost:8080`.

5. **Test the API**:
   - Open a browser to `http://localhost:8080/swagger-ui.html` to explore the API.
   - Or use Postman/cURL (see [Testing the API](#testing-the-api)).

### Setup via Eclipse

1. **Install Eclipse**:
   - Download Eclipse IDE for Java Developers from [eclipse.org/downloads/](https://www.eclipse.org/downloads/), then install it by running the installer and following the prompts.

2. **Clone and Import the Project**:
   - Open Eclipse.
   - Go to `File > Import > Git > Projects from Git`, then click “Next”.
   - Choose “Clone URI” > “Next”.
   - In “URI”, paste: `https://github.com/Lidizz/mail-service-api.git`.
   - Click “Next” through authentication (leave blank if public), choose a directory (e.g., `C:\projects`), and finish cloning.
   - Select “Import as General Project” > “Finish”.
   - Right-click the project in the Project Explorer > “Configure” > “Convert to Maven Project”. Eclipse will load the `pom.xml`.

3. **Configure Database Credentials**:
   - Right-click the project > `Run As > Run Configurations`.
   - In the left panel, select “Java Application”, then click the “New Configuration” icon (top-left).
   - Set:
     - **Name**: `MailServiceApi`.
     - **Main class**: Click “Search”, find `com.lidizz.mailserviceapi.MailServiceApiApplication`, and select it.
   - Go to the “Arguments” tab, in “VM Arguments”, add:
     ```
     -DDB_USERNAME=postgres -DDB_PASSWORD=your_password
     ```
     Replace `your_password` with your PostgreSQL password from the setup.
   - Click `Apply`.

4. **Build and Run**:
   - Right-click the project > `Maven > Update Project`, check “Force Update of Snapshots/Releases`, and click “OK”.
   - Right-click `pom.xml` > `Run As > Maven Build`.
   - In “Goals”, type `clean install`, then click “Run”. Wait for “BUILD SUCCESS”.
   - Right-click the project > `Run As > Java Application`, select `MailServiceApiApplication`, and click “OK”.
   - The app runs at `http://localhost:8080`.

5. **Test the API**:
   - Visit `http://localhost:8080/swagger-ui.html` in a browser.
   - Or test with Postman/cURL (see [Testing the API](#testing-the-api)).

## API Endpoints

### User Controller
- `POST /api/users`: Create a new user.
- `GET /api/users`: Retrieve all users.
- `GET /api/users/username/{username}`: Retrieve a user by username.
- `GET /api/users/id/{id}`: Retrieve a user by ID.
- `GET /api/users/email/{email}`: Retrieve a user by email.
- `PUT /api/users/{id}`: Update a user by ID.
- `DELETE /api/users/{id}`: Delete a user by ID.

### Email Record Controller
- `POST /api/emails`: Send a new email.
- `GET /api/emails`: Retrieve all emails.
- `GET /api/emails/sender/{senderId}`: Retrieve emails sent by a user.
- `GET /api/emails/recipient/{recipientId}`: Retrieve emails received by a user.
- `GET /api/emails/{id}`: Retrieve an email by ID.
- `DELETE /api/emails/{id}`: Delete an email by ID.

---

## Testing the API

This section shows how to test all endpoints using CLI (cURL), Postman, and Swagger UI. Follow the workflow: create users, send emails, retrieve data, update, and delete. For CLI testing on Windows, you can use PowerShell’s native `Invoke-WebRequest` or `curl.exe` (if installed separately); on Linux/macOS, use standard `curl`. Note: Create users first, as email endpoints require existing user IDs. Each test method below uses unique users to demonstrate different data across tools and avoid conflicts.

*NOTE*: I have added mock data to the project so you can start testing immediately (See `util.MockData` for details—e.g., includes users like ‘testuser’ and emails like ‘Test Email’).

---

### Testing via CLI with cURL

This section demonstrates testing all endpoints from a command-line interface (CLI). Choose your tool based on your system:
- **PowerShell (`Invoke-WebRequest`)**: Built into Windows, no extra install needed. Use `.Content` to see raw JSON (e.g., `(Invoke-WebRequest ...).Content`).
- **Windows (`curl.exe`)**: Requires `curl` installed (e.g., via Git Bash or `choco install curl`). Check with `Get-Command curl.exe`.
- **Linux/macOS (`curl`)**: Pre-installed on most systems.

1. **Setup**:
   - Ensure the app is running (`mvn spring-boot:run` from the project directory, e.g., `C:\projects\mail-service-api`).
   - Open a terminal:
     - **Windows**: PowerShell (type `powershell` in Start menu).
     - **Linux/macOS**: Terminal.

2. **Testing with PowerShell (`Invoke-WebRequest`)**:
   - **Create a User (POST /api/users)**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method POST -Headers @{"Content-Type" = "application/json"} -Body '{"username":"alice","email":"alice@example.com","password":"pass123"}'
     ```
     - Response: `{"id":1,...}` (note the `id`, e.g., `1`).
   - **Create Another User**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method POST -Headers @{"Content-Type" = "application/json"} -Body '{"username":"bob","email":"bob@example.com","password":"pass456"}'
     ```
     - Response: `{"id":2,...}` (note `id`, e.g., `2`).
   - **Get All Users (GET /api/users)**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method GET).Content
     ```
     - Response: `[{"id":1,"username":"alice",...},{"id":2,"username":"bob",...}]`.
   - **Get User by Username (GET /api/users/username/{username})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users/username/alice" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/users/username/alice" -Method GET).Content
     ```
     - Response: `{"id":1,"username":"alice",...}`.
   - **Get User by ID (GET /api/users/id/{id})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users/id/1" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/users/id/1" -Method GET).Content
     ```
     - Response: `{"id":1,"username":"alice",...}`.
   - **Get User by Email (GET /api/users/email/{email})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users/email/alice@example.com" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/users/email/alice@example.com" -Method GET).Content
     ```
     - Response: `{"id":1,"username":"alice",...}`.
   - **Update User (PUT /api/users/{id})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users/1" -Method PUT -Headers @{"Content-Type" = "application/json"} -Body '{"username":"alice_new","email":"alice.new@example.com","password":"newpass789"}'
     ```
     - Response: `{"id":1,"username":"alice_new",...}`.
   - **Delete User (DELETE /api/users/{id})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/users/2" -Method DELETE
     ```
     - Response: No content (204 status).
   - **Send an Email (POST /api/emails)**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails" -Method POST -Headers @{"Content-Type" = "application/json"} -Body '{"subject":"Meeting","senderId":1,"recipientId":2,"body":"Hi Bob, meeting at 10am?"}'
     ```
     - Response: `{"id":1,...}` (note `id`, e.g., `1`).
   - **Get All Emails (GET /api/emails)**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/emails" -Method GET).Content
     ```
     - Response: `[{"id":1,"subject":"Meeting",...}]`.
   - **Get Emails by Sender (GET /api/emails/sender/{senderId})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails/sender/1" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/emails/sender/1" -Method GET).Content
     ```
     - Response: `[{"id":1,"subject":"Meeting",...}]`.
   - **Get Emails by Recipient (GET /api/emails/recipient/{recipientId})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails/recipient/2" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/emails/recipient/2" -Method GET).Content
     ```
     - Response: `[{"id":1,"subject":"Meeting",...}]`.
   - **Get Email by ID (GET /api/emails/{id})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails/1" -Method GET
     ```
     - Example of using the .Content:
     ```powershell
     (Invoke-WebRequest -Uri "http://localhost:8080/api/emails/1" -Method GET).Content
     ```
     - Response: `{"id":1,"subject":"Meeting",...}`.
   - **Delete Email (DELETE /api/emails/{id})**:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/emails/1" -Method DELETE
     ```
     - Response: No content (204 status).

   **Command Notes**: Uses `-Uri` (URL), `-Method` (HTTP method), `-Headers` (hashtable), `-Body` (JSON payload, less escaping needed). Use `.Content` to see raw JSON (e.g., `(Invoke-WebRequest ...).Content`).  


3. **Testing with Windows (`curl.exe`)**:
   - **Note**: These examples use the Windows Command Prompt (CMD) for `curl.exe` commands due to PowerShell’s parsing limitations with JSON data. Open CMD by typing `cmd` in the Start menu.

   - **Create a User (POST /api/users)**:
     ```cmd
     curl.exe -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" --data-raw "{\"username\":\"charlie\",\"email\":\"charlie@example.com\",\"password\":\"pass789\"}"
     ```
     - Response: `{"id":3,...}` (note the `id`, e.g., `3`).
   - **Create Another User**:
     ```cmd
     curl.exe -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" --data-raw "{\"username\":\"dave\",\"email\":\"dave@example.com\",\"password\":\"pass101\"}"
     ```
     - Response: `{"id":4,...}` (note `id`, e.g., `4`).
   - **Get All Users (GET /api/users)**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/users"
     ```
     - Response: `[{"id":1,...},{"id":2,...},{"id":3,"username":"charlie",...},{"id":4,"username":"dave",...}]`.
   - **Get User by Username (GET /api/users/username/{username})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/users/username/charlie"
     ```
     - Response: `{"id":3,"username":"charlie",...}`.
   - **Get User by ID (GET /api/users/id/{id})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/users/id/3"
     ```
     - Response: `{"id":3,"username":"charlie",...}`.
   - **Get User by Email (GET /api/users/email/{email})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/users/email/charlie@example.com"
     ```
     - Response: `{"id":3,"username":"charlie",...}`.
   - **Update User (PUT /api/users/{id})**:
     ```cmd
     curl.exe -X PUT "http://localhost:8080/api/users/3" -H "Content-Type: application/json" --data-raw "{\"username\":\"charlie_new\",\"email\":\"charlie.new@example.com\",\"password\":\"newpass111\"}"
     ```
     - Response: `{"id":3,"username":"charlie_new",...}`.
   - **Delete User (DELETE /api/users/{id})**:
     ```cmd
     curl.exe -X DELETE "http://localhost:8080/api/users/4"
     ```
     - Response: No content (204 status).
   - **Send an Email (POST /api/emails)**:
     ```cmd
     curl.exe -X POST "http://localhost:8080/api/emails" -H "Content-Type: application/json" --data-raw "{\"subject\":\"Lunch\",\"senderId\":3,\"recipientId\":4,\"body\":\"Hi Dave, lunch at noon?\"}"
     ```
     - Response: `{"id":2,...}` (note `id`, e.g., `2`).
   - **Get All Emails (GET /api/emails)**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/emails"
     ```
     - Response: `[{"id":1,...},{"id":2,"subject":"Lunch",...}]`.
   - **Get Emails by Sender (GET /api/emails/sender/{senderId})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/emails/sender/3"
     ```
     - Response: `[{"id":2,"subject":"Lunch",...}]`.
   - **Get Emails by Recipient (GET /api/emails/recipient/{recipientId})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/emails/recipient/4"
     ```
     - Response: `[{"id":2,"subject":"Lunch",...}]`.
   - **Get Email by ID (GET /api/emails/{id})**:
     ```cmd
     curl.exe -X GET "http://localhost:8080/api/emails/2"
     ```
     - Response: `{"id":2,"subject":"Lunch",...}`.
   - **Delete Email (DELETE /api/emails/{id})**:
     ```cmd
     curl.exe -X DELETE "http://localhost:8080/api/emails/2"
     ```
     - Response: No content (204 status).

   **Command Notes**: Uses `-X` (method), `-H` (header), `--data-raw` (data). In CMD, `--data-raw` with double quotes (e.g., `"{\"key\":\"value\"}"`) ensures JSON is sent correctly. Use `-d` as an alternative if preferred, though `--data-raw` is recommended for reliability. Add `-s` to suppress progress output if desired. Ensure `senderId` and `recipientId` exist in the database before testing.  


4. **Testing with Linux/macOS (`curl`)**:
   - **Create a User (POST /api/users)**:
     ```bash
     curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d "{\"username\":\"emma\",\"email\":\"emma@example.com\",\"password\":\"pass222\"}"
     ```
     - Response: `{"id":5,...}` (note the `id`, e.g., `5`).
   - **Create Another User**:
     ```bash
     curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d "{\"username\":\"frank\",\"email\":\"frank@example.com\",\"password\":\"pass333\"}"
     ```
     - Response: `{"id":6,...}` (note `id`, e.g., `6`).
   - **Get All Users (GET /api/users)**:
     ```bash
     curl -X GET "http://localhost:8080/api/users"
     ```
     - Response: `[{"id":1,...},...,{"id":5,"username":"emma",...},{"id":6,"username":"frank",...}]`.
   - **Get User by Username (GET /api/users/username/{username})**:
     ```bash
     curl -X GET "http://localhost:8080/api/users/username/emma"
     ```
     - Response: `{"id":5,"username":"emma",...}`.
   - **Get User by ID (GET /api/users/id/{id})**:
     ```bash
     curl -X GET "http://localhost:8080/api/users/id/5"
     ```
     - Response: `{"id":5,"username":"emma",...}`.
   - **Get User by Email (GET /api/users/email/{email})**:
     ```bash
     curl -X GET "http://localhost:8080/api/users/email/emma@example.com"
     ```
     - Response: `{"id":5,"username":"emma",...}`.
   - **Update User (PUT /api/users/{id})**:
     ```bash
     curl -X PUT "http://localhost:8080/api/users/5" -H "Content-Type: application/json" -d "{\"username\":\"emma_new\",\"email\":\"emma.new@example.com\",\"password\":\"newpass444\"}"
     ```
     - Response: `{"id":5,"username":"emma_new",...}`.
   - **Delete User (DELETE /api/users/{id})**:
     ```bash
     curl -X DELETE "http://localhost:8080/api/users/6"
     ```
     - Response: No content (204 status).
   - **Send an Email (POST /api/emails)**:
     ```bash
     curl -X POST "http://localhost:8080/api/emails" -H "Content-Type: application/json" -d "{\"subject\":\"Coffee\",\"senderId\":5,\"recipientId\":6,\"body\":\"Hi Frank, coffee at 3pm?\"}"
     ```
     - Response: `{"id":3,...}` (note `id`, e.g., `3`).
   - **Get All Emails (GET /api/emails)**:
     ```bash
     curl -X GET "http://localhost:8080/api/emails"
     ```
     - Response: `[{"id":1,...},...,{"id":3,"subject":"Coffee",...}]`.
   - **Get Emails by Sender (GET /api/emails/sender/{senderId})**:
     ```bash
     curl -X GET "http://localhost:8080/api/emails/sender/5"
     ```
     - Response: `[{"id":3,"subject":"Coffee",...}]`.
   - **Get Emails by Recipient (GET /api/emails/recipient/{recipientId})**:
     ```bash
     curl -X GET "http://localhost:8080/api/emails/recipient/6"
     ```
     - Response: `[{"id":3,"subject":"Coffee",...}]`.
   - **Get Email by ID (GET /api/emails/{id})**:
     ```bash
     curl -X GET "http://localhost:8080/api/emails/3"
     ```
     - Response: `{"id":3,"subject":"Coffee",...}`.
   - **Delete Email (DELETE /api/emails/{id})**:
     ```bash
     curl -X DELETE "http://localhost:8080/api/emails/3"
     ```
     - Response: No content (204 status).

   **Command Notes**: Uses `-X` (method), `-H` (header), `-d` (data, more escaping for quotes). Add `-s` to suppress progress output if desired.

### Testing via Postman

1. **Setup**:
   - Download Postman from [postman.com/downloads/](https://www.postman.com/downloads/) and install it.
   - Open Postman, click “New” > “Collection”, name it “Mail Service API”, and save.

2. **User Controller Tests**:
   - **Create a User (POST /api/users)**:
     - New > Request > Name: “Create User” > Save to collection.
     - Method: `POST`, URL: `http://localhost:8080/api/users`.
     - Headers: `Content-Type: application/json`.
     - Body > raw > JSON:
       ```json
       {
         "username": "grace",
         "email": "grace@example.com",
         "password": "pass555"
       }
       ```
     - Click “Send”. Response: `201 Created`, `{"id":7,...}` (note `id`, e.g., `7`).
   - **Create Another User**: Repeat with:
       ```json
       {
         "username": "hank",
         "email": "hank@example.com",
         "password": "pass666"
       }
       ```
     - Response: `{"id":8,...}` (note `id`, e.g., `8`).
   - **Get All Users (GET /api/users)**:
     - New Request > Name: “Get All Users”.
     - Method: `GET`, URL: `http://localhost:8080/api/users`.
     - Send. Response: `200 OK`, array of users.
   - **Get User by Username (GET /api/users/username/{username})**:
     - Method: `GET`, URL: `http://localhost:8080/api/users/username/grace`.
     - Send. Response: `200 OK`, user details.
   - **Get User by ID (GET /api/users/id/{id})**:
     - Method: `GET`, URL: `http://localhost:8080/api/users/id/7`.
     - Send. Response: `200 OK`, user details.
   - **Get User by Email (GET /api/users/email/{email})**:
     - Method: `GET`, URL: `http://localhost:8080/api/users/email/grace@example.com`.
     - Send. Response: `200 OK`, user details.
   - **Update User (PUT /api/users/{id})**:
     - Method: `PUT`, URL: `http://localhost:8080/api/users/7`.
     - Headers: `Content-Type: application/json`.
     - Body:
       ```json
       {
         "username": "grace_new",
         "email": "grace.new@example.com",
         "password": "newpass777"
       }
       ```
     - Send. Response: `200 OK`, updated user.
   - **Delete User (DELETE /api/users/{id})**:
     - Method: `DELETE`, URL: `http://localhost:8080/api/users/8`.
     - Send. Response: `204 No Content`.

3. **Email Record Controller Tests**:
   - **Send an Email (POST /api/emails)**:
     - Method: `POST`, URL: `http://localhost:8080/api/emails`.
     - Headers: `Content-Type: application/json`.
     - Body:
       ```json
       {
         "subject": "Dinner",
         "senderId": 7,
         "recipientId": 8,
         "body": "Hi Hank, dinner at 7pm?"
       }
       ```
     - Send. Response: `201 Created`, `{"id":4,...}`.
   - **Get All Emails (GET /api/emails)**:
     - Method: `GET`, URL: `http://localhost:8080/api/emails`.
     - Send. Response: `200 OK`, email array.
   - **Get Emails by Sender (GET /api/emails/sender/{senderId})**:
     - Method: `GET`, URL: `http://localhost:8080/api/emails/sender/7`.
     - Send. Response: `200 OK`, emails sent by user 7.
   - **Get Emails by Recipient (GET /api/emails/recipient/{recipientId})**:
     - Method: `GET`, URL: `http://localhost:8080/api/emails/recipient/8`.
     - Send. Response: `200 OK`, emails received by user 8.
   - **Get Email by ID (GET /api/emails/{id})**:
     - Method: `GET`, URL: `http://localhost:8080/api/emails/4`.
     - Send. Response: `200 OK`, email details.
   - **Delete Email (DELETE /api/emails/{id})**:
     - Method: `DELETE`, URL: `http://localhost:8080/api/emails/4`.
     - Send. Response: `204 No Content`.

### Testing via Swagger UI

1. **Setup**:
   - Run the app, then open `http://localhost:8080/swagger-ui.html` in a browser.

2. **User Controller Tests**:
   - **Create a User (POST /api/users)**:
     - Expand `POST /api/users`, click “Try it out”.
     - Edit the request body:
       ```json
       {
         "username": "iris",
         "email": "iris@example.com",
         "password": "pass888"
       }
       ```
     - Click “Execute”. Response: `201`, `{"id":9,...}` (note `id`, e.g., `9`).
   - **Create Another User**: Repeat with:
       ```json
       {
         "username": "jack",
         "email": "jack@example.com",
         "password": "pass999"
       }
       ```
     - Response: `{"id":10,...}` (note `id`, e.g., `10`).
   - **Get All Users (GET /api/users)**:
     - Expand `GET /api/users`, click “Try it out”, then “Execute”.
     - Response: `200`, user list.
   - **Get User by Username (GET /api/users/username/{username})**:
     - Expand, enter `iris` in `username`, execute. Response: `200`, user details.
   - **Get User by ID (GET /api/users/id/{id})**:
     - Enter `9` in `id`, execute. Response: `200`, user details.
   - **Get User by Email (GET /api/users/email/{email})**:
     - Enter `iris@example.com` in `email`, execute. Response: `200`, user details.
   - **Update User (PUT /api/users/{id})**:
     - Enter `9` in `id`, edit body:
       ```json
       {
         "username": "iris_new",
         "email": "iris.new@example.com",
         "password": "newpass000"
       }
       ```
     - Execute. Response: `200`, updated user.
   - **Delete User (DELETE /api/users/{id})**:
     - Enter `10` in `id`, execute. Response: `204`.

3. **Email Record Controller Tests**:
   - **Send an Email (POST /api/emails)**:
     - Expand `POST /api/emails`, edit body:
       ```json
       {
         "subject": "Movie",
         "senderId": 9,
         "recipientId": 10,
         "body": "Hi Jack, movie at 8pm?"
       }
       ```
     - Execute. Response: `201`, `{"id":5,...}`.
   - **Get All Emails (GET /api/emails)**:
     - Execute. Response: `200`, email list.
   - **Get Emails by Sender (GET /api/emails/sender/{senderId})**:
     - Enter `9` in `senderId`, execute. Response: `200`, sender’s emails.
   - **Get Emails by Recipient (GET /api/emails/recipient/{recipientId})**:
     - Enter `10` in `recipientId`, execute. Response: `200`, recipient’s emails.
   - **Get Email by ID (GET /api/emails/{id})**:
     - Enter `5` in `id`, execute. Response: `200`, email details.
   - **Delete Email (DELETE /api/emails/{id})**:
     - Enter `5` in `id`, execute. Response: `204`.

## Additional Notes

- The database schema is recreated each run (`spring.jpa.hibernate.ddl-auto=create-drop`). For persistent data, change to `update` or `validate` in `application.properties`.
- Mock data in `util.MockData` populates the database on startup for testing.

## Troubleshooting

- **Database Connection Issues**: Verify PostgreSQL is running (port `5432`) and credentials match (`postgres`, your password).
- **Port Conflicts**: If `8080` is busy, edit `application.properties` to `server.port=8081`.
- **JSON Errors**: Check JSON syntax in requests; ensure `Content-Type: application/json`.
- **400 Bad Request**: Validation failed—check required fields, email format, etc.
- **500 Errors**: See server logs (console output) for details.
- **Not Found Errors**: Ensure users/emails exist before using their IDs.
- **Seeing PowerShell Responses**: `Invoke-WebRequest` returns an object; use `.Content` to see raw JSON (e.g., `(Invoke-WebRequest -Uri "http://localhost:8080/api/users" -Method GET).Content`).

## License

[MIT License](LICENSE)