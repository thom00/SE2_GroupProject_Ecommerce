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

import java.io.IOException;
import java.util.List;

/**
 * @overview VoucherListServlet is the servlet used to handle the use case of
 * displaying the list of all vouchers
 */
@WebServlet("/admin/voucher/list")
public class VoucherListServlet extends HttpServlet {
    VoucherService voucherService = new VoucherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Voucher> voucherList = voucherService.getAllVouchers();
        req.setAttribute("voucherList", voucherList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/voucher-list.jsp");
        dispatcher.forward(req, resp);
    }
}