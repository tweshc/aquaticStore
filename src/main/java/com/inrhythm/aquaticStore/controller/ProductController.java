package com.inrhythm.aquaticStore.controller;

import com.inrhythm.aquaticStore.model.entity.OrderDetail;
import com.inrhythm.aquaticStore.model.entity.Product;
import com.inrhythm.aquaticStore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping(value = { "" , "/", "/welcome" })
    public String home(Model model){

        List<Product> productsList = new ArrayList<>();
        service.getAllProducts().forEach(productsList::add);

        model.addAttribute("products", productsList);

        return "index";
    }

    @GetMapping(value = { "/details/{productId}" })
    public String details(Model model, @PathVariable String productId){

        log.info("/details/" + productId);
        Product product = service.findById(productId);

        model.addAttribute("id", product.getId());
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("careLevel", product.getCareLevel());
        model.addAttribute("temperament", product.getTemperament());
        model.addAttribute("venemous", product.getVenemous());
        model.addAttribute("minimumTankSize", product.getMinimumTankSize());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("pictureUrl", "/"+product.getPictureUrl());

        return "productDetails";
    }

    @GetMapping(value = { "/shoppingCart/{productId}" })
    public String shoppingCart(HttpServletRequest request, Model model, @PathVariable String productId){

        log.info("/shoppingCart/" + productId);
        Product product = service.findById(productId);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setPrice(product.getPrice());
        orderDetail.setQuantity(1);

        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("careLevel", product.getCareLevel());
        model.addAttribute("temperament", product.getTemperament());
        model.addAttribute("venemous", product.getVenemous());
        model.addAttribute("minimumTankSize", product.getMinimumTankSize());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("pictureUrl", "/"+product.getPictureUrl());

        return "productDetails";
    }
}