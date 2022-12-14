package ru.kovshov.shop.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
