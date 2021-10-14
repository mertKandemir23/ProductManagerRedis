package com.mert.productapi.dto;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
@Builder

@EqualsAndHashCode
public class ProductDto  implements Serializable{


    private Long id;

    private String productName;

    private String productDescription;

    private BigDecimal price;

    private LocalDateTime updateAt;

    private LocalDateTime createdAt;


}
