package com.eya.mongodbpractice.dto;

import com.eya.mongodbpractice.schema.Player;
import com.eya.mongodbpractice.schema.PlayerPosition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class CreatePlayerDto {
    private String name;

    private Date birthDate;

    private PlayerPosition position;

    private boolean isAvailable;

    public Player toPlayer() {
        return new Player().setName(name).setBirthDate(birthDate).setPosition(position).setAvailable(isAvailable);
    }
}
