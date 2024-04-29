
import {AppBar, Toolbar} from "@mui/material";
import NavbarComponent from "./NavbarComponent.jsx";


const Vehicles = () => {
  return (
      <div>
          <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
            <Toolbar>
              <NavbarComponent />
            </Toolbar>
          </AppBar>
          <h1>Vehicles</h1>
          <div className="body">
              <h2>Options</h2>
              <div className="options-grid">
                  <div className='option-row'>
                      <div className='option-card'>
                          <h3>Find All</h3>
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

export default Vehicles;