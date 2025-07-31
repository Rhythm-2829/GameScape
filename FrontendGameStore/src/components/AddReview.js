import React, { useEffect, useState } from 'react';
import axios from '../api';
import './AddReview.css';

const AddReview = () => {
  const [rating, setRating] = useState('');
  const [comment, setComment] = useState('');
  const [userId, setUserId] = useState('');
  const [gameId, setGameId] = useState('');

  const [users, setUsers] = useState([]);
  const [games, setGames] = useState([]);

  useEffect(() => {
    axios.get('/users')
      .then(res => setUsers(res.data))
      .catch(err => console.error('Error fetching users:', err));

    axios.get('/games')
      .then(res => setGames(res.data))
      .catch(err => console.error('Error fetching games:', err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/reviews/create', {
        rating,
        comment,
        userId,
        gameId
      });
      alert('Review added successfully!');
      setRating('');
      setComment('');
      setUserId('');
      setGameId('');
    } catch (err) {
      console.error('Error adding review:', err);
      alert('Failed to add review.');
    }
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Add Review</h2>
      <form onSubmit={handleSubmit} className="form">
        <label>User:</label>
        <select value={userId} onChange={(e) => setUserId(e.target.value)} required>
          <option value="">Select User</option>
          {users.map(user => (
            <option key={user.id} value={user.id}>{user.username}</option>
          ))}
        </select>

        <label>Game:</label>
        <select value={gameId} onChange={(e) => setGameId(e.target.value)} required>
          <option value="">Select Game</option>
          {games.map(game => (
            <option key={game.id} value={game.id}>{game.title}</option>
          ))}
        </select>

        <label>Rating (1-10):</label>
        <input
          type="number"
          min="1"
          max="10"
          value={rating}
          onChange={(e) => setRating(e.target.value)}
          required
        />

        <label>Comment:</label>
        <textarea
          rows="4"
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          required
        ></textarea>

        <button type="submit" className="submit-btn">Submit Review</button>
      </form>
    </div>
  );
};

export default AddReview;
