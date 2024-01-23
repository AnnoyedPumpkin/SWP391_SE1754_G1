function sendOTP() {
                var contactInfo = document.getElementById("contactInfo").value.trim();

                if (contactInfo === "") {
                    alert("Please enter an email first.");
                    return;
                }

                alert("Sending OTP to " + contactInfo);

                $.ajax({
                    type: "POST",
                    url: "ForgotPassword?action=sendOTP",
                    data: $("#forgotPasswordForm").serialize(),
                    success: function (response) {
                        console.log(response);
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
            
function copyPassword() {
    var copyPass = document.getElementById("np");
    copyPass.type = "text";
    copyPass.select();
    document.execCommand("copy");
    copyPass.type = "password";
}