package com.ecommerce.controller.admin;

import com.ecommerce.service.CategoryService;
import com.ecommerce.service.impl.CategoryServiceImpl;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @overview CategoryDeleteServlet is a servlet used to handle the use-case of
 * deleting a  from database
 */
@WebServlet("/admin/category/delete")
public class CategoryDeleteServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        categoryService.deleteCategory(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}