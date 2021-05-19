package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.parent is NULL")
    List<Category> listRootCategory();
    
//    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    public Category findByCategoryName(String categoryName);
    
    Category findByCategoriesAlias(String categoriesAlias);
    
//    public Long countById(Integer Id);
   
}