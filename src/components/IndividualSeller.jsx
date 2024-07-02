import { useState, useEffect } from 'react';
import '../css/RegisterUser.css';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function Propseller() {
    const params = useParams();
    const [userAppointments, setUserAppointments] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8889/appointments?sellerId=${params.sellerId}`)
            .then((response) => response.data)
            .then((data) => {
                setUserAppointments(data);
                console.log(data);
            })
            .catch((error) => console.error('Error:', error));
    }, [params.sellerId]);

    const handleDelete = (e, id) => {
        e.preventDefault();
        axios.delete(`http://localhost:8889/appointments/${id}`)
            .then(() => {
                setUserAppointments(userAppointments.filter(appoint => appoint.id !== id));
            })
            .catch((error) => console.error('Error:', error));
    };

    return (
        <div>
            <br /> <br />
            <h1 className="pagetitle">Booked Appointments</h1>
            <br /><br />
            <div className='table-container'>
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Seller ID</th>
                            <th>First Name</th>
                            <th>Surname</th>
                            <th>Property ID</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {userAppointments && userAppointments.map((appoint) => (
                            <tr key={appoint.id}>
                                <td>{appoint.id}</td>
                                <td>{appoint.sellerId}</td>
                                <td>{appoint.firstName}</td>
                                <td>{appoint.surname}</td>
                                <td>{appoint.propertyId}</td>
                                <td>{appoint.date}</td>
                                <td>{appoint.timeSlot}</td>
                                <td>
                                    <button className="delete-btn" onClick={(e) => handleDelete(e, appoint.id)}>Cancel</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Propseller;
