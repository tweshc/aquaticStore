package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.entity.OrderDetail;
import com.inrhythm.aquaticStore.model.entity.Product;
import com.inrhythm.aquaticStore.model.entity.ShoppingCart;
import com.inrhythm.aquaticStore.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public Iterable<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product findById(String id) {
        Optional<Product> prod = repository.findById(id);
        if(prod.get() != null)
            return prod.get();
        return null;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void addToCart(Product product, Model model) {
        for(OrderDetail od : cart.getOrderDetails()){
            if(product.getName().equals(od.getProduct().getName())) {
                incrementQuantitySetPrice(od, product);
                cart.getOrderDetails().add(od);
                List<OrderDetail> noDuplicates = cart.getOrderDetails().stream().distinct().collect(Collectors.toList());
                model.addAttribute("orderDetails", noDuplicates);
                model.addAttribute("total", calculateTotal(noDuplicates));
                model.addAttribute("shoppingCart", cart);
                return;
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setPictureUrl("/"+product.getPictureUrl());
        incrementQuantitySetPrice(orderDetail, product);
        cart.getOrderDetails().add(orderDetail);
        List<OrderDetail> noDuplicates = cart.getOrderDetails().stream().distinct().collect(Collectors.toList());
        model.addAttribute("orderDetails", noDuplicates);
        model.addAttribute("total", calculateTotal(noDuplicates));
        ShoppingCart cart2 = cart;
        model.addAttribute("shoppingCart", cart2);
    }

    private void incrementQuantitySetPrice(OrderDetail od, Product product){
        od.incrementQuantity();
        od.setPrice(Math.round(product.getPrice() * od.getQuantity() * 100.0)/100.0);
    }

    private Double calculateTotal(List<OrderDetail> list){
        Double total = 0.0;
        for(OrderDetail od : list){
            total = total + od.getPrice();
        }
        return Math.round(total * 100.0) / 100.0;
    }
}
