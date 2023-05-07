package com.ecommerce.service.impl;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.impl.ProductDAOImpl;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import java.util.List;

/**
 * @overview ProductServiceImpl implements all methods of ProductService interface.
 */
public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = new ProductDAOImpl();

    /**
     * Insert a new product
     *
     * @param product - the product to be inserted to database
     */
    @Override
    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }

    /**
     * Update an existing product
     *
     * @param product - the product to be updated
     */
    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    /**
     * Delete an existing product
     *
     * @param productID - the id of the product to be deleted
     */
    @Override
    public void deleteProduct(int productID) {
        productDAO.deleteProduct(productID);
    }

    /**
     * Retreive a product by its id
     *
     * @param productID - the id of the desired product
     */
    @Override
    public Product getProductByID(int productID) {
        return productDAO.getProductByID(productID);
    }

    /**
     * Retrieve all products
     *
     * @return Either the list of all products or null if there is none
     */
    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    /**
     * Search for a product by its name
     *
     * @param productName - the name of the desired product
     * @return Either the list of all matching products or null if there is none
     */
    @Override
    public List<Product> searchProductByName(String productName) {
        return productDAO.searchProductByName(productName);
    }

    /**
     * Search for a product by its category
     *
     * @param categoryID - the id of the desired category
     * @return Either the list of all matching products or null if there is none
     */
    @Override
    public List<Product> searchProductByCategory(int categoryID) {
        return productDAO.searchProductByCategory(categoryID);
    }
}