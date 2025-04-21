<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Back - Dashboard</title>
    <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Seal_of_the_Reserve_Bank_of_India.svg/2048px-Seal_of_the_Reserve_Bank_of_India.svg.png" type="image/png">
    <style>
        /* General Styling */
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: 'Arial', sans-serif;
            color: white;
            background: linear-gradient(to bottom, #002f4b, #1a3c64, #125d88, #68a6d6);
        }

        header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
            background-color: rgba(0, 47, 75, 0.9);
            padding: 10px 20px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.5);
        }

        .logo {
            height: 50px;
        }

        .profile-section {
            display: flex;
            align-items: center;
        }

        .profile-photo {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 10px;
            border: 2px solid white;
        }

        .nav-links {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            font-size: 14px;
            padding: 5px 10px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .nav-links a:hover {
            background-color: #68a6d6;
        }

        .welcome-message {
            margin-top: 100px;
            z-index: 2;
            text-align: center;
            font-size: 3rem;
            font-weight: bold;
            animation: fadeIn 2s ease-in-out;
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
    <!-- Header with Profile Photo and Navigation -->
    <header>
        <!-- Logo -->
        <a href="index">
           <img src="view/${dto.getFileName()}" alt="Profile Photo" class="profile-photo" onerror="this.onerror=null; this.src='/new-year/URLToReachStaticFolder/image/profile-image.jpg';">
        </a>

        <!-- Navigation and Profile -->
        <div class="profile-section">
            <!-- Profile Photo -->
            <img src="view/${dto.getFileName()}" alt="Profile Photo" class="profile-photo" onerror="this.onerror=null; this.src='/new-year/URLToReachStaticFolder/image/default-image.jpg';">

            <!-- Navigation Links -->
            <div class="nav-links">
                <a href="fetch?email=${dto.getEmail()}">Update</a>
                <img src="view/${dto.getFileName()}" alt="Profile Photo" class="profile-photo" onerror="this.onerror=null; this.src='/new-year/URLToReachStaticFolder/photo/profile-image.png';">
                <a href="index">Logout</a>
            </div>
        </div>
    </header>

    <!-- Welcome Message -->
    <div class="welcome-message">Welcome Back, ${dto.getFirstName()}!</div>
</body>
</html>
