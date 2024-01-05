package by.clevertec.Service;

import by.clevertec.Entity.Entity;
import by.clevertec.Mapping.EntityMapper;
import by.clevertec.Dto.EntityDto;
import by.clevertec.Repository.CachingEntityRepProxy;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
@Service
public class EntityServiceImp implements EntityService {
    public final CachingEntityRepProxy dao;
    public EntityMapper entityMapper = EntityMapper.INSTANCE;

    public EntityServiceImp(CachingEntityRepProxy dao) {
        this.dao = dao;
    }


    @Override
    public EntityDto getEntityById(String id) throws SQLException {
        EntityDto dto = entityMapper.toResponse((Entity) dao.get(id));
        return dto;
    }

    @Override
    public EntityDto addEntity(EntityDto entityDto) {
        dao.save(entityMapper.toRequest(entityDto));
        return entityDto;
    }

    @Override
    public void deleteEntityById(String id) {
        dao.delete(id);
    }

    @Override
    public EntityDto updateEntity(EntityDto entityDto) {
        return null;
    }

    public List findAll(Integer page, Integer pageSize){
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        return dao.finaAll(page, pageSize);
    }
}
