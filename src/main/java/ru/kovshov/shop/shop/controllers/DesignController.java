package ru.kovshov.shop.shop.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.kovshov.shop.shop.data.BurgerRepsitory;
import ru.kovshov.shop.shop.data.IngredientRepository;
import ru.kovshov.shop.shop.models.Burger;
import ru.kovshov.shop.shop.models.Ingredient;
import ru.kovshov.shop.shop.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {

    private final IngredientRepository ingredientRepo;
    private final BurgerRepsitory burgerRepsitory;

    @Autowired
    public DesignController(IngredientRepository ingredientRepository, BurgerRepsitory burgerRepsitory) {
        this.ingredientRepo = ingredientRepository;
        this.burgerRepsitory = burgerRepsitory;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "burger")
    public Burger burger(){
        return new Burger();
    }


    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Burger design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Processing design: " + design);
        Burger seved = burgerRepsitory.save(design);
        order.addDesign(seved);
        return "redirect:/orders/current";
    }

    public List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType() == type)
                .collect(Collectors.toList());
    }


}
