package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        this.merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {

        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                this.merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id) {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                this.merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    // ------------------------------  end CRUD -----------------------------------

    //-----------------------   endpoint 11   -------------------------------------
    public boolean addStocksOfProduct(String id, int stock) {

        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getProductid().equalsIgnoreCase(id)) {
                this.merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
                return true;
            }
        }
        return false;
    }

//---------------------------  extra endpoint 1  -------------------------------
    public boolean searchIdProduct(String id) {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getProductid().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    //---------------------------  extra endpoint 2  -------------------------------
    public boolean searchIdMerchant(String id) {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getMerchantid().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    //---------------------------  extra endpoint 3  -------------------------------
    public boolean reduceStock(String id) {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getProductid().equalsIgnoreCase(id)) {
                if (merchantStocks.get(i).getStock() > 0) {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - 1);
                    return true;
                }
            }

        }
        return false;
    }

    public int getStock(String id) {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getProductid().equalsIgnoreCase(id)) {
                    return merchantStocks.get(i).getStock();
                }
            }

        return 0;
    }
}