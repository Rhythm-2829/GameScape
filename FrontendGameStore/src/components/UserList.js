// FILE: UserList.js

import React, { useEffect, useState } from 'react';
import axios from '../api';
import styles from './UserList.module.css'; // Use the module import

const UserList = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get('/users')
            .then(res => setUsers(res.data))
            .catch(err => console.error('Error fetching users:', err));
    }, []);

    return (
        <div className={styles.userContainer}>
            <h2 className={styles.title}>Users</h2>
            <ul className={styles.userList}>
                {users.map(user => (
                    <li key={user.id} className={styles.userCard}>
                        <h3>{user.username}</h3>
                        <p><strong>Email:</strong> {user.email}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UserList;