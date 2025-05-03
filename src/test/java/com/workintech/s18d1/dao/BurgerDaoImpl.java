package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.repository.BurgerRepository;
import com.workintech.s18d1.exceptions.BurgerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BurgerDaoImpl implements BurgerDao {

    private final BurgerRepository burgerRepository;

    @Autowired
    public BurgerDaoImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public Burger save(Burger burger) {
        return burgerRepository.save(burger);
    }

    @Override
    public Burger findById(Long id) {
        Optional<Burger> burgerOpt = burgerRepository.findById(id);
        if (burgerOpt.isPresent()) {
            return burgerOpt.get();
        }
        throw new BurgerException("Burger not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Burger> findAll() {
        return burgerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Burger::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Burger> findByPrice(double price) {
        return burgerRepository.findAll()
                .stream()
                .filter(b -> b.getPrice() == price)
                .sorted(Comparator.comparing(Burger::getName))
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
                .filter(b -> b.getContents() != null && b.getContents().toLowerCase().contains(content.toLowerCase()))
                .sorted(Comparator.comparing(Burger::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Burger update(Burger burger) {
        return burgerRepository.save(burger);
    }

    @Override
    public Burger remove(Long id) {
        Burger found = findById(id);
        burgerRepository.delete(found);
        return found;
    }
}
