package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Purchase {
    @NotEmpty
    private String idProduct;
    @NotEmpty
    private String idUser;

}
