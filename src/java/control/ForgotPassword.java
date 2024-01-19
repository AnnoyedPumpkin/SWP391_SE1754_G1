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
        response.sendRedirect("forgotpassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        switch (action) {
            case "getNewPassword":
                addNewPassword(request, response);
                break;
            case "sendOTP":
                sendOTP(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }
private void addNewPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactInfo = request.getParameter("contactInfo");
        String otp = request.getParameter("otpCode");

        boolean accountExists = commonDao.checkAccountExistByEmail(contactInfo) ;
        int accountID = commonDao.getAccountIdByEmail(contactInfo);
        boolean otpMatched = commonDao.checkOTPMatchedByEmail(contactInfo);
        
        if (accountExists && otpMatched) {
            String newPassword = commonDao.generateRandomPassword();
            commonDao.updatePasswordById(newPassword, accountID);
            request.setAttribute("newPassword", newPassword);
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Incorrect username or contact information.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }
    
    private void sendOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String otpCode = commonDao.generateRandomOTP();
        String email = request.getParameter("contactInfo");
        sendOTPEmail(email, otpCode);
        response.sendRedirect("forgotpassword.jsp");
    }
    
    private void sendOTPEmail(String toEmail, String otpCode) {
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
            message.setText("Your OTP code is: " + otpCode);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", FROM_EMAIL, EMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
