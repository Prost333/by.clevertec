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

}
