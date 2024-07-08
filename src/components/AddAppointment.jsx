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
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [buyer, setBuyer]=useState();

    const availableSlots = [
        { value: '8:00-9:00', label: '8:00 AM - 9:00 AM' },
        { value: '9:00-10:00', label: '9:00 AM - 10:00 AM' },
        { value: '10:00-11:00', label: '10:00 AM - 11:00 AM' },
        { value: '12:00-13:00', label: '12:00 PM - 13:00 PM' },
        { value: '13:00-14:00', label: '13:00 PM - 14:00 PM' },
        { value: '15:00-16:00', label: '15:00 PM - 16:00 PM' },
        { value: '16:00-17:00', label: '16:00 PM - 17:00 PM' },
    ];

    const handleTimeChange = (e) => {
        setTimeSlot(e.target.value);
    };

    const handlePopulate = async () => {
        if (!buyerId) return;
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
        if (!propertyId) return;
        try {
            const response = await axios.get(`http://localhost:4495/property/${propertyId}`);
            const propertyData = response.data;

            if (!propertyData || propertyData.length === 0) {
                setAlertMessage(`Property ID ${propertyId} does not exist.`);
                setShowAlert(true);
                setPropertyId('');
            }
        } catch (error) {
            console.error('Error fetching property data:', error.response ? error.response.data : error.message);
            setAlertMessage('Error fetching property data.');
            setShowAlert(true);
        }
    };

    useEffect(() => {
        if (!buyerId) {
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
        setIsSubmitting(true);

        if (!buyerId || !firstName || !surname || !propertyId || !date || !timeSlot) {
            setAlertMessage('All fields are required.');
            setShowAlert(true);
            setIsSubmitting(false);
            return;
        }

      
        const convertedPropertyId = parseInt(propertyId, 10);

        try {
            const response = await axios.get('http://localhost:4495/appointments/getAll');
            const bookingData = response.data;

            const appointmentExists = bookingData.some(
                (booking) => booking.propertyId === convertedPropertyId && booking.date === date && booking.timeSlot === timeSlot
            );

            console.log('Existing bookings:', bookingData);
            console.log('Checking for appointment:', { propertyId: convertedPropertyId, date, timeSlot });

            if (appointmentExists) {
                setAlertMessage(`This timeslot is already booked for Property ID ${propertyId} on ${date} at ${timeSlot}. Please select another time slot.`);
                setShowAlert(true);
                setIsSubmitting(false);
                return;
            }

            const appointment = {
                buyer: { id: buyer },
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

            setBuyerId('');
            setFirstName('');
            setSurname('');
            setPropertyId('');
            setBuyer('');
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
        <div className="body">
            <form onSubmit={handleSubmit}>
                <div>
                    <label className="label1">Buyer ID:</label>
                    <input
                        className="input1"
                        type="text"
                        required
                        value={buyerId}
                        onChange={(e) => setBuyerId(e.target.value)+ setBuyer(e.target.value)}
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
                        readOnly
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
                        onBlur={handlePropertyValidation}
                    />
                </div>
                <br />
                <div>
                    <label className="label1">Date:</label>
                    <input
                        className="input1"
                        type="date"
                        required
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
                            <option key={slot.value} value={slot.value}>
                                {slot.label}
                            </option>
                        ))}
                    </select>
                </div>
                <br />
                <button className="button1" type="submit" disabled={isSubmitting}>
                    {isSubmitting ? 'Booking...' : 'Book'}
                </button>
                {showAlert && (
                    <CustomAlert
                        message={alertMessage}
                        onClose={() => {
                            setShowAlert(false);
                            window.location.reload()
                        }}
                    />
                )}
            </form>
        </div>
    );
};

export default BookAppointment;
