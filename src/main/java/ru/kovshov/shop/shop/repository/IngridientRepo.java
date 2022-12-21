package ru.kovshov.shop.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovshov.shop.shop.models.Ingredient;

@Repository
public interface IngridientRepo extends JpaRepository<Ingredient, String> {
}
