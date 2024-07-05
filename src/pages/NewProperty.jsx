import AddProperty from "../components/AddProperty";
import PropertyDisplay from "../components/PropertyTable";
import axios from 'axios';
import React, { useState, useEffect } from "react";



function NewProperty() {

    const [properties, setProperties] = useState([]);

        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:4495/property/getAll');
                setProperties(response.data);
            } catch (error) {
                console.error('Error:', error);
            }
        };

        useEffect(() => {
            fetchData();
        }, []);

  return (

    <div className="body" >
      <div className="container1">
      <h1 className="pagetitle">Property Admin</h1>
      <br/>
        <AddProperty onAddProperty={fetchData} />
        <PropertyDisplay properties={properties} />
      </div>    
    </div>
  )
}


export default NewProperty;
