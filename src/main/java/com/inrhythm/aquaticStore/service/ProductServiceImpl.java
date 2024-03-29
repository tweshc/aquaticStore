package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.OrderDetail;
import com.inrhythm.aquaticStore.model.Product;
import com.inrhythm.aquaticStore.model.ShoppingCart;
import com.inrhythm.aquaticStore.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    private MongoTemplate mongoTemplate;

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

    /**
     * Adds product to cart. if There is already a product in the cart with the same name {@link Product#getName()}
     * then it will not add it to the cart, instead it will increment the quantity by one.
     * @param product
     * @param model
     */
    @Override
    public void addToCart(Product product, Model model) {
        for(OrderDetail od : cart.getOrderDetails()){
            if(product.getName().equals(od.getProduct().getName())) {
                incrementQuantitySetPrice(od, product);
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
        cart.setCartTotal(calculateTotal(noDuplicates));
        model.addAttribute("orderDetails", noDuplicates);
        model.addAttribute("total", calculateTotal(noDuplicates));
        model.addAttribute("shoppingCart", cart);
    }

    /**
     * increment quantity of the given product in the Order Detail object and set the price by multiplying unit price
     * by quantity
     *
     * @param od
     * @param product
     */
    private void incrementQuantitySetPrice(OrderDetail od, Product product){
        od.incrementQuantity();
        od.setPrice(Math.round(product.getPrice() * od.getQuantity() * 100.0)/100.0);
    }

    /**
     * This method is used to calculate the cart total. It iterates through the List of Order Detail see
     * {@link ShoppingCart#getOrderDetails()} and adds all the prices of each Order Detail obj to calculate a grand total
     * @param list
     * @return
     */
    private Double calculateTotal(List<OrderDetail> list){
        Double total = 0.0;
        for(OrderDetail od : list){
            total = total + od.getPrice();
        }
        return Math.round(total * 100.0) / 100.0;
    }

    @Override
    public List<Product> filterProductsDouble(String field, String filter, Double amount){

        Query query = new Query();

        if(filter.equals("lt"))
            query.addCriteria(Criteria.where(field).lt(amount));
        else if(filter.equals("lte"))
            query.addCriteria(Criteria.where(field).lte(amount));
        else if(filter.equals("gt"))
            query.addCriteria(Criteria.where(field).gt(amount));
        else if(filter.equals("gte"))
            query.addCriteria(Criteria.where(field).gte(amount));
        else if(filter.equals("is"))
            query.addCriteria(Criteria.where(field).is(amount));


        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public List<Product> filterProductsString(String field, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));

        return mongoTemplate.find(query, Product.class);
    }
}
