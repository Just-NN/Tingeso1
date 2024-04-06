package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.VehiculoEntity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;


@Service
public class PrecioService {

    public final ReparacionService reparacionService;
    @Autowired
    public PrecioService(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    // Calcula cada recargo

    // Calcula recargo por kilometraje
    public double getRecargoPorKM(VehiculoEntity vehiculo){
        double recargoKM = 0;
        int kilometraje = vehiculo.getKilometraje();
        int tipoVehiculo = vehiculo.getTipoVehiculo();
        if(kilometraje > 5000){
            switch (tipoVehiculo){
                case 0:// Caso Sedan/Hatchback y sus rangos de kilometraje
                case 1:
                    if (kilometraje<12000){
                        recargoKM = 1+0.03;
                    }
                    else if (kilometraje>12000 && kilometraje<25000){
                        recargoKM = 1+0.07;
                    }
                    else if (kilometraje>25000 && kilometraje<40000){
                        recargoKM = 1+0.12;
                    }
                    else{
                        recargoKM = 1+0.2;
                    }
                case 2: // Caso SUV/Pickup/Furgoneta
                case 3:
                case 4:
                    if (kilometraje<12000){
                        recargoKM = 1+0.05;
                    }
                    else if (kilometraje>12000 && kilometraje<25000){
                        recargoKM = 1+0.09;
                    }
                    else if (kilometraje>25000 && kilometraje<40000){
                        recargoKM = 1+0.12;
                    }
                    else{
                        recargoKM = 1+0.2;
                    }
            }
        }
        else if(kilometraje < 0){
            recargoKM = -1;
        }
        return recargoKM;
    }
    public double recargoPorAntiguedad(VehiculoEntity vehiculo, ReparacionEntity reparacion) {
        double recargoAntiguedad = 0;
        int antiguedad = reparacion.getFechaIngreso().getYear() - vehiculo.getAno();
        if (antiguedad < 0) {
            recargoAntiguedad = -1;
        } else if (antiguedad > 5) {
            switch (antiguedad) {
                case 0:// Caso Sedan/Hatchback y sus rangos de kilometraje
                case 1:
                    if (antiguedad < 10) {
                        recargoAntiguedad = 1+0.05;
                    } else if (antiguedad > 10 && antiguedad < 15) {
                        recargoAntiguedad = 1+0.09;
                    } else {
                        recargoAntiguedad = 1+0.15;
                    }
                case 2: // Caso SUV/Pickup/Furgoneta
                case 3:
                case 4:
                    if (antiguedad < 12000) {
                        recargoAntiguedad = 1+0.07;
                    } else if (antiguedad > 12000 && antiguedad < 25000) {
                        recargoAntiguedad = 1+0.11;
                    } else {
                        recargoAntiguedad = 1+0.2;
                    }
            }
        }
        return recargoAntiguedad;
    }
    public double recargoPorRetraso(ReparacionEntity reparacion){
        int retraso = reparacion.getFechaIngreso().getDay() - reparacion.getFechaRetiro().getDay();
        return 1+(retraso*0.05);
    }

    public double descuentoPorReparaciones(ReparacionEntity reparacion, VehiculoEntity vehiculo){
        List<ReparacionEntity> reparaciones = reparacionService.getReparacionesVehiculoEsteAno(vehiculo);
        int cantidadReparaciones = reparaciones.size();
        int tipoMotor = vehiculo.getTipoMotor();

        if (cantidadReparaciones > 0){
            switch (tipoMotor){
                case 0: // Caso Gasolina
                    if (cantidadReparaciones < 3){
                        return 1-0.05;
                    }
                    else if (cantidadReparaciones < 6 && cantidadReparaciones > 3){
                        return 1-0.10;
                    }
                    else if (cantidadReparaciones < 10 && cantidadReparaciones > 6){
                        return 1-0.15;
                    }
                    else {
                        return 1-0.2;
                    }
                case 1: // Caso Diésel
                    if (cantidadReparaciones < 3){
                        return 1-0.07;
                    }
                    else if (cantidadReparaciones < 6 && cantidadReparaciones > 3){
                        return 1-0.12;
                    }
                    else if (cantidadReparaciones < 10 && cantidadReparaciones > 6){
                        return 1-0.17;
                    }
                    else {
                        return 1-0.22;
                    }
                case 2: // Caso Híbrido
                    if (cantidadReparaciones < 3){
                        return 1-0.1;
                    }
                    else if (cantidadReparaciones < 6 && cantidadReparaciones > 3){
                        return 1-0.15;
                    }
                    else if (cantidadReparaciones < 10 && cantidadReparaciones > 6){
                        return 1-0.2;
                    }
                    else {
                        return 1-0.25;
                    }
                case 3: // Caso Eléctrico
                    if (cantidadReparaciones < 3){
                        return 1-0.08;
                    }
                    else if (cantidadReparaciones < 6 && cantidadReparaciones > 3){
                        return 1-0.13;
                    }
                    else if (cantidadReparaciones < 10 && cantidadReparaciones > 6){
                        return 1-0.18;
                    }
                    else {
                        return 1-0.23;
                    }
            }
        }

        else{
            return -1;
        }
        return 0;
    }
    public double descuentoPorDia(ReparacionEntity reparacion){
        DayOfWeek diaIngreso = reparacion.getDiaDeSemana();
        LocalTime horaIngreso = reparacion.getHoraIngreso();
        if (diaIngreso.getValue() <= DayOfWeek.THURSDAY.getValue() && diaIngreso.getValue() >= DayOfWeek.MONDAY.getValue()){
            if (horaIngreso.getHour() >= 9 && horaIngreso.getHour() <= 12){
                return 1-0.1;
            }
        }
        return 0;
    }
}

