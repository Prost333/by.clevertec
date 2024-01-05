package by.clevertec.Mapping;

import by.clevertec.Entity.Entity;
import by.clevertec.Dto.EntityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    EntityDto toResponse(Entity entity);

    Entity toRequest(EntityDto entityDto);
}
