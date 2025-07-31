import React, { useEffect, useState } from 'react';
import axios from '../api';
import { useParams, useNavigate } from 'react-router-dom';

const ReviewForm = () => {
  const { id } = useParams(); // gameId from URL
  const [rating, setRating] = useState('');
  const [comment, setComment] = useState('');
  const [userId, setUserId] = useState('');
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch users for dropdown
    axios.get('/users')
      .then(res => setUsers(res.data))
      .catch(err => console.error('Error fetching users:', err));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const review = {
      rating: parseInt(rating),
      comment,
      userId: parseInt(userId),
      gameId: parseInt(id)
    };

    axios.post('/reviews/create', review)
      .then(res => {
        alert('Review added successfully!');
        navigate(`/games/${id}`); // Redirect to game detail
      })
      .catch(err => {
        console.error('Error submitting review:', err);
        alert('Failed to submit review.');
      });
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Submit Review for Game ID: {id}</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-medium">Rating (1-10)</label>
          <input
            type="number"
            value={rating}
            onChange={(e) => setRating(e.target.value)}
            min={1}
            max={10}
            className="border p-2 rounded w-full"
            required
          />
        </div>

        <div>
          <label className="block font-medium">Comment</label>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            className="border p-2 rounded w-full"
            required
          />
        </div>

        <div>
          <label className="block font-medium">User</label>
          <select
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            className="border p-2 rounded w-full"
            required
          >
            <option value="">Select User</option>
            {users.map(user => (
              <option key={user.id} value={user.id}>
                {user.username} ({user.email})
              </option>
            ))}
          </select>
        </div>

        <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
          Submit Review
        </button>
      </form>
    </div>
  );
};

export default ReviewForm;
