package com.eya.mongodbpractice.repository;

import com.eya.mongodbpractice.schema.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

}
