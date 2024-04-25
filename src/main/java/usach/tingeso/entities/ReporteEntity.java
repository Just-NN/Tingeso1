package usach.tingeso.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idReporte;


    @Column(columnDefinition = "TEXT")
    private String r1Detalles;

    @Column(columnDefinition = "TEXT")
    private String r2ReparacionesVsVehiculosPorTipoTotal;

    @Column(columnDefinition = "TEXT")
    private String r3PromedioPorMarca;

    @Column(columnDefinition = "TEXT")
    private String r4ReparacionesVsVehiculosPorMotorTotal;
}
