package usach.tingeso.services;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.VehiculoRepository;


import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehiculoServiceTest {
    @Autowired
    VehiculoService vehiculoService;

    @MockBean
    VehiculoRepository vehiculoRepository;

    @Test
    public void whenCreateVehiculo_thenReturnVehiculo() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Yaris");
        vehiculo.setAno(2010);

        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        when(vehiculoRepository.findById(1L)).thenReturn(java.util.Optional.of(vehiculo));

        vehiculoService.saveVehiculo(vehiculo);
        assertThat(vehiculoService.getVehiculoById(1L)).isEqualTo(vehiculo);
    }
    @Test
    public void whenUpdateVehiculo_thenReturnVehiculo() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Yaris");
        vehiculo.setAno(2010);

        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        when(vehiculoRepository.findById(1L)).thenReturn(java.util.Optional.of(vehiculo));

        vehiculoService.saveVehiculo(vehiculo);
        vehiculo.setAno(2011);
        vehiculoService.updateVehiculo(vehiculo);
        assertThat(vehiculoService.getVehiculoById(1L).getAno()).isEqualTo(2011);
    }
    @Test
    public void whenDeleteVehiculo_thenReturnTrue() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Yaris");
        vehiculo.setAno(2010);

        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        when(vehiculoRepository.findById(1L)).thenReturn(java.util.Optional.of(vehiculo));

        vehiculoService.saveVehiculo(vehiculo);
        assertThat(vehiculoService.deleteVehiculo(1L)).isEqualTo(true);
    }
    @Test
    public void whenGetVehiculos_thenReturnVehiculos() {
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Yaris");
        vehiculo.setAno(2010);

        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);
        when(vehiculoRepository.findById(1L)).thenReturn(java.util.Optional.of(vehiculo));
        when(vehiculoRepository.findAll()).thenReturn(Collections.singletonList(vehiculo)); // Mock findAll()

        vehiculoService.saveVehiculo(vehiculo);
        assertThat(vehiculoService.getVehiculos().size()).isEqualTo(1);
    }
}