package by.clevertec.Repository;

import by.clevertec.Cache.Cache;
import by.clevertec.Cache.LFUCache;
import by.clevertec.Cache.LRUCache;
import by.clevertec.Entity.Entity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public class CachingEntityRepProxy implements Dao {
    private final EntityRep entityRep;
    private final Cache<String, Entity> cache;

    public CachingEntityRepProxy(EntityRep entityRep, int cacheCapacity, String algorithm) {
        this.entityRep = entityRep;

        if (algorithm.equals("LRU")){
            this.cache = new LRUCache<>(cacheCapacity);
        }else {
            this.cache=new LFUCache<>(cacheCapacity);
        }

    }

    @Override
    public Object get(String id) throws SQLException {
        Entity cachedEntity = cache.get(id);
        if (cachedEntity != null) {
            return cachedEntity;
        }
        Entity entity = entityRep.get(id);
        if (entity != null) {
            cache.put(id, entity);
        }
        return entity;
    }

    @Override
    public void save(Object o) {
        Entity entity = (Entity) o;
        entityRep.save(entity);
        cache.put(entity.getId(), entity);
    }

    @Override
    public void delete(String id) {
        entityRep.delete(id);
        cache.delete(id);
    }

    @Override
    public Object update(Object o) {
        Entity entity = (Entity) o;
        Entity updatedEntity = entityRep.update(entity);
        cache.put(entity.getId(), updatedEntity);
        return updatedEntity;
    }

    @Override
    public List<Object> finaAll(Integer page, Integer pageSize) {
        cache.findAll();
        return entityRep.finaAll(page,pageSize);
    }




}
