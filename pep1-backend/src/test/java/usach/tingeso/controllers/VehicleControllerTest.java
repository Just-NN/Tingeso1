package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.services.VehicleService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @InjectMocks
    VehicleController vehicleController;

    @Mock
    VehicleService vehicleService;

    @Test
    public void testGetVehicleById() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        ResponseEntity<VehicleEntity> response = vehicleController.getVehicleById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicle, response.getBody());
    }

    @Test
    public void testGetVehicleByIdNotFound() {
        Long id = 1L;
        when(vehicleService.getVehicleById(id)).thenReturn(null);

        ResponseEntity<VehicleEntity> response = vehicleController.getVehicleById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.saveVehicle(vehicle)).thenReturn(vehicle);

        ResponseEntity<VehicleEntity> response = vehicleController.saveVehicle(vehicle);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicle, response.getBody());
    }

    @Test
    public void testSaveVehicleBadRequest() {
        ResponseEntity<VehicleEntity> response = vehicleController.saveVehicle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        ResponseEntity<Void> response = vehicleController.deleteVehicle(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteVehicleNotFound() {
        Long id = 1L;
        when(vehicleService.getVehicleById(id)).thenReturn(null);

        ResponseEntity<Void> response = vehicleController.deleteVehicle(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleService.getVehicleById(vehicle.getLicensePlate())).thenReturn(vehicle);
        when(vehicleService.updateVehicle(vehicle)).thenReturn(vehicle);

        ResponseEntity<VehicleEntity> response = vehicleController.updateVehicle(vehicle);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicle, response.getBody());
    }

    @Test
    public void testUpdateVehicleBadRequest() {
        ResponseEntity<VehicleEntity> response = vehicleController.updateVehicle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAllVehicles() {
        List<VehicleEntity> vehicles = Collections.singletonList(new VehicleEntity());
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        ResponseEntity<List<VehicleEntity>> response = vehicleController.getAllVehicles();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(vehicles, response.getBody());
    }

    @Test
    public void testGetAllVehiclesNotFound() {
        when(vehicleService.getAllVehicles()).thenReturn(Collections.emptyList());

        ResponseEntity<List<VehicleEntity>> response = vehicleController.getAllVehicles();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}