package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.domain.Cat;
import ru.clevertec.exception.CatNotFoundException;
import ru.clevertec.service.CatService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/api/v1/cats")
    public ResponseEntity<List<Cat>> findAll() {
        List<Cat> cats = catService.getCats();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cats);
    }

    @GetMapping("/api/v1/cats/{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable("id") UUID id) {
        try {
            Cat cat = catService.getCatById(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(cat);
        } catch (CatNotFoundException e) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null);
        }
    }

    @PostMapping("/api/v1/cats")
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catService.create(cat);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdCat);
    }

    @PutMapping("/api/v1/cats/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable("id") UUID id, @RequestBody Cat cat) {

        try {
            Cat updatedCat = catService.update(id, cat);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(updatedCat);
        } catch (CatNotFoundException e) {
            return ResponseEntity.internalServerError()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null);
        }
    }

    @DeleteMapping("/api/v1/cats/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable("id") UUID id) {
        catService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}