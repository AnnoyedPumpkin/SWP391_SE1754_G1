<%-- 
    Document   : change_password
    Created on : Jan 15, 2024, 10:18:06 PM
    Author     : FPT-LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </head>
    <body>
    <body>
        <div class="container-wrapper">
            <div class="container">
                <form action="change_password" method="post">
                    <h2>Get New Password</h2>
                    <div class="error-message">
                        <c:if test="${not empty requestScope.errorMessage}">
                            ${requestScope.errorMessage}
                        </c:if>
                    </div>

                    <label for="contactInfo">Enter Email:</label>
                    <input type="text" id="contactInfo" name="contactInfo" >

                    <label for="otpCode" >Enter OTP:</label>
                    <input type="text" id="otpCode" name="otpCode" >
                    <button style="background-color: red;">Send OTP Code</button>

                    <label for="newPassword">Enter New Password:</label>
                    <input type="password" id="newPassword" name="newPassword" >

                    <a href="#" id="toggleLink" onclick="toggleContactType()" style="display: block; padding-bottom: 10px;">Get new password by phone number</a>

                    <button type="submit">Submit</button>
                </form>
            </div>

            <div class="container">

            </div>
        </div>  

        <script>
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
                    contactInfo.type = "text";
                    toggleLink.innerText = "Get new password by phone number";
                }
            }
        </script>
    </body>
</html>
