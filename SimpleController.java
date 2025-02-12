package org.nagesh.springbootbasics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @GetMapping("/success")
    public String successMessage() {

        BigDecimal big = new BigDecimal(1288.28);
        String words = NumberToWordsConverter.convertToIndianCurrency(big);




        return "Success!";
    }
}