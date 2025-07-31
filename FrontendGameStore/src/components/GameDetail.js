import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from '../api';
import './GameDetail.css';

const GameDetail = () => {
  const { id } = useParams();
  const [game, setGame] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [usersMap, setUsersMap] = useState({});

  useEffect(() => {
    // Fetch game details
    axios.get(`/games/${id}`)
      .then(res => setGame(res.data))
      .catch(err => console.error('Error fetching game:', err));

    // Fetch game reviews
    axios.get(`/reviews/game/${id}`)
      .then(res => setReviews(res.data))
      .catch(err => console.error('Error fetching reviews:', err));

    // Fetch all users to map id → username
    axios.get('/users')
      .then(res => {
        const map = {};
        res.data.forEach(user => {
          map[user.id] = user.username;
        });
        setUsersMap(map);
      })
      .catch(err => console.error('Error fetching users:', err));
  }, [id]);

  const deleteReview = async (id) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this review?');

    if (!confirmDelete) return;

    try {
      await axios.delete(`/reviews/delete/${id}`);
      alert('Review deleted successfully!');
      // Remove deleted review from UI
      setReviews(prev => prev.filter(review => review.id !== id));
    } catch (err) {
      console.error('Error deleting review:', err);
      alert('Failed to delete review');
    }
  };

  const calculateAverageRating = (reviews) => {
    if (reviews.length === 0) return null;
    const total = reviews.reduce((sum, review) => sum + review.rating, 0);
    return (total / reviews.length).toFixed(1); // one decimal point
  };

  if (!game) return <p>Loading game...</p>;

  return (
    <div className="game-detail">
      <h2>{game.title}</h2>
      <p><strong>Genre:</strong> {game.genre}</p>
      <p><strong>Developer ID:</strong> {game.developerId}</p>

      <hr />

      {reviews.length > 0 && (
        <p className="average-rating">
          ⭐ Average Rating: {calculateAverageRating(reviews)} / 10
        </p>
      )}

      <h3>Reviews</h3>

      {reviews.length === 0 ? (
        <p>No reviews yet for this game.</p>
      ) : (
        <ul className="reviews-list">
          {reviews.map(review => (
            <li key={review.id} className="review-card">
              <p><strong>User:</strong> {usersMap[review.userId] || 'Loading...'}</p>
              <p><strong>Rating:</strong> {review.rating} / 10</p>
              <p><strong>Comment:</strong> {review.comment}</p>
              <button
                onClick={() => deleteReview(review.id)}
                className="delete-btn"
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default GameDetail;
