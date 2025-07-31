// FILE: AddDeveloper.js (Updated Structure)

import React, { useState } from 'react';
import styles from './AddDeveloper.module.css'; // Sahi CSS Module import karein

const AddDeveloper = () => {
    const [name, setName] = useState('');
    const [website, setWebsite] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        alert(`Developer Added (Simulated): \nName: ${name}\nWebsite: ${website}`);
        setName('');
        setWebsite('');
    };

    return (
        // STEP 1: Outer container jo animated border banayega
        <div className={styles.formContainer}>
            {/* STEP 2: Inner div jispar form ka background aur padding lagegi */}
            <div className={styles.form}>
                <h2 className={styles.formTitle}>Add Developer</h2>
                <form onSubmit={handleSubmit}>
                    <div className={styles.formRow}>
                        <label>Name:</label>
                        <input
                            type="text"
                            value={name}
                            required
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div className={styles.formRow}>
                        <label>Website:</label>
                        <input
                            type="url"
                            value={website}
                            required
                            onChange={(e) => setWebsite(e.target.value)}
                        />
                    </div>
                    <button type="submit" className={styles.submitBtn}>
                        Add Developer
                    </button>
                </form>
            </div>
        </div>
    );
};

export default AddDeveloper;