import React, { useEffect, useState } from 'react';
import axios from '../api';
import './AddGame.css';

const AddGame = () => {
  const [title, setTitle] = useState('');
  const [genre, setGenre] = useState('');
  const [developerId, setDeveloperId] = useState('');
  const [developers, setDevelopers] = useState([]);

  useEffect(() => {
    axios.get('/developers')
      .then(res => setDevelopers(res.data))
      .catch(err => console.error('Error fetching developers:', err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/games/create', { title, genre, developerId });
      alert('Game added successfully!');
      setTitle('');
      setGenre('');
      setDeveloperId('');
    } catch (err) {
      console.error('Error adding game:', err);
      alert('Failed to add game.');
    }
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Add New Game</h2>
      <form onSubmit={handleSubmit} className="form">
        <label>Title:</label>
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />

        <label>Genre:</label>
        <input type="text" value={genre} onChange={(e) => setGenre(e.target.value)} required />

        <label>Developer:</label>
        <select value={developerId} onChange={(e) => setDeveloperId(e.target.value)} required>
          <option value="">Select Developer</option>
          {developers.map(dev => (
            <option key={dev.id} value={dev.id}>{dev.name}</option>
          ))}
        </select>

        <button type="submit" className="submit-btn">Add Game</button>
      </form>
    </div>
  );
};

export default AddGame;
