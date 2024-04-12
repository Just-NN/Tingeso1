package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.services.BoletaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boleta")
@CrossOrigin("*")

public class BoletaController {
    @Autowired
    BoletaService boletaService;


    @GetMapping("/")
    public ResponseEntity<List<BoletaEntity>> getBoletas(){
        return ResponseEntity.ok(boletaService.getBoletas());
    }
    @PostMapping("/")
    public ResponseEntity<BoletaEntity> saveBoleta(@RequestBody BoletaEntity boleta){

        return ResponseEntity.ok(boletaService.saveBoleta(boleta));
    }
    @PutMapping("/")
    public ResponseEntity<BoletaEntity> updateBoleta(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.saveBoleta(boleta));
    }
    @PutMapping("/bono/")
    public ResponseEntity<BoletaEntity> updateRecargoKM(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarRecargoPorKM(boleta));
    }

}
