package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/E-commerce")
@RequiredArgsConstructor
public class MerchantController {

        private final MerchantService merchantService;

        @GetMapping("/getMerchant")
        public ResponseEntity getMerchant(){
            ArrayList<Merchant> merchants = merchantService.getMerchant();
            return ResponseEntity.status(200).body(merchants);

        }

        @PostMapping("/addMerchant")
        public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            merchantService.addMerchant(merchant);
            return ResponseEntity.status(200).body(new ApiResponce("Merchant added"));

        }
        @PutMapping("/updateMerchant/{id}")
        public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            boolean isUpdated= merchantService.updateMerchant(id,merchant);

            if(isUpdated){
                return ResponseEntity.status(200).body(new ApiResponce("Merchant updated"));}
            return ResponseEntity.status(400).body(new ApiResponce("Merchant not updated"));

        }
        @DeleteMapping("/deleteMerchant/{id}")
        public ResponseEntity deleteMerchant(@PathVariable String id){

            boolean isdeleted= merchantService.deleteMerchant(id);
            if(isdeleted){
                return ResponseEntity.status(200).body(new ApiResponce("Merchant deleted"));}
            return ResponseEntity.status(400).body(new ApiResponce("Merchant not deleted"));

        }
    // -------------------------- END CRUD --------------------------------
    }
