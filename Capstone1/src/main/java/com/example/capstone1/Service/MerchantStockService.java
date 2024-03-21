package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
     private final ProductService productService;
    private final MerchantService merchantService;
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock() {
        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (productService.getProduct1(merchantStocks.get(i).getProductid())== null || merchantService.getMerchant1(merchantStocks.get(i).getMerchantid()) == null) {
                   merchantStocks.remove(i);
                } }
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
if (productService.getProduct1(merchantStock.getProductid())!= null)
    if (merchantService.getMerchant1(merchantStock.getMerchantid())!=null)
        this.merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {

        for (int i = 0; i < this.merchantStocks.size(); i++) {
            if (this.merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                 if (productService.getProduct1(merchantStock.getProductid())!= null && merchantService.getMerchant1(merchantStock.getMerchantid()) != null) {
                this.merchantStocks.set(i, merchantStock);
                return true;}
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
