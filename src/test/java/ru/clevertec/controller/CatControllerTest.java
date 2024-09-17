package ru.clevertec.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.domain.Cat;
import ru.clevertec.service.CatService;
import ru.clevertec.utils.TestUtils;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
public class CatControllerTest {

    @MockBean
    private CatService catService;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void shouldFindAll() throws Exception{
        //given
        int catListSize = 5;
        when(catService.getCats())
                .thenReturn(TestUtils.generateCatsList(catListSize));

        //when, then
        mockMvc.perform(get("/api/v1/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(catListSize));
    }


    @Test
    void shouldGetCatById() throws Exception {
        //given
        Cat cat  = TestUtils.generateDefaultCat();
        UUID uuid = cat.getId();
        when(catService.getCatById(uuid))
                .thenReturn(cat);

        //when, then
        mockMvc.perform(get("/api/v1/cats/{id}",uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cat.getId().toString()))
                .andExpect(jsonPath("$.name").value(cat.getName()))
                .andExpect(jsonPath("$.catBreed").value(cat.getCatBreed().toString()))
                .andExpect(jsonPath("$.gender").value(cat.getGender().toString()));

    }


    @Test
    void shouldCreateCat() throws Exception {
        //given
        Cat cat  = TestUtils.generateDefaultCat();
        when(catService.create(cat))
                .thenReturn(cat);

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(cat);

        // when, then
        mockMvc.perform(post("/api/v1/cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(catJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cat.getId().toString()))
                .andExpect(jsonPath("$.name").value(cat.getName()))
                .andExpect(jsonPath("$.catBreed").value(cat.getCatBreed().toString()))
                .andExpect(jsonPath("$.gender").value(cat.getGender().toString()));

    }

    @Test
    void shouldUpdateCat() throws Exception {
        //given
        Cat cat  = TestUtils.generateDefaultCat();
        UUID uuid = cat.getId();
        when(catService.update(uuid, cat))
                .thenReturn(cat);

        ObjectMapper objectMapper = new ObjectMapper();
        String catJson = objectMapper.writeValueAsString(cat);

        // when, then
        mockMvc.perform(put("/api/v1/cats/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(catJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cat.getId().toString()))
                .andExpect(jsonPath("$.name").value(cat.getName()))
                .andExpect(jsonPath("$.catBreed").value(cat.getCatBreed().toString()))
                .andExpect(jsonPath("$.gender").value(cat.getGender().toString()));

    }

}