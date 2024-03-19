package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProductService productService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategory() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public boolean updateCategory(String id, Category category) {

        for (int i = 0; i < this.categories.size(); i++) {
            if (this.categories.get(i).getId().equalsIgnoreCase(id)) {
                this.categories.set(i, category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id) {
        for (int i = 0; i < this.categories.size(); i++) {
            if (this.categories.get(i).getId().equalsIgnoreCase(id)) {
                this.categories.remove(i);
                return true;
            }
        }
        return false;
    }

    //----------------------------------  end CRUD  --------------------------------------

    //---------------------------------------- 1 extra endpoint ---------------------------------------
    public ArrayList<Product> productOfCategory(String name) {
        ArrayList<Product> products1 = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++)
            if (categories.get(i).getName().equalsIgnoreCase(name)) {
                for (int j = 0; j < productService.products.size(); j++)
                    if (productService.products.get(j).getCategoryID().equalsIgnoreCase(categories.get(i).getId())) {
                        products1.add(productService.products.get(j));
                    }
            }
        return products1;
    }
}