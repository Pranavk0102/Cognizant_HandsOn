import React from 'react';
import './CohortCard.css';

const CohortCard = ({ cohortId, startedOn, status, coach, trainer }) => {
  const getColor = () => {
    if (cohortId.includes('NET')) return 'blue';
    if (cohortId.includes('Java')) return 'green';
    return 'black';
  };

  return (
    <div className="cohort-card">
      <h3 style={{ color: getColor() }}>{cohortId}</h3>
      <p><strong>Started On</strong><br />{startedOn}</p>
      <p><strong>Current Status</strong><br />{status}</p>
      <p><strong>Coach</strong><br />{coach}</p>
      <p><strong>Trainer</strong><br />{trainer}</p>
    </div>
  );
};

export default CohortCard;
