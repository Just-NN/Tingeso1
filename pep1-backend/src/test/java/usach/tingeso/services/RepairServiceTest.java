package usach.tingeso.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.entities.VehicleEntity;
import usach.tingeso.repositories.RepairRepository;
import usach.tingeso.repositories.TicketRepository;
import usach.tingeso.repositories.VehicleRepository;

import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RepairServiceTest {

    @InjectMocks
    RepairService repairService;

    @Mock
    RepairRepository repairRepository;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    VehicleRepository vehicleRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRepairByIdTest() {
        RepairEntity repair = new RepairEntity();
        when(repairRepository.findById(1L)).thenReturn(Optional.of(repair));

        repairService.getRepairById(1L);

        verify(repairRepository).findById(1L);
    }
    @Test
    public void getRepairByIdNotFoundTest() {
        when(repairRepository.findById(1L)).thenReturn(Optional.empty());

        repairService.getRepairById(1L);

        verify(repairRepository).findById(1L);
    }

    @Test
    public void saveRepairTest() {
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L); // Assuming license plate is a Long
        when(vehicleRepository.findVehicleByLicensePlate(anyLong())).thenReturn(new VehicleEntity());
        when(ticketRepository.findTicketByIdTicket(anyLong())).thenReturn(new TicketEntity());
        when(repairRepository.save(repair)).thenReturn(repair);

        repairService.saveRepair(repair);

        verify(repairRepository).save(repair);
    }
    @Test
    public void saveRepairNullTest() {
        RepairEntity repair = null;

        RepairEntity result = repairService.saveRepair(repair);

        assertEquals(null, result);
    }

    @Test
    public void deleteRepairTest() {
        when(repairRepository.existsById(1L)).thenReturn(true);

        repairService.deleteRepair(1L);

        verify(repairRepository).deleteById(1L);
    }
    @Test
    public void deleteRepairNotFoundTest() {
        when(repairRepository.existsById(1L)).thenReturn(false);

        repairService.deleteRepair(1L);

        verify(repairRepository, never()).deleteById(1L);
    }

    @Test
    public void updateRepairTest() {
        RepairEntity repair = new RepairEntity();
        when(repairRepository.existsById(repair.getIdRepair())).thenReturn(true);
        when(repairRepository.save(repair)).thenReturn(repair);

        repairService.updateRepair(repair);

        verify(repairRepository).save(repair);
    }
    @Test
    public void updateRepairNotFoundTest() {
        RepairEntity repair = new RepairEntity();
        when(repairRepository.existsById(repair.getIdRepair())).thenReturn(false);

        RepairEntity result = repairService.updateRepair(repair);

        assertEquals(null, result);
    }


    public List<RepairEntity> getRepairsByVehicleThisYear(Long licensePlate) {
        // Create a Calendar object representing the start of the current year
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfYear = calendar.getTime();

        return repairRepository.findByVehicleThisYear(licensePlate, startOfYear);
    }

    @Test
    public void getRepairsByVehicleThisYearNullTest() {
        Long licensePlate = 123L;
        List<RepairEntity> repairs = null;
        when(repairRepository.findByVehicleThisYear(eq(licensePlate), any(Date.class))).thenReturn(repairs);

        List<RepairEntity> result = repairService.getRepairsByVehicleThisYear(licensePlate);

        verify(repairRepository).findByVehicleThisYear(eq(licensePlate), any(Date.class));
        assertNull(result);
    }

    @Test
    public void getRepairsByVehicleAndEntryDateTest() {
        Long licensePlate = 123L;
        Date date = new Date();
        List<RepairEntity> repairs = Arrays.asList(new RepairEntity(), new RepairEntity());
        when(repairRepository.findRepairsByVehicleAndEntryDate(licensePlate, date)).thenReturn(repairs);

        List<RepairEntity> result = repairService.getRepairsByVehicleAndEntryDate(licensePlate, date);

        verify(repairRepository).findRepairsByVehicleAndEntryDate(licensePlate, date);
        assertEquals(repairs, result);
    }

    @Test
    public void getRepairsByVehicleAndEntryDateNullTest() {
        Long licensePlate = 123L;
        Date date = new Date();
        List<RepairEntity> repairs = null;
        when(repairRepository.findRepairsByVehicleAndEntryDate(licensePlate, date)).thenReturn(repairs);

        List<RepairEntity> result = repairService.getRepairsByVehicleAndEntryDate(licensePlate, date);

        verify(repairRepository).findRepairsByVehicleAndEntryDate(licensePlate, date);
        assertEquals(null, result);
    }
    @Test
    public void getRepairTypeCountAndTotalPriceTest() {
        List<Object[]> repairTypeCountAndTotalPrice = Arrays.asList(new Object[]{"repairType1", "vehicleType1", "1", "100"},
                new Object[]{"repairType2", "vehicleType2", "2", "200"});
        when(repairRepository.getRepairTypeVehicleTypeCountAndTotalPrice()).thenReturn(repairTypeCountAndTotalPrice);

        String result = repairService.getRepairTypeCountAndTotalPrice();

        verify(repairRepository).getRepairTypeVehicleTypeCountAndTotalPrice();
        // Add assertions to check the result
    }
    @Test
    public void getRepairTypeCountAndTotalPriceNullTest() {
        List<Object[]> repairTypeCountAndTotalPrice = Collections.emptyList();
        when(repairRepository.getRepairTypeVehicleTypeCountAndTotalPrice()).thenReturn(repairTypeCountAndTotalPrice);

        String result = repairService.getRepairTypeCountAndTotalPrice();

        verify(repairRepository).getRepairTypeVehicleTypeCountAndTotalPrice();
        // Add assertions to check the result
        assertEquals("", result);
    }

    @Test
    public void getAverageTotalRepairAmountByBrandTest() {
        List<Object[]> averageTotalRepairAmountByBrand = Arrays.asList(new Object[]{"brand1", "100"},
                new Object[]{"brand2", "200"});
        when(repairRepository.getAverageTotalRepairAmountByBrand()).thenReturn(averageTotalRepairAmountByBrand);

        String result = repairService.getAverageTotalRepairAmountByBrand();

        verify(repairRepository).getAverageTotalRepairAmountByBrand();
        // Add assertions to check the result
    }
    @Test
    public void getAverageTotalRepairAmountByBrandNullTest() {
        List<Object[]> averageTotalRepairAmountByBrand = Collections.emptyList();
        when(repairRepository.getAverageTotalRepairAmountByBrand()).thenReturn(averageTotalRepairAmountByBrand);

        String result = repairService.getAverageTotalRepairAmountByBrand();

        verify(repairRepository).getAverageTotalRepairAmountByBrand();
        // Add assertions to check the result
        assertEquals("", result);
    }

    @Test
    public void getRepairTypeMotorTypeCountAndTotalPriceTest() {
        List<Object[]> repairTypeMotorTypeCountAndTotalPrice = Arrays.asList(new Object[]{"repairType1", "motorType1", "1", "100"},
                new Object[]{"repairType2", "motorType2", "2", "200"});
        when(repairRepository.getRepairTypeMotorTypeCountAndTotalPrice()).thenReturn(repairTypeMotorTypeCountAndTotalPrice);

        String result = repairService.getRepairTypeMotorTypeCountAndTotalPrice();

        verify(repairRepository).getRepairTypeMotorTypeCountAndTotalPrice();
        // Add assertions to check the result
    }

    @Test
    public void getRepairTypeMotorTypeCountAndTotalPriceNullTest() {
        List<Object[]> repairTypeMotorTypeCountAndTotalPrice = Collections.emptyList();
        when(repairRepository.getRepairTypeMotorTypeCountAndTotalPrice()).thenReturn(repairTypeMotorTypeCountAndTotalPrice);

        String result = repairService.getRepairTypeMotorTypeCountAndTotalPrice();

        verify(repairRepository).getRepairTypeMotorTypeCountAndTotalPrice();
        // Add assertions to check the result
        assertEquals("", result);
    }
    @Test
    public void getIdTicketFromRepairsByEntryDateTest() {
        Date entryDate = new Date();
        List<RepairEntity> repairs = Arrays.asList(new RepairEntity(), new RepairEntity());
        when(repairRepository.findRepairsByEntryDate(entryDate)).thenReturn(repairs);

        Long result = repairService.getIdTicketFromRepairsByEntryDate(entryDate);

        verify(repairRepository).findRepairsByEntryDate(entryDate);
        // Add assertions to check the result
        assertEquals(null, result);
    }

    @Test
    public void getIdTicketFromRepairsByEntryDateNullTest() {
        Date entryDate = new Date();
        List<RepairEntity> repairs = Collections.emptyList();
        when(repairRepository.findRepairsByEntryDate(entryDate)).thenReturn(repairs);

        Long result = repairService.getIdTicketFromRepairsByEntryDate(entryDate);

        verify(repairRepository).findRepairsByEntryDate(entryDate);
        // Add assertions to check the result
        assertNull(result);
    }

//    @Test
//    public void getRepairsInLastYearTest() {
//        RepairEntity repair = new RepairEntity();
//        repair.setLicensePlate(123L);
//        Date lastYear = new Date();
//        lastYear.setYear(lastYear.getYear() - 1);
//        List<RepairEntity> repairs = Arrays.asList(new RepairEntity(), new RepairEntity());
//        when(repairRepository.findByVehicleThisYear(eq(repair.getLicensePlate()), any(Date.class))).thenReturn(repairs);
//
//        List<RepairEntity> result = repairService.getRepairsInLastYear(repair);
//
//        verify(repairRepository).findByVehicleThisYear(eq(repair.getLicensePlate()), eq(lastYear));
//        assertEquals(repairs, result);
//    }

    @Test
    public void getRepairsInLastYearNullTest() {
        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(123L);
        List<RepairEntity> repairs = null;
        when(repairRepository.findByVehicleThisYear(eq(repair.getLicensePlate()), any(Date.class))).thenReturn(repairs);

        List<RepairEntity> result = repairService.getRepairsInLastYear(repair);

        verify(repairRepository).findByVehicleThisYear(eq(repair.getLicensePlate()), any(Date.class));
        assertNull(result);
    }

}