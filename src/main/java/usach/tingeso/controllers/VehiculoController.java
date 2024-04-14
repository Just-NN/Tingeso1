package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.VehiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculo")
@CrossOrigin("*")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/")
    public ResponseEntity<List<VehiculoEntity>> getVehiculos(){
        return ResponseEntity.ok(vehiculoService.getVehiculos());
    }

    @GetMapping("{id}")
    public ResponseEntity<VehiculoEntity> getVehiculoById(@PathVariable Long id){
        return ResponseEntity.ok(vehiculoService.getVehiculoById(id));
    }
    @PostMapping("/")
    public ResponseEntity<VehiculoEntity> saveVehiculo(@RequestBody VehiculoEntity vehiculo){
        return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
    }
    @PutMapping("/")
    public ResponseEntity<VehiculoEntity> updateVehiculo(@RequestBody VehiculoEntity vehiculo){
        return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable Long id){
        return ResponseEntity.ok(vehiculoService.deleteVehiculo(id));
    }



}
