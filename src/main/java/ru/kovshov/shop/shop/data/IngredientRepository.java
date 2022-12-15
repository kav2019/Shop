package ru.kovshov.shop.shop.data;

import ru.kovshov.shop.shop.models.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
