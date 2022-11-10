package com.eya.mongodbpractice.schema;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
public abstract class BaseModel {
    @MongoId(FieldType.OBJECT_ID)
    protected String id;

    protected Date createdAt;

    protected Date updatedAt;
}
