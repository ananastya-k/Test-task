package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.domain.Cat;
import ru.clevertec.entity.CatEntity;
import ru.clevertec.exception.CatNotFoundException;
import ru.clevertec.mapper.CatMapper;
import ru.clevertec.repositiry.CatRepository;
import ru.clevertec.utils.TestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {


    @Mock
    private CatRepository catRepository;
    @Mock
    private CatMapper catMapper;

    @InjectMocks
    private CatService catService;

    @Test
    void shouldGetCats() {
        //given
        List<CatEntity> catEntities  = TestUtils.generateCatEntityList(5);
        List<Cat> cats = TestUtils.generateCatsList(5);

        when(catRepository.getCats())
                .thenReturn(catEntities);
        when(catMapper.toDomains(catEntities))
                .thenReturn(cats);

        //when
        List<Cat> actualCats = catService.getCats();

        //then
        assertFalse(actualCats.isEmpty());
    }

    @Test
    void shouldGetCatById() {
        //given
        CatEntity catEntity = TestUtils.generateDefaultCatEntity();
        Cat cat = TestUtils.generateDefaultCat();

        UUID catId = cat.getId();

        when(catRepository.getCatById(catId))
                .thenReturn(Optional.of(catEntity));
        when(catMapper.toDomain(catEntity))
                .thenReturn(cat);

        //when
        Cat actualCat = catService.getCatById(catId);

        //then
        assertEquals(cat.getId(), actualCat.getId());
    }

    @Test
    void shouldThrowCatNotFoundException_whenCatNotFoundById() {
        //given
        UUID catId = UUID.randomUUID();

        when(catRepository.getCatById(catId))
                .thenReturn(Optional.empty());

        //when, then
        assertThrows(
                CatNotFoundException.class,
                () -> catService.getCatById(catId)
        );
    }

    @Test
    void create() {
        //given
        CatEntity catEntity = TestUtils.generateDefaultCatEntity();
        Cat cat = TestUtils.generateDefaultCat();

        when(catMapper.toEntity(cat))
                .thenReturn(catEntity);
        when(catRepository.create(catEntity))
                .thenReturn(catEntity);
        when(catMapper.toDomain(catEntity))
                .thenReturn(cat);

        //when
        Cat actualCat = catService.create(cat);

        //then
        assertEquals(cat.getId(),actualCat.getId());
    }
    @Test
    void shouldUpdateCat() {
        //given
        CatEntity catEntity = TestUtils.generateDefaultCatEntity();
        Cat cat = TestUtils.generateDefaultCat();
        UUID uuid = cat.getId();

        when(catMapper.toEntity(cat))
                .thenReturn(catEntity);
        when(catRepository.update(uuid, catEntity))
                .thenReturn(Optional.of(catEntity));
        when(catMapper.toDomain(catEntity))
                .thenReturn(cat);

        //when
        Cat actualCat = catService.update(uuid, cat);

        //then
        assertEquals(cat.getId(), actualCat.getId());
    }

    @Test
    void  shouldThrowCatNotFoundException_whenCanNotUpdateCatById() {
        //given
        UUID uuid = UUID.randomUUID();
        CatEntity catEntity = TestUtils.generateDefaultCatEntity();
        Cat cat = TestUtils.generateDefaultCat();

        when(catMapper.toEntity(cat))
                .thenReturn(catEntity);
        when(catRepository.update(uuid, catEntity))
                .thenReturn(Optional.empty());

        //when, then
        assertThrows(
                CatNotFoundException.class,
                () -> catService.update(uuid, cat)
        );
    }

    @Test
    void shouldDeleteById() {
        //given
        UUID uuid = UUID.randomUUID();

        //when
        catService.delete(uuid);

        //then
        verify(catRepository).delete(uuid);
    }
}