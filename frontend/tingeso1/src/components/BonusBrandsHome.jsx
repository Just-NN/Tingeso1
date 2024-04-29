
import {AppBar, Toolbar} from "@mui/material";
import NavbarComponent from "./NavbarComponent.jsx";


const BonusBrands = () => {

  return (
      <div>
          <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
            <Toolbar>
              <NavbarComponent />
            </Toolbar>
          </AppBar>
          <h1>Brand Bonuses</h1>
          <div className="body">
              <h2>Options</h2>
              <div className="options-grid">
                  <div className='option-row'>
                      <div className='option-card'>
                          <h3>Show All</h3>
                          <p>Option 1 description</p>
                      </div>
                      <div className='option-card'>
                          <h3>Create a Bonus</h3>
                          <p>Option 1 description</p>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  );
};

export default BonusBrands;