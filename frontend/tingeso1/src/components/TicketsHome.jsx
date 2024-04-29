
import {AppBar, Toolbar} from "@mui/material";
import NavbarComponent from "./NavbarComponent.jsx";


const Tickets = () => {
  return (
      <div>
          <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
            <Toolbar>
              <NavbarComponent />
            </Toolbar>
          </AppBar>
          <h1>Tickets</h1>
          <div className="body">
              <div className="options-grid">
                  <div className='option-row'>
                      <div className='option-card'>
                          <h3>Calculate and show</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Find by id</h3>
                          <p>Option 1 description</p>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  );
};

export default Tickets;