import { useEffect, useState } from 'react';
import repairService from '../services/repair.service';
import NavBar from "./NavBar.jsx";
import './theme.css';
import { format } from 'date-fns';

const RepairList = () => {
    const [repairs, setRepairs] = useState([]);

    const fetchRepairs = () => {
        repairService.getAllRepairs()
            .then(response => {
                const data = response.data.map(repair => ({
                    ...repair,
                    entryDate: new Date(repair.entryDate),
                    exitDate: new Date(repair.exitDate),
                    pickupDate: new Date(repair.pickupDate)
                }));
                setRepairs(data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    useEffect(() => {
        fetchRepairs();
    }, []);

    return (
        <div className='option-body'>
            <NavBar></NavBar>
            <h1>Repair List</h1>

            <button className="reload-button" onClick={fetchRepairs}>Reload Table</button>

            <table className="repair-table">
                <thead>
                <tr>
                    <th>ID Ticket</th>
                    <th>License Plate</th>
                    <th>Repair Type</th>
                    <th>Entry Date</th>
                    <th>Exit Date</th>
                    <th>Exit Time</th>
                    <th>Pickup Date</th>
                    <th>Pickup Time</th>
                    <th>Total Repair Amount</th>
                    <th>KM Surcharge</th>
                    <th>Age Surcharge</th>
                    <th>Delay Surcharge</th>
                    <th>Day Discount</th>
                    <th>Repairs Discount</th>
                    <th>Base Price</th>
                    <th>Total Price</th>
                </tr>
                </thead>
                <tbody>
                {repairs.map((repair, index) => (
                    <tr key={index}>
                        <td>{repair.idTicket}</td>
                        <td>{repair.licensePlate}</td>
                        <td>{repair.repairType}</td>
                        <td>{format(repair.entryDate, "yyyy-MM-dd'T'HH:mm")}</td>
                        <td>{format(repair.exitDate, "yyyy-MM-dd'T'HH:mm")}</td>
                        <td>{repair.exitTime}</td>
                        <td>{format(repair.pickupDate, "yyyy-MM-dd'T'HH:mm")}</td>
                        <td>{repair.pickupTime}</td>
                        <td>{repair.totalRepairAmount}</td>
                        <td>{repair.kmSurcharge}</td>
                        <td>{repair.ageSurcharge}</td>
                        <td>{repair.delaySurcharge}</td>
                        <td>{repair.dayDiscount}</td>
                        <td>{repair.repairsDiscount}</td>
                        <td>{repair.basePrice}</td>
                        <td>{repair.totalPrice}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default RepairList;