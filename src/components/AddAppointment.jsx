import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CustomAlert from './CustomAlert';

const BookAppointment = () => {
  const [timeSlot, setTimeSlot] = useState('');
  const [firstName, setFirstName] = useState('');
  const [surname, setSurname] = useState('');
  const [propertyId, setPropertyId] = useState('');
  const [date, setDate] = useState('');
  const [buyerId, setBuyerId] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');

  const availableSlots = [
    '8:00-9:00',
    '9:00-10:00',
    '10:00-11:00',
    '12:00-13:00',
    '13:00-14:00',
    '15:00-16:00',
    '16:00-17:00',
  ];

  const handleTimeChange = (e) => {
    setTimeSlot(e.target.value);
  };

  const handlePopulate = async () => {
    try {
      const response = await axios.get(`http://localhost:4495/buyer/get/${buyerId}`);
      const userData = response.data;

      if (userData) {
        setFirstName(userData.firstname);
        setSurname(userData.surname);
      } else {
        setAlertMessage(`Buyer ID ${buyerId} does not exist. Please enter a valid Buyer ID`);
        setShowAlert(true);
        setBuyerId('');
        setFirstName('');
        setSurname('');
      }
    } catch (error) {
      console.error('Error fetching buyer data:', error);
      setAlertMessage('Error fetching buyer data.');
      setShowAlert(true);
    }
  };

  const handlePropertyValidation = async () => {
    if (propertyId === '') return;

    try {
      const response = await axios.get(`http://localhost:4495/property/getAll/${propertyId}`);
      const propertyData = response.data;

      if (propertyData) {
        // Assuming propertyData has the necessary fields, you can set additional state if needed
      } else {
        setAlertMessage(`Property ID ${propertyId} does not exist.`);
        setShowAlert(true);
        setPropertyId('');
      }
    } catch (error) {
      console.error('Error fetching property data:', error);
      setAlertMessage('Error fetching property data.');
      setShowAlert(true);
    }
  };

  useEffect(() => {
    if (buyerId === '') {
      setFirstName('');
      setSurname('');
    }
  }, [buyerId]);

  const handleDateChange = (e) => {
    const selectedDate = new Date(e.target.value);
    const today = new Date();

    selectedDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    if (selectedDate < today) {
      setAlertMessage('You cannot select a past date. Please select a valid date.');
      setShowAlert(true);
      setDate('');
    } else {
      setDate(e.target.value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get('http://localhost:4495/appointments/getAll');
      const bookingData = response.data;
      const appointmentExists = bookingData.some(
          (booking) => booking.propertyId === propertyId && booking.date === date && booking.timeSlot === timeSlot
      );
      if (appointmentExists) {
        setAlertMessage(`This timeslot is already booked for Property ID ${propertyId} on ${date} at ${timeSlot}. Please select another time slot.`);
        setShowAlert(true);
        return;
      }
    } catch (error) {
      console.error('Error fetching appointment data:', error);
      setAlertMessage('Error fetching appointment data.');
      setShowAlert(true);
      return;
    }

    const appointment = { buyerId, firstName, surname, propertyId, date, timeSlot };
    try {
      const appointmentResponse = await axios.post(`http://localhost:4495/appointments/create`, appointment);
      const appointmentData = appointmentResponse.data;
      setAlertMessage(`Appointment Booked. Your Booking ID is ${appointmentData.id}`);
      setShowAlert(true);

      setBuyerId('');
      setFirstName('');
      setSurname('');
      setPropertyId('');
      setDate('');
      setTimeSlot('');
    } catch (error) {
      console.error('Booking error', error);
      setAlertMessage('Error booking appointment.');
      setShowAlert(true);
    }
  };

  return (
      <div className="body">
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
          <br />
          <div>
            <label className="label1">First Name:</label>
            <input
                className="input1"
                type="text"
                required
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
            />
          </div>
          <br />
          <div>
            <label className="label1">Surname:</label>
            <input
                className="input1"
                type="text"
                required
                value={surname}
                onChange={(e) => setSurname(e.target.value)}
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
                // onBlur={handlePropertyValidation}
            />
          </div>
          <br />
          <div>
            <label className="label1">Date:</label>
            <input
                className="input1"
                type="date"
                value={date}
                onChange={handleDateChange}
            />
          </div>
          <br />
          <div>
            <label className="label1">Select Time:</label>
            <select className="input1" value={timeSlot} onChange={handleTimeChange}>
              <option value="">Choose a time</option>
              {availableSlots.map((slot) => (
                  <option key={slot} value={slot}>
                    {slot}
                  </option>
              ))}
            </select>
          </div>
          <br />
          <button className="button1">Book</button>
          {showAlert && (
              <CustomAlert
                  message={alertMessage}
                  onClose={() => {
                    setShowAlert(false);
                  }}
              />
          )}
        </form>
      </div>
  );
};

export default BookAppointment;
