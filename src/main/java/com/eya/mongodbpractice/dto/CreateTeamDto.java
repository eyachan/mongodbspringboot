package com.eya.mongodbpractice.dto;

import com.eya.mongodbpractice.schema.Address;
import com.eya.mongodbpractice.schema.Team;
import lombok.Data;

@Data
public class CreateTeamDto {
    private String name;

    private String acronym;

    private Address address;

    public Team toTeam() {
        return new Team().setName(name).setAcronym(acronym).setAddress(address);
    }
}