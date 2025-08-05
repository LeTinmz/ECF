package org.example.environement.controller;

import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.service.TravelLogService;

import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/travellog")
public class TravellogController {

    private final TravelLogService travelLogService;

    public TravellogController(TravelLogService travellogsService) {
        this.travelLogService = travellogsService;
    }

    @GetMapping
    public ResponseEntity<List<TravellogDtoResponse>> getAllTravellogs (){
        return ResponseEntity.ok(travelLogService.get());
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<TravellogDtoStat> getStatFromObservation (@PathVariable long id){
        return ResponseEntity.ok(travelLogService.getStat(id));
    }

    @PostMapping
    public ResponseEntity<TravellogDtoResponse> create(@RequestBody TravellogDtoReceive request) {
        TravellogDtoResponse saved = travelLogService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
//
//    @GetMapping("/user/{name}")
//    public ResponseEntity<Map<String,TravellogDtoStat>> getTravelStatForUserOnLAstMonth (@PathVariable String name){
//        return ResponseEntity.ok(travelLogService.getStatForUserLastMonth(name));
//    }


}
