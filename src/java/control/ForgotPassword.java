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
        
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String localhostAddress = "http://" + serverName + ":" + serverPort;
        
        if (otpCheck != null && otpInput.equals(otpCheck)) {
            String newPassword = commonDao.generateRandomPassword();
            commonDao.updatePasswordById(newPassword, accountID);
            String MsgSend = gethtmlTemplate(newPassword);
            sendMsgEmail(email, MsgSend, localhostAddress);
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
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String localhostAddress = "http://" + serverName + ":" + serverPort;
        String otp = commonDao.generateRandomOTP();
        commonDao.addOTPForAccountByEmail(otp, email);
        String MsgSend = gethtmlTemplate(otp);
        sendMsgEmail(email, MsgSend, localhostAddress);
    }

    private void sendMsgEmail(String toEmail, String msg, String localhostAddress) {
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
            //message.setText(msg);

            String htmlContent = msg;
            message.setContent(htmlContent, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", FROM_EMAIL, EMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String gethtmlTemplate(String MSG) {
        String htmlContect = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
                + "\n"
                + "<head>\n"
                + "  <!--[if gte mso 9]>\n"
                + "<xml>\n"
                + "  <o:OfficeDocumentSettings>\n"
                + "    <o:AllowPNG/>\n"
                + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "  </o:OfficeDocumentSettings>\n"
                + "</xml>\n"
                + "<![endif]-->\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <meta name=\"x-apple-disable-message-reformatting\">\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "  <!--<![endif]-->\n"
                + "  <title></title>\n"
                + "\n"
                + "  <style type=\"text/css\">\n"
                + "    @media only screen and (min-width: 620px) {\n"
                + "      .u-row {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "      .u-row .u-col {\n"
                + "        vertical-align: top;\n"
                + "      }\n"
                + "      .u-row .u-col-100 {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "    }\n"
                + "    \n"
                + "    @media (max-width: 620px) {\n"
                + "      .u-row-container {\n"
                + "        max-width: 100% !important;\n"
                + "        padding-left: 0px !important;\n"
                + "        padding-right: 0px !important;\n"
                + "      }\n"
                + "      .u-row .u-col {\n"
                + "        min-width: 320px !important;\n"
                + "        max-width: 100% !important;\n"
                + "        display: block !important;\n"
                + "      }\n"
                + "      .u-row {\n"
                + "        width: 100% !important;\n"
                + "      }\n"
                + "      .u-col {\n"
                + "        width: 100% !important;\n"
                + "      }\n"
                + "      .u-col>div {\n"
                + "        margin: 0 auto;\n"
                + "      }\n"
                + "    }\n"
                + "    \n"
                + "    body {\n"
                + "      margin: 0;\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "    \n"
                + "    table,\n"
                + "    tr,\n"
                + "    td {\n"
                + "      vertical-align: top;\n"
                + "      border-collapse: collapse;\n"
                + "    }\n"
                + "    \n"
                + "    p {\n"
                + "      margin: 0;\n"
                + "    }\n"
                + "    \n"
                + "    .ie-container table,\n"
                + "    .mso-container table {\n"
                + "      table-layout: fixed;\n"
                + "    }\n"
                + "    \n"
                + "    * {\n"
                + "      line-height: inherit;\n"
                + "    }\n"
                + "    \n"
                + "    a[x-apple-data-detectors='true'] {\n"
                + "      color: inherit !important;\n"
                + "      text-decoration: none !important;\n"
                + "    }\n"
                + "    \n"
                + "    table,\n"
                + "    td {\n"
                + "      color: #000000;\n"
                + "    }\n"
                + "    \n"
                + "    #u_body a {\n"
                + "      color: #0000ee;\n"
                + "      text-decoration: underline;\n"
                + "    }\n"
                + "  </style>\n"
                + "\n"
                + "\n"
                + "\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <!--<![endif]-->\n"
                + "\n"
                + "</head>\n"
                + "\n"
                + "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #f9f9f9;color: #000000\">\n"
                + "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; color: #afb0c7; line-height: 170%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 14px; line-height: 23.8px;\">View Email in Browser</span></p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://assets.unlayer.com/projects/218606/1708650172744-689422.png\" alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 8%;max-width: 44.8px;\"\n"
                + "                                      width=\"44.8\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #003399;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://cdn.templates.unlayer.com/assets/1597218650916-xxxxc.png\" alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 26%;max-width: 150.8px;\"\n"
                + "                                      width=\"150.8\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; color: #e5eaf5; line-height: 140%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 140%;\"><strong>T H A N K S&nbsp; &nbsp;F O R&nbsp; &nbsp;S I G N I N G&nbsp; &nbsp;U P !</strong></p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 31px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; color: #e5eaf5; line-height: 140%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 28px; line-height: 39.2px;\"><strong><span style=\"line-height: 39.2px; font-size: 28px;\">Your Verify E-mail Address OTP Code </span></strong>\n"
                + "                                  </span>\n"
                + "                                </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:33px 55px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; line-height: 160%; text-align: justify; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 22px; line-height: 35.2px;\">Hi, </span></p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 18px; line-height: 28.8px;\">This OTP is valid for a single use and should not be shared with anyone. If you did not request this OTP, please ignore this message. Due to security issues, the OTP code <br>will expire in 5 minutes.&nbsp;</span></p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:33px 55px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; line-height: 160%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"line-height: 160%;\"><strong><span style=\"font-size: 40px; line-height: 64px; color: #000000;\"><span style=\"background-color: #c2e0f4; line-height: 22.4px;\">"+MSG+"</span></span></strong></p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #e5eaf5;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #e5eaf5;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:41px 55px 18px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; color: #003399; line-height: 160%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 20px; line-height: 32px;\"><strong>Get in touch:</strong></span></p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 16px; line-height: 25.6px; color: #000000;\">+09 123 456 78</span></p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 16px; line-height: 25.6px; color: #000000;\">Email Contact@: </span>clothingshoponlineg1se1754@gmail.com</p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 33px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div align=\"center\">\n"
                + "                                <div style=\"display: table; max-width:244px;\">\n"
                + "                                  <!--[if (mso)|(IE)]><table width=\"244\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:244px;\"><tr><![endif]-->\n"
                + "\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 17px;\" valign=\"top\"><![endif]-->\n"
                + "                                  <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 17px\">\n"
                + "                                    <tbody>\n"
                + "                                      <tr style=\"vertical-align: top\">\n"
                + "                                        <td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "                                          <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\n"
                + "                                            <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-black/facebook.png\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "                                          </a>\n"
                + "                                        </td>\n"
                + "                                      </tr>\n"
                + "                                    </tbody>\n"
                + "                                  </table>\n"
                + "                                  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 17px;\" valign=\"top\"><![endif]-->\n"
                + "                                  <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 17px\">\n"
                + "                                    <tbody>\n"
                + "                                      <tr style=\"vertical-align: top\">\n"
                + "                                        <td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "                                          <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\n"
                + "                                            <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-black/linkedin.png\" alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "                                          </a>\n"
                + "                                        </td>\n"
                + "                                      </tr>\n"
                + "                                    </tbody>\n"
                + "                                  </table>\n"
                + "                                  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 17px;\" valign=\"top\"><![endif]-->\n"
                + "                                  <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 17px\">\n"
                + "                                    <tbody>\n"
                + "                                      <tr style=\"vertical-align: top\">\n"
                + "                                        <td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "                                          <a href=\"https://instagram.com/\" title=\"Instagram\" target=\"_blank\">\n"
                + "                                            <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-black/instagram.png\" alt=\"Instagram\" title=\"Instagram\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "                                          </a>\n"
                + "                                        </td>\n"
                + "                                      </tr>\n"
                + "                                    </tbody>\n"
                + "                                  </table>\n"
                + "                                  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 17px;\" valign=\"top\"><![endif]-->\n"
                + "                                  <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 17px\">\n"
                + "                                    <tbody>\n"
                + "                                      <tr style=\"vertical-align: top\">\n"
                + "                                        <td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "                                          <a href=\"https://youtube.com/\" title=\"YouTube\" target=\"_blank\">\n"
                + "                                            <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-black/youtube.png\" alt=\"YouTube\" title=\"YouTube\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "                                          </a>\n"
                + "                                        </td>\n"
                + "                                      </tr>\n"
                + "                                    </tbody>\n"
                + "                                  </table>\n"
                + "                                  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                                  <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n"
                + "                                    <tbody>\n"
                + "                                      <tr style=\"vertical-align: top\">\n"
                + "                                        <td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "                                          <a href=\"https://email.com/\" title=\"Email\" target=\"_blank\">\n"
                + "                                            <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-black/email.png\" alt=\"Email\" title=\"Email\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
                + "                                          </a>\n"
                + "                                        </td>\n"
                + "                                      </tr>\n"
                + "                                    </tbody>\n"
                + "                                  </table>\n"
                + "                                  <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "\n"
                + "\n"
                + "                                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "                                </div>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #003399;\">\n"
                + "              <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n"
                + "\n"
                + "                              <div style=\"font-size: 14px; color: #fafafa; line-height: 180%; text-align: center; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-size: 16px; line-height: 28.8px;\">Copyrights © Company All Rights Reserved</span></p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "  <!--[if mso]></div><![endif]-->\n"
                + "  <!--[if IE]></div><![endif]-->\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        return htmlContect;
    }
}
