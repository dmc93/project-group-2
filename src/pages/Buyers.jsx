import AddBuyer from "../components/AddBuyer";
import JsonDataDisplay from "../components/BuyerTable";
import '../css/RegisterUser.css';

function AddBuyers() {

    return (

        <div className="container1">

            <h1>Register a New Buyer</h1>

            <AddBuyer />
            <JsonDataDisplay />

        </div>
    );
}


export default AddBuyers;
