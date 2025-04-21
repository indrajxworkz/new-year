<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to New Year</title>
     <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Seal_of_the_Reserve_Bank_of_India.svg/2048px-Seal_of_the_Reserve_Bank_of_India.svg.png" type="image/png"> <!-- Favicon -->

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', Comic Sans MS;
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column; 
            height: 100vh;
        }
        header {
            background-color: #343a40;
            padding: 10px 20px;
            color: white;
        }
        header .nav-link {
            color: white;
            font-weight: bold;
        }
        header .nav-link:hover {
            text-decoration: underline;
        }
        main {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(to bottom, #ffcccb, #f5f5f5);
        }
        .main-content {
            text-align: center;
            background: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }
        .btn-custom {
            background-color: #ff4500;
            color: white;
            font-size: 1.2rem;
            padding: 10px 20px;
            border-radius: 8px;
        }
        .btn-custom:hover {
            background-color: #e63900;
        }
        footer {
            background-color: #343a40;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        .time {
            font-weight: bold;
        }
         .logo {
                    height: 50px;
                    margin-right: 10px;
                }
    </style>
    <script>
        // Function to update the time in the footer
        function updateTime() {
            const now = new Date();
            const timeString = now.toLocaleTimeString();
            document.getElementById("current-time").innerText = timeString;
        }

        // Set interval to update the time every second
        setInterval(updateTime, 1000);
    </script>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="container-fluid d-flex justify-content-between align-items-center">
            <h1 class="h4 m-0">New Year's Event</h1>
                      <nav>
                <a href="index" class="nav-link d-inline-block me-3">Home</a>
                <a href="signIn" class="nav-link d-inline-block me-3">Sign In</a>
                <a href="signUp" class="nav-link d-inline-block">Sign Up</a>
            </nav>
        </div>
    </header>

    <!-- Main -->
    <main>
        <div class="main-content">
            <h2>Welcome to Our New Year's Celebration!</h2>
              <p>Join us to welcome the New Year with joy and festivities.</p>
            <a href="signUp" class="btn btn-custom">Register Now</a>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <p>Current Time: <span id="current-time" class="time"></span></p>
    </footer>
</body>
</html>
