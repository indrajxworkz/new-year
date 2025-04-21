<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Header styling */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #007BFF;
            padding: 10px 20px;
            color: white;
        }

        .header img {
            height: 40px;
            border-radius: 50%;
        }

        .header .greeting {
            font-size: 1.2rem;
            font-weight: bold;
        }

        .header .nav-links {
            display: flex;
            gap: 15px;
        }

        .header .nav-links a {
            color: white;
            text-decoration: none;
            padding: 5px 10px;
            border: 1px solid white;
            border-radius: 5px;
        }

        .header .nav-links a:hover {
            background-color: white;
            color: #007BFF;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #ffffff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input, select, button {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        input:focus, select:focus {
            border-color: #007BFF;
            outline: none;
        }

        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Cancel button styles */
        .btn-cancel {
            width: 100px;
            padding: 10px;
            font-size: 0.9rem;
            color: white;
            background-color: #dc3545;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 20px auto;
            text-align: center;
        }

        .btn-cancel:hover {
            background-color: #c82333;
        }

        .cancel-link {
            text-decoration: none;
            color: white;
        }

        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #888;
            padding: 10px 0;
            background-color: #333;
            color: white;
        }
        .profile-photo {
                    width: 40px;
                    height: 40px;
                    border-radius: 50%;
                    object-fit: cover;
                    margin-right: 10px;
                    border: 2px solid white;
                }
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <div class="profile">
           <img src="view/${detailsUpdate.getFileName()}" alt="Profile Photo" class="profile-photo">
            <span class="greeting">Welcome, ${detailsUpdate.getFirstName()}</span>
        </div>
        <div class="nav-links">
            <a href="dashboard?email=${detailsUpdate.getEmail()}">Dashboard</a>

            <a href="index">Logout</a>
        </div>
    </div>

    <!-- Main Content -->
    <div class="container">
        <h1>Edit Profile Details</h1>

        <form action="update" method="post" enctype="multipart/form-data">
            <label for="email">Email</label>
            <input type="email" name="email" value="${detailsUpdate.getEmail()}" readonly>

            <label for="id">ID</label>
            <input type="number" name="id" value="${detailsUpdate.getId()}" readonly>

            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" value="${detailsUpdate.getFirstName()}">

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" value="${detailsUpdate.getLastName()}">

            <label for="userName">Username</label>
            <input type="text" id="userName" name="userName" value="${detailsUpdate.getUserName()}">

            <label for="age">Age</label>
            <input type="number" id="age" name="age" value="${detailsUpdate.getAge()}">

            <label for="phone">Phone Number</label>
            <input type="number" id="phone" name="phone" value="${detailsUpdate.getPhone()}">

            <label for="createdBy">Created By</label>
            <input type="text" id="createdBy" name="createdBy" value="${detailsUpdate.getCreatedBy()}" required>

            <label for="updatedBy">Updated By</label>
            <input type="text" id="updatedBy" name="updatedBy" value="${detailsUpdate.getUpdatedBy()}">

            <label for="pwd">Password</label>
            <input type="password" id="pwd" hidden name="pwd" value="${detailsUpdate.getPwd()}">

            <label for="confirmPwd">Confirm Password</label>
            <input type="password" id="confirmPwd" hidden  name="confirmPwd" value="${detailsUpdate.getConfirmPwd()}">

            <label for="file">Upload Document:</label>
            <input type="file" id="file" name="file" required>

            <button type="submit" value="submit">Save Changes</button>
        </form>

        <a href="dashboard" class="btn-cancel cancel-link">Cancel</a>
    </div>

    <!-- Footer -->
    <div class="footer">
        © 2025 Happy New Year. All Rights Reserved.
    </div>
</body>
</html>
