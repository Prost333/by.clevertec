package by.clevertec.Service;

import by.clevertec.Entity.Product;
import by.clevertec.Repository.product.CachingProductRepository;
import by.clevertec.Dto.ProductDto;
import by.clevertec.Mapping.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private CachingProductRepository dao;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDto getById(String id) throws SQLException {
        return productMapper.toResponse((Product) dao.get(id));
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        dao.save(productMapper.toRequest(productDto));
        return productDto;
    }

    @Override
    public void deleteById(String id) {
        dao.delete(id);

    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public List<ProductDto> finaAll(Integer page, Integer pageSize) {
        return dao.finaAll(page, pageSize);
    }


}
