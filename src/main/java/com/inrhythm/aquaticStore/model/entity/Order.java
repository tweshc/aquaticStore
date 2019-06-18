package com.inrhythm.aquaticStore.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Data
@Document(collection = "orders")
public class Order {
    @SerializedName("id")
    private String id;

    @SerializedName("orderDate")
    private String orderDate;

    @Indexed(unique = true)
    @SerializedName("orderNum")
    private String orderNum;

    @SerializedName("amount")
    private String amount;

    @SerializedName("customerName")
    private String customerName;

    @SerializedName("customerAddress")
    private String customerAddress;

    @SerializedName("customerEmail")
    private String customerEmail;

    @SerializedName("customerPhone")
    private String customerPhone;
}
