package usach.tingeso.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.services.TicketService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @InjectMocks
    TicketController ticketController;

    @Mock
    TicketService ticketService;

    //------------------------------------------------------------------------------------------------
    //Test for getTicketById
    @Test
    public void testGetTicketById() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.getTicketById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testGetTicketByIdNotFound() {
        Long id = 1L;
        when(ticketService.getTicketById(id)).thenReturn(null);

        ResponseEntity<TicketEntity> response = ticketController.getTicketById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveTicket
    @Test
    public void testSaveTicket() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveTicket(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveTicket(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveTicketBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveTicket(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for deleteTicket
    @Test
    public void testDeleteTicket() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        ResponseEntity<Void> response = ticketController.deleteTicket(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeleteTicketNotFound() {
        Long id = 1L;
        when(ticketService.getTicketById(id)).thenReturn(null);

        ResponseEntity<Void> response = ticketController.deleteTicket(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for updateTicket
    @Test
    public void testUpdateTicket() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L); // Set the ID of the ticket

        when(ticketService.getTicketById(ticket.getIdTicket())).thenReturn(ticket);
        when(ticketService.updateTicket(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.updateTicket(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testUpdateTicketBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.updateTicket(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for getAllTickets
    @Test
    public void testGetAllTickets() {
        List<TicketEntity> tickets = Collections.singletonList(new TicketEntity());
        when(ticketService.getAllTickets()).thenReturn(tickets);

        ResponseEntity<List<TicketEntity>> response = ticketController.getAllTickets();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tickets, response.getBody());
    }

    @Test
    public void testGetAllTicketsNotFound() {
        when(ticketService.getAllTickets()).thenReturn(Collections.emptyList());

        ResponseEntity<List<TicketEntity>> response = ticketController.getAllTickets();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveBasePrice
    @Test
    public void testSaveBasePrice() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveBasePrice(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveBasePrice(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveBasePriceBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveBasePrice(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveKMSurcharge

    @Test
    public void testSaveKMSurcharge() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveKMSurcharge(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveKMSurcharge(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveKMSurchargeBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveKMSurcharge(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveAgeSurcharge

    @Test
    public void testSaveAgeSurcharge() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveAgeSurcharge(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveAgeSurcharge(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }


    @Test
    public void testSaveAgeSurchargeBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveAgeSurcharge(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveSurchargeForDelay

    @Test
    public void testSaveSurchargeForDelay() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveSurchargeForDelay(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveSurchargeForDelay(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }


    @Test
    public void testSaveSurchargeForDelayBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveSurchargeForDelay(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveDiscountByRepairs
    @Test
    public void testSaveDiscountByRepairs() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveDiscountByRepairs(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveDiscountByRepairs(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveDiscountByRepairsBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveDiscountByRepairs(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveDiscountByDay
    @Test
    public void testSaveDiscountByDay() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveDiscountByDay(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveDiscountByDay(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveDiscountByDayBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveDiscountByDay(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveBrandBonus
    @Test
    public void testSaveBrandBonus() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveBrandBonus(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveBrandBonus(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }


    @Test
    public void testSaveBrandBonusBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveBrandBonus(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveTotalPrice

    @Test
    public void testSaveTotalPrice() {
        TicketEntity ticket = new TicketEntity();
        when(ticketService.saveTotalPrice(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveTotalPrice(ticket);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testSaveTotalPriceBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveTotalPrice(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    // Test for saveInit
    @Test
    public void testSaveInitBadRequest() {
        ResponseEntity<TicketEntity> response = ticketController.saveInit(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSaveInit() {
        TicketEntity ticket = new TicketEntity();
        ticket.setIdTicket(1L); // Set the ID of the ticket

        when(ticketService.savePickupDate(ticket)).thenReturn(ticket);
        when(ticketService.saveBasePrice(ticket)).thenReturn(ticket);
        when(ticketService.saveKMSurcharge(ticket)).thenReturn(ticket);
        when(ticketService.saveAgeSurcharge(ticket)).thenReturn(ticket);
        when(ticketService.saveSurchargeForDelay(ticket)).thenReturn(ticket);
        when(ticketService.saveDiscountByRepairs(ticket)).thenReturn(ticket);
        when(ticketService.saveDiscountByDay(ticket)).thenReturn(ticket);
        when(ticketService.saveBrandBonus(ticket)).thenReturn(ticket);
        when(ticketService.saveTotalPrice(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveInit(ticket);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

}