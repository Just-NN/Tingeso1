import { useEffect, useState } from 'react';
import repairService from '../services/repair.service'; // adjust the path according to your project structure

const RepairList = () => {
    const [repairs, setRepairs] = useState([]);

    useEffect(() => {
        repairService.getAllRepairs()
            .then(response => {
                setRepairs(response.data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }, []);

    return (
        <div>
            <h1>Repair List</h1>
            {repairs.map((repair, index) => (
                <div key={index}>
                    <h3>ID Ticket: {repair.idTicket}</h3>
                <p>License Plate: {repair.licensePlate}</p>
                <p>Repair Type: {repair.repairType}</p>
                <p>Entry Date: {repair.entryDate}</p>
                <p>Exit Date: {repair.exitDate}</p>
                <p>Exit Time: {repair.exitTime}</p>
                <p>Pickup Date: {repair.pickupDate}</p>
                <p>Pickup Time: {repair.pickupTime}</p>
                <p>Total Repair Amount: {repair.totalRepairAmount}</p>
                <p>KM Surcharge: {repair.kmSurcharge}</p>
                <p>Age Surcharge: {repair.ageSurcharge}</p>
                <p>Delay Surcharge: {repair.delaySurcharge}</p>
                <p>Day Discount: {repair.dayDiscount}</p>
                <p>Repairs Discount: {repair.repairsDiscount}</p>
                <p>Base Price: {repair.basePrice}</p>
                <p>Total Price: {repair.totalPrice}</p>
                </div>
            ))}
        </div>
    )
}

export default RepairList;