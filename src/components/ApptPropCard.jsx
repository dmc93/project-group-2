
import '../App.css'
import '../css/PropertyCard.css'
import { FaBed, FaBath, FaTree } from 'react-icons/fa';


export default function ApptPropCard({street, town, bedrooms, bathrooms, price, garden, imageUrl, state}) {

  
  // Safely handle price formatting
  const formattedPrice = price
    ? price.toLocaleString('en-GB', {
        style: 'currency',
        currency: 'GBP',
        minimumFractionDigits: 0,
      })
    : 'N/A';




  const getStatusClass = (state) => {
        switch (state) {
            case 'For Sale':
              return 'bg-success text-white';
            case 'Withdrawn':
              return 'bg-warning text-dark';
            case 'Sold':
              return 'bg-danger text-white';
            default:
              return 'bg-secondary text-white';
        }
    };

    const isButtonDisabled = (state) => {
        return state === 'Sold' || state === 'Withdrawn';
      };

      return (
        
          <div className="card">
            <div className="card-header">
              <h3 className="mb-0 street-name">{street}</h3>
            </div>
            <img className="card-img-top" src={imageUrl} alt="Property" />
            <div className="card-body">
              <h4 className='street-name'>{town}</h4>
              <h4>{formattedPrice}</h4>
              <p><span className={`badge ${getStatusClass(state)}`}>{state}</span></p>
              <div className="row text-center">
                <div className="col">
                  <FaBed size={24} />
                  <p>{bedrooms}</p>
                </div>
                <div className="col">
                  <FaBath size={24} />
                  <p>{bathrooms}</p>
                </div>
                <div className="col">
                  <FaTree size={24} />
                  <p>{garden}</p>
                </div>
              </div>
            </div>
          </div>
        
        
      );
      
    }

