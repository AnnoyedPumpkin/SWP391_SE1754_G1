/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import constant.Constant;
import dao.CommonDao;
import entity.Account;
import entity.Account_Form;
import entity.Gender;
import entity.Invoice_Form;
import entity.Pagination;
import entity.Product;
import entity.Product_Form;
import helper.BCrypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    CommonDao commonDAO = new CommonDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = "";
        List<Gender> listG;
        String page = request.getParameter("page") == null ? "default" : request.getParameter("page");
        Pagination pagination = new Pagination();
        List<Product_Form> listP = commonDAO.getTop12ProductNewest();
        switch (page) {
            case "403":
                url = "views/common/errorpage.jsp";
                break;
            case "update-profile":
                Account_Form account = (Account_Form) session.getAttribute(Constant.SESSION_ACCOUNT);
                if (account == null) {
                    url = "views/common/login.jsp";
                } else {
                    Account_Form accountProfile = commonDAO.findProfileById(account);
                    listG = commonDAO.findAllGender();
                    session.setAttribute("accountProfile", accountProfile);
                    session.setAttribute("listG", listG);
                    url = "views/common/updateProfile.jsp";
                }
                break;
            case "view-invoice":
                account = (Account_Form) session.getAttribute(Constant.SESSION_ACCOUNT);
                if (account == null) {
                    url = "views/common/login.jsp";
                }
                List<Invoice_Form> listIf = commonDAO.findAllInvoiceByAccountId(account);
                session.setAttribute("listIf", listIf);
                url = "views/common/orderHistory.jsp";
                break;
            case "view-invoice-details":
                String invoiceID = request.getParameter("invoiceID");
                String productID = request.getParameter("productID");
                if (productID == null || invoiceID == null || productID.isEmpty() || invoiceID.isEmpty()) {
                    request.setAttribute("errorvid", "Cannot find invoice detail of invoice id " + invoiceID);
                    url = "view/common/orderHistory.jsp";
                } else {
                    listIf = commonDAO.getInvoiceDetails(invoiceID, productID);
                    request.setAttribute("listIf", listIf);
                    url = "views/common/orderHistoryDetails.jsp";
                }
                break;
            default:
                session.setAttribute("listP", listP);
                session.setAttribute("pagination", pagination);
                url = "views/common/homePage.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = "";
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        switch (action) {
            case "changePassword":
                changePassword(request, response);
                url = "views/common/updateProfile.jsp";
                break;
            case "updateProfile":
                Account_Form accountProfile = updateProfile(request, response);
                session.setAttribute("accountProfile", accountProfile);
                url = "views/common/updateProfile.jsp";
                break;
            default:
                throw new AssertionError();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newPassword");
        String passwordConfirm = request.getParameter("newPassword2");
        if (email == null || email.isEmpty() || password == null || password.isEmpty()
                || newpassword == null || newpassword.isEmpty() || passwordConfirm == null || passwordConfirm.isEmpty()) {
            request.setAttribute("errorupa", "All field must be required");
            return;
        }
        Account account = Account.builder()
                .email(email)
                .password(password)
                .build();
        Account_Form af = commonDAO.checkExistOfAcc(account);
        if (af != null) {
            if (!newpassword.matches(Constant.PASSWORD_REGEX)) {
                request.setAttribute("errorupa", "New password contains at lease 8 character (1 number, 1 uppercase char, 1 special char(no space)");
                return;
            } else {
                if (!newpassword.equals(passwordConfirm)) {
                    request.setAttribute("errorupa", "New password and confirm password must be equal");
                    return;
                }
                BCrypt bcryp = new BCrypt();
                String passwordBcryp = bcryp.hashpw(newpassword, bcryp.gensalt());
                int result = commonDAO.changePassword(passwordBcryp, af.getId());
                if (result > 0) {
                    request.setAttribute("msgupa", "Change password successful!");
                }
            }
        } else {
            request.setAttribute("errorupa", "Password not correct!");
        }
    }

    private Account_Form updateProfile(HttpServletRequest request, HttpServletResponse response) {
        String accountIdRaw = request.getParameter("account_id");
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        String genderIdRaw = request.getParameter("gender");
        String dobRaw = request.getParameter("dob");
        String address = request.getParameter("address");
        if (accountIdRaw == null || accountIdRaw.isEmpty() || userName == null || userName.isEmpty() || phone == null || phone.isEmpty()
                || genderIdRaw == null || genderIdRaw.isEmpty() || dobRaw == null || dobRaw.isEmpty()
                || address == null || address.isEmpty()) {
            request.setAttribute("errorupa", "All field must be required");
            return null;
        }
        int accountID = Integer.parseInt(accountIdRaw);
        int genderID = Integer.parseInt(genderIdRaw);
        Date dob = Date.valueOf(dobRaw);
        Account_Form af = Account_Form.builder()
                .id(accountID)
                .username(userName)
                .phone_number(phone)
                .gender_id(genderID)
                .dob(dob)
                .address(address).build();
        boolean isExistAccountDetails = commonDAO.checkExistOfAccountDetail(af);
        if (isExistAccountDetails) {
            Account_Form newProfileAccount = commonDAO.editAccountDetails(af);
            request.setAttribute("msgupa", "Update profile successfully!");
            return newProfileAccount;
        } else {
            Account_Form newProfileAccount = commonDAO.insertAccountDetails(af);
            request.setAttribute("msgupa", "Update profile successfully!");
            return newProfileAccount;
        }

    }
}
