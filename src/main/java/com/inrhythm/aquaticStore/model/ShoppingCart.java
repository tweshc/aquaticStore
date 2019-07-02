package com.inrhythm.aquaticStore.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@Document(collection = "carts")
public class ShoppingCart {
    @SerializedName("id")
    @Id
    private String id;

    @SerializedName("orderDetails")
    List<OrderDetail> orderDetails = new ArrayList<>();

    @SerializedName("cartTotal")
    private Double cartTotal;

    public void clearCart(){
        this.orderDetails = new ArrayList<>();
    }

}
