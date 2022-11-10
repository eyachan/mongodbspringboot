package com.eya.mongodbpractice.controller;

import com.eya.mongodbpractice.dto.CreatePlayerDto;
import com.eya.mongodbpractice.dto.CreateTeamDto;
import com.eya.mongodbpractice.repository.PlayerRepository;
import com.eya.mongodbpractice.repository.TeamRepository;
import com.eya.mongodbpractice.schema.Player;
import com.eya.mongodbpractice.schema.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SoccerController {
    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    public SoccerController(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody CreateTeamDto createTeamDto) {
        Team teamCreated = teamRepository.save(createTeamDto.toTeam());

        return new ResponseEntity<>(teamCreated, HttpStatus.CREATED);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody CreatePlayerDto createPlayerDto) {
        Player playerCreated = playerRepository.save(createPlayerDto.toPlayer());
        return new ResponseEntity<>(playerCreated, HttpStatus.CREATED);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody CreateTeamDto createTeamDto) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        Team teamToUpdate = optionalTeam.get()
                .setAddress(createTeamDto.getAddress())
                .setName(createTeamDto.getName())
                .setAcronym(createTeamDto.getAcronym());

        Team teamUpdated = teamRepository.save(teamToUpdate);

        return new ResponseEntity<>(teamUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id, @RequestBody CreateTeamDto createTeamDto) {
        teamRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/players/bulk")
    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<CreatePlayerDto> createPlayerDtoList) {
        List<Player> players = createPlayerDtoList
                .stream()
                .map(CreatePlayerDto::toPlayer)
                .collect(Collectors.toList());

        List<Player> playersCreated = playerRepository.saveAll(players);

        return new ResponseEntity<>(playersCreated, HttpStatus.CREATED);
    }


    @PostMapping("/teams/{id}/players")
    public ResponseEntity<Team> addPlayersToTeam(@PathVariable String id, @RequestBody List<String> playerIds) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        Team teamToUpdate = optionalTeam.get();

        Set<Player> playersToAdd = playerIds.stream()
                .map(playerRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        teamToUpdate.setPlayers(playersToAdd);

        Team teamUpdated =  teamRepository.save(teamToUpdate);

        return new ResponseEntity<>(teamUpdated, HttpStatus.OK);
    }
}
