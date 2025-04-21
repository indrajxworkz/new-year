<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <title>Reset Password</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #ff7e5f, #feb47b);
            font-family: 'Arial', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .reset-container {
            background: white;
            border-radius: 10px;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.3);
        }
        .reset-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .links {
            text-align: center;
            margin-top: 10px;
        }
        .links a {
            color: #007bff;
        }
          .logo {
                    height: 50px;
                    margin-right: 10px;
                }
    </style>
</head>
<body>
    <div class="reset-container">
        <h2>Reset Your Password</h2>
        <h1>${FoundEmail}</h1>
          <a href = "index" > <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Seal_of_the_Reserve_Bank_of_India.svg/2048px-Seal_of_the_Reserve_Bank_of_India.svg.png" alt="Logo" class="logo"></a>
        <form action = "resetPassword" method ="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" id="email" name="email" value = "${FoundEmail}"  class="form-control" placeholder="Enter your email" required>
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">New Password</label>
                <input type="password" id="pwd" name="pwd" class="form-control" placeholder="Enter new password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPwd" class="form-label">Confirm New Password</label>
                <input type="password" id="confirmPwd" name="confirmPwd" class="form-control" placeholder="Confirm new password" required>
            </div>
            <button type="submit" class="btn btn-custom">Update </button>
        </form>
        <div class="links">
            <p><a href="signIn">Back to Sign In</a></p>
        </div>
    </div>

      </body>
</html>
