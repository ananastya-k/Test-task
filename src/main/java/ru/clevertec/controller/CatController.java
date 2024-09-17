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
@RequestMapping("/api/v1/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping
    public ResponseEntity<List<Cat>> findAll() {
        List<Cat> cats = catService.getCats();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cats);
    }

    @GetMapping("/{id}")
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

    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        Cat createdCat = catService.create(cat);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdCat);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable("id") UUID id) {
        catService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}