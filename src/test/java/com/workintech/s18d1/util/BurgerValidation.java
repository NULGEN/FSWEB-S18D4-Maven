package com.workintech.s18d1.util;


import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {

    public static void validateBurger(Burger burger) {
        if (burger.getName() == null || burger.getName().isBlank()) {
            throw new BurgerException("Burger name cannot be null or blank", HttpStatus.BAD_REQUEST);
        }
        if (burger.getPrice() <= 0) {
            throw new BurgerException("Burger price must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if (burger.getBreadType() == null) {
            throw new BurgerException("Bread type must be specified", HttpStatus.BAD_REQUEST);
        }
        if (burger.getContents() == null || burger.getContents().isBlank()) {
            throw new BurgerException("Contents cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkName(String name) {
    }
}