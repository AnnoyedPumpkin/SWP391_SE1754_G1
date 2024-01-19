function sendOTP() {
                var contactInfo = document.getElementById("contactInfo").value.trim();

                if (contactInfo === "") {
                    alert("Please enter an email first.");
                    return;
                }

                alert("Sending OTP to " + contactInfo);

                $.ajax({
                    type: "POST",
                    url: "RecoveryPassword?action=sendOTP",
                    data: $("#recoveryForm").serialize(),
                    success: function (response) {
                        console.log(response);
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }