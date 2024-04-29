package usach.tingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.RepairEntity;
import usach.tingeso.entities.TicketEntity;
import usach.tingeso.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketEntity> getTicketById(@PathVariable Long id){
        TicketEntity ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/")
    public ResponseEntity<TicketEntity> saveTicket(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveTicket(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id){
        TicketEntity ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<TicketEntity> updateTicket(@RequestBody TicketEntity ticket){
        if (ticket == null || ticketService.getTicketById(ticket.getIdTicket()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.updateTicket(ticket));
    }

    @GetMapping("/")
    public ResponseEntity<List<TicketEntity>> getAllTickets(){
        List<TicketEntity> tickets = ticketService.getAllTickets();
        if (tickets == null || tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tickets);
    }

    //------------------------------------------------------------------------------------------------------------
    // Operations
    @PutMapping("/basePrice")
    public ResponseEntity<TicketEntity> saveBasePrice(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveBasePrice(ticket));
    }

    @PutMapping("/kmSurcharge")
    public ResponseEntity<TicketEntity> saveKMSurcharge(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveKMSurcharge(ticket));
    }

    @PutMapping("/ageSurcharge")
    public ResponseEntity<TicketEntity> saveAgeSurcharge(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveAgeSurcharge(ticket));
    }

    @PutMapping("/delaySurcharge")
    public ResponseEntity<TicketEntity> saveSurchargeForDelay(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveSurchargeForDelay(ticket));
    }

    @PutMapping("/repairDiscount")
    public ResponseEntity<TicketEntity> saveDiscountByRepairs(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveDiscountByRepairs(ticket));
    }

    @PutMapping("/dayDiscount")
    public ResponseEntity<TicketEntity> saveDiscountByDay(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveDiscountByDay(ticket));
    }

    @PutMapping("/brandBonus")
    public ResponseEntity<TicketEntity> saveBrandBonus(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveBrandBonus(ticket));
    }

    @PutMapping("/totalPrice")
    public ResponseEntity<TicketEntity> saveTotalPrice(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(ticketService.saveTotalPrice(ticket));
    }
    //------------------------------------------------------------------------------------------------------------
    // Initialization for the ticket
    @PutMapping("/init")
    public ResponseEntity<TicketEntity> saveInit(@RequestBody TicketEntity ticket){
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ticketService.savePickupDate(ticket);
        ticketService.saveBasePrice(ticket);
        ticketService.saveKMSurcharge(ticket);
        ticketService.saveAgeSurcharge(ticket);
        ticketService.saveSurchargeForDelay(ticket);
        ticketService.saveDiscountByRepairs(ticket);
        ticketService.saveDiscountByDay(ticket);
        ticketService.saveBrandBonus(ticket);
        ticketService.saveTotalPrice(ticket);
        return ResponseEntity.ok(ticket);
    }


}