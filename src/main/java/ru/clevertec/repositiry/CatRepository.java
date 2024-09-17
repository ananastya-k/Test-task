package ru.clevertec.repositiry;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ru.clevertec.common.CatBreed;
import ru.clevertec.common.Gender;
import ru.clevertec.entity.CatEntity;
import ru.clevertec.exception.CatNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CatRepository {

    private static List<CatEntity> db = new ArrayList<>(List.of(
            new CatEntity(UUID.randomUUID(), "Arc", CatBreed.SIAMESE, Gender.MALE),
            new CatEntity(UUID.randomUUID(), "Kris", CatBreed.BENGAL, Gender.FEMALE),
            new CatEntity(UUID.randomUUID(), "Volodya", CatBreed.ANGORA, Gender.MALE)

    ));

    public List<CatEntity> getCats() {
        return db;
    }

    public Optional<CatEntity> getCatById(UUID catId) {
        return db.stream()
                .filter(cat -> cat.getId().equals(catId))
                .findFirst();
    }

    public CatEntity create(@NonNull CatEntity catEntity) {
        delete(catEntity.getId());
        db.add(catEntity);
        return catEntity;
    }
    public Optional<CatEntity> update(UUID catID, @NonNull CatEntity newCatEntity){
        return getCatById(catID)
                .map(cat -> {
                    cat.setName(newCatEntity.getName());
                    cat.setCatBreed(newCatEntity.getCatBreed());
                    cat.setGender(newCatEntity.getGender());
                    return cat;
                });
    }

    public void delete(UUID catId) {
        db.removeIf(cat -> cat.getId().equals(catId));
    }
}
