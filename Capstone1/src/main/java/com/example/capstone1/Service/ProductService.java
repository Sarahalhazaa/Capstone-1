package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void addProduct(Product product) {
        
        if (categoryService.oneCategory(product.getCategoryId())!= null)
      products.add(product);
    }

    public boolean updateProduct(String id, Product product) {

        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId().equalsIgnoreCase(id)) {
                this.products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId().equalsIgnoreCase(id)) {
                this.products.remove(i);
                return true;
            }
        }
        return false;
    }


    // -------------------------- END CRUD --------------------------------

// ---------------------------   1 extra endpoint ---------------------------
    public int getPriceOfProduct(String id) {
       int price = 0;
        for (int u = 0; u < products.size(); u++) {
            if (products.get(u).getId().equalsIgnoreCase(id)) {
                return price = products.get(u).getPrice();
            }
        }
        return 0;
    }

// ------------------- 2 extra endpoint  -------------------------------------
    public Product getProduct1(String id) {
        for (int u = 0; u < products.size(); u++) {
            if (products.get(u).getId().equalsIgnoreCase(id)) {
                return products.get(u);
            }
        }
        return null;
    }

// ------------------- 3 extra endpoint  -------------------------------------

    public ArrayList<Product> ProductsInRange(int min , int max) {
        ArrayList<Product> products1 = new ArrayList<>();
        for (int u = 0; u < products.size(); u++) {
            if (products.get(u).getPrice()>=min && products.get(u).getPrice()<=max) {
                products1.add(products.get(u));
            }
        }
        return products1;
    }


}
