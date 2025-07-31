import React from 'react';

const GameCard = ({ game }) => {
  return (
    <div style={{
      border: '1px solid #ccc',
      padding: '10px',
      margin: '10px',
      width: '250px',
      borderRadius: '8px'
    }}>
      <h3>{game.title}</h3>
      <p>{game.genre}</p>
      <p><strong>Price:</strong> ₹{game.price}</p>
    </div>
  );
};

export default GameCard;
