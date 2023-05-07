package com.ecommerce.service;

import com.ecommerce.model.Cart;

import java.util.List;

/**
 * @overview
 */
public interface CartService {
    /**
     * Insert new cart
     */
    int insertCart(Cart cart);

    /**
     * Update cart information
     */
    boolean updateCart(Cart cart);

    /**
     * Update cart information
     */
    void updateCartStatus(int cartID, String cartStatus);

    /**
     * Delete a cart
     */
    boolean deleteCart(int cartId);

    /**
     * Retreive a cart by its id
     *
     * @param cartId - the id of the desired cart
     */
    Cart getCartById(int cartId);

    /**
     * Get Cart from database by using it's user id
     */
    List<Cart> getCartByUserId(int userId);

    /**
     * Retrieve all carts
     *
     * @return Either the list of all carts or null if there is none
     */
    List<Cart> getAllCarts();

    /**
     * Search cart using email
     */
    List<Cart> searchCartByEmail(String email);

    /**
     * Search cart by phone
     */
    List<Cart> searchCartByPhone(String phone);
}