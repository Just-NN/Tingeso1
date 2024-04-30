package usach.tingeso.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.BonusBrandRepository;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.repositories.VehicleRepository;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@Service
public class InternalCalculationService {
    @Autowired
    RepairRepository repairRepository;;
    @Autowired
    RepairService repairService;
    @Autowired
    private BonusBrandRepository bonusBrandRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    // This is the service that will calculate the final price for the repair

    public int calculateBasePrice(RepairEntity repair) {
        if (repair == null) {
            return -1;
        }
        Long licensePlate = repair.getLicensePlate();
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        int engineType = vehicle.getEngineType();
        int repairType = repair.getRepairType();

        // Define a 2D array to store the repair costs
        int[][] repairCosts = {
                {120000, 130000, 350000, 210000, 150000, 100000, 80000, 180000, 150000, 130000, 80000}, // Gasoline
                {120000, 130000, 450000, 210000, 150000, 120000, 80000, 180000, 150000, 140000, 80000}, // Diesel
                {180000, 190000, 700000, 300000, 200000, 450000, 100000, 210000, 180000, 220000, 80000}, // Hybrid
                {220000, 230000, 800000, 300000, 250000, 0, 100000, 250000, 180000, 0, 80000} // Electric
        };

        int basePrice = -1;
        // Check if the repairType and engineType are within the valid range
        if (repairType >= 1 && repairType <= 11 && engineType >= 0 && engineType <= 3) {
            System.out.printf("Repair type: %d\n", repairType);
            System.out.println("Engine type: " + engineType);

            // Calculate the base price from the array
            basePrice = repairCosts[engineType][repairType - 1];
        }

        // Save the base price in the RepairEntity
        repair.setBasePrice(basePrice);
        repairRepository.save(repair);
        return basePrice;
    }

    // Calculate the surcharge for the kilometers
    public double calculateSurchargeForKM(RepairEntity repair){
        if (repair == null) {
            return -1;
        }
        Long licensePlate = repair.getLicensePlate();
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        int engineType = vehicle.getEngineType();
        double surchargeKM = 0;
        int mileage = vehicle.getMileage();
        int vehicleType = vehicle.getVehicleType();

        // Define a 2D array to store the surcharge rates
        double[][] surchargeRates = {
                {0.03, 0.07, 0.12, 0.2}, // Sedan/Hatchback
                {0.03, 0.07, 0.12, 0.2}, // Sedan/Hatchback
                {0.05, 0.09, 0.12, 0.2}, // SUV/Pickup/Van
                {0.05, 0.09, 0.12, 0.2}, // SUV/Pickup/Van
                {0.05, 0.09, 0.12, 0.2}  // SUV/Pickup/Van
        };

        // Check if the mileage is within the valid range
        if(mileage > 5000){
            int mileageIndex;
            if (mileage < 12000) {
                mileageIndex = 0;
            } else if (mileage < 25000) {
                mileageIndex = 1;
            } else if (mileage < 40000) {
                mileageIndex = 2;
            } else {
                mileageIndex = 3;
            }

            // Calculate the surcharge rate from the array
            surchargeKM = surchargeRates[vehicleType][mileageIndex];
        } else if(mileage < 0){
            surchargeKM = -1;
        }

        // Save the surcharge in the RepairEntity
        repair.setKmSurcharge(surchargeKM);
        repairRepository.save(repair);

        return surchargeKM;
    }
    public double calculateSurchargeByAge(RepairEntity repair) {
        if (repair == null) {
            return -1;
        }
        Long licensePlate = repair.getLicensePlate();
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        int engineType = vehicle.getEngineType();
        double ageSurcharge = 0;
        int age = Calendar.getInstance().get(Calendar.YEAR) - vehicle.getYear();
        int type = vehicle.getVehicleType();

        // Define a 2D array to store the surcharge rates
        double[][] surchargeRates = {
                {0.05, 0.09, 0.15}, // Sedan/Hatchback
                {0.05, 0.09, 0.15}, // Sedan/Hatchback
                {0.07, 0.11, 0.2},  // SUV/Pickup/Van
                {0.07, 0.11, 0.2},  // SUV/Pickup/Van
                {0.07, 0.11, 0.2}   // SUV/Pickup/Van
        };

        if (age < 0) {
            ageSurcharge = -1;
        } else if (age > 5) {
            int ageIndex;
            if (age < 10) {
                ageIndex = 0;
            } else if (age < 15) {
                ageIndex = 1;
            } else {
                ageIndex = 2;
            }

            // Calculate the surcharge rate from the array
            ageSurcharge = surchargeRates[type][ageIndex];
        }

        // Save the surcharge in the RepairEntity
        repair.setAgeSurcharge(ageSurcharge);
        repairRepository.save(repair);
        return ageSurcharge;
    }

    // Calculate the surcharge for the delay
    public double calculateSurchargeForDelay(RepairEntity repair){
        if (repair == null) {
            return -1;
        }
        long delay = ChronoUnit.DAYS.between(repair.getEntryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                repair.getPickupDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        // This is to prevent round errors
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        double delaySurcharge = Double.valueOf(df.format(-(delay)*0.05));
        System.out.println("Delay: " + delaySurcharge);
        // Save the surcharge in the RepairEntity
        repair.setDelaySurcharge(delaySurcharge);
        repairRepository.save(repair);
        return delaySurcharge;
    }

    // Calculate the discount by the number of repairs
    public double calculateDiscountByRepairs(RepairEntity repair){
        if (repair == null) {
            return -1;
        }
        Long licensePlate = repair.getLicensePlate();
        VehicleEntity vehicle = vehicleRepository.findVehicleByLicensePlate(licensePlate);
        System.out.println("Vehicle: " + vehicle.getLicensePlate());
        int engineType = vehicle.getEngineType();
        List<RepairEntity> repairs = repairService.getRepairsInLastYear(repair);
        System.out.println("Repairs: " + repairs.size());
        int repairCount = repairs.size();

        // Define a 2D array to store the discount rates
        double[][] discountRates = {
                {0.05, 0.10, 0.15, 0.2},  // Gasoline
                {0.07, 0.12, 0.17, 0.22}, // Diesel
                {0.10, 0.15, 0.20, 0.25}, // Hybrid
                {0.08, 0.13, 0.18, 0.23}  // Electric
        };

        double discount = 0;
        if (repairCount > 0){
            int repairIndex;
            if (repairCount < 3) {
                repairIndex = 0;
            } else if (repairCount < 6) {
                repairIndex = 1;
            } else if (repairCount < 10) {
                repairIndex = 2;
            } else {
                repairIndex = 3;
            }

            // Calculate the discount rate from the array
            discount = discountRates[engineType][repairIndex];
            System.out.println("Discount: " + discount);
        }

        // Save the discount in the RepairEntity
        repair.setRepairsDiscount(discount);
        repairRepository.save(repair);
        return discount;
    }

    // Calculate the discount by the day of the week

    public double calculateDiscountByDay(RepairEntity repair){
        if (repair == null) {
            return -1;
        }
        ZonedDateTime entryDateTime = repair.getEntryDate().toInstant().atZone(ZoneId.systemDefault());
        int entryDay = entryDateTime.getDayOfWeek().getValue();
        System.out.println("Day: " + entryDay);
        int entryHour = entryDateTime.getHour();
        System.out.println("Hour: " + entryHour);
        double dayDiscount = 0;
        if (entryDay >= 1 && entryDay <= 4){ // 1 = Monday, 4 = Thursday in java.time API
            System.out.println("Es buen dia");
            if (entryHour >= 9 && entryHour <= 12){
                System.out.println("Es buen horario");
                dayDiscount = 0.1;
            }
        }

        // Save the discount in the RepairEntity
        repair.setDayDiscount(dayDiscount);
        repairRepository.save(repair);
        return dayDiscount;
    }

    // Calculate the total price for the ticket
    public double calculateTotalPrice(TicketEntity ticket){
        if (ticket == null) {
            return -1;
        }
        double kmSurcharge = ticket.getSurchargeForKM();
        double ageSurcharge = ticket.getSurchargeForAge();
        double delaySurcharge = ticket.getSurchargeForDelay();
        double repairDiscount = ticket.getDiscountForRepairs();
        double dayDiscount = ticket.getDiscountPerDay();
        double finalPrice = 0;
        if (kmSurcharge < 0 || ageSurcharge < 0 || repairDiscount < 0){
            return -1;
        }
        else{
            finalPrice = (ticket.getBasePrice() + (kmSurcharge + ageSurcharge + delaySurcharge) - (repairDiscount + dayDiscount))*1.19;
        }
        return finalPrice;
    }

}
