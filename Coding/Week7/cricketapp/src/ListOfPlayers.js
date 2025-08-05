import React from 'react';

const ListofPlayers = () => {
  const players = [
    { name: "Virat Kohli", score: 85 },
    { name: "Rohit Sharma", score: 45 },
    { name: "KL Rahul", score: 90 },
    { name: "Shikhar Dhawan", score: 67 },
    { name: "Rishabh Pant", score: 50 },
    { name: "Hardik Pandya", score: 75 },
    { name: "Ravindra Jadeja", score: 68 },
    { name: "Bhuvneshwar Kumar", score: 30 },
    { name: "Jasprit Bumrah", score: 55 },
    { name: "Yuzvendra Chahal", score: 42 },
    { name: "Mohammed Shami", score: 80 }
  ];

  return (
    <div>
      <h2>All Players</h2>
      {players.map((p, index) => (
        <p key={index}>{p.name} - {p.score}</p>
      ))}

      <h2>Players with Score ≥ 70</h2>
      {players.filter(p => p.score >= 70).map((p, index) => (
        <p key={index}>{p.name} - {p.score}</p>
      ))}
    </div>
  );
};

export default ListofPlayers;
