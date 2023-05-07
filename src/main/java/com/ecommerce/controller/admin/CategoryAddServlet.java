package com.ecommerce.controller.admin;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.impl.CategoryServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @overview CategoryAddServlet is a controller servlet used to handle the use
 * case of Adding a new Category to the database
 */
@WebServlet(urlPatterns = "/admin/category/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CategoryAddServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/category-add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        String categoryName = null;
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String name = part.getName();
            if (name.equals("categoryName")) {
                categoryName = request.getParameter("categoryName");
                System.out.println(name + "-" + categoryName);
            }
        }
        category.setCategoryName(categoryName);
        categoryService.insertCategory(category);
        response.sendRedirect("list");
    }
}