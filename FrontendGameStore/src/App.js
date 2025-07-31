import React from 'react';
import './App.css'; // This will contain our enhanced navbar styles
import './components/Navbar.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import GameList from './components/GameList';
import GameDetail from './components/GameDetail';
import ReviewForm from './components/ReviewForm';
import DeveloperList from './components/DeveloperList';
import UserList from './components/UserList';
import { Link } from 'react-router-dom';
import AddGame from './components/AddGame';
import AddDeveloper from './components/AddDeveloper';
import AddUser from './components/AddUser';
import AddReview from './components/AddReview';

function App() {
  return (
    <Router>
      <nav className="navbar">
        {/* Updated links - removed inline Tailwind classes */}
        <Link to="/" className="nav-link">Games</Link>
        <Link to="/developers" className="nav-link">Developers</Link>
        <Link to="/users" className="nav-link">Users</Link>
        <Link to="/games/add" className="nav-link nav-add-btn">Add Game</Link> {/* Added a specific class for 'Add' links */}
        <Link to="/developers/add" className="nav-link nav-add-btn">Add Developer</Link>
        <Link to="/users/add" className="nav-link nav-add-btn">Add User</Link>
        <Link to="/reviews/add" className="nav-link nav-add-btn">Add Review</Link>
      </nav>
      <div className="main-content"> {/* Changed class for content wrapper */}
        <Routes>
          <Route path="/" element={<GameList />} />
          <Route path="/games/:id" element={<GameDetail />} />
          <Route path="/games/:id/review" element={<ReviewForm />} />
          <Route path="/developers" element={<DeveloperList />} />
          <Route path="/users" element={<UserList />} />
          <Route path="/games/add" element={<AddGame />} />
          <Route path="/developers/add" element={<AddDeveloper />} />
          <Route path="/users/add" element={<AddUser />} />
          <Route path="/reviews/add" element={<AddReview />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;