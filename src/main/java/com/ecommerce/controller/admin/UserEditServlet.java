package com.ecommerce.controller.admin;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import com.ecommerce.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

/**
 * @overview UserEditServlet is a servlet used to handle the use-case of
 * editing a user
 */
@WebServlet("/admin/user/edit")
public class UserEditServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.getUserById(id);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/user-edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gender;
        User user = new User();
        try {
            user.setUserID(Integer.parseInt(req.getParameter("userID")));
            user.setEmail(req.getParameter("email"));
            user.setUsername(req.getParameter("username"));
            user.setFullname(req.getParameter("fullname"));
            user.setPassword(req.getParameter("password"));
            user.setMobile(req.getParameter("mobile"));
            user.setAddress(req.getParameter("address"));
            String tmp = req.getParameter("gender");
            int genderID = Integer.parseInt(tmp);
            if (genderID == 1) {
                gender = "Male";
            } else if (genderID == 2) {
                gender = "Female";
            } else {
                gender = "Other";
            }
            user.setGender(gender);
            user.setDob(Date.valueOf(req.getParameter("dob")));
            user.setRoleID(Integer.parseInt(req.getParameter("roleID")));
            userService.updateUser(user);
            resp.sendRedirect(req.getContextPath() + "/admin/user/list");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/user/add?e=1");
        }
    }
}