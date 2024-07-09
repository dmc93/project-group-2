import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import CustomAlert from "./CustomAlert";
import axios from "axios";
import { Container, Row, Col } from "react-bootstrap";
import '../css/PropertyCard.css';
import ApptPropCard from "./ApptPropCard";

const BookAppointment = () => {
  const [timeSlot, setTimeSlot] = useState('');
  const [firstName, setFirstName] = useState('');
  const [surname, setSurname] = useState('');
  const [propertyId, setPropertyId] = useState('');
  const [date, setDate] = useState('');
  const [buyerId, setBuyerId] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [details, setDetails] = useState([]);
  const [userPopulate, setUserPopulate] = useState({
    firstName: "",
    surname: "",
  });
  const availableSlots = [
    { value: '8:00-9:00', label: '8:00 AM - 9:00 AM' },
    { value: '9:00-10:00', label: '9:00 AM - 10:00 AM' },
    { value: '10:00-11:00', label: '10:00 AM - 11:00 AM' },
    { value: '12:00-13:00', label: '12:00 PM - 1:00 PM' },
    { value: '13:00-14:00', label: '1:00 PM - 2:00 PM' },
    { value: '15:00-16:00', label: '3:00 PM - 4:00 PM' },
    { value: '16:00-17:00', label: '4:00 PM - 5:00 PM' },
  ];

  const params = useParams();
  const navigate = useNavigate();

  const handleCloseAlert = () => {
    setShowAlert(false);
    navigate("/appointments");
  };

  useEffect(() => {
    axios
        .get("http://localhost:4495/property/" + params.id)
        .then((res) => {
          setPropertyId(res.data.id);
          setDetails(res.data);
        })
        .catch((err) => console.log(err));
  }, [params.id]);

  const handleTimeChange = (e) => {
    setTimeSlot(e.target.value); // Update the selected time
  };

  const handlePopulate = async (e) => {
    const buyerId = e.target.value;
    setBuyerId(buyerId);

    if (buyerId) {
      try {
        const response = await fetch("http://localhost:4495/buyer/get/" + buyerId);
        const userData = await response.json();
        if (userData) {
          setFirstName(userData.firstname);
          setSurname(userData.surname);
        } else {
          console.log("User not found");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    if (!buyerId || !firstName || !surname || !propertyId || !date || !timeSlot) {
      setAlertMessage('All fields are required.');
      setShowAlert(true);
      setIsSubmitting(false);
      return;
    }

    const convertedPropertyId = parseInt(propertyId, 10);
    try {
      // Fetch all existing appointments
      const response = await axios.get('http://localhost:4495/appointments/getAll');
      const bookingData = response.data;

      // Check if the selected timeslot is already booked
      const appointmentExists = bookingData.some(
          (booking) => booking.propertyId === convertedPropertyId && booking.date === date && booking.timeSlot === timeSlot
      );

      if (appointmentExists) {
        setAlertMessage(`This timeslot is already booked for Property ID ${propertyId} on ${date} at ${timeSlot}. Please select another time slot.`);
        setShowAlert(true);
        setIsSubmitting(false); // Reset the submitting state
        return;
      }

      // If the slot is available, proceed with booking
      const appointment = {
        buyer: { id: buyerId },
        firstName,
        surname,
        property: { id: convertedPropertyId },
        date,
        timeSlot
        
    };

      const appointmentResponse = await axios.post('http://localhost:4495/appointments/create', appointment);
      const appointmentData = appointmentResponse.data;
      setAlertMessage(`Appointment Booked. Your Booking ID is ${appointmentData.id}`);
      setShowAlert(true);

      // Clear form fields after successful booking
      setBuyerId('');
      setFirstName('');
      setSurname('');
      setPropertyId('');
      setDate('');
      setTimeSlot('');
    } catch (error) {
      console.error('Booking error:', error.response ? error.response.data : error.message);
      setAlertMessage('Error booking appointment.');
      setShowAlert(true);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
      <div>
        <Container fluid className="h-100 d-flex align-items-center">
          <Row className="w-100">
            <Col
                md={6}
                className="d-flex align-items-center justify-content-center"
            >
              {showAlert && (
                  <CustomAlert message={alertMessage} onClose={handleCloseAlert} />
              )}
              <form onSubmit={handleSubmit}>
                <div>
                  <label className="label1">Buyer ID:</label>
                  <input
                      className="input1"
                      type="text"
                      required
                      value={buyerId}
                      onChange={(e) => setBuyerId(e.target.value)}
                      onBlur={handlePopulate}
                  />
                </div>
                <div style={{ fontSize: '14px', color: '#800880', marginBottom: '10px', marginTop: '5px' }}>
                  Not currently registered? <a href="/Buyers" style={{ color: '#800880' }}>Click here to register</a>
                </div>

                <div>
                  <label className="label1">First Name:</label>
                  <input
                      className="input1"
                      type="text"
                      required
                      value={firstName}
                      onChange={(e) => setFirstName(e.target.value)}
                      readOnly
                  />
                </div>
                <br />
                <div>
                  <label className="label1">Surname:</label>
                  <input
                      className="input1"
                      type="text"
                      value={surname}
                      onChange={(e) => setSurname(e.target.value)}
                      readOnly
                  />
                </div>
                <br />
                <div>
                  <label className="label1">Property ID:</label>
                  <input
                      className="input1"
                      type="text"
                      required
                      value={propertyId}
                      onChange={(e) => setPropertyId(e.target.value)}
                  />
                </div>
                <br />
                <div>
                  <label className="label1">Date:</label>
                  <input
                      className="input1"
                      type="date"
                      value={date}
                      onChange={(e) => setDate(e.target.value)}
                  />
                </div>
                <br />
                <div>
                  <label className="label1">Select Time:</label>
                  <select
                      className="input1"
                      value={timeSlot}
                      onChange={handleTimeChange}
                  >
                    <option value="">Choose a time</option>
                    {availableSlots.map((slot) => (
                        <option key={slot.value} value={slot.value}>
                          {slot.label}
                        </option>
                    ))}
                  </select>
                </div>
                <br />
                <button className="button1" type="submit" disabled={isSubmitting}>Book</button>

                {showAlert && (
                    <CustomAlert
                        message={alertMessage}
                        onClose={() => {
                          setShowAlert(false); // Close the alert
                          window.location.reload(); // Reload the page after closing the alert
                        }}
                    />
                )}
              </form>
            </Col>
            <Col
                md={6}
                className="d-flex align-items-center justify-content-center"
            >
              <div className="propertyCard">
                <ApptPropCard
                    street={details.street}
                    town={details.town}
                    bedrooms={details.bedrooms}
                    bathrooms={details.bathrooms}
                    price={details.price}
                    garden={details.garden}
                    imageUrl={details.imageUrl}
                    status={details.status}
                />
              </div>
            </Col>
          </Row>
        </Container>
      </div>
  );
};

export default BookAppointment;
