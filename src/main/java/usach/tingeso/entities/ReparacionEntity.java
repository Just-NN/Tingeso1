package usach.tingeso.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "patente")
    private VehiculoEntity vehiculoEntity;

    @Getter
    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "idBoleta", nullable = true)
    private BoletaEntity boletaEntity;

    private Date fechaIngreso;
    private Date fechaSalida;
    private LocalTime horaSalida;

    private Date fechaRetiro;

    private LocalTime horaRetiro;

    // Estos atributos me hacen pensar en una 3era entidad: boleta - a considerar



    public LocalTime getHoraIngreso(){
    if (this.fechaIngreso == null) {
        // return a default value or throw an exception
        return null;
    }
    ZonedDateTime zdt = this.fechaIngreso.toInstant().atZone(ZoneId.systemDefault());
    return zdt.toLocalTime();
}

public DayOfWeek getDiaDeSemana(){
    if (this.fechaIngreso == null) {
        // return a default value or throw an exception
        return null;
    }
    return fechaIngreso.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getDayOfWeek();
}

}