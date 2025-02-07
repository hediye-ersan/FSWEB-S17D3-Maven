package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public Collection<Kangaroo> getAllKangaroos() {
        return kangaroos.values();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangaroosById(@PathVariable Integer id){
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with ID " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo addKangaroos(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null || kangaroo.getName() == null || kangaroo.getName().isBlank()) {
            throw new ZooException("Invalid Kangaroo: ID and Name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }


    @PutMapping("/{id}")
    public Kangaroo updateKangaroos(@PathVariable Integer id, @RequestBody Kangaroo kangaroo){
        if(kangaroos.containsKey(id)){
            kangaroo.setId(id);
            kangaroos.put(id, kangaroo);
            return kangaroo;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id){
        return kangaroos.remove(id);
    }

}
