package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.BoletaRepository;
import usach.tingeso.repositories.ReparacionRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BonosRecargosServiceTest {

    @Autowired
    BonosRecargosService bonosRecargosService;

    @MockBean
    BoletaRepository boletaRepository;
    @MockBean
    ReparacionRepository reparacionRepository;
    @Autowired
    private ReparacionService reparacionService;

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM03() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(6000);
        vehiculo.setTipoVehiculo(1);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.05);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM07() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(13000);
        vehiculo.setTipoVehiculo(1);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.09);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM12() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(26000);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.12);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM20() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(41000);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.20);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM05() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(6000);
        vehiculo.setTipoVehiculo(2);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.05);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM09() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(13000);
        vehiculo.setTipoVehiculo(2);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.09);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM12_2() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(26000);
        vehiculo.setTipoVehiculo(2);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.12);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKM20_2() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(41000);
        vehiculo.setTipoVehiculo(2);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0.20);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKMError() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(-100);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(-1);
    }

    @Test
    public void whenCalcularRecargoPorKM_thenRecargoKMZero() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setKilometraje(5000);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoKM = bonosRecargosService.calcularRecargoPorKM(reparacion);
        assertThat(recargoKM).isEqualTo(0);
    }


    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad07() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2018);
        vehiculo.setTipoVehiculo(4);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.07);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad05() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2016);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.05);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad09() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2012);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.09);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad11() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2010);
        vehiculo.setTipoVehiculo(4);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.11);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad15() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2005);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.15);
    }

    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad20() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(1990);
        vehiculo.setTipoVehiculo(4);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0.20);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedadERROR() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2030);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(-1);
    }
    @Test
    public void whenCalcularRecargoPorAntiguedad_thenRecargoAntiguedad0() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setAno(2024);
        vehiculo.setTipoVehiculo(0);
        reparacion.setVehiculoEntity(vehiculo);

        double recargoAntiguedad = bonosRecargosService.calcularRecargoPorAntiguedad(reparacion);
        assertThat(recargoAntiguedad).isEqualTo(0);
    }

    @Test
    public void whenCalcularRecargoPorRetraso_thenRecargoRetraso() {
        ReparacionEntity reparacion = new ReparacionEntity();
        Calendar fechaIngreso = new GregorianCalendar(2020, Calendar.OCTOBER, 10);
        Calendar fechaRetiro = new GregorianCalendar(2020, Calendar.OCTOBER, 15);
        reparacion.setFechaIngreso(fechaIngreso);
        reparacion.setFechaRetiro(fechaRetiro);

        double recargoRetraso = bonosRecargosService.calcularRecargoPorRetraso(reparacion);
        assertThat(recargoRetraso).isEqualTo(0.25);
    }

//    @Test
//    public void whenCalcularDescuentoPorReparaciones_thenDescuentoReparaciones() {
//        ReparacionEntity reparacion = new ReparacionEntity();
//        VehiculoEntity vehiculo = new VehiculoEntity();
//        vehiculo.setTipoMotor(0);
//        reparacion.setVehiculoEntity(vehiculo);
//
//        when(reparacionRepository.countByVehiculoEntityAndFechaIngresoYear(vehiculo, Calendar.getInstance().get(Calendar.YEAR))).thenReturn(4L);
//
//        double descuentoReparaciones = bonosRecargosService.calcularDescuentoPorReparaciones(reparacion);
//        assertThat(descuentoReparaciones).isEqualTo(0.10);
//    }

    @Test
    public void whenCalcularDescuentoPorDia_thenDescuentoDia01() {
        ReparacionEntity reparacion = new ReparacionEntity();
        Calendar fechaIngreso = new GregorianCalendar(2020, Calendar.OCTOBER, 12, 10, 0);
        reparacion.setFechaIngreso(fechaIngreso);

        double descuentoDia = bonosRecargosService.calcularDescuentoPorDia(reparacion);
        assertThat(descuentoDia).isEqualTo(0.1);
    }
    @Test
    public void whenCalcularDescuentoPorDia_thenDescuentoDia00() {
        ReparacionEntity reparacion = new ReparacionEntity();
        Calendar fechaIngreso = new GregorianCalendar(2020, Calendar.OCTOBER, 10, 10, 0);
        reparacion.setFechaIngreso(fechaIngreso);

        double descuentoDia = bonosRecargosService.calcularDescuentoPorDia(reparacion);
        assertThat(descuentoDia).isEqualTo(0.0);
    }
    @Test
    public void whenCalcularDescuentoPorDia_thenDescuentoDia02() {
        ReparacionEntity reparacion = new ReparacionEntity();
        Calendar fechaIngreso = new GregorianCalendar(2020, Calendar.OCTOBER, 11, 15, 0);
        reparacion.setFechaIngreso(fechaIngreso);

        double descuentoDia = bonosRecargosService.calcularDescuentoPorDia(reparacion);
        assertThat(descuentoDia).isEqualTo(0.0);
    }

    @Test
    public void whenCalcularDescuentoPorReparaciones_thenDescuento00() {
        ReparacionEntity reparacion = new ReparacionEntity();
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setTipoMotor(0);
        reparacion.setVehiculoEntity(vehiculo);

        when(reparacionService.getReparacionesVehiculoEsteAno(vehiculo)).thenReturn(Arrays.asList());

        double descuentoReparaciones = bonosRecargosService.calcularDescuentoPorReparaciones(reparacion);
        assertThat(descuentoReparaciones).isEqualTo(0.0);
    }
    @Test
    public void whenCalcularDescuentoPorReparaciones_thenDescuento05() {
        ReparacionEntity reparacion = new ReparacionEntity();
        reparacion.setFechaIngreso(new GregorianCalendar(2024, Calendar.JANUARY, 1));
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setTipoMotor(0);
        reparacion.setVehiculoEntity(vehiculo);

        when(reparacionService.getReparacionesVehiculoEsteAno(vehiculo)).thenReturn(Arrays.asList(new ReparacionEntity(), new ReparacionEntity(), new ReparacionEntity(), new ReparacionEntity()));

        double descuentoReparaciones = bonosRecargosService.calcularDescuentoPorReparaciones(reparacion);
        assertThat(descuentoReparaciones).isEqualTo(0.05);
    }



    @Test
    public void whenCalcularPrecioTotal_thenPrecioTotal() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setPrecioBase(1000);
        boleta.setRecargoPorKM(30); // 3% of base price
        boleta.setRecargoPorAntiguedad(50); // 5% of base price
        boleta.setRecargoPorRetraso(250); // 25% of base price
        boleta.setDescuentoPorReparaciones(100); // 10% of base price
        boleta.setDescuentoPorDia(100); // 10% of base price

        double precioTotal = bonosRecargosService.calcularPrecioTotal(boleta);
        assertThat(precioTotal).isEqualTo(1000 + (0.03*1000 + 0.05*1000 + 0.25*1000) - (0.10*1000 + 0.10*1000));
    }

    @Test
    public void whenCalcularPrecioTotal_thenPrecioTotalError() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setPrecioBase(1000);
        boleta.setRecargoPorKM(-1);
        boleta.setRecargoPorAntiguedad(5);
        boleta.setRecargoPorRetraso(25);
        boleta.setDescuentoPorReparaciones(10);
        boleta.setDescuentoPorDia(10);

        double precioTotal = bonosRecargosService.calcularPrecioTotal(boleta);
        assertThat(precioTotal).isEqualTo(-1);
    }


    @Test
    public void whenCalcularPrecioTotal_thenPrecioTotalZero() {
        BoletaEntity boleta = new BoletaEntity();
        boleta.setPrecioBase(1000);
        boleta.setRecargoPorKM(0);
        boleta.setRecargoPorAntiguedad(0);
        boleta.setRecargoPorRetraso(0);
        boleta.setDescuentoPorReparaciones(0);
        boleta.setDescuentoPorDia(0);

        double precioTotal = bonosRecargosService.calcularPrecioTotal(boleta);
        assertThat(precioTotal).isEqualTo(1000);
    }

//    @Test
//    public void whenCalcularPrecioTotal_thenPrecioTotal() {
//        BoletaEntity boleta = new BoletaEntity();
//        boleta.setPrecioBase(1000);
//        boleta.setRecargoPorKM(0.03);
//        boleta.setRecargoPorAntiguedad(0.05);
//        boleta.setRecargoPorRetraso(0.25);
//        boleta.setDescuentoPorReparaciones(0.10);
//        boleta.setDescuentoPorDia(0.1);
//
//        double precioTotal = bonosRecargosService.calcularPrecioTotal(boleta);
//        assertThat(precioTotal).isEqualTo(1000 + (0.03 + 0.05 + 0.25) - (0.10 + 0.1));
//    }
}