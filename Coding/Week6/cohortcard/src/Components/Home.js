import React from 'react';
import CohortCard from './CohortCard';
import './Home.css';

const Home = () => {
  const cohorts = [
    {
      cohortId: 'INTADMDF10 - .NET FSD',
      startedOn: '22-Feb-2022',
      status: 'Scheduled',
      coach: 'Aathma',
      trainer: 'Jojo Jose'
    },
    {
      cohortId: 'ADM21JF014 - Java FSD',
      startedOn: '10-Sep-2021',
      status: 'Ongoing',
      coach: 'Apoorv',
      trainer: 'Elisa Smith'
    },
    {
      cohortId: 'CDBJF21025 - Java FSD',
      startedOn: '24-Dec-2021',
      status: 'Ongoing',
      coach: 'Aathma',
      trainer: 'John Doe'
    }
  ];

  return (
    <div className="home-container">
      <h2>Cohorts Details</h2>
      <div className="card-container">
        {cohorts.map((c, index) => (
          <CohortCard key={index} {...c} />
        ))}
      </div>
    </div>
  );
};

export default Home;
