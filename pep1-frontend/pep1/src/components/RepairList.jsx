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
                console.log("UPDATED");
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

            <div className="table-container">
                <table className="repair-table">
                    <thead>
                    <tr>
                        <th>ID Repair</th>
                        <th>Vehicle ID</th>
                        <th>Entry Date</th>
                        <th>Exit Date</th>
                        <th>Pickup Date</th>
                        <th>Repair Description</th>
                        <th>Repair Cost</th>
                        <th>Repair Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {repairs.map(repair => (
                        <tr key={repair.idRepair}>
                            <td>{repair.idRepair}</td>
                            <td>{repair.vehicleId}</td>
                            <td>{format(repair.entryDate, 'dd/MM/yyyy')}</td>
                            <td>{format(repair.exitDate, 'dd/MM/yyyy')}</td>
                            <td>{format(repair.pickupDate, 'dd/MM/yyyy')}</td>
                            <td>{repair.repairDescription}</td>
                            <td>{repair.repairCost}</td>
                            <td>{repair.repairStatus}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                </div>
        </div>
    )
}

export default RepairList;