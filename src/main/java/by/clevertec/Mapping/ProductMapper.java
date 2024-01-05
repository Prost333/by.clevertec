package by.clevertec.Mapping;

import by.clevertec.Dto.ProductDto;
import by.clevertec.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    @Mappings({
//            @Mapping(target = "name", source = "product.name"),
//            @Mapping(target = "id", source = "product.id")
//
//    })
    ProductDto toResponse(Product product);

//    @Mappings({
//            @Mapping(target = "name", source = "productDto.name"),
//            @Mapping(target = "id", source = "productDto.id")
//
//    })
    Product toRequest(ProductDto productDto);
}
