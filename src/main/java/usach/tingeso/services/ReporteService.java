package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.BoletaEntity;
import usach.tingeso.entities.ReparacionEntity;
import usach.tingeso.entities.ReporteEntity;
import usach.tingeso.entities.VehiculoEntity;
import usach.tingeso.repositories.BoletaRepository;
import usach.tingeso.repositories.ReparacionRepository;
import usach.tingeso.repositories.ReporteRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    ReporteRepository reporteRepository;
    @Autowired
    private BoletaRepository boletaRepository;
    @Autowired
    private BoletaService boletaService;

    // CRUD básico

    public List<ReporteEntity> getReportes(){
        return (List<ReporteEntity>) reporteRepository.findAll();
    }
    public ReporteEntity getReporteById(Long id){
        return reporteRepository.findById(id).orElse(null);
    }

    public ReporteEntity saveReporte(ReporteEntity reporte){
        return reporteRepository.save(reporte);
    }
    public ReporteEntity updateReporte(ReporteEntity reporte){
        return reporteRepository.save(reporte);
    }
    public boolean deleteReporte(Long id){
        reporteRepository.deleteById(id);
        return true;
    }

    //--------------------------------------------------------------------------------------------------------------



    //--------------------------------------------------------------------------------------------------------------
    public ReporteEntity saveR1(ReporteEntity reporte){
        if (reporte == null) {
            throw new IllegalArgumentException("ReporteEntity cannot be null");
        }
        List<BoletaEntity> boletas = boletaService.getBoletas();

        if (boletas == null) {
            System.out.println("BOLETA NULA");
            throw new IllegalArgumentException("BoletaEntity cannot be null");
        }
        String boletasString = boletas.stream()
                .map(BoletaEntity::toString)
                .collect(Collectors.joining(",\n "));
        System.out.println("BOLETAS STRING: " + boletasString);
        reporte.setR1Detalles(boletasString);
        return reporteRepository.save(reporte);
    }

    public ReporteEntity saveR2(ReporteEntity reporte){
        if (reporte == null) {
            System.out.println("REPORTE NULO");
            throw new IllegalArgumentException("ReporteEntity cannot be null");
        }
        List<Object[]> repairs = reporteRepository.findRepairTypesVsVehicleTypesAndTotalAmount();
        if (repairs == null) {
            System.out.println("REPARACION NULA");
            throw new IllegalArgumentException("Repairs cannot be null");
        }
        List<String> detailsList = repairs.stream()
                .filter(result -> result != null)
                .map(result -> "[Tipo de reparacion: " + result[0] + ", Total de reparaciones: " + result[1] + "]\n")
                .collect(Collectors.toList());
        System.out.println("DETAILS LIST: " + detailsList);

        String details = String.join("; ", detailsList);
        reporte.setR2ReparacionesVsVehiculosPorTipoTotal(details);
        return reporteRepository.save(reporte);
    }

    public ReporteEntity saveR3(ReporteEntity reporte){
        if (reporte == null) {
            throw new IllegalArgumentException("ReporteEntity cannot be null");
        }
        List<Object[]> averageRepairTimes = reporteRepository.findAverageRepairTimePerBrand();
        if (averageRepairTimes == null) {
            throw new IllegalArgumentException("Average repair times cannot be null");
        }
        List<String> detailsList = averageRepairTimes.stream()
                .filter(result -> result != null)
                .map(result -> "Marca: " + result[0] + ", Promedio de tiempo de reparación: " + result[1] + "\n")
                .collect(Collectors.toList());
        String details = String.join("; ", detailsList);

        reporte.setR3PromedioPorMarca(details);
        return reporteRepository.save(reporte);
    }



    public ReporteEntity saveR4(ReporteEntity reporte){
        if (reporte == null) {
            throw new IllegalArgumentException("ReporteEntity cannot be null");
        }
        List<Object[]> repairs = reporteRepository.findRepairTypesVsEngineTypesAndTotalAmount();
        if (repairs == null) {
            throw new IllegalArgumentException("Repairs cannot be null");
        }
        List<String> detailsList = repairs.stream()
                .filter(result -> result != null)
                .map(result -> "Tipo de reparación: " + result[0] + ", Cantidad de este tipo: " + result[1] + ", Tipo de Motor " + result[2] + ", Monto total: " + result[3] + "\n")
                .collect(Collectors.toList());

        String details = String.join("; ", detailsList);
        reporte.setR4ReparacionesVsVehiculosPorMotorTotal(details);
        return reporteRepository.save(reporte);
    }


}
