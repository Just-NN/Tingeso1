package usach.tingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.services.RepairService;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("api/v1/repair")
@CrossOrigin(origins = "*")
public class RepairController {

    @Autowired
    RepairService repairService;
    @Autowired
    private RepairRepository repairRepository;

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id){
        RepairEntity repair = repairService.getRepairById(id);
        if (repair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(repair);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair){
        System.out.println("EJECUTANDO SAVEREPAIR");
        if (repair == null) {
            System.out.println("Error");
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("EJECUTANDO SAVEREPAIR");
        return ResponseEntity.ok(repairService.saveRepair(repair));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id){
        RepairEntity repair = repairService.getRepairById(id);
        if (repair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repairService.deleteRepair(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repair){
        if (repair == null || repairService.getRepairById(repair.getIdRepair()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(repairService.updateRepair(repair));
    }

    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> getAllRepairs(){
        List<RepairEntity> repairs = repairService.getAllRepairs();
        if (repairs == null || repairs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(repairs);
    }

}