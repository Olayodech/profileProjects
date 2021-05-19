package com.shop.site.mains;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.common.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> listNoChildrenCategory(){
		
		List<Category> listNoChildrenCategoryies = new ArrayList<>();
//		List<Category> listEnabledCategory = categoryRepository.findByCategoryEnabled();
		List<Category> listEnabledCategory = (List<Category>) categoryRepository.findAll();
		
		listEnabledCategory.forEach(cat ->{
			Set<Category> children= cat.getChilderen();
			if(children == null || children.size() == 0) {
				listNoChildrenCategoryies.add(cat);
			}
		});
		
		return listNoChildrenCategoryies;	
	}
	
	public Category getCategoryByAlias(String alias) {
		return categoryRepository.findByAliasEnabled(alias);
	}
	
	public List<Category> getCategoryPatents(Category child){
		List<Category> listParent = new ArrayList<>();
		
		Category parent = child.getParent();
		
		while(parent != null) {
			listParent.add(0, parent);
			parent = parent.getParent();
		}
		
		listParent.add(child);
		
		return listParent;
	}

	
}
