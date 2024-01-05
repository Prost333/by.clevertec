package by.clevertec.Service;

import by.clevertec.Dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    ProductDto getById(String id) throws SQLException;
    ProductDto add(ProductDto productDto);
    void deleteById(String id) throws SQLException;
    ProductDto update(ProductDto productDto);
    List<ProductDto> finaAll(Integer page, Integer pageSize);
}
