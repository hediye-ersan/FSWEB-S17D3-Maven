package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void  init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public Collection<Koala> getAllKoalas() {
        return koalas.values();
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable Integer id){
        return koalas.get(id);
    }

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
        return koala;
    }
    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id, @RequestBody Koala koala){
        if (koalas.containsKey(id)) {
            koala.setId(id);
            koalas.put(id, koala);
            return koala;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id){
        return koalas.remove(id);
    }


}
