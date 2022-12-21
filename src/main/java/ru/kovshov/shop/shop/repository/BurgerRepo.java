package ru.kovshov.shop.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovshov.shop.shop.models.Burger;

@Repository
public interface BurgerRepo extends JpaRepository<Burger, Long> {
}
