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
import java.util.Arrays;
import java.util.List;

/**
 * @overview VoucherEditServlet is a servlet used to handle the use-case of
 * editing a voucher
 */
@WebServlet("/admin/voucher/edit")
public class VoucherEditServlet extends HttpServlet {
    VoucherService voucherService = new VoucherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int voucherID = Integer.parseInt(req.getParameter("id"));
        Voucher voucher = voucherService.getVoucherByID(voucherID);
        List<Voucher> vouchers = voucherService.getAllVouchers();
        req.setAttribute("vouchers", vouchers);
        req.setAttribute("voucher", voucher);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/admin/voucher-edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Voucher voucher = new Voucher();
        // Get the part for the voucherID
        Part partVoucherID = req.getPart("voucherID");
        voucher.setVoucherID(Integer.parseInt(Arrays.toString(partVoucherID.getInputStream().readAllBytes())));
        // Get the part for the voucherCode
        Part partVoucherCode = req.getPart("voucherCode");
        voucher.setVoucherCode(Arrays.toString(partVoucherCode.getInputStream().readAllBytes()));
        // Get the part for the discountPercent
        Part partDiscountPercent = req.getPart("discountPercent");
        voucher.setDiscountPercentage(Integer.parseInt(Arrays.toString(partDiscountPercent.getInputStream().readAllBytes())));
        // Get the part for the expireDate
        Part partExpireDate = req.getPart("expireDate");
        voucher.setExpireDate(Date.valueOf(Arrays.toString(partExpireDate.getInputStream().readAllBytes())));
        voucherService.updateVoucher(voucher);
        resp.sendRedirect("list");
    }
}