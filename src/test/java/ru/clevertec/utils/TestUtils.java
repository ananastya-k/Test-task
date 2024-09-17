package ru.clevertec.utils;

import ru.clevertec.common.CatBreed;
import ru.clevertec.common.Gender;
import ru.clevertec.domain.Cat;
import ru.clevertec.entity.CatEntity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestUtils {

    private static final String DEFAULT_NAME = "Noname";
    private static final Gender DEFAULT_GENDER = Gender.UNKNOWN;
    private static final CatBreed DEFAULT_BREED = CatBreed.CAT_FROM_GETTO;



    public static CatEntity generateDefaultCatEntity() {
        return new CatEntity(UUID.randomUUID(), DEFAULT_NAME, DEFAULT_BREED, DEFAULT_GENDER);
    }
    public static Cat generateDefaultCat() {
        return new Cat(UUID.randomUUID(), DEFAULT_NAME, DEFAULT_BREED, DEFAULT_GENDER);
    }

    public static boolean compareCatAndCatEntity(Cat cat, CatEntity catEntity) {
        return (cat != null && catEntity != null)
                && cat.getId().equals(catEntity.getId())
                && cat.getName().equals(catEntity.getName())
                && cat.getCatBreed().equals(catEntity.getCatBreed())
                && cat.getGender().equals(catEntity.getGender());
    }

    public static List<CatEntity> generateCatEntityList(int size) {

        return (new Random()).ints(size)
                .mapToObj(i -> generateDefaultCatEntity())
                .collect(Collectors.toList());
    }

    public static List<Cat> generateCatsList(int size) {

        return (new Random()).ints(size)
                .mapToObj(i -> generateDefaultCat())
                .collect(Collectors.toList());
    }

}
