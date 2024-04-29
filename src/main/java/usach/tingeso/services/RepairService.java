package usach.tingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.repositories.TicketRepository;
import usach.tingeso.repositories.VehicleRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    // Basic CRUD operations
    public RepairEntity getRepairById(Long id){
        return repairRepository.findById(id).orElse(null);
    }

    public RepairEntity saveRepair(RepairEntity repair) {
        System.out.println("Empecé");
        if (repair == null) {
            System.out.println("HOLA MI REPAIR ES NULA");
            // Handle the case where repair is null
            return null;
        }

        Long licensePlate = repair.getLicensePlate();
        if (licensePlate == null) {
            // Handle the case where the licensePlate is null
            return null;
        }
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        if (vehicle == null) {
            // Handle the case where the vehicle does not exist
            // Return null if no vehicle with the given licensePlate exists
            System.out.println("No vehicle found with license plate: " + licensePlate);
            return null;
        }

        Date entryDate = repair.getEntryDate();
        Long idTicket = getIdTicketFromRepairsByEntryDate(entryDate);
        System.out.println("ID TICKET: " + idTicket);
        TicketEntity ticket = ticketRepository.findTicketByIdTicket(idTicket);
        System.out.println("TIKET: " + ticket);
        if (ticket == null) {
            System.out.println("HOLA MI TICKET ES NULO");
            // Handle the case where the ticket does not exist
            // Create a ticket with the idTicket
            ticket = new TicketEntity();
            ticket.setIdTicket(idTicket);
            ticketRepository.save(ticket);
        }
        idTicket = ticket.getIdTicket();
        System.out.println("ID TICKET: " + idTicket);
        repair.setIdTicket(idTicket);
        System.out.println("Ticket: " + ticket);

        System.out.println("LLEGUÉ HASTA EL FINAL");
        repair.calculateDifferenceInDays();
        return repairRepository.save(repair);
    }

    public void deleteRepair(Long id){
        if (repairRepository.existsById(id)) {
            repairRepository.deleteById(id);
        }
    }

    public RepairEntity updateRepair(RepairEntity repair){
        if (repairRepository.existsById(repair.getIdRepair())) {
            return repairRepository.save(repair);
        }
        return null;
    }

    // Get all repairs
    public List<RepairEntity> getAllRepairs(){
        return repairRepository.findAll();
    }

    // Repairs of a vehicle this year
    public List<RepairEntity> getRepairsByVehicleThisYear(Long licensePlate){
        Date currentYear = new Date();
        return repairRepository.findByVehicleThisYear(licensePlate, currentYear);
    }

    // Repairs of a vehicle in a specific date
    public List<RepairEntity> getRepairsByVehicleAndEntryDate(Long licensePlate, Date date){
        return repairRepository.findRepairsByVehicleAndEntryDate(licensePlate, date);
    }
    //------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------
    // All the values for R2
    public String getRepairTypeCountAndTotalPrice(){
        List<Object[]> repairTypeCountAndTotalPrice = repairRepository.getRepairTypeVehicleTypeCountAndTotalPrice();
        StringBuilder result = new StringBuilder();
        for (Object[] values : repairTypeCountAndTotalPrice) {
            String repairType = String.valueOf(values[0]);
            String vehicleType = String.valueOf(values[1]);
            String count = String.valueOf(values[2]);
            String totalPrice = String.valueOf(values[3]);
            result.append("Repair type: ").append(repairType).append(", vehicle type: ").append(vehicleType).append(", count: ").append(count).append(", total price: ").append(totalPrice).append("\n");

        }
        return result.toString();
    }
    // All the values for R3
    public String getAverageTotalRepairAmountByBrand(){
        List<Object[]> averageTotalRepairAmountByBrand = repairRepository.getAverageTotalRepairAmountByBrand();
        StringBuilder result = new StringBuilder();
        for (Object[] values : averageTotalRepairAmountByBrand) {
            String brand = String.valueOf(values[0]);
            String averageTotalRepairAmount = String.valueOf(values[1]);
            result.append("Brand: ").append(brand)
                    .append(", average total repair amount: ").append(averageTotalRepairAmount).append(" Days")
                    .append("\n");
        }
        return result.toString();
    }
    // All the values for R4
    public String getRepairTypeMotorTypeCountAndTotalPrice(){
        List<Object[]> repairTypeMotorTypeCountAndTotalPrice = repairRepository.getRepairTypeMotorTypeCountAndTotalPrice();
        StringBuilder result = new StringBuilder();
        for (Object[] values : repairTypeMotorTypeCountAndTotalPrice) {
            String repairType = String.valueOf(values[0]);
            String motorType = String.valueOf(values[1]);
            String count = String.valueOf(values[2]);
            String totalPrice = String.valueOf(values[3]);
            result.append("Repair type: ").append(repairType)
                    .append(", motor type: ").append(motorType)
                    .append(", count: ").append(count)
                    .append(", total price: ").append(totalPrice).append("\n");
        }
        return result.toString();
    }

    // Get idTicket from repair by date

    public Long getIdTicketFromRepairsByEntryDate(Date entryDate){
        List<RepairEntity> repairs = repairRepository.findRepairsByEntryDate(entryDate);
        for (RepairEntity repair : repairs) {
            if (repair != null && repair.getIdTicket() != null) {
                return repair.getIdTicket();
            }
        }
        return null;
    }

    public List<RepairEntity> getRepairsInLastYear(RepairEntity repair){
        Long licensePlate = repair.getLicensePlate();
        Date lastYear = new Date();
        lastYear.setYear(lastYear.getYear() - 1);
        return repairRepository.findByVehicleThisYear(licensePlate, lastYear);
    }

}