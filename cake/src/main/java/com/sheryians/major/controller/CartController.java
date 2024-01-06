package com.sheryians.major.controller;

import com.sheryians.major.dto.PaymentForm;
import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

    @ModelAttribute("paymentForm")
    public PaymentForm paymentForm() {
        return new PaymentForm();
    }

    @PostMapping("/payNow/submitPayment")
    public String processPayment(@ModelAttribute PaymentForm paymentForm, Model model) {
        System.out.println("Card number: " + paymentForm.getCardNumber());
        System.out.println("Card Holder: " + paymentForm.getCardHolder());
        System.out.println("Expiration month: " + paymentForm.getExpirationMonth());
        System.out.println("Expiration Year: " + paymentForm.getExpirationYear());
        System.out.println("CVV: " + paymentForm.getCvv());

        return "submitPayment";
    }

    @PostMapping("/payNow")
    public String PayNow() {;
        return "visacard";
    }

}
