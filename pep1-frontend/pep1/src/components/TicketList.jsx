import { useEffect, useState } from 'react';
import ticketService from '../services/ticket.service';
import NavBar from "./NavBar.jsx";
import './theme.css';
import { format } from 'date-fns';

const TicketList = () => {
    const [tickets, setTickets] = useState([]);

    const fetchTickets = () => {
        ticketService.getAllTickets()
            .then(response => {
                const data = response.data.map(ticket => ({
                    ...ticket,
                    pickupDate: new Date(ticket.pickupDate)
                }));
                setTickets(data);
                console.log('Tickets wa:', data)
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    const initTickets = () => {
        console.log('Initializing tickets...')
        tickets.forEach(ticket => {
            console.log('Initializing ticket:', ticket.idTicket)
            ticketService.initTicket(ticket)
                .then(response => {
                    console.log('Response data:', response.data);
                    // Update the ticket in the state if necessary
                })
                .catch(error => {
                    console.error('There was an error!', error, 'IN TICKET', ticket.idTicket);
                });
        });
        // Fetch the updated tickets after initializing
        fetchTickets();
    }

    useEffect(() => {
        fetchTickets();
    }, []);

    return (
        <div className='option-body'>
            <NavBar></NavBar>
            <h1>Ticket List</h1>

            <button className="reload-button" onClick={fetchTickets}>Reload Table</button>
            <button className="init-button" onClick={initTickets}>Initialize Tickets</button>

            <table className="repair-table">
                <thead>
                <tr>
                    <th>ID Ticket</th>
                    <th>Pickup Date</th>
                    <th>ID Bonus</th>
                    <th>Surcharge for KM</th>
                    <th>Surcharge for Age</th>
                    <th>Surcharge for Delay</th>
                    <th>Discount for Repairs</th>
                    <th>Discount per Day</th>
                    <th>Discount for Bonus</th>
                    <th>Brand Bonus</th>
                    <th>Base Price</th>
                    <th>Total Price</th>
                </tr>
                </thead>
                <tbody>
                {tickets.map((ticket, index) => (
                    <tr key={index}>
                        <td>{ticket.idTicket}</td>
                        <td>{format(ticket.pickupDate, "yyyy-MM-dd'T'HH:mm")}</td>
                        <td>{ticket.idBonus}</td>
                        <td>{ticket.surchargeForKM}</td>
                        <td>{ticket.surchargeForAge}</td>
                        <td>{ticket.surchargeForDelay}</td>
                        <td>{ticket.discountForRepairs}</td>
                        <td>{ticket.discountPerDay}</td>
                        <td>{ticket.discountForBonus}</td>
                        <td>{ticket.brandBonus}</td>
                        <td>{ticket.basePrice}</td>
                        <td>{ticket.totalPrice}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default TicketList;