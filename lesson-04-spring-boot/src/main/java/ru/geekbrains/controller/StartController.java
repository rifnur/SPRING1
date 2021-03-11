package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    //если запрос к корню, то пересылаем на страницу welcome
    @GetMapping("")
    public String rootPage(Model model) {
        return "redirect:start";
    }

    @GetMapping("/start")
    public String start(Model model) {

        model.addAttribute("activePage", "IndexPage");
        model.addAttribute("helloText",
                "Не сайт а мечта!");
        return "start";
    }

}
