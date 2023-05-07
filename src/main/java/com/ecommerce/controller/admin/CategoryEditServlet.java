package com.ecommerce.controller.admin;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @overview CategoryEditServlet is a servlet used to handle the use case of editing a category
 */
@WebServlet("/admin/category/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CategoryEditServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryID = Integer.parseInt(req.getParameter("id"));
        Category category = categoryService.getCategoryByID(categoryID);
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.setAttribute("category", category);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/category-edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        String categoryName;
        int categoryID;
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String name = part.getName();
            if (name.equals("categoryName")) {
                categoryName = request.getParameter("categoryName");
                System.out.println(categoryName);
                category.setCategoryName(categoryName);
            } else if (name.equals("categoryID")) {
                String categoryIDString = request.getParameter("categoryID");
                System.out.println(categoryIDString);
                categoryID = Integer.parseInt(categoryIDString);
                category.setCategoryID(categoryID);
            }
        }
        categoryService.updateCategory(category);
        response.sendRedirect(request.getContextPath() + "/admin/category/list");
    }
}