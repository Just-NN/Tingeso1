package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.services.VehiculoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculo")
@CrossOrigin("*")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/")
    public List<VehiculoEntity> getVehiculos(){
        List<VehiculoEntity> vehiculos = vehiculoService.getVehiculos();
        if(vehiculos == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
        return vehiculos;
    }

    @GetMapping("{id}")
    public ResponseEntity<VehiculoEntity> getVehiculoById(@PathVariable Long id){
        VehiculoEntity vehiculo = vehiculoService.getVehiculoById(id);
        if(vehiculo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
        return ResponseEntity.ok(vehiculo);
    }
    @PostMapping("/")
    public ResponseEntity<VehiculoEntity> saveVehiculo(@RequestBody VehiculoEntity vehiculo){
        if(vehiculo != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already exists");
        }
        return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
    }
    @PutMapping("/")
    public ResponseEntity<VehiculoEntity> updateVehiculo(@RequestBody VehiculoEntity vehiculo){
        VehiculoEntity vehiculoEntity = vehiculoService.getVehiculoById(vehiculo.getPatente());
        if(vehiculoEntity == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        }
        return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable Long id){
        VehiculoEntity vehiculoEntity = vehiculoService.getVehiculoById(id);
        if(vehiculoEntity == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }



}
