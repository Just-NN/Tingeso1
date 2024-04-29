
import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home.jsx";
import Repairs from "./components/RepairsHome.jsx";
import Vehicles from "./components/VehiclesHome.jsx";
import BonusBrands from "./components/BonusBrandsHome.jsx";
import Reports from "./components/ReportsHome.jsx";
import Tickets from "./components/TicketsHome.jsx";
import RepairList from "./components/RepairList.jsx";
import AddEditRepair from "./components/AddEditRepair.jsx";

function App() {
    return (
        <>
        <BrowserRouter>
            <Routes>
                <Route path="/home" element={<Home />} />
                <Route path="/vehicles" element={<Vehicles />} />
                <Route path="/repairs" element={<Repairs />} />
                <Route path="/tickets" element={<Tickets />} />
                <Route path="/brands" element={<BonusBrands />} />
                <Route path="/reports" element={<Reports />} />
                <Route path="/repairs/list" element={<RepairList />} />
                <Route path="/repairs/add" element={<AddEditRepair />} />

            </Routes>
        </BrowserRouter>
        </>
    )
}

export default App