function sendOTP() {
    var contactInfo = document.getElementById("contactInfo").value.trim();

    if (contactInfo === "") {
        alert("Please enter an email first.");
        return;
    }

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

