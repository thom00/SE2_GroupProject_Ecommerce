package com.ecommerce.controller.client;

import com.ecommerce.model.Voucher;
import com.ecommerce.service.VoucherService;
import com.ecommerce.service.impl.VoucherServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/customer/cart")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CartServlet extends HttpServlet {
    VoucherService voucherService = new VoucherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Voucher> list = voucherService.getAllVouchers();
        System.out.println(list.size());
        req.setAttribute("vouchers", list);
        HttpSession httpSession = req.getSession();
        httpSession.getAttribute("cart");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/customer/list-cart.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Part> parts = req.getParts();
            for (Part part : parts) {
                if (part.getName().equals("subtotal")) {
                    String tmp = new String(part.getInputStream().readAllBytes());
                    int subtotal = Integer.parseInt(tmp);
                    System.out.println(subtotal);
                    req.setAttribute("subtotal", subtotal);
                } else if (part.getName().equals("discount")) {
                    String tmp = new String(part.getInputStream().readAllBytes());
                    int discount = Integer.parseInt(tmp);
                    System.out.println(discount);
                    req.setAttribute("discount", discount);
                } else if (part.getName().equals("total")) {
                    String tmp = new String(part.getInputStream().readAllBytes());
                    int total = Integer.parseInt(tmp);
                    System.out.println(total);
                    req.setAttribute("total", total);
                } else if (part.getName().equals("voucherID")) {
                    String tmp = new String(part.getInputStream().readAllBytes());
                    int voucherID = Integer.parseInt(tmp);
                    System.out.println(voucherID);
                    req.setAttribute("voucherID", voucherID);
                }
            }
            RequestDispatcher rd = req.getRequestDispatcher("/view/customer/checkout.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}