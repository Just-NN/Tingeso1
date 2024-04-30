package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import usach.tingeso.entities.BonusBrandEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.VehicleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    VehicleService vehicleService;

    @Mock
    VehicleRepository vehicleRepository;

    //------------------------------------------------------------------------------------------------
    // Test for getVehicleById
    @Test
    public void testGetVehicleById() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        VehicleEntity result = vehicleService.getVehicleById(1L);
        assertNotNull(result);
        assertEquals(vehicle, result);
    }
    @Test
    public void testGetVehicleByIdNotFound() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        VehicleEntity result = vehicleService.getVehicleById(1L);
        assertEquals(null, result);
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveVehicle
    @Test
    public void testSaveVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        VehicleEntity result = vehicleService.saveVehicle(vehicle);
        assertNotNull(result);
        assertEquals(vehicle, result);
    }
    @Test
    public void testSaveVehicleNull() {
        VehicleEntity result = vehicleService.saveVehicle(null);
        assertEquals(null, result);
    }

    //------------------------------------------------------------------------------------------------
    // Test for deleteVehicle
    @Test
    public void testDeleteVehicle() {
        Long id = 1L;
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(id);

        when(vehicleRepository.findVehicleByLicensePlate(id)).thenReturn(vehicle);

        vehicleService.deleteVehicle(id);

        verify(vehicleRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteVehicleNotFound() {
        Long id = 1L;

        when(vehicleRepository.findVehicleByLicensePlate(id)).thenReturn(null);

        vehicleService.deleteVehicle(id);

        verify(vehicleRepository, times(0)).deleteById(id);
    }

    //------------------------------------------------------------------------------------------------
    // Test for updateVehicle
    @Test
    public void testUpdateVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(1L);
        when(vehicleRepository.existsById(vehicle.getLicensePlate())).thenReturn(true);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        VehicleEntity result = vehicleService.updateVehicle(vehicle);
        assertNotNull(result);
        assertEquals(vehicle, result);
    }
    @Test
    public void testUpdateVehicleNotFound() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(1L);
        when(vehicleRepository.existsById(vehicle.getLicensePlate())).thenReturn(false);

        VehicleEntity result = vehicleService.updateVehicle(vehicle);
        assertEquals(null, result);
    }

    //------------------------------------------------------------------------------------------------
    // Test for getAllVehicles
    @Test
    public void testGetAllVehicles() {
        VehicleEntity vehicle = new VehicleEntity();
        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(vehicle));

        var result = vehicleService.getAllVehicles();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(vehicle, result.get(0));
    }
    @Test
    public void testGetAllVehiclesEmpty() {
        when(vehicleRepository.findAll()).thenReturn(Collections.emptyList());

        List<VehicleEntity> result = vehicleService.getAllVehicles();
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}