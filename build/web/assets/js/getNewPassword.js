function getNewPassword() {
    var contactInfo = document.getElementById("contactInfo").value.trim();
    var otp = document.getElementById("otpCode").value.trim();
    
    if (contactInfo === "" || otp === "") {
        alert("Please fill the email and OTP Code.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "ForgotPassword?action=getNewPassword",
        data: $("#forgotPasswordForm").serialize(),
        success: function (response) {
            console.log(response);
        },
        error: function (error) {
            console.log(error);
        }
    });
}