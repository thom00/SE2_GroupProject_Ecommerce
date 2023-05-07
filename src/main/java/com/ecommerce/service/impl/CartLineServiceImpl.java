package com.ecommerce.service.impl;

import com.ecommerce.dao.CartLineDAO;
import com.ecommerce.dao.impl.CartLineDAOImpl;
import com.ecommerce.model.CartLine;
import com.ecommerce.service.CartLineService;

import java.util.List;

public class CartLineServiceImpl implements CartLineService {
    private final CartLineDAO cartLineDAO = new CartLineDAOImpl();

    /**
     * Insert new cartline
     */
    @Override
    public void insertCartLine(CartLine cartLine) {
        cartLineDAO.insertCartLine(cartLine);
    }

    /**
     * Update cartline information
     */
    @Override
    public boolean updateCartLine(CartLine cartLine) {
        return cartLineDAO.updateCartLine(cartLine);
    }

    /**
     * Delete a cartline
     */
    @Override
    public boolean deleteCartLine(int cartLineID) {
        return cartLineDAO.deleteCartLine(cartLineID);
    }

    /**
     * get cartline using id
     */
    @Override
    public CartLine getCartLineByID(int cartLineID) {
        return cartLineDAO.getCartLineByID(cartLineID);
    }

    /**
     * Retrieve all cartliness
     */
    @Override
    public List<CartLine> getAllCartLines() {
        return cartLineDAO.getAllCartLines();
    }

    /**
     * Retrieve all cartLine belong to the desired cart
     *
     * @param cartID the id of cartID
     */
    @Override
    public List<CartLine> getCartLineByCartID(int cartID) {
        return cartLineDAO.getCartLineByCartID(cartID);
    }
}