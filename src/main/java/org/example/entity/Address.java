package org.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Address {
    @Id
    private UUID id;

    private String city;

    private String country;

    private String zipCode;

}
