
import './App.css'
import {BrowserRouter, Route, Routes, Navigate} from "react-router-dom";
import Home from "./components/Home.jsx";
import Repairs from "./components/RepairsHome.jsx";
import Vehicles from "./components/VehiclesHome.jsx";
import BonusBrands from "./components/BonusBrandsHome.jsx";
import Reports from "./components/ReportsHome.jsx";
import Tickets from "./components/TicketsHome.jsx";
import RepairList from "./components/RepairList.jsx";
import AddEditRepair from "./components/AddEditRepair2.jsx";
import RepairDetail from "./components/FindRepairById.jsx";
import AddVehicle from "./components/AddVehicle.jsx";
import ReportList from "./components/ReportList.jsx";
import TicketList from "./components/TicketList.jsx";
import AddBrand from "./components/AddBrand.jsx";
import BonusBrandList from "./components/BonusList.jsx";

function App() {
    return (
        <>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/home" />} />
                <Route path="/home" element={<Home />} />
                <Route path="/vehicles" element={<Vehicles />} />
                <Route path="/repairs" element={<Repairs />} />
                <Route path="/tickets" element={<Tickets />} />
                <Route path="/brands" element={<BonusBrands />} />
                <Route path="/reports" element={<Reports />} />
                <Route path="/repairs/list" element={<RepairList />} />
                <Route path="/repairs/add" element={<AddEditRepair />} />
                <Route path="/repairs/:id" element={<RepairDetail />} />
                <Route path="/vehicles/add" element={<AddVehicle />} />
                <Route path="/reports/1" element={<ReportList />} />
                <Route path="/tickets/list" element={<TicketList />} />
                <Route path="/brands/add" element={<AddBrand />} />
                <Route path="/brands/list" element={<BonusBrandList />} />

            </Routes>
        </BrowserRouter>
        </>
    )
}

export default App