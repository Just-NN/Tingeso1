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
    @PutMapping("/bono/km")
    public ResponseEntity<BoletaEntity> updateRecargoKM(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarRecargoPorKM(boleta));
    }
    @PutMapping("/bono/a")
    public ResponseEntity<BoletaEntity> updateRecargoAntiguedad(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarRecargoPorAntiguedad(boleta));
    }
    @PutMapping("/bono/d")
    public ResponseEntity<BoletaEntity> updateDescuentoDia(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarDescuentoPorDia(boleta));
    }
    @PutMapping("/bono/h")
    public ResponseEntity<BoletaEntity> updateDescuentoReparaciones(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarDescuentoPorReparaciones(boleta));
    }
    @PutMapping("/total/")
    public ResponseEntity<BoletaEntity> updateTotal(@RequestBody BoletaEntity boleta){
        return ResponseEntity.ok(boletaService.guardarPrecioTotal(boleta));
    }
    @PutMapping("/init/")
    public ResponseEntity<?> inicializarBoleta(@RequestBody BoletaEntity boleta){
        boletaService.guardarRecargoPorKM(boleta);
        boletaService.guardarRecargoPorAntiguedad(boleta);
        boletaService.guardarDescuentoPorDia(boleta);
        boletaService.guardarDescuentoPorReparaciones(boleta);
        boletaService.guardarRecargoPorRetraso(boleta);
        boletaService.guardarPrecioTotal(boleta);
        return ResponseEntity.ok("Tudo bem");

    }

}
