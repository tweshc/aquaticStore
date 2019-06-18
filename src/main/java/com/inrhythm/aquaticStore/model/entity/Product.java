package com.inrhythm.aquaticStore.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Data
@Document(collection = "products")
public class Product {

    @SerializedName("id")
    @Id
    public String id;

    @SerializedName("name")
    @NotNull(message = "Product name is required.")
    public String name;

    @SerializedName("price")
    @NotNull(message = "Product price is required.")
    private Double price;

    @SerializedName("careLevel")
    public String careLevel;

    @SerializedName("temperament")
    public String temperament;

    @SerializedName("venemous")
    public String venemous;

    @SerializedName("minimumTankSize")
    public Integer minimumTankSize;

    @SerializedName("description")
    public String description;

    @SerializedName("pictureUrl")
    private String pictureUrl;

}
