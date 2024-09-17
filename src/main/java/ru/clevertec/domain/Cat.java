package ru.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.common.CatBreed;
import ru.clevertec.common.Gender;

import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    private UUID id;
    private String name;
    private CatBreed catBreed;
    private Gender gender;
}
