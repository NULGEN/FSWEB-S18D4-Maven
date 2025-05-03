package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/burgers")
@RequiredArgsConstructor
@Slf4j
public class BurgerController {
    private final BurgerDao burgerDao;

    @GetMapping
    public List<Burger> getAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getById(@PathVariable long id) {
        return burgerDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Burger create(@RequestBody Burger burger) {
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }

    @PutMapping("/{id}")
    public Burger update(@PathVariable int id, @RequestBody Burger burger) {
        burger.setId(id);
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        burgerDao.remove(id);
    }

    @GetMapping("/findByPrice")
    public List<Burger> findByPrice(@RequestParam double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadType(@RequestParam String breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestParam String content) {
        return burgerDao.findByContent(content);
    }

}
