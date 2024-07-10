import { useState, useEffect } from 'react';
import '../css/RegisterUser.css';
import ConfirmationDialog from './ConfirmationDialog';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function JsonDataDisplay({buyer, onBuyerUpdate}) {
    const navigate = useNavigate();
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [buyers, setBuyers] = useState([]);
    const [buyerIdToDelete, setBuyerIdToDelete] = useState(null);
    const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'ascending' });



    const handleDelete = (id) => {
        setBuyerIdToDelete(id);
        setShowConfirmation(true);
    };

    const handleCancel = () => {
        setShowConfirmation(false);
        setBuyerIdToDelete(null);
    };

    const handleConfirm = async () => {
        try {
            const response = await axios.delete(`http://localhost:4495/buyer/remove/${buyerIdToDelete}`);
            if (response.status === 200) {
                onBuyerUpdate(); // Trigger fetching new data in parent

                console.log(`Buyer with ID ${buyerIdToDelete} successfully deleted.`);
            } else {
                console.error('Failed to delete the buyer with ID:', buyerIdToDelete);
            }
        } catch (error) {
            console.error('Error during deletion:', error);
        } finally {
            setShowConfirmation(false);
            setBuyerIdToDelete(null);
        }
    };

    const sortedBuyers = [...buyer].sort((a, b) => {
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
                            <th onClick={() => requestSort('id')}>Buyers ID ↑ ↓</th>
                            <th onClick={() => requestSort('firstname')}>First Name ↑ ↓</th>
                            <th onClick={() => requestSort('surname')}>Surname ↑ ↓</th>
                            <th>Delete Buyer</th>
                        </tr>
                    </thead>
                    <tbody>
                        {sortedBuyers.map((info) => (
                            <tr key={info.id}>
                                <td onClick={() => navigate('../appointments/' + info.id)}>{info.id}</td>
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
                    message="Are you sure you want to delete this buyer? Removing this buyer will delete any associated appointments."
                    onConfirm={handleConfirm}
                    onCancel={handleCancel}
                />
            )}
        </div>
    );
}

export default JsonDataDisplay;
