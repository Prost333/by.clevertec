package by.clevertec.Service;

import by.clevertec.Dto.EntityDto;

import java.sql.SQLException;

public interface EntityService {
    EntityDto getEntityById(String id) throws SQLException;
    EntityDto addEntity(EntityDto entityDto);
    void deleteEntityById(String id) throws SQLException;
    EntityDto updateEntity(EntityDto entityDto);
}
