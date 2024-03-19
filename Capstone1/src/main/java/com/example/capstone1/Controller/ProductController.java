package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/E-commerce")
@RequiredArgsConstructor
public class ProductController {

        private final ProductService productService;

        @GetMapping("/getProduct")
        public ResponseEntity getProduct(){
            ArrayList<Product> products = productService.getProduct();
            return ResponseEntity.status(200).body(products);

        }

        @PostMapping("/addProduct")
        public ResponseEntity addProduct(@RequestBody @Valid Product product , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            productService.addProduct(product);
            return ResponseEntity.status(200).body(new ApiResponce("Product added"));

        }
        @PutMapping("/updateProduct/{id}")
        public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid Product product , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            boolean isUpdated= productService.updateProduct(id, product);
            if(isUpdated){
                return ResponseEntity.status(200).body(new ApiResponce("Product updated"));}
            return ResponseEntity.status(400).body(new ApiResponce("Product not updated"));

        }
        @DeleteMapping("/deleteProduct/{id}")
        public ResponseEntity deleteProduct(@PathVariable String id){

            boolean isdeleted= productService.deleteProduct(id);
            if(isdeleted){
                return ResponseEntity.status(200).body(new ApiResponce("Product deleted"));}
            return ResponseEntity.status(400).body(new ApiResponce("Product not deleted"));

        }

//---------------------------------- end CRUD ------------------------------------


// ---------------------------   1 extra endpoint ---------------------------

    @GetMapping("/getProduct1/{id}")
    public ResponseEntity getProduct1(@PathVariable String id){
       Product product = productService.getProduct1(id);
        return ResponseEntity.status(200).body(product);

    }

 // ---------------------------  2 extra endpoint ---------------------------
    @GetMapping("/getPriceOfProduct/{id}")
    public ResponseEntity getPriceOfProduct(@PathVariable String id){
        int price = productService.getPriceOfProduct(id);
        return ResponseEntity.status(200).body(price);

    }
    // ---------------------------  3 extra endpoint ---------------------------

    @GetMapping("/ProductsInRange/{min}/{max}")
    public ResponseEntity ProductsInRange(@PathVariable int min, @PathVariable int max){
        ArrayList<Product> products = productService.ProductsInRange(min , max);
        return ResponseEntity.status(200).body(products);

    }


}
