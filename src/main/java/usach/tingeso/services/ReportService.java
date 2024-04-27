package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.ReportEntity;
import usach.tingeso.repositories.ReportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    TicketService ticketService;
    @Autowired
    private RepairService repairService;

    // Basic CRUD operations
    public ReportEntity getReportById(Long id){
        return reportRepository.findById(id).orElse(null);
    }

    public ReportEntity saveReport(ReportEntity report){
        if (report == null)
            return null;
        return reportRepository.save(report);
    }

    public void deleteReport(Long id){
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
        }
    }

    public ReportEntity updateReport(ReportEntity report){
        if (reportRepository.existsById(report.getIdReport())) {
            return reportRepository.save(report);
        }
        return null;
    }

    // Here starts the code with Rx


    // R1
    public ReportEntity saveR1(ReportEntity report){
        List<String> allTicketValues = ticketService.getAllTicketValues();
        if (allTicketValues != null) {
            String r1Details = String.join(",\n ", allTicketValues);
            report.setR1Details(r1Details);
        }
        return reportRepository.save(report);
    }

    // R2
    public ReportEntity saveR2(ReportEntity report){
        String allTicketValues = repairService.getRepairTypeCountAndTotalPrice();
        if (allTicketValues != null) {
            String r2Details = String.join(",\n ", allTicketValues);
            report.setR2RepairsVsVehiclesByTotalType(r2Details);
        }
        return reportRepository.save(report);
    }

    // R3
    public ReportEntity saveR3(ReportEntity reporte){
        String allTicketValues = repairService.getAverageTotalRepairAmountByBrand();
        if (allTicketValues != null) {
            String r3Details = String.join(",\n ", allTicketValues);
            reporte.setR3AverageByBrand(r3Details);
        }
        return reportRepository.save(reporte);
    }
    // R4
    public ReportEntity saveR4(ReportEntity report){
        String allTicketValues = repairService.getRepairTypeMotorTypeCountAndTotalPrice();
        if (allTicketValues != null) {
            String r4Details = String.join(",\n ", allTicketValues);
            report.setR4RepairsVsVehiclesByTotalEngine(r4Details);
        }
        return reportRepository.save(report);
    }

}