package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.repositories.VehicleRepository;
import usach.tingeso.services.InternalCalculationService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class InternalCalculationServiceTest {

    @InjectMocks
    InternalCalculationService internalCalculationService;

    @Mock
    RepairRepository repairRepository;

    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    RepairService repairService;

    //------------------------------------------------------------------------------------------------
    // Test for calculateBasePrice
    @Test
    public void calculateBasePriceTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);
        repair.setRepairType(2);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1);

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);
        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        int result = internalCalculationService.calculateBasePrice(repair);

        // Assert
        assertEquals(130000, result);
        assertEquals(130000, repair.getBasePrice());
    }

    //------------------------------------------------------------------------------------------------
    // Test for calculateSurchargeForKM
    @Test
    public void calculateSurchargeForKMTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1);
        vehicle.setVehicleType(2);
        vehicle.setMileage(15000);

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);
        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateSurchargeForKM(repair);

        // Assert
        assertEquals(0.09, result);
        assertEquals(0.09, repair.getKmSurcharge());
    }

    //------------------------------------------------------------------------------------------------
    // Test for calculateSurchargeByAge
    @Test
    public void calculateSurchargeByAgeTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1);
        vehicle.setVehicleType(2);
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) - 8); // Vehicle is 8 years old

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);
        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(0.07, result);
        assertEquals(0.07, repair.getAgeSurcharge());
    }

    @Test
    public void calculateSurchargeByAgeTestNegative() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1);
        vehicle.setVehicleType(2);
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) + 1); // Vehicle is 1 year in the future

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);
        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(-1, result);
        assertEquals(-1, repair.getAgeSurcharge());
    }
    @Test
    public void calculateSurchargeByAgeTest_NullRepair() {
        // Act
        double result = internalCalculationService.calculateSurchargeByAge(null);

        // Assert
        assertEquals(-1, result); // The surcharge for a null repair is -1
    }

    @Test
    public void calculateSurchargeByAgeTest_AgeLessThanFive() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Sedan/Hatchback
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) - 4); // Vehicle is 4 years old

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(0, result); // The surcharge for a vehicle less than 5 years old is 0
    }

    @Test
    public void calculateSurchargeByAgeTest_AgeBetweenFiveAndTen() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Sedan/Hatchback
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) - 7); // Vehicle is 7 years old

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(0.05, result); // The surcharge for a vehicle between 5 and 10 years old is 0.05
    }

    @Test
    public void calculateSurchargeByAgeTest_AgeBetweenTenAndFifteen() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Sedan/Hatchback
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) - 12); // Vehicle is 12 years old

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(0.09, result); // The surcharge for a vehicle between 10 and 15 years old is 0.09
    }

    @Test
    public void calculateSurchargeByAgeTest_AgeGreaterThanFifteen() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Sedan/Hatchback
        vehicle.setYear(Calendar.getInstance().get(Calendar.YEAR) - 16); // Vehicle is 16 years old

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Act
        double result = internalCalculationService.calculateSurchargeByAge(repair);

        // Assert
        assertEquals(0.15, result); // The surcharge for a vehicle older than 15 years is 0.15
    }

    //------------------------------------------------------------------------------------------------
    // Test for delaySurcharge
    @Test
    public void calculateSurchargeForDelayTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        // Set entry date to 7 days ago
        Date entryDate = new Date();
        entryDate.setTime(entryDate.getTime() - 7 * 24 * 60 * 60 * 1000); // Subtract 7 days in milliseconds
        repair.setEntryDate(entryDate);

        // Set pickup date to current date
        Date pickupDate = new Date();
        repair.setPickupDate(pickupDate);

        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateSurchargeForDelay(repair);

        // Assert
        assertEquals(0.35, result);
        assertEquals(0.35, repair.getDelaySurcharge());
    }

    //------------------------------------------------------------------------------------------------
    // Test for discount by repairs
    @Test
    public void calculateDiscountByRepairsTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Diesel engine

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Assume that the repairService.getRepairsInLastYear method returns a list of 5 repairs
        when(repairService.getRepairsInLastYear(repair)).thenReturn(Arrays.asList(new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity()));

        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateDiscountByRepairs(repair);

        // Assert
        assertEquals(0.12, result); // The discount for a diesel engine with 5 repairs is 0.12
        assertEquals(0.12, repair.getRepairsDiscount());
    }
    @Test
    public void calculateDiscountByRepairsTest_LessThanThree() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Diesel engine

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Assume that the repairService.getRepairsInLastYear method returns a list of 2 repairs
        when(repairService.getRepairsInLastYear(repair)).thenReturn(Arrays.asList(new RepairEntity(), new RepairEntity()));

        // Act
        double result = internalCalculationService.calculateDiscountByRepairs(repair);

        // Assert
        assertEquals(0.07, result); // The discount for a diesel engine with 2 repairs is 0.07
        assertEquals(0.07, repair.getRepairsDiscount());
    }

    @Test
    public void calculateDiscountByRepairsTestLessThanSix() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Diesel engine

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Assume that the repairService.getRepairsInLastYear method returns a list of 5 repairs
        when(repairService.getRepairsInLastYear(repair)).thenReturn(Arrays.asList(new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity()));

        // Act
        double result = internalCalculationService.calculateDiscountByRepairs(repair);

        // Assert
        assertEquals(0.12, result); // The discount for a diesel engine with 5 repairs is 0.12
        assertEquals(0.12, repair.getRepairsDiscount());
    }
    @Test
    public void calculateDiscountByRepairsTestLessThanTen() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setEngineType(1); // Diesel engine

        when(vehicleRepository.findVehicleByLicensePlate(123L)).thenReturn(vehicle);

        // Assume that the repairService.getRepairsInLastYear method returns a list of 9 repairs
        when(repairService.getRepairsInLastYear(repair)).thenReturn(Arrays.asList(new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity(), new RepairEntity()));

        // Act
        double result = internalCalculationService.calculateDiscountByRepairs(repair);

        // Assert
        assertEquals(0.17, result); // The discount for a diesel engine with 9 repairs is 0.15
        assertEquals(0.17, repair.getRepairsDiscount());
    }

    @Test
    public void calculateDiscountByRepairsTest_NullRepair() {
        // Act
        double result = internalCalculationService.calculateDiscountByRepairs(null);

        // Assert
        assertEquals(-1, result); // The discount for a null repair is -1
    }

    //------------------------------------------------------------------------------------------------
    // Test for discount by day
    @Test
    public void calculateDiscountByDayTest() {
        // Arrange
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);

        // Set entry date to a Thursday at 10 AM
        Date entryDate = new Date();
        // Ensure it's Thursday at 10 AM
        Calendar cal = Calendar.getInstance();
        cal.setTime(entryDate);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        entryDate = cal.getTime();
        repair.setEntryDate(entryDate);

        when(repairRepository.save(any(RepairEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        double result = internalCalculationService.calculateDiscountByDay(repair);

        // Assert
        assertEquals(0.1, result); // The discount for a repair entered on a Thursday at 10 AM is 0.1
        assertEquals(0.1, repair.getDayDiscount());
    }
    @Test
    public void calculateDiscountByDayTestRepairNull() {
        // Act
        double result = internalCalculationService.calculateDiscountByDay(null);

        // Assert
        assertEquals(-1, result);
    }
    //------------------------------------------------------------------------------------------------
    // Test for calculateTotalPrice
    @Test
    public void calculateTotalPriceTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setBasePrice(1000);
        ticket.setSurchargeForKM((int)(0.05 * ticket.getBasePrice()));
        ticket.setSurchargeForAge((int)(0.07 * ticket.getBasePrice()));
        ticket.setSurchargeForDelay((int)(0.35 * ticket.getBasePrice()));
        ticket.setDiscountForRepairs((int)(0.12 * ticket.getBasePrice()));
        ticket.setDiscountPerDay((int)(0.1 * ticket.getBasePrice()));

        // Act
        double result = internalCalculationService.calculateTotalPrice(ticket);

        // Assert
        double expected = ticket.getBasePrice() + ticket.getSurchargeForKM() + ticket.getSurchargeForAge() + ticket.getSurchargeForDelay() - ticket.getDiscountForRepairs() - ticket.getDiscountPerDay();
        expected *= 1.19;
        assertEquals(expected, result);
    }
    @Test
    public void calculateTotalPriceWhenTicketIsNullTest() {
        // Act
        double result = internalCalculationService.calculateTotalPrice(null);

        // Assert
        assertEquals(-1, result);
    }
    @Test
    public void calculateTotalPriceWhenBasePriceIsNegativeTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setSurchargeForKM(-1000);

        // Act
        double result = internalCalculationService.calculateTotalPrice(ticket);

        // Assert
        assertEquals(-1, result);
    }

}