package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usach.tingeso.entities.ReportEntity;
import usach.tingeso.services.ReportService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @InjectMocks
    ReportController reportController;

    @Mock
    ReportService reportService;

    //------------------------------------------------------------------------------------------------
    //Test for getReportById
    @Test
    public void testGetReportById() {
        ReportEntity report = new ReportEntity();
        when(reportService.getReportById(1L)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.getReportById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testGetReportByIdNotFound() {
        Long id = 1L;
        when(reportService.getReportById(id)).thenReturn(null);

        ResponseEntity<ReportEntity> response = reportController.getReportById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveReport

    @Test
    public void testSaveReport() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveReport(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveReport(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testSaveReportBadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveReport(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for deleteReport

    @Test
    public void testDeleteReport() {
        ReportEntity report = new ReportEntity();
        when(reportService.getReportById(1L)).thenReturn(report);

        ResponseEntity<Void> response = reportController.deleteReport(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteReportNotFound() {
        Long id = 1L;
        when(reportService.getReportById(id)).thenReturn(null);

        ResponseEntity<Void> response = reportController.deleteReport(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for updateReport

    @Test
    public void testUpdateReport() {
        ReportEntity report = new ReportEntity();
        report.setIdReport(1L); // Set the ID of the report

        when(reportService.getReportById(report.getIdReport())).thenReturn(report);
        when(reportService.updateReport(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.updateReport(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testUpdateReportBadRequest() {
        ResponseEntity<ReportEntity> response = reportController.updateReport(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveR1
    @Test
    public void testSaveR1() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveR1(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveR1(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testSaveR1BadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveR1(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveR2() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveR2(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveR2(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testSaveR2BadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveR2(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveR3() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveR3(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveR3(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testSaveR3BadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveR3(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveR4() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveR4(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveR4(report);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(report, response.getBody());
    }

    @Test
    public void testSaveR4BadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveR4(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // Test for saveInit
    @Test
    public void testSaveInitBadRequest() {
        ResponseEntity<ReportEntity> response = reportController.saveInit(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInit() {
        ReportEntity report = new ReportEntity();
        when(reportService.saveR1(report)).thenReturn(report);
        when(reportService.saveR2(report)).thenReturn(report);
        when(reportService.saveR3(report)).thenReturn(report);
        when(reportService.saveR4(report)).thenReturn(report);

        ResponseEntity<ReportEntity> response = reportController.saveInit(report);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(report, response.getBody());
    }

}