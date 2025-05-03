package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.repository.BurgerRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final BurgerRepository burgerRepository;

    public BurgerDaoImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public Burger save(Burger burger) {
        return burgerRepository.save(burger);
    }

    @Override
    public Burger findById(Long id) {
        return burgerRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    @Override
    public List<Burger> findAll() {
        return burgerRepository.findAll();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        return burgerRepository.findAll()
                .stream()
                .filter(burger -> burger.getPrice() > price)
                .sorted(Comparator.comparing(Burger::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Burger> findByBreadType(String breadType) {
        return burgerRepository.findAll()
                .stream()
                .filter(b -> b.getBreadType().name().equalsIgnoreCase(breadType))
                .sorted(Comparator.comparing(Burger::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Burger> findByContent(String content) {
        return burgerRepository.findAll()
                .stream()
                .filter(burger -> burger.getContents() != null && burger.getContents().contains(content))
                .collect(Collectors.toList());
    }

    @Override
    public Burger update(Burger burger) {
        return burgerRepository.save(burger);
    }

    @Override
    public Burger remove(Long id) {
        Burger burger = findById(id);
        if (burger != null) {
            burgerRepository.delete(burger);
        }
        return burger;
    }
}
