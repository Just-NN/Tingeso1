package usach.tingeso.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTicket;

    // Using this value i can verify that the repairs are related to this ticket
    Date pickupDate;

    private Long idBonus;

    // Other fields related to prices and discounts
    private int surchargeForKM;
    private int surchargeForAge;
    private int surchargeForDelay;
    private int discountForRepairs;
    private int discountPerDay;
    private int discountForBonus;
    private int brandBonus;
    private int basePrice;
    private int totalPrice;

}