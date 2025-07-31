import React from 'react';
import GameList from '../components/GameList';

const Home = () => {
  return (
    <div style={{ padding: '20px' }}>
      <h1>🎮 Welcome to Game Store</h1>
      <GameList />
    </div>
  );
};

export default Home;
