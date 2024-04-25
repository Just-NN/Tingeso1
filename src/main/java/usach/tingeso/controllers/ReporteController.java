package usach.tingeso.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import usach.tingeso.entities.ReporteEntity;
import usach.tingeso.services.ReporteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reporte")
@CrossOrigin("*")
public class ReporteController {
    @Autowired
    ReporteService reporteService;

    // CRUD básico
    @GetMapping("/")
    public List<ReporteEntity> getReportes(){
        List<ReporteEntity> reportes = reporteService.getReportes();
        if(reportes == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        return reportes;
    }
    @GetMapping("{id}")
    public ResponseEntity<ReporteEntity> getReporteById(@PathVariable Long id){
        ReporteEntity reporte = reporteService.getReporteById(id);
        if(reporte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        return ResponseEntity.ok(reporte);
    }
    @PostMapping("/")
    public ResponseEntity<ReporteEntity> saveReporte(@RequestBody ReporteEntity reporte){
        if(reporte == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Report already exists");
        }
        return ResponseEntity.ok(reporteService.saveReporte(reporte));
    }
    @PutMapping("/")
    public ResponseEntity<ReporteEntity> updateReporte(@RequestBody ReporteEntity reporte){
        ReporteEntity reporteEntity = reporteService.getReporteById(reporte.getIdReporte());
        if(reporteEntity == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        }
        return ResponseEntity.ok(reporteService.saveReporte(reporte));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReporte(@PathVariable Long id){
        ReporteEntity reporteEntity = reporteService.getReporteById(id);
        if(reporteEntity == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        reporteService.deleteReporte(id);
        return ResponseEntity.ok(true);
    }
    @PutMapping("/init/")
    public ResponseEntity<ReporteEntity> initReporte(@RequestBody ReporteEntity reporte){
        if(reporte == null){
            System.out.println("Reporte nulo");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        System.out.println("Init reporte");
        reporteService.saveR1(reporte);
        reporteService.saveR2(reporte);
        reporteService.saveR3(reporte);
        reporteService.saveR4(reporte);
        return ResponseEntity.ok(reporte);
    }


}
