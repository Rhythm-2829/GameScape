import React, { useEffect, useState } from 'react';
import axios from '../api';
import { Link } from 'react-router-dom';
import './GameList.css';

const GameList = () => {
  const [games, setGames] = useState([]);
  const [developersMap, setDevelopersMap] = useState({});

  useEffect(() => {
    // Fetch all developers once
    axios.get('/developers')
      .then(res => {
        const map = {};
        res.data.forEach(dev => {
          map[dev.id] = dev.name;
        });
        setDevelopersMap(map);
      })
      .catch(err => console.error('Error fetching developers:', err));

    // Fetch games
    axios.get('/games')
      .then(res => setGames(res.data))
      .catch(err => console.error('Error fetching games:', err));
  }, []);

  return (
    <div className="game-list-container"> {/* Keeping this as a main wrapper for title and grid */}
      <h2 className="title">Game List</h2>
      <div className="game-grid"> {/* Changed from ul to div and added game-grid class */}
        {games.map((game) => (
          <div key={game.id} className="game-card"> {/* Changed from li to div */}
            <img
              src={`https://via.placeholder.com/300x200?text=${encodeURIComponent(game.title)}`} // Placeholder image
              alt={game.title}
              className="game-image"
            />
            <div className="game-content">
              <h3 className="game-title">{game.title}</h3>
              <p className="game-genre"><strong>Genre:</strong> {game.genre}</p>
              <p className="game-developer"><strong>Developer:</strong> {developersMap[game.developerId] || 'Loading...'}</p>
              <Link to={`/games/${game.id}`} className="view-btn"> {/* Applied view-btn class */}
                View Details
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default GameList;