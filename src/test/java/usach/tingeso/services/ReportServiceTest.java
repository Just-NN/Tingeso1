package usach.tingeso.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usach.tingeso.entities.ReportEntity;
import usach.tingeso.repositories.ReportRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ReportServiceTest {

    @InjectMocks
    ReportService reportService;

    @Mock
    ReportRepository reportRepository;

    @Mock
    TicketService ticketService;

    @Mock
    RepairService repairService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //------------------------------------------------------------------------------------------------
    // Test for getReportById
    @Test
    public void getReportByIdTest() {
        ReportEntity report = new ReportEntity();
        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        reportService.getReportById(1L);

        verify(reportRepository).findById(1L);
    }
    @Test
    public void getReportByIdNotFoundTest() {
        when(reportRepository.findById(1L)).thenReturn(Optional.empty());

        reportService.getReportById(1L);

        verify(reportRepository).findById(1L);
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveReport
    @Test
    public void saveReportTest() {
        ReportEntity report = new ReportEntity();
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveReport(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void saveReportNullTest() {
        ReportEntity report = null;

        reportService.saveReport(report);

        verify(reportRepository, never()).save(report);
    }

    //------------------------------------------------------------------------------------------------
    // Test for deleteReport
    @Test
    public void deleteReportTest() {
        when(reportRepository.existsById(1L)).thenReturn(true);

        reportService.deleteReport(1L);

        verify(reportRepository).deleteById(1L);
    }
    @Test
    public void deleteReportNotFoundTest() {
        when(reportRepository.existsById(1L)).thenReturn(false);

        reportService.deleteReport(1L);

        verify(reportRepository, never()).deleteById(1L);
    }

    //------------------------------------------------------------------------------------------------
    // Test for updateReport
    @Test
    public void updateReportTest() {
        ReportEntity report = new ReportEntity();
        when(reportRepository.existsById(report.getIdReport())).thenReturn(true);
        when(reportRepository.save(report)).thenReturn(report);

        reportService.updateReport(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void updateReportNotFoundTest() {
        ReportEntity report = new ReportEntity();
        when(reportRepository.existsById(report.getIdReport())).thenReturn(false);

        reportService.updateReport(report);

        verify(reportRepository, never()).save(report);
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveR1
    @Test
    public void saveR1Test() {
        ReportEntity report = new ReportEntity();
        when(ticketService.getAllTicketValues()).thenReturn(Arrays.asList("value1", "value2"));
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR1(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void saveR1NullTest() {
        ReportEntity report = new ReportEntity();
        when(ticketService.getAllTicketValues()).thenReturn(null);
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR1(report);

        verify(reportRepository).save(report);
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveR2
    @Test
    public void saveR2Test() {
        ReportEntity report = new ReportEntity();
        when(repairService.getRepairTypeCountAndTotalPrice()).thenReturn("value1, value2");
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR2(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void saveR2NullTest() {
        ReportEntity report = new ReportEntity();
        when(repairService.getRepairTypeCountAndTotalPrice()).thenReturn(null);
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR2(report);

        verify(reportRepository).save(report);
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveR3
    @Test
    public void saveR3Test() {
        ReportEntity report = new ReportEntity();
        when(repairService.getAverageTotalRepairAmountByBrand()).thenReturn("value1, value2");
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR3(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void saveR3NullTest() {
        ReportEntity report = new ReportEntity();
        when(repairService.getAverageTotalRepairAmountByBrand()).thenReturn(null);
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR3(report);

        verify(reportRepository).save(report);
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveR4
    @Test
    public void saveR4Test() {
        ReportEntity report = new ReportEntity();
        when(repairService.getRepairTypeMotorTypeCountAndTotalPrice()).thenReturn("value1, value2");
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR4(report);

        verify(reportRepository).save(report);
    }
    @Test
    public void saveR4NullTest() {
        ReportEntity report = new ReportEntity();
        when(repairService.getRepairTypeMotorTypeCountAndTotalPrice()).thenReturn(null);
        when(reportRepository.save(report)).thenReturn(report);

        reportService.saveR4(report);

        verify(reportRepository).save(report);
    }
}