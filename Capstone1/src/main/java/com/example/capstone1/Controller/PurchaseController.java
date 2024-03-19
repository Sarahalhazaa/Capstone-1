package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.Purchase;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.ProductService;
import com.example.capstone1.Service.PurchaseService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/E-commerce")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/getPurchase")
    public ResponseEntity getPurchase(){
        ArrayList<Purchase> purchases = purchaseService.getPurchase();
        return ResponseEntity.status(200).body(purchases);

    }

    @PostMapping("/addPurchase")
    public ResponseEntity addPurchase(@RequestBody @Valid  Purchase purchase , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        purchaseService.addPurchase(purchase);
        return ResponseEntity.status(200).body(new ApiResponce("Purchase added"));

    }
    @PutMapping("/updatePurchase/{id}")
    public ResponseEntity updatePurchase(@PathVariable String id, @RequestBody @Valid  Purchase purchase , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= purchaseService.updatePurchase(id,purchase);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponce("Purchase updated"));}
        return ResponseEntity.status(400).body(new ApiResponce("Purchase not updated"));

    }
    @DeleteMapping("/ deletePurchase /{id}")
    public ResponseEntity deletePurchase(@PathVariable String id){

        boolean isdeleted= purchaseService.deletePurchase(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce(" Purchase deleted"));}
        return ResponseEntity.status(400).body(new ApiResponce("Purchase not deleted"));

    }

    // -------------------------- END CRUD --------------------------------






}
