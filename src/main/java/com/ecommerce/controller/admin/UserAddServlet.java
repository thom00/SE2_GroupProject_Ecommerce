package com.ecommerce.controller.admin;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import com.ecommerce.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @overview UserAddServlet is a controller servlet used to handle the use
 * case of Adding a new User to the database
 */
@WebServlet("/admin/user/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eString = req.getParameter("e");
        if (eString != null) {
            if (eString.equals("1")) {
                req.setAttribute("errMsg", "Username existed");
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/user-add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gender;
        User user = new User();
        try {
            Collection<Part> parts = req.getParts();
            for (Part part : parts) {
                String fieldName = part.getName();
                switch (fieldName) {
                    case "email":
                        String email = req.getParameter("email");
                        user.setEmail(email);
                        System.out.println(email);
                        break;
                    case "username":
                        String username = req.getParameter("username");
                        user.setUsername(username);
                        System.out.println(username);
                        break;
                    case "fullname":
                        String fullname = req.getParameter("fullname");
                        user.setFullname(fullname);
                        System.out.println(fullname);
                        break;
                    case "password":
                        String password = req.getParameter("password");
                        user.setPassword(password);
                        System.out.println(password);
                        break;
                    case "mobile":
                        String mobile = req.getParameter("mobile");
                        user.setMobile(mobile);
                        System.out.println(mobile);
                        break;
                    case "address":
                        String address = req.getParameter("address");
                        user.setAddress(address);
                        System.out.println(address);
                        break;
                    case "gender": {
                        String tmp = req.getParameter("gender");
                        int genderID = Integer.parseInt(tmp);
                        if (genderID == 1) {
                            gender = "Male";
                        } else if (genderID == 2) {
                            gender = "Female";
                        } else {
                            gender = "Other";
                        }
                        System.out.println(gender);
                        user.setGender(gender);
                        break;
                    }
                    case "dob":
                        String dobStr = req.getParameter("dob");
                        LocalDate dob = LocalDate.parse(dobStr);
                        user.setDob(Date.valueOf(dob));
                        System.out.println(dob);
                        break;
                    case "roleID": {
                        String tmp = req.getParameter("roleID");
                        int roleID = Integer.parseInt(tmp);
                        user.setRoleID(roleID);
                        System.out.println(roleID);
                        break;
                    }
                }
            }
            userService.insertUser(user);
            resp.sendRedirect(req.getContextPath() + "/admin/user/list");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/user/add?e=1");
        }
    }
}