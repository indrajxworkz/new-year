<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
        .signin-container {
            background: white;
            border-radius: 10px;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.3);
            text-align: center;
        }
        .signin-container h2 {
            margin-bottom: 20px;
        }
        .btn-custom {
            background-color: #ff4500;
            color: white;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #e63900;
        }
        .links {
            margin-top: 10px;
        }
        .links a {
            color: #ff4500;
        }
        .toggle-password {
            cursor: pointer;
            position: absolute;
            right: 15px;
            top: 72%;
            transform: translateY(-50%);
            font-size: 18px;
            color: #ff4500;
        }
    </style>
</head>
<body>
    <div class="signin-container">
        <h2>Sign In</h2>
        <form action="signin" method="post">

            <span >${valid}</span>
            <span>${validation}</span>
            <div class="mb-3 position-relative">
                   <label for="email" class="form-label">Email Address</label>
                <input type="email" id="email" name="email" value="${found}" class="form-control" required>
            </div>
            <div class="mb-3 position-relative">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="pwd" name="pwd" class="form-control" required>
                <i class="fa-solid fa-eye toggle-password" onclick="togglePassword()"></i>
            </div>
            <button type="submit" class="btn btn-custom">Sign In</button>
        </form>
        <div class="links">
            <div class="forgot-password">
                <p><a href="forgot-password">Forgot Password?</a></p>
            </div>
            Don't have an account? <a href="signUp">Sign Up</a><br>
            Go back to home <a href="index">HOME</a>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordField = document.getElementById("pwd");
            const toggleIcon = document.querySelector(".toggle-password");

            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }
    </script>
</body>
</html>
