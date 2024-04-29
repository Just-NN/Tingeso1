import { useNavigate } from 'react-router-dom';
import NavBar from "./NavBar.jsx";
import './theme.css';

const Reports = () => {
    const navigate = useNavigate();

    const goToReportList = () => {
        navigate('/reports/1');
    }

    return (
        <div>
            <NavBar></NavBar>
            <h1>Reports</h1>
            <div className="body">
                <div className="options-grid">
                    <div className='option-row'>
                        <div className='option-card'>
                            <button onClick={goToReportList}>
                                <h3>Go to Report List</h3>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Reports;