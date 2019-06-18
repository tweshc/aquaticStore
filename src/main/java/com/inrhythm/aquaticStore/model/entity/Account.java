package com.inrhythm.aquaticStore.model.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Data
@Document(collection = "accounts")
public class Account {

    @SerializedName("id")
    @Id
    private String id;
    @SerializedName("userName")
    private String userName;
    @SerializedName("encryptedPassword")
    private String encryptedPassword;
    @SerializedName("active")
    private boolean active;
    @SerializedName("userRole")
    private String userRole;

}
