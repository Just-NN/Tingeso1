package usach.tingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.ReportEntity;
import usach.tingeso.services.ReportService;

@RestController
@RequestMapping("api/v1/report")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportEntity> getReportById(@PathVariable Long id){
        ReportEntity report = reportService.getReportById(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(report);
    }

    @PostMapping("/")
    public ResponseEntity<ReportEntity> saveReport(@RequestBody ReportEntity report){
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id){
        ReportEntity report = reportService.getReportById(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<ReportEntity> updateReport(@RequestBody ReportEntity report){
        if (report == null || reportService.getReportById(report.getIdReport()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.updateReport(report));
    }

    @PutMapping("/r1")
    public ResponseEntity<ReportEntity> saveR1(@RequestBody ReportEntity report){
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.saveR1(report));
    }

    @PutMapping("/r2")
    public ResponseEntity<ReportEntity> saveR2(@RequestBody ReportEntity report){
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.saveR2(report));
    }

    @PutMapping("/r3")
    public ResponseEntity<ReportEntity> saveR3(@RequestBody ReportEntity report){
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.saveR3(report));
    }

    @PutMapping("/r4")
    public ResponseEntity<ReportEntity> saveR4(@RequestBody ReportEntity report){
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(reportService.saveR4(report));
    }
    @CrossOrigin(origins = "localhost:5173")
    @PutMapping("/init")
    public ResponseEntity<ReportEntity> saveInit(@RequestBody ReportEntity report){
        if (report == null) {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reportService.saveR1(report);
        reportService.saveR2(report);
        reportService.saveR3(report);
        reportService.saveR4(report);
        return ResponseEntity.ok(report);

    }

}