package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty
   private String id ;
   @NotEmpty
   @Size(min = 4)
    private String name ;
    @NotNull
    @Positive
    private int price ;
    @NotEmpty
    private String categoryID ;
}
