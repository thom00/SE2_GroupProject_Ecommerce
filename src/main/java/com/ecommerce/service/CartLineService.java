package com.ecommerce.service;

import com.ecommerce.model.CartLine;

import java.util.List;

public interface CartLineService {
    /**
     * Insert new cartline
     */
    void insertCartLine(CartLine cartLine);

    /**
     * Update cartline information
     */
    boolean updateCartLine(CartLine cartLine);

    /**
     * Delete a cartline
     */
    boolean deleteCartLine(int cartLineID);

    /**
     * Retrieve a cartline by its id
     *
     * @param cartLineID - the id of the desired cartline
     */
    CartLine getCartLineByID(int cartLineID);

    /**
     * Retrieve all cartLine belong to the desired cart
     *
     * @param cartID the id of cartID
     */
    List<CartLine> getCartLineByCartID(int cartID);

    /**
     * Retrieve all cartlines
     *
     * @return Either the list of all cartlines or null if there is none
     */
    List<CartLine> getAllCartLines();
}