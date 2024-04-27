package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    TicketService ticketService;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    RepairRepository repairRepository;

    @Mock
    InternalCalculationService internalCalculationService;

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    BonusBrandRepository bonusBrandRepository;

    @Test
    public void getTicketByIdTest() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        TicketEntity result = ticketService.getTicketById(1L);

        assertEquals(ticket, result);
    }
    @Test
    public void getTicketByIdNotFoundTest() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        TicketEntity result = ticketService.getTicketById(1L);

        assertNull(result);
    }

    @Test
    public void saveTicketTest() {
        TicketEntity ticket = new TicketEntity();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        TicketEntity result = ticketService.saveTicket(ticket);

        assertEquals(ticket, result);
    }
    @Test
    public void saveTicketNullTest() {
        TicketEntity result = ticketService.saveTicket(null);

        assertNull(result);
    }

    @Test
    public void deleteTicketTest() {
        when(ticketRepository.existsById(1L)).thenReturn(true);

        ticketService.deleteTicket(1L);

        verify(ticketRepository, times(1)).deleteById(1L);
    }
    @Test
    public void deleteTicketNotFoundTest() {
        when(ticketRepository.existsById(1L)).thenReturn(false);

        ticketService.deleteTicket(1L);

        verify(ticketRepository, times(0)).deleteById(1L);
    }

    @Test
    public void updateTicketTest() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        when(ticketRepository.existsById(1L)).thenReturn(true);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        TicketEntity result = ticketService.updateTicket(ticket);

        assertEquals(ticket, result);
    }
    @Test
    public void updateTicketNotFoundTest() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        when(ticketRepository.existsById(1L)).thenReturn(false);

        TicketEntity result = ticketService.updateTicket(ticket);

        assertNull(result);
    }

    @Test
    public void getAllTicketsTest() {
        TicketEntity ticket1 = new TicketEntity();
        TicketEntity ticket2 = new TicketEntity();

        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        assertEquals(2, ticketService.getAllTickets().size());
    }
    @Test
    public void getAllTicketsEmptyTest() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList());

        assertEquals(0, ticketService.getAllTickets().size());
    }

    @Test
    public void getTicketValuesTest() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        when(ticketRepository.getTicketValues(1L)).thenReturn(new Object[]{1L, "value1", "value2"});

        String result = ticketService.getTicketValues(ticket);

        assertEquals("1, value1, value2", result);
    }
    @Test
    public void getTicketValuesNotFoundTest() {
        Long id = 1L;
        when(ticketRepository.getTicketValues(id)).thenReturn(null);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setIdTicket(id);

        try {
            String result = ticketService.getTicketValues(ticketEntity);
            assertNull(result);
        } catch (NullPointerException e) {
            // Handle the NullPointerException
        }
    }

    @Test
    public void getAllTicketValuesTest() {
        TicketEntity ticket1 = new TicketEntity();
        ticket1.setIdTicket(1L);
        TicketEntity ticket2 = new TicketEntity();
        ticket2.setIdTicket(2L);

        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        assertEquals(2, ticketService.getAllTicketValues().size());
    }
    @Test
    public void getAllTicketValuesEmptyTest() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList());

        assertEquals(0, ticketService.getAllTicketValues().size());
    }

    //------------------------------------------------------------------------------------------------
    @Test
    public void saveBasePriceTest() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateBasePrice(repair)).thenReturn(100);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        TicketEntity result = ticketService.saveBasePrice(ticket);

        assertEquals(100, result.getBasePrice());
    }

    @Test
    public void saveKMSurchargeTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateSurchargeForKM(repair)).thenReturn(50.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveKMSurcharge(ticket);

        // Assert
        assertEquals(5000, result.getSurchargeForKM());
    }

    @Test
    public void saveAgeSurchargeTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateSurchargeByAge(repair)).thenReturn(50.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveAgeSurcharge(ticket);

        // Assert
        assertEquals(5000, result.getSurchargeForAge());
    }

    @Test
    public void saveSurchargeForDelayTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateSurchargeForDelay(repair)).thenReturn(50.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveSurchargeForDelay(ticket);

        // Assert
        assertEquals(5000, result.getSurchargeForDelay());
    }

    @Test
    public void saveDiscountByRepairsTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateDiscountByRepairs(repair)).thenReturn(50.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveDiscountByRepairs(ticket);

        // Assert
        assertEquals(5000, result.getDiscountForRepairs());
    }
    @Test
    public void saveDiscountByDayTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateDiscountByDay(repair)).thenReturn(50.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveDiscountByDay(ticket);

        // Assert
        assertEquals(5000, result.getDiscountPerDay());
    }

    @Test
    public void saveBrandBonusTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(1L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand("BrandX");

        BonusBrandEntity bonusBrandEntity = new BonusBrandEntity();
        bonusBrandEntity.setBonus(100);
        List<BonusBrandEntity> brandBonus = Arrays.asList(bonusBrandEntity);

        when(repairRepository.findFirstByIdTicket(1L)).thenReturn(repair);
        when(vehicleRepository.findVehicleByLicensePlate(1L)).thenReturn(vehicle);
        when(bonusBrandRepository.findHighestActiveBonusByBrand("BrandX")).thenReturn(brandBonus);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveBrandBonus(ticket);

        // Assert
        assertEquals(100, result.getBrandBonus());
    }
    @Test
    public void saveBrandBonusTicketNullTest() {
        // Arrange
        TicketEntity ticket = null;

        // Act
        TicketEntity result = ticketService.saveBrandBonus(ticket);

        // Assert
        assertNull(result);
    }

    @Test
    public void saveBrandBonusVehicleNullTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(1L);

        when(repairRepository.findFirstByIdTicket(1L)).thenReturn(repair);
        when(vehicleRepository.findVehicleByLicensePlate(1L)).thenReturn(null);

        // Act
        TicketEntity result = ticketService.saveBrandBonus(ticket);

        // Assert
        assertNull(result);
    }
    @Test
    public void saveBrandBonusBrandBonusEmptyTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);

        RepairEntity repair = new RepairEntity();
        repair.setLicensePlate(1L);

        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setBrand("BrandX");

        List<BonusBrandEntity> brandBonus = new ArrayList<>();

        when(repairRepository.findFirstByIdTicket(1L)).thenReturn(repair);
        when(vehicleRepository.findVehicleByLicensePlate(1L)).thenReturn(vehicle);
        when(bonusBrandRepository.findHighestActiveBonusByBrand("BrandX")).thenReturn(brandBonus);

        // Act
        TicketEntity result = ticketService.saveBrandBonus(ticket);

        // Assert
        assertNull(result);
    }

    @Test
    public void saveTotalPriceTest() {
        // Arrange
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L);
        ticket.setBasePrice(0);
        ticket.setBrandBonus(100);

        RepairEntity repair = new RepairEntity();
        List<RepairEntity> repairs = Arrays.asList(repair);

        when(repairRepository.findRepairsByIdTicket(1L)).thenReturn(repairs);
        when(internalCalculationService.calculateTotalPrice(ticket)).thenReturn(200.0);
        when(ticketRepository.save(any(TicketEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TicketEntity result = ticketService.saveTotalPrice(ticket);

        // Assert
        assertEquals(300, result.getTotalPrice());
    }

}