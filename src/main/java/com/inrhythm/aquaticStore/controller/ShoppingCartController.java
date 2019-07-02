package com.inrhythm.aquaticStore.controller;

import com.inrhythm.aquaticStore.model.Product;
import com.inrhythm.aquaticStore.model.ShoppingCart;
import com.inrhythm.aquaticStore.service.CartService;
import com.inrhythm.aquaticStore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ShoppingCartController {

    @Autowired
    CartService service;

    @Autowired
    ProductService productService;

    /**
     * returns a list of {@link ShoppingCart} - all orders that have been submitted
     * @param cart
     * @return
     */
    @GetMapping(value = {"/carts/findAll"})
    public List<ShoppingCart> submitOrder(ShoppingCart cart){
        List<ShoppingCart> carts = new ArrayList<>();
        service.getAllCarts().forEach(carts::add);
        return carts;
    }

    /**
     * example GET /filterDouble/products/price?filter=gt&amount=250
     * @param field
     * @param filter
     * @param amount
     * @return
     */
    @GetMapping(
            value = "/filterDouble/products/{field}",
            params = { "filter", "amount" })
    @ResponseBody
    public List<Product> filterProductsDouble(@PathVariable("field") String field,
                                        @RequestParam("filter") String filter,
                                        @RequestParam("amount") String amount){
        log.info("inside filterProductsDouble()");
        return productService.filterProductsDouble(field, filter, Double.parseDouble(amount));
    }

    /**
     * example GET /filterString/products/temperament?value=peaceful
     * @param field name of property within {@link Product}
     * @param value value of property of field
     * @return list of products that match this filter
     */
    @GetMapping(
            value = "/filterString/products/{field}",
            params = { "value" })
    @ResponseBody
    public List<Product> filterProductsString(@PathVariable("field") String field,
                                        @RequestParam("value") String value){
        log.info("inside filterProductsString()");
        return productService.filterProductsString(field, value);
    }
}
