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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {

    CommonDao commonDao = new CommonDao();
    private final String FROM_EMAIL = "clothingshoponlineg1se1754@gmail.com";
    private final String EMAIL_PASSWORD = "pizwgjrviipmttyx";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String contactInfo = request.getParameter("contactInfo");
        boolean accountExists = commonDao.checkAccountExistByEmail(contactInfo);
        if (accountExists) {
            switch (action) {
                case "getNewPassword":
                    getNewPassword(request, response);
                    break;
                case "sendOTP":
                    sendOTP(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            request.setAttribute("errorMessage", "This email not correct or not register yet.");
            request.getRequestDispatcher("views/common/forgotpassword.jsp").forward(request, response);
        }

    }

    private void getNewPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("contactInfo");
        String OTP = request.getParameter("otpCode");

        int accountID = commonDao.getAccountIdByEmail(email);
        String OTPChecked = commonDao.getOTPByEmail(email);
        if (OTPChecked.equals(OTP)) {
            String newPassword = commonDao.generateRandomPassword();
            commonDao.updatePasswordById(newPassword, accountID);
            sendMsgEmail(email, "Your new password is: " + newPassword, "Your New Password");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Your email or OTP Code not correct!.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }

    private void sendOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otp = commonDao.generateRandomOTP();
        String email = request.getParameter("contactInfo");
        commonDao.addOTPForAccountByEmail(otp, email);
        sendMsgEmail(email, "Your OTP Code is: " + otp, "Your OTP Code");
        request.setAttribute("notificationMessage", "Your OTP Code will exprie after 5 minutes, click Send OTP Code again if you don't recive any OTP Code.");
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
    }

    private void sendMsgEmail(String toEmail, String msg, String titleMsg) {
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
            message.setSubject(titleMsg);
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
