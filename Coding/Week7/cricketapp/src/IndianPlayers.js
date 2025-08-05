import React from 'react';

const IndianPlayers = () => {
  const players = [
    "Virat Kohli", "Rohit Sharma", "KL Rahul", "Shikhar Dhawan",
    "Rishabh Pant", "Hardik Pandya", "Ravindra Jadeja", "Bhuvneshwar Kumar"
  ];

  // Destructuring
  const oddPlayers = players.filter((_, index) => index % 2 !== 0);
  const evenPlayers = players.filter((_, index) => index % 2 === 0);

  // Merge feature of ES6
  const T20Players = ["Virat Kohli", "Rohit Sharma"];
  const RanjiTrophyPlayers = ["Cheteshwar Pujara", "Ajinkya Rahane"];
  const mergedPlayers = [...T20Players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h2>Odd Team Players</h2>
      {oddPlayers.map((p, index) => <p key={index}>{p}</p>)}

      <h2>Even Team Players</h2>
      {evenPlayers.map((p, index) => <p key={index}>{p}</p>)}

      <h2>Merged Players List</h2>
      {mergedPlayers.map((p, index) => <p key={index}>{p}</p>)}
    </div>
  );
};

export default IndianPlayers;
