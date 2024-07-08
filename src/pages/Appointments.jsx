import { useState, useEffect } from "react";
import BookAppointment from "../components/AddAppointment";
import CustomAlert from '../components/CustomAlert';
import PasswordInput from '../components/PasswordInput';
import ConfirmationDialog from '../components/ConfirmationDialog';

const useFetch = (url) => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true); // Add loading state

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      const response = await fetch(url);
      const result = await response.json();
      setData(result);
      setLoading(false);
    };

    fetchData();
  }, [url]); // Depend on URL only

  return [data, loading, setData];
};

const Appointments = () => {
  const [deleteId, setDeleteId] = useState('');
  const [data, loading, setData] = useFetch('http://localhost:4495/appointments/getAll');
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const [showConfirmation, setShowConfirmation] = useState(false);



  const handleDelete = (id) => {
    setDeleteId(id);
    setShowConfirmation(true);
  };

  const handleConfirmDelete = async () => {
    try {
      await fetch(`http://localhost:4495/appointments/remove/${deleteId}`, {
        method: 'DELETE',
      });

      // Update the data without reloading the page
      const newData = data.filter(appoint => appoint.id !== deleteId);
      setData(newData);
      setDeleteId(null);
      setShowConfirmation(false);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleCancelDelete = () => {
    setDeleteId(null);
    setShowConfirmation(false);
  };

  // // Define function to handle password submission
  // const handlePasswordSubmit = (password = "Password") => {
  //   // Check if the password is correct
  //   if (password === 'Password') {
  //     setIsAuthenticated(true); // Set isAuthenticated to true
  //   } else {
  //     setShowAlert(true); // Show alert if the password is incorrect
  //   }
  // };
console.log("CHECKPOINT",data)
  return (
      <div className="body">
        <div className="container2" style={{ paddingBottom: 0 }}>
          <h1 className="pagetitle">Book Appointment</h1>
          <BookAppointment />

          {/* {!isAuthenticated && <PasswordInput onSubmit={handlePasswordSubmit} />} */}

          <br />
          <br />

          <div>
            <br /> <br />
            <div className='table-container'>
              {loading ? (
                  <p>Loading...</p>
              ) : (
                  <table>
                    <thead>
                    <tr>
                      <th>Booking ID</th>
                      <th>Buyer ID</th>
                      <th>First Name</th>
                      <th>Surname</th>
                      <th>Property ID</th>
                      <th>Date</th>
                      <th>Time</th>
                      <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {data && data.map((appoint) => (
                        <tr key={appoint.id}>
                          <td>{appoint.id}</td>
                          <td>{appoint.buyerId}</td>
                          <td>{appoint.firstName}</td>
                          <td>{appoint.surname}</td>
                          <td>{appoint.propertyId}</td>
                          <td>{appoint.date}</td>
                          <td>{appoint.timeSlot}</td>
                          <td><button className="delete-btn" onClick={() => handleDelete(appoint.id)}>Cancel</button></td>
                        </tr>
                    ))}
                    </tbody>
                  </table>
              )}
            </div>
          </div>

          {showConfirmation && (
              <ConfirmationDialog
                  message="Are you sure you want to cancel this Appointment?"
                  onConfirm={handleConfirmDelete}
                  onCancel={handleCancelDelete}
              />
          )}
        </div>
      </div>
  );
}

export default Appointments;
