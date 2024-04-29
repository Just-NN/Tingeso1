
import {AppBar, Toolbar} from "@mui/material";
import NavbarComponent from "./NavbarComponent.jsx";
import {useNavigate} from "react-router-dom";

const Repairs = () => {
    const navigate = useNavigate();
    const handlePageChange = (operation) => {
        navigate(operation);
    }
  return (
      <div>
          <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
            <Toolbar>
              <NavbarComponent />
            </Toolbar>
          </AppBar>
          <h1>Repairs</h1>
          <div className="body">
              <div className="options-grid">
                  <div className='option-row'>
                      <div className='option-card'>
                          <h3 onClick={handlePageChange("get")}>Find All</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Find by id</h3>
                          <p>Option 1 description</p>
                      </div>
                  </div>
                  <div className="option-row">
                      <div className='option-card'>
                          <h3>Create</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Delete</h3>
                          <p>Option 1 description</p>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  );
};

export default Repairs;