package usach.tingeso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    /// Boleta y Reparación compartirán ID así que no lo usaré aquí

    private int recargoPorKM;
    private int recargoPorAntiguedad;
    private int recargoPorRetraso; // estas 3 líneas podrían irse a
    private int descuentoPorReparaciones;
    private int descuentoPorDia;
    private int descuentoPorBono;// una 3era entidad que me facilite las cosas
    private int precioBase; //
    private int precioTotal;
}
