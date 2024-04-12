package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.BoletaService;
import usach.tingeso.services.ReparacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reparacion")
@CrossOrigin("*")

public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;
    @Autowired
    private BoletaService boletaService;


    @GetMapping("/")
    public ResponseEntity<List<ReparacionEntity>> getReparaciones(){
        return ResponseEntity.ok(reparacionService.getReparaciones());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReparacionEntity> getReparacionById(@PathVariable Long id){
        return ResponseEntity.ok(reparacionService.getReparacionById(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> saveReparacion(@RequestBody ReparacionEntity reparacion){
        ReparacionEntity reparacionEntity = reparacion;
        BoletaEntity boletaEntity = new BoletaEntity();
        boletaEntity = boletaService.saveBoleta(boletaEntity);
        reparacionEntity.setBoletaEntity(boletaEntity);
        boletaEntity.setReparacionEntity(reparacionEntity);

        reparacionEntity = reparacionService.saveReparacion(reparacionEntity);

        return ResponseEntity.ok("Reparacion y boleta creadas exitosamente");
    }


}
