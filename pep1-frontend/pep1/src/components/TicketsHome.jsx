import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import NavBar from "./NavBar.jsx";
import './theme.css';

const TicketsHome = () => {
    const navigate = useNavigate();
    const [id, setId] = useState(''); // Add a new state variable for the ID

    const handlePageChange = (path) => {
        return () => navigate(path);
    }

    const handleIdSubmit = (event) => {
        event.preventDefault();
        navigate(`/tickets/${id}`);
    }

    return (
        <div className="section-body">
            <NavBar></NavBar>

            <h1 className="title">Tickets</h1>
            <div className="body">
                <div className="options-grid">
                    <div className='option-row'>
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/tickets/list")}>
                                <h3>Show all Tickets</h3>
                            </button>
                            <p>Display all tickets into a table</p>
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
                                    <h3>Find by ID</h3>
                                </button>
                            </form>
                            <p>Display a Ticket using its ID</p>
                        </div>
                    </div>
                    <div className="option-row">
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/tickets/add")}>
                                <h3>Create Ticket</h3>
                            </button>
                            <p>Fill the forms to create a new Ticket</p>
                        </div>
                        <div className='option-card'>
                            <button className="op-button" onClick={handlePageChange("/tickets/delete")}>
                                <h3>Delete a Ticket</h3>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TicketsHome;