import NavBar from "./NavBar.jsx";
import './theme.css';

const BonusBrands = () => {

  return (
      <div>
          <NavBar></NavBar>
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