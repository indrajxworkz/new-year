<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        body {
            background: linear-gradient(135deg, #7f7fd5, #86a8e7, #91eae4);
            font-family: 'Arial', sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .signup-container {
            background: white;
            border-radius: 10px;
            padding: 30px;
            width: 100%;
            max-width: 500px;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.3);
        }

        .signup-container h2 {
            margin-bottom: 20px;
            font-size: 1.8rem;
        }
        .signup-container {
            transform: scale(0.8); /* Scale up the entire container by 20% */
        }


        .btn-custom {
            background-color: #007bff;
            color: white;
            width: 100%;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .forgot-password {
            margin-top: 10px;
        }

        .forgot-password a {
            color: #ff4500;
            text-decoration: none;
            font-weight: bold;
        }

        .forgot-password a:hover {
            text-decoration: underline;
        }

        .links {
            margin-top: 10px;
        }

        .links a {
            color: #0007bff;
        }

        /* Responsive Design */
        @media (max-width: 576px) {
            .signup-container {
                padding: 20px;
                max-width: 90%;
            }

            .signup-container h2 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>Create an Account</h2>
        <form action="signup" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">First Name</label>
                <input type="text"  id="firstName" name="firstName" class="form-control" required>



                <span>${firstNameError}</span>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Last Name</label>
                <input type="text" id="lastName" name="lastName" class="form-control" required>
                <span>${lastName}</span>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">User Name</label>
                <input type="text" id="userName" name="userName" class="form-control" required>
                <span>${userName}</span>
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Age</label>
                <input type="number" id="age" name="age" class="form-control" required>
            </div>
            <span id = "phoneError"></span>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone Number</label>
                <input type="tel" id="phone" onblur = "checkPhone()"  name="phone" class="form-control" required>
            </div>
            <span id  = "emailError" ></span>
            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" id="email" onblur = "checkEmail()"   name="email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Gender</label><br>
                <div>
                    <label class="radio-group me-3">
                        <input type="radio" id="MALE" name="Gender" value="MALE" > Male
                    </label>
                    <label class="radio-group">
                        <input type="radio" id="FEMALE" name="Gender" value="FEMALE"> Female
                    </label>
                </div>
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">Password</label>
                <input type="password" id="pwd" name="pwd" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="confirmPwd" class="form-label">Confirm Password</label>
                <input type="password" id="confirmPwd" name="confirmPwd" class="form-control" required>
            </div>
             <div class="mb-3">
                            <label for="createdBy" class="form-label">Created By</label>
                            <input type="text" id="createdBy" name="createdBy" class="form-control" required>
                        </div>
               <div class="mb-3">
                              <label for="updatedBy" class="form-label">Updated By </label>
                              <input type="text" id="updatedBy" name="updatedBy" class="form-control" required>
                          </div>
            <button type="submit" class="btn btn-custom">Sign Up</button>
        </form>

        <div class="links">
            <p>Already have an account? <a href="signIn">Sign In</a></p>
        </div>
    </div>

    <script>

    const checkEmail= async() =>{

    const email = document.getElementById("email").value
    console.log(email)
    const response = await axios("http://localhost:1903/new-year/api/checkEmail/"+email)
    console.log(response)
    if(response.data === "Email Exists"){

    document.getElementById("emailError").innerHTML=response.data

    }else{

     document.getElementById("emailError").innerHTML=""
    }
}



      const checkPhone= async() =>{

        const phone = document.getElementById("phone").value
        console.log(phone)
        const response = await axios("http://localhost:1903/new-year/api/checkPhone/"+phone)
        console.log(response)
        if(response.data === "Phone number Exists"){

        document.getElementById("phoneError").innerHTML=response.data

        }else  {
        document.getElementById("phoneError").innerHTML=""
        }

}
    </script>




</body>
</html>
