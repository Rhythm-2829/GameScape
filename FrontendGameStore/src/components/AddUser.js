import React, { useState } from 'react';
import axios from '../api';
import './AddUser.css';

const AddUser = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/users', { username, email });
      alert('User added successfully!');
      setUsername('');
      setEmail('');
    } catch (err) {
      console.error('Error adding user:', err);
      alert('Failed to add user.');
    }
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Add New User</h2>
      <form onSubmit={handleSubmit} className="form">
        <label>Username:</label>
        <input
          type="text"
          value={username}
          required
          onChange={(e) => setUsername(e.target.value)}
        />

        <label>Email:</label>
        <input
          type="email"
          value={email}
          required
          onChange={(e) => setEmail(e.target.value)}
        />

        <button type="submit" className="submit-btn">Add User</button>
      </form>
    </div>
  );
};

export default AddUser;
