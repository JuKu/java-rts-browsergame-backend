package com.jukusoft.browsergame.controller;

import com.jukusoft.browsergame.dao.GameWorldEntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameWorldController {

    @Autowired
    private GameWorldEntityDAO gameWorldEntityDAO;

    @GetMapping("/api/gameworlds/keys")
    public ResponseEntity<List<String>> listGameWorldKeys() {
        return ResponseEntity.ok(gameWorldEntityDAO.findAll().stream().map(gameWorldEntity -> gameWorldEntity.getKey()).collect(Collectors.toList()));
    }

}
