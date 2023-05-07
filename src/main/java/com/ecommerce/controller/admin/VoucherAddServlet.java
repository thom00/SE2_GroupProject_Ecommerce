package com.ecommerce.controller.admin;

import com.ecommerce.model.Voucher;
import com.ecommerce.service.VoucherService;
import com.ecommerce.service.impl.VoucherServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @overview VoucherAddServlet is a controller servlet used to handle the use
 * case of Adding a new Voucher to the database
 */
@WebServlet(urlPatterns = "/admin/voucher/add")
public class VoucherAddServlet extends HttpServlet {
    VoucherService voucherService = new VoucherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        req.setAttribute("vouchers", vouchers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/voucher-add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Voucher voucher = new Voucher();
        String voucherCode;
        int discountPercentage;
        LocalDate expireDate;
        for (Part part : req.getParts()) {
            switch (part.getName()) {
                case "voucherCode":
                    voucherCode = req.getParameter("voucherCode");
                    voucher.setVoucherCode(voucherCode);
                    break;
                case "discountPercent":
                    discountPercentage = Integer.parseInt(req.getParameter("discountPercent"));
                    voucher.setDiscountPercentage(discountPercentage);
                    break;
                case "expireDate":
                    expireDate = LocalDate.parse(req.getParameter("expireDate"));
                    voucher.setExpireDate(Date.valueOf(expireDate));
                    break;
                default:
                    // Other form data fields
                    break;
            }
        }
        voucherService.insertVoucher(voucher);
        resp.sendRedirect("list");
    }
}