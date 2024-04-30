package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.BonusBrandEntity;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.BonusBrandRepository;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.repositories.TicketRepository;
import usach.tingeso.repositories.VehicleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private RepairService repairService;
    @Autowired
    private InternalCalculationService internalCalculationService;
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private BonusBrandRepository bonusBrandRepository;

    // Basic CRUD operations
    public TicketEntity getTicketById(Long id){
        if (id == null) {
            // Handle the case where id is null
            return null;
        }
        return ticketRepository.findById(id).orElse(null);
    }

    public TicketEntity saveTicket(TicketEntity ticket){
        if (ticket == null)
            return null;
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id){
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        }
    }

    public TicketEntity updateTicket(TicketEntity ticket){
        if (ticketRepository.existsById(ticket.getIdTicket())) {
            return ticketRepository.save(ticket);
        }
        return null;
    }

    // Get all tickets
    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> tickets = ticketRepository.findAll();
        if (tickets == null) {
            return new ArrayList<>(); // return an empty list instead of null
        }
        return tickets;
    }
    //------------------------------------------------------------------------------------------------------------
    // Get all values for R1 by ticket
    public String getTicketValues(TicketEntity ticket){
        Object[] values = ticketRepository.getTicketValues(ticket.getIdTicket());
        return Arrays.stream(values)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
    // Get all values for R1
    public List<String> getAllTicketValues() {
        List<TicketEntity> allTickets = ticketRepository.findAll();
        List<String> allTicketValues = new ArrayList<>();
        for (TicketEntity ticket : allTickets) {
            String ticketValues = ticket.toString();
            System.out.println("Ticket values: " + ticketValues);
            allTicketValues.add(ticketValues);
        }
        return allTicketValues;
    }


    //------------------------------------------------------------------------------------------------------------

    //Operations
    // Save base price
    public TicketEntity saveBasePrice(TicketEntity ticket){
        if (ticket == null) {
            // Handle the case where ticket is null
            return null;
        }

        Long ticketId = ticket.getIdTicket();
        System.out.println("Ticket ID: " + ticketId);

        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticketId);
        System.out.println("Repairs: " + repairs.size());

        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticketId);
        } else {
            for (RepairEntity repair : repairs) {
                System.out.println("Found repair with ID: " + repair.getIdRepair() + " for ticket ID: " + ticketId);
            }
        }

        double totalBasePrice = 0;

        for (RepairEntity repair : repairs) {
            double basePrice = internalCalculationService.calculateBasePrice(repair);
            totalBasePrice += basePrice;
        }

        ticket.setBasePrice((int) Math.round(totalBasePrice));
        return ticketRepository.save(ticket);
    }

    // Save KM surcharge
    public TicketEntity saveKMSurcharge(TicketEntity ticket){
        if (ticket == null) {
            // Handle the case where ticket is null
            return null;
        }

        Long ticketId = ticket.getIdTicket();
        System.out.println("Ticket ID: " + ticketId);

        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticketId);
        System.out.println("Repairs: " + repairs.size());

        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticketId);
        } else {
            for (RepairEntity repair : repairs) {
                System.out.println("Found repair with ID: " + repair.getIdRepair() + " for ticket ID: " + ticketId);
            }
        }

        double totalKMSurcharge = 0;

        for (RepairEntity repair : repairs) {
            double kmSurcharge = internalCalculationService.calculateSurchargeForKM(repair);
            System.out.println("KM surcharge: " + kmSurcharge);
            totalKMSurcharge = kmSurcharge;
        }

        ticket.setSurchargeForKM((int) Math.round(totalKMSurcharge * ticket.getBasePrice()));
        return ticketRepository.save(ticket);
    }

    // Save Age surcharge
    public TicketEntity saveAgeSurcharge(TicketEntity ticket){
        if (ticket == null) {
            // Handle the case where ticket is null
            return null;
        }

        Long ticketId = ticket.getIdTicket();
        System.out.println("Ticket ID: " + ticketId);

        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticketId);
        System.out.println("Repairs: " + repairs.size());

        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticketId);
        } else {
            for (RepairEntity repair : repairs) {
                System.out.println("Found repair with ID: " + repair.getIdRepair() + " for ticket ID: " + ticketId);
            }
        }

        double totalAgeSurcharge = 0;

        for (RepairEntity repair : repairs) {
            double ageSurcharge = internalCalculationService.calculateSurchargeByAge(repair);
            System.out.println("Age surcharge: " + ageSurcharge);
            totalAgeSurcharge = ageSurcharge;
        }

        ticket.setSurchargeForAge((int) Math.round(totalAgeSurcharge * ticket.getBasePrice()));
        return ticketRepository.save(ticket);
    }

    // Save Surchage per day
    public TicketEntity saveSurchargeForDelay(TicketEntity ticket){
        if (ticket == null) {
            // Handle the case where ticket is null
            return null;
        }

        Long ticketId = ticket.getIdTicket();
        System.out.println("Ticket ID: " + ticketId);

        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticketId);
        System.out.println("Repairs: " + repairs.size());

        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticketId);
        } else {
            for (RepairEntity repair : repairs) {
                System.out.println("Found repair with ID: " + repair.getIdRepair() + " for ticket ID: " + ticketId);
            }
        }

        double totalDelaySurcharge = 0;

        for (RepairEntity repair : repairs) {
            double delaySurcharge = internalCalculationService.calculateSurchargeForDelay(repair);
            System.out.println("Delay surcharge: " + delaySurcharge);
            totalDelaySurcharge = delaySurcharge;
        }

        ticket.setSurchargeForDelay((int) Math.round(totalDelaySurcharge * ticket.getBasePrice()));
        return ticketRepository.save(ticket);
    }

    // Save discount for repairs
    public TicketEntity saveDiscountByRepairs(TicketEntity ticket){
        if (ticket == null) {
            // Handle the case where ticket is null
            return null;
        }

        Long ticketId = ticket.getIdTicket();
        System.out.println("Ticket ID: " + ticketId);

        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticketId);
        System.out.println("Repairs: " + repairs.size());

        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticketId);
        } else {
            for (RepairEntity repair : repairs) {
                System.out.println("Found repair with ID: " + repair.getIdRepair() + " for ticket ID: " + ticketId);
            }
        }

        double totalDiscountByRepairs = 0;

        for (RepairEntity repair : repairs) {
            double discountByRepairs = internalCalculationService.calculateDiscountByRepairs(repair);
            System.out.println("Discount by repairs: " + discountByRepairs);
            totalDiscountByRepairs = discountByRepairs;
        }

        ticket.setDiscountForRepairs((int) Math.round(totalDiscountByRepairs * ticket.getBasePrice()));
        return ticketRepository.save(ticket);
    }

    // Save discount by day
    public TicketEntity saveDiscountByDay(TicketEntity ticket){
        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticket.getIdTicket());
        double totalDiscountByDay = 0;

        for (RepairEntity repair : repairs) {
            totalDiscountByDay = internalCalculationService.calculateDiscountByDay(repair);
        }

        ticket.setDiscountPerDay((int) Math.round(totalDiscountByDay * ticket.getBasePrice()));
        return ticketRepository.save(ticket);
    }

    // Save brand bonus
    public TicketEntity saveBrandBonus(TicketEntity ticket){
        if (ticket == null) {
            System.out.println("Ticket is null");
            return null;
        }
        System.out.println("VAMOS A PROBAR");
        RepairEntity repair = repairRepository.findFirstByIdTicket(ticket.getIdTicket());
        if (repair == null) {
            System.out.println("No repair found for ticket ID: " + ticket.getIdTicket());
            return null;
        }
        System.out.println("Repair: " + repair.toString());
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(repair.getLicensePlate());
        if (vehicle == null) {
            System.out.println("No vehicle found for repair with ID: " + repair.getLicensePlate());
            return null;
        }

        String brand = vehicle.getBrand();
        System.out.println("Brand: " + brand);

        List<BonusBrandEntity> brandBonus = bonusBrandRepository.findHighestActiveBonusByBrand(brand);
        System.out.println("Brand bonus: " + brandBonus.toString());
        if (brandBonus.isEmpty()) {
            System.out.println("No bonus found for brand: " + brand);
            return null;
        }
        System.out.println("BONUS: " + brandBonus.get(0).getBonus());
        ticket.setBrandBonus(brandBonus.get(0).getBonus());
        ticketRepository.save(ticket);

        System.out.println("llegó hasta acá");
        // Get the BonusBrandEntity from the Optional and set it as inactive
        BonusBrandEntity bonusBrandEntity = brandBonus.get(0);
        bonusBrandEntity.setActive(false);
        ticket.setIdBonus(bonusBrandEntity.getIdBonus());
        bonusBrandRepository.save(bonusBrandEntity);
        System.out.println("TERMINÉ");
        return ticket;
    }


    // Total price
    public TicketEntity saveTotalPrice(TicketEntity ticket){
        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticket.getIdTicket());
        double totalPrice = 0;
        if (repairs.isEmpty()) {
            System.out.println("No repairs found for ticket ID: " + ticket.getIdTicket());
            return null;
        }
        for (RepairEntity repair : repairs) {
            System.out.println("Repair: " + repair.toString());
            if (ticket.getBasePrice() == 0) {
                ticket = saveBasePrice(ticket);
            }
            totalPrice = internalCalculationService.calculateTotalPrice(ticket);
            totalPrice += ticket.getBrandBonus();
        }

        ticket.setTotalPrice((int) Math.round(totalPrice));
        System.out.println("Total price: " + ticket.getTotalPrice());
        System.out.println("Saved Ticket: " + ticket.toString());
        return ticketRepository.save(ticket);
    }
    //------------------------------------------------------------------------------------------------------------
    // Setup pickup date
    public TicketEntity savePickupDate(TicketEntity ticket){
        System.out.println("funca la cosa de repairs");
        if (ticket == null) {
            System.out.println("Ticket is null");
            return null;
        }

        // Fetch the repairs associated with the ticket
        List<RepairEntity> repairs = repairRepository.findRepairsByIdTicket(ticket.getIdTicket());

        // Iterate over the repairs
        for (RepairEntity repair : repairs) {
            // Check if the repair has a pickupDate
            if (repair.getPickupDate() != null) {
                // Save the pickupDate in the ticket
                ticket.setPickupDate(repair.getPickupDate());
                // Save and return the updated ticket
                return ticketRepository.save(ticket);
            }
        }

        // If no repair has a pickupDate, return null
        return null;
    }
}