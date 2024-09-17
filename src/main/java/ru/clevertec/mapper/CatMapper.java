package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.Cat;
import ru.clevertec.entity.CatEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {

    List<Cat> toDomains(List<CatEntity> cats);

    Cat toDomain(CatEntity cats);

    CatEntity toEntity(Cat cat);
}
