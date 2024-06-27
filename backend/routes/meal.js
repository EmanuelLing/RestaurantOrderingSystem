// routes/meal.js
const express = require('express');
const mealRouter = express.Router();

// database connection
const db = require('./../db');

// read meals
// request meal type
// response meals details base on meal type
mealRouter.get('/', (req, res) => {

    const query = `SELECT * FROM meal`;

    db.query(query, (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        if (results.length > 0) {
            res.status(201).send({Meals: results});
        }      
        else {
            res.status(404).send({error: 'no meal found'});
        }
    });
});

module.exports = mealRouter;