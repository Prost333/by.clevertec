package by.clevertec.Entity;


import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Data
@ToString
@FieldNameConstants
public class Product {

    private String id;

    private String name;

    private BigDecimal price;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
