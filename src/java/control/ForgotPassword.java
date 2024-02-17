/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.CommonDao;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;

public class ForgotPassword extends HttpServlet {

    CommonDao commonDao = new CommonDao();
    private final String FROM_EMAIL = "clothingshoponlineg1se1754@gmail.com";
    private final String EMAIL_PASSWORD = "pizwgjrviipmttyx"; //pizwgjrviipmttyx //pvjvxphaxwavjvzp

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        switch (action) {
            case "verifyCode":
                verifyCode(request, response, email);
                break;
            case "sendCode":
                boolean accountExists = commonDao.checkAccountExistByEmail(email);
                if (accountExists) {
                    sendCode(request, response, email);
                    request.setAttribute("email", email);
                    request.setAttribute("message", "Your OTP code was sent to the email \"" + email + "\". Please check it, then enter your code here, and the code will expire after 5 minutes.");
                    request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
                    break;
                } else {
                    request.setAttribute("errorMessage", "This account not correct or not register yet.");
                    request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
                    break;
                }
            default:
                throw new AssertionError();
        }
    }

    private void verifyCode(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
        String digit1 = request.getParameter("digit1");
        String digit2 = request.getParameter("digit2");
        String digit3 = request.getParameter("digit3");
        String digit4 = request.getParameter("digit4");
        String digit5 = request.getParameter("digit5");
        String digit6 = request.getParameter("digit6");

        int accountID = commonDao.getAccountIdByEmail(email);
        String otpInput = digit1 + digit2 + digit3 + digit4 + digit5 + digit6;
        String otpCheck = commonDao.getOTPByEmail(email);

        if (otpCheck != null && otpInput.equals(otpCheck)) {
            String newPassword = commonDao.generateRandomPassword();
            commonDao.updatePasswordById(newPassword, accountID);
            //sendMsgEmail(email, "Your new password is: " + newPassword);
            request.setAttribute("email", email);
            request.setAttribute("successMes", "Your new password was sent to yout email, please check it.");
            request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMes", "Incorrect code, please check it again.");
            request.setAttribute("email", email);
            request.setAttribute("message", "Your OTP code was sent to the email \"" + email + "\". Please check it, then enter your code here, and the code will expire after 5 minutes.");
            request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
        }
    }

    private void sendCode(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException {
        String otp = commonDao.generateRandomOTP();
        commonDao.addOTPForAccountByEmail(otp, email);
//                    sendMsgEmail(email, "Your OTP Code is: " + otp);

    }

    private void sendMsgEmail(String toEmail, String msg) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", FROM_EMAIL);
        props.put("mail.smtp.password", EMAIL_PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your OTP Code");
            message.setText(msg);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", FROM_EMAIL, EMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
