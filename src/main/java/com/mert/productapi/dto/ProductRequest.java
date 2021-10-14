package com.mert.productapi.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest implements Serializable {

    @NotNull(message = "Fiyat boş bırakılamaz.")
    BigDecimal price;

    @NotBlank(message = "Ürün adı boş bırakılamaz.")
    String productName;

    @NotBlank(message = "Ürün açıklaması boş bırakılamaz")
    String productDescription;

}
