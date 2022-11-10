package com.eya.mongodbpractice.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Data
public class Address {
    private String city;

    private String postalCode;

    private String street;

}
