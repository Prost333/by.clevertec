package by.clevertec.Dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Data
@Getter
@Setter
@ToString
@FieldNameConstants
public class ProductDto {

    private  String id;
    @NotNull

    private  String name;
    @NotNull

    private BigDecimal price;
}
