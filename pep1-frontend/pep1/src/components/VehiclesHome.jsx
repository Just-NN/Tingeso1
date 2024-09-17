import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import NavBar from "./NavBar.jsx";
import './theme.css';

const Vehicles = () => {
    const navigate = useNavigate();
    const [id, setId] = useState(''); // Add a new state variable for the ID

    const handlePageChange = (path) => {
        return () => navigate(path);
    }

    const handleIdSubmit = (event) => {
        event.preventDefault();
        navigate(`/vehicles/${id}`);
    }

    return (
        <div>
            <NavBar></NavBar>
            <h1 className="title">Vehicles</h1>
            <div className="body">
                <div className="options-grid">
                    <div className='option-row'>
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/vehicles/list")}>
                                <h3>Show All Vehicles</h3>
                            </button>
                            <p>Display all vehicles as a table</p>
                        </div>
                        <div className='option-card'>
                            <form onSubmit={handleIdSubmit}>
                                <input
                                    type="text"
                                    value={id}
                                    onChange={e => setId(e.target.value)}
                                    placeholder="Enter ID"
                                    required
                                />
                                <button className="op-button" type="submit">
                                    <h3>Find by id</h3>
                                </button>
                            </form>
                            <p>Display a Vehicle using its ID</p>
                        </div>
                    </div>
                    <div className="option-row">
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/vehicles/add")}>
                                <h3>Create Vehicle</h3>
                            </button>
                            <p>Fill the forms to create a new Vehicle</p>
                        </div>
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/vehicles/delete")}>
                                <h3>Delete</h3>
                            </button>
                            <p>Option 1 description</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Vehicles;