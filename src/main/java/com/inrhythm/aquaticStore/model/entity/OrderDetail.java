package com.inrhythm.aquaticStore.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Data
@Document(collection = "orderDetail")
public class OrderDetail {

    @SerializedName("id")
    private String id;

    /*
     * Order order
     */

    @DBRef
    @SerializedName("product")
    private Product product;

    @NotNull(message = "Product quantity is required.")
    @SerializedName("quantity")
    private int quantity;

    @NotNull(message = "Product price is required.")
    @SerializedName("price")
    private double price;

}
