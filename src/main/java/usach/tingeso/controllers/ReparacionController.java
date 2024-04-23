package usach.tingeso.controllers;


import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<ReparacionEntity> reparaciones = reparacionService.getReparaciones();
        if(reparaciones == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reparaciones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReparacionEntity> getReparacionById(@PathVariable Long id) {
        ReparacionEntity reparacion = reparacionService.getReparacionById(id);
        if (reparacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reparacion, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<ReparacionEntity> saveReparacion(@RequestBody ReparacionEntity reparacion) {
        if (reparacion.getIdReparacion() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReparacionEntity savedReparacion = reparacionService.saveReparacion(reparacion);
        if (savedReparacion == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedReparacion, HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<ReparacionEntity> updateReparacion(@RequestBody ReparacionEntity reparacion){
        if(reparacion == null){
            throw new RuntimeException("Reparacion vacía");
        }
        ReparacionEntity reparacionEntity = reparacion;
        BoletaEntity boletaEntity = new BoletaEntity();
        boletaEntity = boletaService.saveBoleta(boletaEntity);
        if (boletaEntity == null) {
            throw new RuntimeException("Boleta not found");
        }
        return ResponseEntity.ok(reparacionService.saveReparacion(reparacion));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReparacion(@PathVariable Long id){
        ReparacionEntity reparacion = reparacionService.getReparacionById(id);
        if(reparacion == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reparacionService.deleteReparacion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/reparacion/{patente}")
    public ResponseEntity<List<ReparacionEntity>> getReparacionesByVehiculo(@PathVariable Long patente){
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(patente);
        List<ReparacionEntity> reparaciones = reparacionService.getReparacionesByVehiculo(vehiculo);
        if(reparaciones == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reparaciones, HttpStatus.OK);
    }



}
