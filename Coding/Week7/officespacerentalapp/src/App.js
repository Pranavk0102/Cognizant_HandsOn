import React from 'react';

function App() {
  const office = {
    name: "Skyline Office Space",
    rent: 55000,
    address: "123 Business Park, Bengaluru",
    image: "https://images.stockcake.com/public/e/2/e/e2ec77f5-b74c-455f-a7be-bd80ad0402e5/modern-office-view-stockcake.jpg"
  };

  const offices = [
    { name: "Skyline Office Space", rent: 55000, address: "123 Business Park, Bengaluru" },
    { name: "TechHub Workspace", rent: 75000, address: "IT Corridor, Hyderabad" },
    { name: "Corporate Heights", rent: 60000, address: "MG Road, Pune" },
    { name: "Startup Studio", rent: 45000, address: "Sector 18, Gurugram" }
  ];

  return (
    <div style={{ fontFamily: "Arial", textAlign: "center", padding: "20px" }}>
      {}
      <h1>Office Space Rental App</h1>

      {}
      <img src={office.image} alt="Office Space" style={{ borderRadius: "8px" }} />

      {}
      <h2>{office.name}</h2>
      <p style={{ color: office.rent < 60000 ? "red" : "green" }}>
        Rent: ₹{office.rent}
      </p>
      <p>{office.address}</p>

      {}
      <h2>Available Office Spaces</h2>
      <ul style={{ listStyleType: "none", padding: 0 }}>
        {offices.map((item, index) => (
          <li key={index} style={{ marginBottom: "15px", borderBottom: "1px solid #ccc", paddingBottom: "10px" }}>
            <strong>{item.name}</strong><br />
            <span style={{ color: item.rent < 60000 ? "red" : "green" }}>Rent: ₹{item.rent}</span><br />
            <span>{item.address}</span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
