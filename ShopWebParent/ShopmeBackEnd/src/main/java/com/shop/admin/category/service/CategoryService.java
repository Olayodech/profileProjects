package com.shop.admin.category.service;

import com.shop.admin.CategoryNotFoundException;
import com.shop.admin.category.CategoryRepository;
import com.shop.common.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listAll(){
       List<Category> rootCategories = categoryRepository.listRootCategory();
        return listHierachial(rootCategories);
    }

    private List<Category> listHierachial(List<Category> rootCat){
        List<Category> hierachialCat = new ArrayList<>();

        for(Category rootscat : rootCat){
            hierachialCat.add(Category.copyFullCategory(rootscat));
            Set<Category> children = rootscat.getChilderen();
            for (Category subs : children){
                String name = "--" + subs.getCategoryName();
                hierachialCat.add(Category.copyfulls(subs, name));
                listSubHierachy(hierachialCat, subs, 1);
            }
        }
        return hierachialCat;
    }

    private  void listSubHierachy(List<Category> hierachyCategories, Category parent, int levelNumber) {
        Set<Category> children = parent.getChilderen();
        int newSubLevel = levelNumber + 1;

        for (Category subcat : children) {
            String name = "";
            for (int i = 0; i < newSubLevel; i++) {
                name += "--";
            }
            name += subcat.getCategoryName();
            hierachyCategories.add(Category.copyfulls(subcat, name));
            listSubHierachy(hierachyCategories, subcat, newSubLevel);
        }
    }

    public List<Category> listCategoriesForForm(){
        List<Category> allCategory = new ArrayList<>();
        List<Category> catInDb = (List<Category>) categoryRepository.findAll();
        for(Category category: catInDb){
            if(category.getParent() == null){
                allCategory.add(Category.copyIdandName(category));
              Set<Category> children =  category.getChilderen();

              for(Category subcategory : children){
                  String name = "--"+ subcategory.getCategoryName();
                  allCategory.add(Category.copyIdandNameOnly(subcategory.getCategoryId(), name));
                  listSubCategories(allCategory, subcategory, 1);
              }
            }
        }
        return allCategory;
    }

    private void listSubCategories(List<Category> allCategory, Category category, int subLevel){
        int newSubLevel = subLevel + 1;
        Set<Category> children = category.getChilderen();

        for(Category subcategory : children){
            String name = "";
            for(int i = 0; i < newSubLevel; i++){
                name += "--";
            }
            name += subcategory.getCategoryName();
            allCategory.add(Category.copyIdandNameOnly(subcategory.getCategoryId(), subcategory.getCategoryName()));
            listSubCategories(allCategory, subcategory, newSubLevel);
        }
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

	public Category getCategory(Integer id) {
		return categoryRepository.findById(id).get();
	}
	
	public String checkUnique(Integer id, String name, String alias) {
		
		boolean iscreatingnew = (id==null || id == 0);
		
		Category categoryByName = categoryRepository.findByCategoryName(name);
		
		if(iscreatingnew) {
			if (categoryByName != null) {
				return "DuplicateName";
			}else {
				Category category = categoryRepository.findByCategoriesAlias(name);
				if(category != null) {
					return "DuplicateAlias";
				}
			}
		}else {
			if(categoryByName != null && categoryByName.getCategoryId() != id) {
				return "DuplicateName";
			}
			
			Category category = categoryRepository.findByCategoriesAlias(name);
			if(category != null && category.getCategoryId() != id) {
				return "DuplicateAlias";
			}
		}
		
		return "OK";
		
	}

	public void deleteCategory(Integer id) {
//		Long countById = categoryRepository.countById(id);
//		
//		if(countById == null || countById == 0) {
//			throw new CategoryNotFoundException("Could not find any category with ID" + id);
//		}
		categoryRepository.deleteById(id);
	}
}
