package ru.kovshov.shop.shop.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.kovshov.shop.shop.data.OrderRepository;
import ru.kovshov.shop.shop.models.Order;
import ru.kovshov.shop.shop.models.User;
import ru.kovshov.shop.shop.repository.UserRepo;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepo userRepo;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepo userRepo) {
        this.orderRepository = orderRepository;
        this.userRepo = userRepo;
    }


    @GetMapping("/current")
    public String orderForm(){
        return "orderFormOLD";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if(errors.hasErrors()){
            return "orderFormOLD";
        }
        order.setUser(user);
        orderRepository.save(order);
        log.info("Order submitted: " + order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
