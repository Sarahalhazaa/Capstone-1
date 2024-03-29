package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();
    public ArrayList<Merchant> getMerchant() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        this.merchants.add(merchant);
    }

    public boolean updateMerchant(String id, Merchant merchant) {

        for (int i = 0; i < this.merchants.size(); i++) {
            if (this.merchants.get(i).getId().equalsIgnoreCase(id)) {
                this.merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        for (int i = 0; i < this.merchants.size(); i++) {
            if (this.merchants.get(i).getId().equalsIgnoreCase(id)) {
                this.merchants.remove(i);
                return true;
            }
        }
        return false;
    }
    //----------------------------------  end CRUD  --------------------------------------

     public Merchant getMerchant1(String id) {
        for (int u = 0; u < merchants.size(); u++) {
            if (merchants.get(u).getId().equalsIgnoreCase(id)) {
                return merchants.get(u);
            }
        }
        return null;
    }
}
