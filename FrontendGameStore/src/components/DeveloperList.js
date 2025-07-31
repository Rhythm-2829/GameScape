// FILE: DeveloperList.js

import React, { useEffect, useState } from 'react';
import axios from '../api';
import styles from './DeveloperList.module.css'; // Import the CSS module

const DeveloperList = () => {
    const [developers, setDevelopers] = useState([]);

    useEffect(() => {
        axios.get('/developers')
            .then(res => setDevelopers(res.data))
            .catch(err => console.error('Error fetching developers:', err));
    }, []);

    return (
        <div className={styles.developerContainer}>
            {/* --- IMPORTANT FIX: Added data-text for the glitch effect --- */}
            <h2 className={styles.title} data-text="Developers">Developers</h2>
            
            <ul className={styles.developerList}>
                {developers.map(dev => (
                    <li key={dev.id} className={styles.developerCard}>
                        <h3>{dev.name}</h3>
                        <p>
                            <strong>Website:</strong> 
                            <a href={dev.website} target="_blank" rel="noopener noreferrer">
                                {dev.website}
                            </a>
                        </p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default DeveloperList;