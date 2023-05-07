package com.ecommerce.controller.admin;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.impl.CategoryServiceImpl;
import com.ecommerce.service.impl.ProductServiceImpl;
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
 * @overview ProductAddServlet is a controller servlet used to handle the use
 * case of Adding a new Product to the database
 */
@WebServlet(urlPatterns = "/admin/product/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductAddServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        System.out.println(categories.size());
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/product-add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        // Retrieve parts from request
        Collection<Part> parts = req.getParts();
        try {
            for (Part part : parts) {
                String fieldName = part.getName();
                switch (fieldName) {
                    case "productName":
                        product.setProductName(req.getParameter("productName"));
                        System.out.println(fieldName + " - " + req.getParameter("productName"));
                        break;
                    case "productPrice":
                        product.setProductPrice(Long.parseLong(req.getParameter("productPrice")));
                        System.out.println(fieldName + " - " + req.getParameter("productPrice"));
                        break;
                    case "instock":
                        product.setInstock(Integer.parseInt(req.getParameter("instock")));
                        System.out.println(fieldName + " - " + req.getParameter("instock"));
                        break;
                    case "productDesc":
                        product.setProductDesc(req.getParameter("productDesc"));
                        System.out.println(fieldName + " - " + req.getParameter("productDesc"));
                        break;
                    case "category":
                        product.setCategory(categoryService.getCategoryByID(Integer.parseInt(req.getParameter("category"))));
                        System.out.println(fieldName + " - " + req.getParameter("category"));
                        break;
                    case "productImg":
                        product.setProductImg(req.getParameter("productImg"));
                        String[] imgs = req.getParameter("productImg").split(",");
                        for (String img : imgs) {
                            System.out.println(img);
                        }
                        System.out.println(fieldName + " - " + req.getParameter("productImg"));
                        break;
                }
            }
            productService.insertProduct(product);
            resp.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}