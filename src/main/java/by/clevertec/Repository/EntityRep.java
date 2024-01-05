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
    private  final  DataSource dataBase;

    private final EntityMapper entityMapper=EntityMapper.INSTANCE;



    @Override
    public Entity get(String id) throws SQLException {
        try (Connection connection = dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entity WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Entity entity = new Entity();
                entity.setId(resultSet.getString("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSurname(resultSet.getString("surname"));
                entity.setLogin(resultSet.getString("login"));
                return entity;
            } else {
                return null;
            }
        }
    }

    @Override
    public void save(Object o) {
        try (Connection connection = dataBase.getConnection()) {
            Entity entity = (Entity) o;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO entity (id, name, surname, login, password) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getLogin());
            statement.setString(5, entity.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM entity WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Entity update(Object o) {
        try (Connection connection = dataBase.getConnection()) {
            Entity entity = (Entity) o;
            PreparedStatement statement = connection.prepareStatement("UPDATE entity SET name = ?, surname = ?, login = ? WHERE id = ?");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getId());
            statement.executeUpdate();
            return entity;
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

        List<Entity> list = new ArrayList<>();
        try (Connection connection = dataBase.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entity LIMIT ? OFFSET ?");
            statement.setInt(1, pageSize);
            statement.setInt(2, pageSize * page);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                Entity entity = new Entity(id, name, surname, login, password);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.stream().map(entityMapper::toResponse).collect(Collectors.toList());
    }

}