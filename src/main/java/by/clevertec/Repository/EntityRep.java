package by.clevertec.Repository;

import by.clevertec.Entity.Entity;
import by.clevertec.Mapping.EntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
@AllArgsConstructor
public class EntityRep implements Dao {

}