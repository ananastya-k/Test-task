package ru.clevertec.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.common.CatBreed;
import ru.clevertec.common.Gender;
import ru.clevertec.domain.Cat;
import ru.clevertec.entity.CatEntity;
import ru.clevertec.exception.CatNotFoundException;
import ru.clevertec.mapper.CatMapper;
import ru.clevertec.mapper.CatMapperImpl;
import ru.clevertec.repositiry.CatRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper;

    public List<Cat> getCats() {
        List<CatEntity> cats = catRepository.getCats();

        return catMapper.toDomains(cats);
    }

    public Cat getCatById(UUID catId) {
        return catRepository.getCatById(catId)
                .map(catMapper::toDomain)
                .orElseThrow(() -> CatNotFoundException.byCatId(catId));

    }

    public Cat create(@NonNull Cat cat) {
        CatEntity catEntity = catMapper.toEntity(cat);
        CatEntity createdEntity = catRepository.create(catEntity);

        return catMapper.toDomain(createdEntity);
    }
    public Cat update(UUID catID, @NonNull Cat newCat){
        CatEntity newCatEntity = catMapper.toEntity(newCat);
        CatEntity updatedEntity = catRepository.update(catID, newCatEntity)
                .orElseThrow(() -> CatNotFoundException.byCatId(catID));

        return catMapper.toDomain(updatedEntity);
    }

    public void delete(UUID catId) {
       catRepository.delete(catId);
    }
}
