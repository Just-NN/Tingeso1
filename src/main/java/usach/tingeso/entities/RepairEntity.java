package usach.tingeso.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idRepair;

    private Long idTicket;

    private Long licensePlate;

    private int repairType;
    private Calendar entryDate;
    private Calendar exitDate;
    private LocalTime exitTime;

    private Calendar pickupDate;

    private LocalTime pickupTime;
    private Long totalRepairAmount;

    private double kmSurcharge;
    private double ageSurcharge;
    private double delaySurcharge;

    private double dayDiscount;
    private double repairsDiscount;

    private int basePrice;
    private int totalPrice;

    public LocalTime getEntryTime(){
        if (this.entryDate == null) {
            // return a default value or throw an exception
            return null;
        }
        ZonedDateTime zdt = this.entryDate.toInstant().atZone(ZoneId.systemDefault());
        return zdt.toLocalTime();
    }

    public void calculateDifferenceInDays() {
        if (this.entryDate == null || this.exitDate == null) {
            // Handle null entryDate or exitDate as per your application's requirements
            return;
        }
        long diffInMillies = Math.abs(this.exitDate.getTimeInMillis() - this.entryDate.getTimeInMillis());
        this.totalRepairAmount = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}