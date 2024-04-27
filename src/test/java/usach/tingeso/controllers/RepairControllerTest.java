package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.services.RepairService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepairControllerTest {

    @InjectMocks
    RepairController repairController;

    @Mock
    RepairService repairService;

    //------------------------------------------------------------------------------------------------
    //Test for getRepairById
    @Test
    public void testGetRepairById() {
        RepairEntity repair = new RepairEntity();
        when(repairService.getRepairById(1L)).thenReturn(repair);

        ResponseEntity<RepairEntity> response = repairController.getRepairById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(repair, response.getBody());
    }

    @Test
    public void testGetRepairByIdNotFound() {
        Long id = 1L;
        when(repairService.getRepairById(id)).thenReturn(null);

        ResponseEntity<RepairEntity> response = repairController.getRepairById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // ------------------------------------------------------------------------------------------------
    // Test for saveRepair
    @Test
    public void testSaveRepair() {
        RepairEntity repair = new RepairEntity();
        when(repairService.saveRepair(repair)).thenReturn(repair);

        ResponseEntity<RepairEntity> response = repairController.saveRepair(repair);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(repair, response.getBody());
    }

    @Test
    public void testSaveRepairBadRequest() {
        ResponseEntity<RepairEntity> response = repairController.saveRepair(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for deleteRepair
    @Test
    public void testDeleteRepair() {
        RepairEntity repair = new RepairEntity();
        when(repairService.getRepairById(1L)).thenReturn(repair);

        ResponseEntity<Void> response = repairController.deleteRepair(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteRepairNotFound() {
        Long id = 1L;
        when(repairService.getRepairById(id)).thenReturn(null);

        ResponseEntity<Void> response = repairController.deleteRepair(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for updateRepair
    @Test
    public void testUpdateRepair() {
        RepairEntity repair = new RepairEntity();
        when(repairService.getRepairById(1L)).thenReturn(repair);
        when(repairService.updateRepair(repair)).thenReturn(repair);

        ResponseEntity<RepairEntity> response = repairController.updateRepair(repair);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(repair, response.getBody());
    }

    @Test
    public void testUpdateRepairBadRequest() {
        ResponseEntity<RepairEntity> response = repairController.updateRepair(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for getAllRepairs
    @Test
    public void testGetAllRepairs() {
        List<RepairEntity> repairs = Collections.singletonList(new RepairEntity());
        when(repairService.getAllRepairs()).thenReturn(repairs);

        ResponseEntity<List<RepairEntity>> response = repairController.getAllRepairs();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(repairs, response.getBody());
    }

    @Test
    public void testGetAllRepairsNotFound() {
        when(repairService.getAllRepairs()).thenReturn(null);

        ResponseEntity<List<RepairEntity>> response = repairController.getAllRepairs();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

