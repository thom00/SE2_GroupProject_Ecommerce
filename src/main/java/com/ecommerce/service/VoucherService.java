package com.ecommerce.service;

import com.ecommerce.model.Voucher;

import java.util.List;

/**
 * @overview VoucherService is a class providing a number of function to be
 * implemented on Voucher.
 */
public interface VoucherService {
    /**
     * Insert a new voucher
     *
     * @param voucher - the voucher to be inserted to database
     */
    void insertVoucher(Voucher voucher);

    /**
     * Update an existing voucher
     *
     * @param voucher - the voucher to be updated
     */
    void updateVoucher(Voucher voucher);

    /**
     * Delete an existing voucher
     *
     * @param voucherID - the id of the voucher to be deleted
     */
    void deleteVoucher(int voucherID);

    /**
     * Retrieve a voucher by its id
     *
     * @param voucherID - the id of the desired voucher
     */
    Voucher getVoucherByID(int voucherID);

    /**
     * Retrieve a voucher by its code
     *
     * @param voucherCode - the code of the desired voucher
     */
    Voucher getVoucherByCode(String voucherCode);

    /**
     * Retrieve all voucher
     *
     * @return Either the list of all vouchers or null if there is none
     */
    List<Voucher> getAllVouchers();

    /**
     * Search for a voucher by its code
     *
     * @param voucherCode - the code of the desired voucher
     * @return Either the list of all matching vouchers or null if there is none
     */
    List<Voucher> searchVoucherByCode(String voucherCode);
}