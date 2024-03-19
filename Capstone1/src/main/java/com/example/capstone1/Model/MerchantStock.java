package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty
    private String id ;
    @NotEmpty
    private String productid ;
    @NotNull
    @Min(10)
    private int stock=10 ;
    @NotEmpty
    private String merchantid ;
}
