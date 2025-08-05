import React, { useState } from 'react';

const CurrencyConvertor = () => {
  const [rupees, setRupees] = useState('');
  const [euro, setEuro] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    // Assume 1 INR = 0.011 EUR
    const converted = (parseFloat(rupees) * 0.011).toFixed(2);
    setEuro(converted);
  };

  return (
    <div>
      <h2>Currency Converter</h2>
      <form onSubmit={handleSubmit}>
        <label>Enter amount in INR: </label>
        <input 
          type="number" 
          value={rupees} 
          onChange={(e) => setRupees(e.target.value)} 
        />
        <button type="submit">Convert</button>
      </form>
      {euro && <p>{rupees} INR = {euro} EUR</p>}
    </div>
  );
};

export default CurrencyConvertor;
