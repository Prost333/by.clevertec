package by.clevertec.Repository.product;

import by.clevertec.Cache.LFUCache;
import by.clevertec.Entity.Product;
import by.clevertec.Repository.Dao;
import by.clevertec.Cache.Cache;
import by.clevertec.Cache.LRUCache;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public class CachingProductRepository implements Dao {

    private final ProductRepository productRepository;
    private final Cache<String, Product> cache;

    public CachingProductRepository(ProductRepository productRepository, int cacheCapacity, String algorithm) {
        this.productRepository = productRepository;
        if (algorithm.equals("LRU")) {
            this.cache = new LRUCache<>(cacheCapacity);
        } else {
            this.cache = new LFUCache<>(cacheCapacity);
        }
    }

    @Override
    public Object get(String id) throws SQLException {
        Product cachedProduct = cache.get(id);
        if (cachedProduct != null) {
            return cachedProduct;
        }
        Product product = (Product) productRepository.get(id);
        if (product != null) {
            cache.put(id, product);
        }
        return product;
    }

    @Override
    public void save(Object t) {
        Product product = (Product) t;
        productRepository.save(product);
        cache.put(product.getId(), product);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(id);
        cache.delete(id);
    }

    @Override
    public Object update(Object o) {
        Product product = (Product) o;
        Product updated = (Product) productRepository.update(product);
        cache.put(product.getId(), updated);
        return updated;
    }

    @Override
    public List finaAll(Integer page, Integer pageSize) {
        cache.findAll();
        return productRepository.finaAll(page,pageSize);
    }
}
