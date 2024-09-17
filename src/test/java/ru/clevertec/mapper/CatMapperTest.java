package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.domain.Cat;
import ru.clevertec.entity.CatEntity;
import ru.clevertec.utils.TestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatMapperTest {

    private CatMapper catMapper = new CatMapperImpl();

    @Test
    void shouldConvertEntitiesToDomains() {
        List<CatEntity> entities = TestUtils.generateCatEntityList(5);

        List<Cat> actualDomains = catMapper.toDomains(entities);

        assertEquals(actualDomains.size(), entities.size());
    }

    @Test
    void shouldConvertEntitiesToDomains_withEmptyList() {
        List<CatEntity> entities = List.of();

        List<Cat> actualDomains = catMapper.toDomains(entities);

        assertThat(actualDomains).isEmpty();
    }

    @Test
    void shouldConvertEntityToDomains() {
        CatEntity entity = TestUtils.generateDefaultCatEntity();

        Cat actualDomain = catMapper.toDomain(entity);

        assertTrue(TestUtils.compareCatAndCatEntity(actualDomain, entity));
    }

    @Test
    void shouldReturnNull_whenConvertEntityToDomains_withNullableEntity() {
        Cat actualDomain = catMapper.toDomain(null);

        assertThat(actualDomain).isNull();
    }

    @Test
    void shouldConvertDomainToEntity() {
        Cat domain = TestUtils.generateDefaultCat();

        CatEntity actualEntity = catMapper.toEntity(domain);

        //assertThat(actualEntity).usingRecursiveComparison().isEqualTo(domain);
        assertTrue(TestUtils.compareCatAndCatEntity(domain, actualEntity));
    }

    @Test
    void shouldReturnNull_whenConvertDomainToEntity_withNullableDomain() {

        CatEntity actualEntity = catMapper.toEntity(null);

        assertThat(actualEntity).isNull();
    }
}