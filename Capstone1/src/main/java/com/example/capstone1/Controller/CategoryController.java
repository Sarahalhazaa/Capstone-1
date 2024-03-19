package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponce;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/vi/E-commerce")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getCategory")
    public ResponseEntity getCategory(){
        ArrayList<Category> category = categoryService.getCategory();
        return ResponseEntity.status(200).body(category);

    }

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody @Valid Category category , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponce("Category added"));

    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= categoryService.updateCategory(id,category);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponce("Category updated"));}
        return ResponseEntity.status(400).body(new ApiResponce("Category not updated"));

    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){

        boolean isdeleted= categoryService.deleteCategory(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce("Category deleted"));}
        return ResponseEntity.status(400).body(new ApiResponce("Category not deleted"));

    }

    // --------------------------    END CRUD     --------------------------------

    //-------------------------- 1 extra endpoint -----------------------------

    @GetMapping("/productOfCategory/{name}")
    public ResponseEntity productOfCategory(@PathVariable String name){
        ArrayList<Product> products = categoryService.productOfCategory(name);
        return ResponseEntity.status(200).body(products);

    }

}
