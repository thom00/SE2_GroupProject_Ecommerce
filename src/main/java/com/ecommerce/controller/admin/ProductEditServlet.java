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
 * @overview ProductEditServlet is a servlet used to handle the use case of
 * editing a product.
 */
@WebServlet("/admin/product/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductEditServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProductByID(productID);
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/product-edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        // Retrieve parts from the request
        Collection<Part> parts = request.getParts();
        // Process each part
        for (Part part : parts) {
            String fieldName = part.getName();
            switch (fieldName) {
                case "productID":
                    product.setProductID(Integer.parseInt(request.getParameter("productID").substring(11)));
                    System.out.println(request.getParameter("productID"));
                    break;
                case "productName":
                    String productName = request.getParameter("productName");
                    product.setProductName(productName);
                    System.out.println(productName);
                    break;
                case "productPrice":
                    long productPrice = Long.parseLong(request.getParameter("productPrice"));
                    product.setProductPrice(productPrice);
                    System.out.println(productPrice);
                    break;
                case "instock":
                    int instock = Integer.parseInt(request.getParameter("instock"));
                    product.setInstock(instock);
                    System.out.println(instock);
                    break;
                case "productDesc":
                    String productDesc = request.getParameter("productDesc");
                    product.setProductDesc(productDesc);
                    System.out.println(productDesc);
                    break;
                case "category":
                    int categoryID = Integer.parseInt(request.getParameter("category"));
                    Category category = categoryService.getCategoryByID(categoryID);
                    product.setCategory(category);
                    System.out.println(categoryID);
                    break;
                case "productImg":
                    String productImg = request.getParameter("productImg");
                    System.out.println(productImg);
                    product.setProductImg(productImg);
                    break;
            }
        }
        productService.updateProduct(product);
        response.sendRedirect(request.getContextPath() + "/admin/product/list");
    }
}