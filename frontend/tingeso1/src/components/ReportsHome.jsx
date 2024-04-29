
import {AppBar, Toolbar} from "@mui/material";
import NavbarComponent from "./NavbarComponent.jsx";


const Reports = () => {
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
                          <h3>Create</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Update</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Show</h3>
                          <p>Option 1 description</p>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  );
};

export default Reports;