package ru.clevertec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.clevertec.common.CatBreed;
import ru.clevertec.common.Gender;

import java.util.UUID;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CatEntity {

    private UUID id;
    private String name;
    private CatBreed catBreed;
    private Gender gender;
}
