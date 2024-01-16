<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Page</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container-wrapper {
                display: flex;
                justify-content: space-between;
                width: 90%;
                height: 75%
            }

            .container {
                width: 45%;
                margin: 20px 0;
                padding: 20px;
                background-color: #ffffff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
                transition: transform 0.3s ease-in-out;
            }

            .container:hover {
                transform: scale(1.05);
            }

            form {
                text-align: left;
            }

            h2 {
                color: #333;
            }

            label {
                display: block;
                margin: 10px 0;
                color: #555;
                text-align: left;
            }

            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            #otpCode {
                width: 75%;
                padding: 10px;
                margin-bottom: 15px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-right: 10px;
            }

            button {
                background-color: #4caf50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            button:hover {
                background-color: #45a049;
            }

            .error-message {
                color: red;
                margin-bottom: 10px;
            }

            #toggleLink {
                color: #007bff;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    </head>
    <body>
        <div class="container-wrapper">
            <div class="container">
                <form id="changePasswordForm" action="change_password?action=addNewPassword" method="post">
                    <h2>Get New Password</h2>
                    <div class="error-message">
                        <c:if test="${not empty requestScope.errorMessage}">
                            ${requestScope.errorMessage}
                        </c:if>
                    </div>

                    <label for="contactInfo">Enter Email:</label>
                    <input type="email" id="contactInfo" name="contactInfo" >

                    <label for="otpCode" >Enter OTP:</label>
                    <input type="text" id="otpCode" name="otpCode" >
                    <button style="background-color: red;" type="button" onclick="sendOTP()">Send OTP Code</button>

                    <label for="newPassword">Enter New Password:</label>
                    <input type="password" id="newPassword" name="newPassword" >

                    <a href="#" id="toggleLink" onclick="toggleContactType()" style="display: block; padding-bottom: 10px;">Get new password by phone number</a>

                    <button type="submit">Submit</button>
                </form>
            </div>

            <div class="container">
                <!--Code change password here-->
            </div>
        </div>  

        <script>
            function sendOTP() {
                var contactInfo = document.getElementById("contactInfo").value.trim();

                if (contactInfo === "") {
                    alert("Please enter an email first.");
                    return;
                }

                alert("Sending OTP to " + contactInfo);

                $.ajax({
                    type: "POST",
                    url: "change_password?action=sendOTP",
                    data: $("#changePasswordForm").serialize(),
                    success: function (response) {
                        console.log(response);
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
            
            function toggleContactType() {
                var contactLabel = document.querySelector('label[for="contactInfo"]');
                var contactInfo = document.getElementById("contactInfo");
                var toggleLink = document.getElementById("toggleLink");

                if (contactLabel.innerText === "Enter Email:") {
                    contactLabel.innerText = "Enter Phone Number:";
                    contactInfo.type = "tel";
                    toggleLink.innerText = "Get new password by email";
                } else {
                    contactLabel.innerText = "Enter Email:";
                    contactInfo.type = "email";
                    toggleLink.innerText = "Get new password by phone number";
                }
            }
        </script>
    </body>
</html>
