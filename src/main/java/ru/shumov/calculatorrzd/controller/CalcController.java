package ru.shumov.calculatorrzd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shumov.calculatorrzd.exceptions.DivideByZeroException;
import ru.shumov.calculatorrzd.exceptions.UnexpectedCharError;
import ru.shumov.calculatorrzd.service.CalcService;

@Controller
public class CalcController {

    private final CalcService calcService;

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/")
    public String getPage(Model model) {
        return "index";
    }

    @PostMapping
    public String postExpression(@RequestParam(value = "expression") String expression, Model model) {
        try {
            model.addAttribute("expression", expression);
            model.addAttribute("result", calcService.calculate(expression));
        } catch (DivideByZeroException | UnexpectedCharError ex) {
            model.addAttribute("exception", ex.getMessage());
        }
        return "index";
    }
}
