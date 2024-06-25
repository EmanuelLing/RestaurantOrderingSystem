// routes/ordered_meal.js
const express = require('express');
const orderMealRouter = express.Router();

// database connection
const db = require('./../db');

orderMealRouter.use(express.urlencoded({ extended: true }));

// add ordered meals
// request order id, meal id and quantity
// response message
orderMealRouter.post('/', (req, res) => {
    const {OrderID, MealID, Quantity} = req.body;

    const query = `INSERT INTO order_meal (OrderID, MealID, Quantity) VALUES (?, ?, ?)`;

    db.query(query, [OrderID, MealID, Quantity], (error, results) => {
        if (error) {
            return res.status(500).send({ error: error });
        }
        res.status(201).send({message: 'ordered meal successfully added'});
    });
});

// read ordered meal
// request order id
// response ordered meals details
orderMealRouter.get('/', (req, res) => {
    const orderID = req.query.OrderID;

    const query = `SELECT * FROM order_meal WHERE OrderID = ?`;

    db.query(query, orderID, (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        if (results.length > 0) {
            res.status(201).send({ordered_meals: results});
        }
        else {
            res.status(404).send({error: 'no meal found'});
        }
    });
});

orderMealRouter.delete('/', (req, res) => {
    const orderID = req.query.OrderID;

    const query = 'DELETE FROM order_meal WHERE OrderID = ?';

    db.query(query, [orderID], (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        if (results.affectedRows > 0) {
            res.status(201).send({message: 'ordered meal successfully deleted'});
        }
        else {
            res.status(404).send({error: 'No ordered meal deleted'});
        }
    });
});

module.exports = orderMealRouter;