package by.clevertec.Repository.product;

import by.clevertec.Entity.Product;
import by.clevertec.Repository.Dao;
import lombok.RequiredArgsConstructor;
import by.clevertec.Mapping.ProductMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements Dao {
    private final DataSource dataBase;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;


    @Override
    public Object get(String id) throws SQLException {
        try (Connection connection =dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                return product;
            } else {
                return null;
            }
        }
    }

    @Override
    public void save(Object t) {
        try (Connection connection = dataBase.getConnection()) {
            Product product = (Product) t;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO product  (id, name, price) VALUES (?, ?, ?)");
            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object update(Object o) {
        try (Connection connection = dataBase.getConnection()) {
            Product product = (Product) o;
            PreparedStatement statement = connection.prepareStatement("UPDATE entity SET name = ?, price = ? WHERE id = ?");
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List finaAll(Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        List<Product> list = new ArrayList<>();
        try (Connection connection = dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product LIMIT ? OFFSET ?");
            statement.setInt(1, pageSize);
            statement.setInt(2, pageSize * page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                BigDecimal price = resultSet.getBigDecimal(3);
                Product product = new Product(id, name, price);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.stream().map(productMapper::toResponse).collect(Collectors.toList());
    }
}
