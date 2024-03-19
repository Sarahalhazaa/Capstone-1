package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class PurchaseService {

    ArrayList<Purchase> purchases = new ArrayList<>();

    public ArrayList<Purchase> getPurchase() {
        return purchases;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public boolean updatePurchase(String id, Purchase purchase) {

        for (int i = 0; i < this.purchases.size(); i++) {
            if (this.purchases.get(i).getIdProduct().equalsIgnoreCase(id)) {
                this.purchases.set(i, purchase);
                return true;
            }
        }
        return false;
    }

    public boolean deletePurchase(String id) {
        for (int i = 0; i < this.purchases.size(); i++) {
            if (this.purchases.get(i).getIdProduct().equalsIgnoreCase(id)) {
                this.purchases.remove(i);
                return true;
            }
        }
        return false;
    }

    // ------------------------------  end CRUD -----------------------------------

}
