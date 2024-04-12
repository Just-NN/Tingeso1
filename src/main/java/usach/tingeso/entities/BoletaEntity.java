package usach.tingeso.entities;

import jakarta.annotation.Nullable;
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
@Table(name = "boleta")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class BoletaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idBoleta;

    @OneToOne(mappedBy = "boletaEntity")
    @PrimaryKeyJoinColumn
    private ReparacionEntity reparacionEntity;

    private double recargoPorKM;
    private double recargoPorAntiguedad;
    private double recargoPorRetraso; // estas 3 líneas podrían irse a
    private double descuentoPorReparaciones;
    private double descuentoPorDia;
    private double descuentoPorBono;// una 3era entidad que me facilite las cosas
    private int precioBase; //
    private double precioTotal;
}
