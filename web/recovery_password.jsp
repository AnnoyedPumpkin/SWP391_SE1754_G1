<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .container {
                max-width: 30%;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="forgotPasswordServlet" method="post">
                <h2>Get new Password</h2>

                <label id="contactLabel" for="contactInfo">Enter Email:</label>
                <input type="text" id="contactInfo" name="contactInfo" required><br>

                <label for="newPassword">Enter New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required><br>

                <a href="#" id="toggleLink" onclick="toggleContactType()">Get new password by phone number</a><br>

                <button type="submit">Submit</button>
            </form>
        </div>

        <script>
            function toggleContactType() {
                var contactLabel = document.getElementById("contactLabel");
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
