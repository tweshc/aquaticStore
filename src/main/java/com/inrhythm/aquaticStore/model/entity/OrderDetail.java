package com.inrhythm.aquaticStore.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class OrderDetail {

    @SerializedName("pictureUrl")
    private String pictureUrl;

    @SerializedName("product")
    private Product product;

    @NotNull(message = "Product quantity is required.")
    @SerializedName("quantity")
    private int quantity;

    @NotNull(message = "Product price is required.")
    @SerializedName("price")
    private double price;

    public void incrementQuantity(){
        this.quantity ++;
    }

}
