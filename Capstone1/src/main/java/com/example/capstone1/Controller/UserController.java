package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
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
public class UserController {

        private final UserService userService;

        @GetMapping("/getUser")
        public ResponseEntity getUser(){
            ArrayList<User> user = userService.getUser();
            return ResponseEntity.status(200).body(user);

        }

        @PostMapping("/addUser")
        public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponce("User added"));

        }
        @PutMapping("/updateUser/{id}")
        public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user , Errors errors){

            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            boolean isUpdated= userService.updateUser(id,user);
            if(isUpdated){
                return ResponseEntity.status(200).body(new ApiResponce("User updated"));}
            return ResponseEntity.status(400).body(new ApiResponce("User not updated"));

        }
        @DeleteMapping("/deleteUser/{id}")
        public ResponseEntity deleteUser(@PathVariable String id){

            boolean isdeleted= userService.deleteUser(id);
            if(isdeleted){
                return ResponseEntity.status(200).body(new ApiResponce("User deleted"));}
            return ResponseEntity.status(400).body(new ApiResponce("User not deleted"));

        }

    // -------------------------- END CRUD --------------------------------

    //-----------------------   endpoint 12   -------------------------------

    @PutMapping("/buyProduct/{id}/{Id}/{ID}")
    public ResponseEntity buyProduct(@PathVariable String id,@PathVariable String Id,@PathVariable String ID){

        String isUpdated= userService.buyProduct(id,Id,ID);
        if(isUpdated.equalsIgnoreCase("Products purchased")){
            return ResponseEntity.status(200).body(new ApiResponce("Products purchased"));}
        else if (isUpdated.equalsIgnoreCase("bad request")) {
            ResponseEntity.status(400).body(new ApiResponce("bad request"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Products NOT purchase"));

    }
    // ---------------------------   1 extra endpoint ---------------------------

    @DeleteMapping("/deleteProduct/{id}/{Id}")
    public ResponseEntity deleteProduct(@PathVariable String id,@PathVariable String Id){

        boolean isdeleted= userService.deleteProduct(id,Id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce("Product deleted"));}
        return ResponseEntity.status(400).body(new ApiResponce("Product not deleted"));

    }
    // ---------------------------   2 extra endpoint ---------------------------
    @PutMapping("/returnPurchases/{id}/{Id}")
    public ResponseEntity returnPurchases(@PathVariable String id,@PathVariable String Id){

        boolean isUpdated= userService.returnPurchases(id,Id);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponce("Return Purchases"));}
        return ResponseEntity.status(400).body(new ApiResponce("Return not Purchases"));

    }
    // ---------------------------   3 extra endpoint ---------------------------
    @GetMapping("/all-Purchased/{id}")
    public ResponseEntity allProductsPurchasedByUser(@PathVariable String id){
        ArrayList<Product> products = userService.allProductsPurchasedByUser(id);
        return ResponseEntity.status(200).body(products);

    }





    }
