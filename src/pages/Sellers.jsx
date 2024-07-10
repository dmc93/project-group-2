import React, { useState, useEffect } from 'react';
import AddSeller from "../components/AddSeller";
import JsonDataDisplay from "../components/SellerTable";
import '../css/RegisterUser.css';
import CustomAlert from '../components/CustomAlert';
import PasswordInput from '../components/PasswordInput';
import axios from 'axios';

function AddSellers() {
    // Define state variables for authentication and alert
   // const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [showAlert, setShowAlert] = useState(false);

    const [seller, setSeller] = useState([]);
    const fetchData = async () => {
                try {
                    const response = await axios.get('http://localhost:4495/seller/get/all');
                    setSeller(response.data);
                } catch (error) {
                    console.error('Error:', error);
                }
            };

            useEffect(() => {
                fetchData();
            }, []);

    // Define function to handle password submission
    // const handlePasswordSubmit = (password) => {
    //     // Check if the password is correct
    //     if (password === 'Password') {
    //         setIsAuthenticated(true); // Set isAuthenticated to true
    //     } else {
    //         setShowAlert(true); // Show alert if the password is incorrect
    //     }
    // };

    return (
        <div className="body">
            <div className="container2" >
            <h1 className="pagetitle">Register a New Seller</h1>
                <AddSeller onAddSeller={fetchData} />
                {/* Render PasswordInput component and pass handlePasswordSubmit as onSubmit prop */}
                {/* {!isAuthenticated && <PasswordInput onSubmit={handlePasswordSubmit} />} */}
                {/* Render CustomAlert component if showAlert is true */}
                {showAlert && (
                    <CustomAlert
                        message="Incorrect password. Please try again."
                        onClose={() => setShowAlert(false)} // Close the alert
                    />
                )}
                {/* Render JsonDataDisplay component if isAuthenticated is true */}
                {/* {isAuthenticated && */} <JsonDataDisplay seller={seller} onSellerUpdate={fetchData}/>
            </div>
        </div>
    );
}

export default AddSellers;
