package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/E-commerce")
@RequiredArgsConstructor
public class MerchantStockController {

        private final MerchantStockService merchantStockService;

        @GetMapping("/getMerchantStock")
        public ResponseEntity getMerchantStock(){
            ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStock();
            return ResponseEntity.status(200).body(merchantStocks);

        }

        @PostMapping("/addMerchantStock")
        public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            merchantStockService.addMerchantStock(merchantStock);
            return ResponseEntity.status(200).body(new ApiResponce("MerchantStock added"));

        }
        @PutMapping("/updateMerchantStock/{id}")
        public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            boolean isUpdated= merchantStockService.updateMerchantStock(id , merchantStock);
            if(isUpdated){
                return ResponseEntity.status(200).body(new ApiResponce("MerchantStock updated"));}
            return ResponseEntity.status(400).body(new ApiResponce("MerchantStock not updated"));

        }
        @DeleteMapping("/deleteMerchantStock/{id}")
        public ResponseEntity deleteCategory(@PathVariable String id){

            boolean isdeleted= merchantStockService.deleteMerchantStock(id);
            if(isdeleted){
                return ResponseEntity.status(200).body(new ApiResponce("MerchantStock deleted"));}
            return ResponseEntity.status(400).body(new ApiResponce("MerchantStock not deleted"));

        }
//--------------------------------  END CRUD ---------------------------------

//--------------------------- endpoint 11  ---------------------------------

    @PutMapping("/addStocksOfProduct/{id}/{stock}")
    public ResponseEntity addStocksOfProduct(@PathVariable String id,@PathVariable int stock){

        boolean isAdded= merchantStockService.addStocksOfProduct(id ,stock);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponce("Stocks Of Product added"));}
        return ResponseEntity.status(400).body(new ApiResponce("Stocks Of Product not added"));

    }


    }
