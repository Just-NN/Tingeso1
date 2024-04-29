import { useEffect, useState } from 'react';
import ticketService from '../services/ticket.service';
import NavBar from "./NavBar.jsx";
import './theme.css';

const TicketList = () => {
    const [tickets, setTickets] = useState([]);

    const fetchTickets = () => {
        ticketService.getAllTickets()
            .then(response => {
                console.log('Response data:', response.data);
                setTickets(response.data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    useEffect(() => {
        fetchTickets();
    }, []);

    return (
        <div className='option-body'>
            <NavBar></NavBar>
            <h1>Ticket List</h1>

            <button className="reload-button" onClick={fetchTickets}>Reload Table</button>

            <table className="repair-table">
                <thead>
                <tr>
                    <th>ID Ticket</th>
                    <th>License Plate</th>
                    <th>Ticket Type</th>
                    <th>Entry Date</th>
                    <th>Exit Date</th>
                    <th>Exit Time</th>
                    <th>Pickup Date</th>
                    <th>Pickup Time</th>
                    <th>Total Ticket Amount</th>
                </tr>
                </thead>
                <tbody>
                {tickets.map((ticket, index) => (
                    <tr key={index}>
                        <td>{ticket.idTicket}</td>
                        <td>{ticket.licensePlate}</td>
                        <td>{ticket.ticketType}</td>
                        <td>{ticket.entryDate}</td>
                        <td>{ticket.exitDate}</td>
                        <td>{ticket.exitTime}</td>
                        <td>{ticket.pickupDate}</td>
                        <td>{ticket.pickupTime}</td>
                        <td>{ticket.totalTicketAmount}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default TicketList;