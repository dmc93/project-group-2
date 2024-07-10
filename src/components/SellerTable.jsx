import { useState, useEffect } from 'react';
import '../css/RegisterUser.css';
import ConfirmationDialog from './ConfirmationDialog';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function JsonDataDisplay({seller, onSellerUpdate}) {
    const navigate = useNavigate();
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [sellerIdToDelete, setSellerIdToDelete] = useState(null);
    const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'ascending' });





    const handleDelete = (id) => {
        setSellerIdToDelete(id);
        setShowConfirmation(true);
    };

    const handleCancel = () => {
        setShowConfirmation(false);
        setSellerIdToDelete(null);
    };

    const handleConfirm = async () => {
        try {
            const response = await axios.delete(`http://localhost:4495/seller/remove/${sellerIdToDelete}`);
            if (response.status === 200) {
                onSellerUpdate(); // Trigger fetching new data in parent

                console.log(`Seller with ID ${sellerIdToDelete} successfully deleted.`);
            } else {
                console.error('Failed to delete the seller with ID:', sellerIdToDelete);
            }
        } catch (error) {
            console.error('Error during deletion:', error);
        } finally {
            setShowConfirmation(false);
            setSellerIdToDelete(null);
        }
    };

    const sortedSellers = [...seller].sort((a, b) => {
        if (a[sortConfig.key] < b[sortConfig.key]) {
            return sortConfig.direction === 'ascending' ? -1 : 1;
        }
        if (a[sortConfig.key] > b[sortConfig.key]) {
            return sortConfig.direction === 'ascending' ? 1 : -1;
        }
        return 0;
    });

    const requestSort = (key) => {
        let direction = 'ascending';
        if (sortConfig.key === key && sortConfig.direction === 'ascending') {
            direction = 'descending';
        }
        setSortConfig({ key, direction });
    };

    return (
        <div>
            <br /><br />
            <div className='table-container'>
                <table>
                    <thead>
                        <tr>
                            <th onClick={() => requestSort('id')}>Sellers ID ↑ ↓</th>
                            <th onClick={() => requestSort('firstname')}>First Name ↑ ↓</th>
                            <th onClick={() => requestSort('surname')}>Surname ↑ ↓</th>
                            <th>Delete Seller</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sortedSellers.map((info) => (
                            <tr key={info.id}>
                                <td onClick={() => navigate('../properties/' + info.id)}>{info.id}</td>
                                <td>{info.firstname}</td>
                                <td>{info.surname}</td>
                                <td>
                                    <button className="delete-btn" onClick={() => handleDelete(info.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {showConfirmation && (
                <ConfirmationDialog
                    message="Are you sure you want to delete this seller? Any properties listed by this seller and associated appointments will also be deleted."
                    onConfirm={handleConfirm}
                    onCancel={handleCancel}
                />
            )}
        </div>
    );
}

export default JsonDataDisplay;
