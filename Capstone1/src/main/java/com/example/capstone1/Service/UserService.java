package com.example.capstone1.Service;

import com.example.capstone1.Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    private final PurchaseService purchaseService;

    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUser() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean updateUser(String id, User user) {

        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equalsIgnoreCase(id)) {
                this.users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equalsIgnoreCase(id)) {
                this.users.remove(i);
                return true;
            }
        }
        return false;
    }

    // -------------------------- END CRUD --------------------------------

    //-----------------------   endpoint 12   -------------------------------
    public String buyProduct(String userId, String productId, String merchantId) {
        int Price = 0;
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId().equalsIgnoreCase(userId)) {
                if (merchantStockService.searchIdMerchant(merchantId)) {
                    if (merchantStockService.searchIdProduct(productId)) {
                        if(merchantStockService.getStock(productId)>0)
                        if (merchantStockService.reduceStock(productId)) {
                            Price = productService.getPriceOfProduct(productId);
                                if(Price<= users.get(i).getBalance()){
                                users.get(i).setBalance(users.get(i).getBalance() - Price);
                                Purchase purchase = new Purchase(productId,userId);
                              purchaseService.purchases.add(purchase);
                                return "Products purchased";
                            }}
                        }else return "bad request";
                    }
                }
            }

        return "";
    }

    //-------------------------------------    1 extra endpoint  ---------------------------------------------
    public boolean deleteProduct(String userId, String productId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(userId)) {
                if (users.get(i).getRole().equalsIgnoreCase("Admin")) {
                    for (int j = 0; j < productService.products.size(); j++) {
                        if (productService.products.get(j).getId().equalsIgnoreCase(productId)) {
                            productService.products.remove(j);
                            if (merchantStockService.merchantStocks.get(j).getProductid().equalsIgnoreCase(productId))
                                merchantStockService.merchantStocks.remove(j);
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }
    //-------------------------------------    2 extra endpoint  ---------------------------------------------
    public boolean returnPurchases(String userId, String productId) {
        int price = 0;
        for (int i = 0; i < purchaseService.purchases.size(); i++) {
            if (purchaseService.purchases.get(i).getIdUser().equalsIgnoreCase(userId)) {
                if (purchaseService.purchases.get(i).getIdProduct().equalsIgnoreCase(productId)){
                    price = productService.getPriceOfProduct(productId);
                    for (int q = 0; q < users.size(); q++) {
                        if (users.get(q).getId().equalsIgnoreCase(userId)) {
                            users.get(q).setBalance(users.get(q).getBalance() + price);
                            for (int o = 0; o < merchantStockService.merchantStocks.size(); o++) {
                                if (merchantStockService.merchantStocks.get(o).getProductid().equalsIgnoreCase(productId)) {
                                    merchantStockService.merchantStocks.get(o).setStock(merchantStockService.merchantStocks.get(o).getStock() + 1);
                                    return true;
                                }
                            }

                        }

                    }}}}
        return false;
    }
    //-------------------------------------    3 extra endpoint  ---------------------------------------------
    public ArrayList<Product> allProductsPurchasedByUser(String userId) {
        ArrayList<Product> products1 = new ArrayList<>();
        for (int i = 0; i < purchaseService.purchases.size(); i++) {
            if (purchaseService.purchases.get(i).getIdUser().equalsIgnoreCase(userId)) {
                products1.add(productService.getProduct1(purchaseService.purchases.get(i).getIdProduct()));
            }
        }
        return products1;
    }


    }


