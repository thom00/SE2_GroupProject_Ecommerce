package com.ecommerce.service.impl;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.impl.CategoryDAOImpl;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import java.util.List;

/**
 * @overview CategoryService is a class providing a number of function to be
 * implemented on Category.
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDao = new CategoryDAOImpl();

    /**
     * Insert a new category
     *
     * @param category - the category to be inserted to database
     */
    @Override
    public void insertCategory(Category category) {
        categoryDao.insertCategory(category);
    }

    /**
     * Update an existing category
     *
     * @param category - the category to be updated
     */
    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    /**
     * Delete an existing category
     *
     * @param categoryID - the id of the category to be deleted
     */
    @Override
    public void deleteCategory(int categoryID) {
        categoryDao.deleteCategory(categoryID);
    }

    /**
     * Retrieve a category by its id
     *
     * @param categoryID - the id of the desired category
     */
    @Override
    public Category getCategoryByID(int categoryID) {
        return categoryDao.getCategoryByID(categoryID);
    }

    /**
     * Retrieve a category by its name
     *
     * @param categoryName - the name of the desired category
     */
    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }

    /**
     * Retrieve all categories
     *
     * @return Either the list of all categories or null if there is none
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    /**
     * Search for a category by its name
     *
     * @param categoryName - the name of the desired category
     * @return Either the list of all matching categories or null if there is none
     */
    @Override
    public List<Category> searchCategoryByName(String categoryName) {
        return categoryDao.searchCategoryByName(categoryName);
    }
}