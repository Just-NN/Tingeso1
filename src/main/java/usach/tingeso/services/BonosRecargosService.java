package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@Service
public class BonosRecargosService {

    // Este servicio existe para poder realizar los cálculos de recargos y descuentos de las boletas

    @Autowired
    ReparacionService reparacionService;


    // Calcula el precio base
    public int calcularPrecioBase(ReparacionEntity reparacion) {
        int tipoMotor = reparacion.getVehiculoEntity().getTipoMotor();
        int tipoReparacion = reparacion.getTipoReparacion();
        if (tipoReparacion >= 1 && tipoReparacion <= 11) {
            System.out.printf("Tipo de reparación: %d\n", tipoReparacion);
            if (tipoMotor >= 0 && tipoMotor <= 3) {
                System.out.println("Tipo de motor: " + tipoMotor);
                switch (tipoReparacion) {
                    case 1: // Caso Mantención
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 120000;
                            case 2: // Caso Híbrido
                                return 180000;
                            case 3: // Caso Eléctrico
                                return 220000;
                        }
                    case 2: // Caso Frenos
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 130000;
                            case 2: // Caso Híbrido
                                return 190000;
                            case 3: // Caso Eléctrico
                                return 230000;
                        }
                    case 3: // Caso Aceite
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                                return 350000;
                            case 1: // Caso Diésel
                                return 450000;
                            case 2: // Caso Híbrido
                                return 700000;
                            case 3: // Caso Eléctrico
                                return 800000;
                        }
                        return 60000;
                    case 4: // Caso Neumáticos
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 210000;
                            case 2: // Caso Híbrido
                            case 3: // Caso Eléctrico
                                return 300000;
                        }
                    case 5: // Caso Batería
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 150000;
                            case 2: // Caso Híbrido
                                return 200000;
                            case 3: // Caso Eléctrico
                                return 250000;
                        }
                    case 6: // Caso Suspensión
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                                return 100000;
                            case 1: // Caso Diésel
                                return 120000;
                            case 2: // Caso Híbrido
                                return 450000;
                            case 3: // Caso Eléctrico
                                return 0;
                        }
                    case 7: // Caso Dirección
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 80000;
                            case 2: // Caso Híbrido
                            case 3: // Caso Eléctrico
                                return 100000;
                        }
                    case 8: // Caso Embrague
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 180000;
                            case 2: // Caso Híbrido
                                return 210000;
                            case 3: // Caso Eléctrico
                                return 250000;
                        }
                    case 9:
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                            case 1: // Caso Diésel
                                return 150000;
                            case 2: // Caso Híbrido
                            case 3: // Caso Eléctrico
                                return 180000;
                        }
                    case 10:
                        switch (tipoMotor) {
                            case 0: // Caso Gasolina
                                return 130000;
                            case 1: // Caso Diésel
                                return 140000;
                            case 2: // Caso Híbrido
                                return 220000;
                            case 3: // Caso Eléctrico
                                return 0;
                        }
                    case 11:
                        return 80000;
                }

            }
        }
        return -1;
    }





    // Calcula cada recargo

    // Calcula recargo por kilometraje
    public double calcularRecargoPorKM(ReparacionEntity reparacion){
        VehiculoEntity vehiculo = reparacion.getVehiculoEntity();
        double recargoKM = 0;
        int kilometraje = vehiculo.getKilometraje();
        int tipoVehiculo = vehiculo.getTipoVehiculo();
        if(kilometraje > 5000){
            switch (tipoVehiculo){
                case 0:// Caso Sedan/Hatchback y sus rangos de kilometraje
                case 1:
                    if (kilometraje<12000){
                        recargoKM = 0.03;
                    }
                    else if (kilometraje>12000 && kilometraje<25000){
                        recargoKM = 0.07;
                    }
                    else if (kilometraje>25000 && kilometraje<40000){
                        recargoKM = 0.12;
                    }
                    else{
                        recargoKM = 0.2;
                    }
                case 2: // Caso SUV/Pickup/Furgoneta
                case 3:
                case 4:
                    if (kilometraje<12000){
                        recargoKM = 0.05;
                    }
                    else if (kilometraje>12000 && kilometraje<25000){
                        recargoKM = 0.09;
                    }
                    else if (kilometraje>25000 && kilometraje<40000){
                        recargoKM = 0.12;
                    }
                    else{
                        recargoKM = 0.2;
                    }
            }
        }
        else if(kilometraje < 0){
            recargoKM = -1;
        }
        return recargoKM;
    }
    public double calcularRecargoPorAntiguedad(ReparacionEntity reparacion) {
        VehiculoEntity vehiculo = reparacion.getVehiculoEntity();
        double recargoAntiguedad = 0;
        int antiguedad = Calendar.getInstance().get(Calendar.YEAR) - vehiculo.getAno();
        int tipo = vehiculo.getTipoVehiculo();
        if (antiguedad < 0) {
            recargoAntiguedad = -1;
        }
        else if (antiguedad > 5) {
            switch (tipo) {
                case 0:// Caso Sedan/Hatchback y sus rangos de kilometraje
                case 1:
                    if (antiguedad < 10) {
                        recargoAntiguedad = 0.05;
                    } else if (antiguedad > 10 && antiguedad < 15) {
                        recargoAntiguedad = 0.09;
                    } else {
                        recargoAntiguedad = 0.15;
                    }
                    break; // Add this
                case 2: // Caso SUV/Pickup/Furgoneta
                case 3:
                case 4:
                    if (antiguedad < 12) {
                        recargoAntiguedad = 0.07;
                    } else if (antiguedad > 12 && antiguedad < 25) {
                        recargoAntiguedad = 0.11;
                    } else {
                        recargoAntiguedad = 0.2;
                    }
                    break; // Add this
            }
        }
        return recargoAntiguedad;
    }
    public double calcularRecargoPorRetraso(ReparacionEntity reparacion){
        long retraso = ChronoUnit.DAYS.between(reparacion.getFechaIngreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                reparacion.getFechaRetiro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return (retraso*0.05);
    }

    // Calcula cada descuento
    public double calcularDescuentoPorReparaciones(ReparacionEntity reparacion){
        VehiculoEntity vehiculo = reparacion.getVehiculoEntity();
        List<ReparacionEntity> reparaciones = reparacionService.getReparacionesVehiculoEsteAno(vehiculo);
        int cantidadReparaciones = reparaciones.size();
        int tipoMotor = vehiculo.getTipoMotor();
        if (cantidadReparaciones > 0){
            switch (tipoMotor){
                case 0: // Caso Gasolina
                    if (cantidadReparaciones < 3){
                        return 0.05;
                    }
                    else if (cantidadReparaciones >= 3 && cantidadReparaciones < 6){
                        return 0.10;
                    }
                    else if (cantidadReparaciones >= 6 && cantidadReparaciones < 10){
                        return 0.15;
                    }
                    else {
                        return 0.2;
                    }
                case 1: // Caso Diésel
                    if (cantidadReparaciones < 3){
                        return 0.07;
                    }
                    else if (cantidadReparaciones >= 3 && cantidadReparaciones < 6){
                        return 0.12;
                    }
                    else if (cantidadReparaciones >= 6 && cantidadReparaciones < 10){
                        return 0.17;
                    }
                    else {
                        return 0.22;
                    }
                case 2: // Caso Híbrido
                    if (cantidadReparaciones < 3){
                        return 0.1;
                    }
                    else if (cantidadReparaciones >= 3 && cantidadReparaciones < 6){
                        return 0.15;
                    }
                    else if (cantidadReparaciones >= 6 && cantidadReparaciones < 10){
                        return 0.2;
                    }
                    else {
                        return 0.25;
                    }
                case 3: // Caso Eléctrico
                    if (cantidadReparaciones < 3){
                        return 0.08;
                    }
                    else if (cantidadReparaciones >= 3 && cantidadReparaciones < 6){
                        return 0.13;
                    }
                    else if (cantidadReparaciones >= 6 && cantidadReparaciones < 10){
                        return 0.18;
                    }
                    else {
                        return 0.23;
                    }
            }
        }
        return 0;
    }
    public double calcularDescuentoPorDia(ReparacionEntity reparacion){
        Calendar fechaIngreso = Calendar.getInstance();
        fechaIngreso.setTime(reparacion.getFechaIngreso().getTime()); // Obtén el objeto Date del Calendar
        int diaIngreso = fechaIngreso.get(Calendar.DAY_OF_WEEK);
        int horaIngreso = fechaIngreso.get(Calendar.HOUR_OF_DAY);
        if (diaIngreso >= Calendar.MONDAY && diaIngreso <= Calendar.THURSDAY){
            if (horaIngreso >= 9 && horaIngreso <= 12){
                return 0.1;
            }
        }
        return 0;
}

    public double calcularPrecioTotal(BoletaEntity boleta){
        double recargoKM = boleta.getRecargoPorKM();
        double recargoAntiguedad = boleta.getRecargoPorAntiguedad();
        double recargoRetraso = boleta.getRecargoPorRetraso();
        double descuentoReparaciones = boleta.getDescuentoPorReparaciones();
        double descuentoDia = boleta.getDescuentoPorDia();
        double precioFinal = 0;
        if (recargoKM == -1 || recargoAntiguedad == -1 || descuentoReparaciones == -1){
            return -1;
        }
        else{
            precioFinal = boleta.getPrecioBase() + (recargoKM + recargoAntiguedad + recargoRetraso) - (descuentoReparaciones + descuentoDia);
        }
        return precioFinal;
    }







}
