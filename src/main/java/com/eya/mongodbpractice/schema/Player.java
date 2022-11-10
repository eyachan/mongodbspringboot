package com.eya.mongodbpractice.schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "players")
@Accessors(chain = true)
@NoArgsConstructor
@Data
public class Player extends BaseModel {
    @Indexed
    private String name;

    private Date birthDate;

    @Indexed
    @Field(targetType = FieldType.STRING)
    private PlayerPosition position;

    private boolean isAvailable;
}
