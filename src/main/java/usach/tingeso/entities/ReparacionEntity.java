package usach.tingeso.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "reparacion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReparacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idReparacion;
    
    @ManyToOne
    @JoinColumn(name = "patenteVehiculo")
    private VehiculoEntity vehiculoEntity;

    private Date fechaIngreso;
    private Date fechaSalida;
    private LocalTime horaSalida;

    private Date fechaRetiro;

    private LocalTime horaRetiro;

    private int recargos;
    private int descuentos;
    private int precio_total;


    public DayOfWeek getDiaDeSemana(){
        return fechaIngreso.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getDayOfWeek();
    }

    public LocalTime getHoraIngreso(){
        ZonedDateTime zdt = this.fechaIngreso.toInstant().atZone(ZoneId.systemDefault());
        return zdt.toLocalTime();
    }

}
