package ru.clevertec.exception;

import java.util.UUID;

public class CatNotFoundException extends RuntimeException{
    private CatNotFoundException(String message) {
        super(message);
    }

    public static CatNotFoundException byCatId(UUID catId) {
        return new CatNotFoundException(
                String.format("Cat not found by id %s", catId)
        );
    }
}
