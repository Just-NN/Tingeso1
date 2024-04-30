package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;

import java.util.Calendar;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    TicketEntity findTicketByIdTicket(Long id);

    @Query("SELECT t.surchargeForKM, t.surchargeForAge, t.surchargeForDelay, t.discountForRepairs, t.discountPerDay, t.discountForBonus, t.brandBonus, t.basePrice, t.totalPrice FROM TicketEntity t WHERE t.idTicket = :id")
    Object[] getTicketValues(@Param("id") Long id);


}
