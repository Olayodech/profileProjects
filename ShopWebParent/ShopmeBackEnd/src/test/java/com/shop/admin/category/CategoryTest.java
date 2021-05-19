package com.shop.admin.category;

import com.shop.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void createRootCategory1(){
        Category category= new Category();
        category.setCategoryName("Cars");
        category.setCategoriesAlias("cars");
        category.setCategoryImage("default.png");
        category.setCategoryEnabled(true);
        category = categoryRepository.save(category);
        assertThat(category.getCategoryId() > 0);
    }


    @Test
    public void createRootCategory2(){
        Category category= new Category();
        category.setCategoryName("Speakers");
        category.setCategoriesAlias("speakers");
        category.setCategoryImage("default.png");
        category.setCategoryEnabled(true);
        category = categoryRepository.save(category);
        assertThat(category.getCategoryId() > 0);
    }

    @Test
    public void createSubCategory(){
        Category category = categoryRepository.findById(1).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Desktops");
        newCategory.setCategoryImage("desktop.png");
        newCategory.setCategoriesAlias("Desktops");
        categoryRepository.save(newCategory);

    }

    @Test
    public void createSubCategory2(){
        Category category = categoryRepository.findById(1).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Laptop");
        newCategory.setCategoryImage("laptop.png");
        newCategory.setCategoriesAlias("Laptops");
        categoryRepository.save(newCategory);

    }

    @Test
    public void createSubCategory3(){
        Category category = categoryRepository.findById(1).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Components");
        newCategory.setCategoryImage("components.png");
        newCategory.setCategoriesAlias("Components");
        categoryRepository.save(newCategory);

    }

    @Test
    public void createSubCategoryChild(){
        Category category = categoryRepository.findById(5).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Memory");
        newCategory.setCategoryImage("memory.png");
        newCategory.setCategoriesAlias("Memory");
        categoryRepository.save(newCategory);

    }

    @Test
    public void createSubCategoryElectronics3(){
        Category category = categoryRepository.findById(2).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Camera");
        newCategory.setCategoryImage("camera.png");
        newCategory.setCategoriesAlias("Camera");
        categoryRepository.save(newCategory);

    }

    @Test
    public void createSubCategoryElectronics4(){
        Category category = categoryRepository.findById(2).get();
        Category newCategory = new Category();
        newCategory.setParent(category);
        newCategory.setCategoryName("Smart Phone");
        newCategory.setCategoryImage("smartphone.png");
        newCategory.setCategoriesAlias("smart Phone");
        categoryRepository.save(newCategory);

    }

    @Test
    public void testGetCategoryAndSubCategory(){
        Category category = new Category();
        category = categoryRepository.findById(2).get();
        Set<Category> child = category.getChilderen();

        for(Category category1 : child){
            System.out.println(category1.getCategoryName());
        }
        assertThat(child.size() > 0);
    }

    @Test
    public void testPrintCategoryHierachy(){
        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category: categories){
            if(category.getParent() == null){
                System.out.println(category.getCategoryName());

                Set<Category> children = category.getChilderen();

                for(Category child : children){
                    System.out.println("--" + child.getCategoryName());


                }
            }
        }
    }

    private void printChildren(Category parent, int sublevel){
        int sublevels= sublevel + 1;
        Set<Category> children = parent.getChilderen();
        for(Category cat : children){
         }
    }

    @Test
    public void testRootCategegories(){
       Iterable<Category> rootCategories = categoryRepository.listRootCategory();
       rootCategories.forEach(cat ->{
           System.out.println(cat.getCategoryName());
       });
    }
    
    @Test
    public void findByCategoryName() {
    	String name = "Computer";
    	Category category = categoryRepository.findByCategoryName(name);
    	System.out.println(category);
    	assertThat(category.getCategoryName().equalsIgnoreCase(name));
    }
    
    @Test
    public void findCategoryByAlias() {
    	String name = "Computer";
    	Category category = categoryRepository.findByCategoriesAlias(name);
    	System.out.println(category);
    	assertThat(category.getCategoryName().equalsIgnoreCase(name));
    }

}
