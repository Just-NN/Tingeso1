package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
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
        List<BoletaEntity> boletas = boletaService.getBoletas();
        if(boletas == null){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Boleta not found");
        }
        return ResponseEntity.ok(boletas);
    }
    @GetMapping("/{reparacion}")
    public ResponseEntity<BoletaEntity> getBoletaByReparacion(@PathVariable ReparacionEntity reparacion){
        BoletaEntity boleta = boletaService.getBoletaByReparacion(reparacion);
        if(boleta == null){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Boleta not found");
        }
        return ResponseEntity.ok(boleta);
    }
    @PostMapping("/")
    public ResponseEntity<BoletaEntity> saveBoleta(@RequestBody BoletaEntity boleta){

        if(boleta==null){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Boleta already exists");
        }
        return ResponseEntity.ok(boletaService.saveBoleta(boleta));
    }
    @PutMapping("/")
    public ResponseEntity<BoletaEntity> updateBoleta(@RequestBody BoletaEntity boleta){
        if(boleta==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return ResponseEntity.ok(boletaService.saveBoleta(boleta));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoleta(@PathVariable Long id){
        BoletaEntity boleta = boletaService.getBoletaById(id);
        if(boleta == null){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Boleta not found");
        }
        return ResponseEntity.ok(boletaService.deleteBoleta(id));
    }



//    @PutMapping("/bono/km")
//    public ResponseEntity<BoletaEntity> updateRecargoKM(@RequestBody BoletaEntity boleta){
//        return ResponseEntity.ok(boletaService.guardarRecargoPorKM(boleta));
//    }
//    @PutMapping("/bono/a")
//    public ResponseEntity<BoletaEntity> updateRecargoAntiguedad(@RequestBody BoletaEntity boleta){
//        return ResponseEntity.ok(boletaService.guardarRecargoPorAntiguedad(boleta));
//    }
//    @PutMapping("/bono/d")
//    public ResponseEntity<BoletaEntity> updateDescuentoDia(@RequestBody BoletaEntity boleta){
//        return ResponseEntity.ok(boletaService.guardarDescuentoPorDia(boleta));
//    }
//    @PutMapping("/bono/h")
//    public ResponseEntity<BoletaEntity> updateDescuentoReparaciones(@RequestBody BoletaEntity boleta){
//        return ResponseEntity.ok(boletaService.guardarDescuentoPorReparaciones(boleta));
//    }
//    @PutMapping("/total/")
//    public ResponseEntity<BoletaEntity> updateTotal(@RequestBody BoletaEntity boleta){
//        return ResponseEntity.ok(boletaService.guardarPrecioTotal(boleta));
//    }
    @PutMapping("/init/")
    public ResponseEntity<?> inicializarBoleta(@RequestBody BoletaEntity boleta){
        if(boleta==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boleta already exists");
        }
        boletaService.guardarRecargoPorKM(boleta);
        boletaService.guardarRecargoPorAntiguedad(boleta);
        boletaService.guardarDescuentoPorDia(boleta);
        boletaService.guardarDescuentoPorReparaciones(boleta);
        boletaService.guardarRecargoPorRetraso(boleta);
        boletaService.guardarPrecioTotal(boleta);
        return ResponseEntity.ok("Boleta inicializada");

    }

}
