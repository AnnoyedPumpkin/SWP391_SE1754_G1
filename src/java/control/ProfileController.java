package control;

import dao.CommonDao;
import entity.Account_Detail;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.catalina.User;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    private CommonDao commonDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        commonDAO = new CommonDao();

        // Retrieve user information based on parameters
        String id = request.getParameter("id");
        String account_id = request.getParameter("account_id");
        String username = request.getParameter("username");
        String phone_number = request.getParameter("phone_number");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        

        // Assuming you have a method to retrieve user details from your data source
        

        // Set user information as an attribute in the request
       

        // Forward the request to the JSP page for displaying the profile
        request.getRequestDispatcher("/views/common/profile.jsp").forward(request, response);
    }
}
