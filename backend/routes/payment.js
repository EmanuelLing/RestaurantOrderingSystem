// routes/customer_order.js
const express = require('express');
const paymentRouter = express.Router();

// database connection
const db = require('./../db');

paymentRouter.use(express.urlencoded({ extended: true }));

// add new payment
// request payment details
// response message
paymentRouter.post('/', (req, res) => {
    const {PaymentID, TotalPayment, PaymentDateTime, PaymentType, OrderID, StaffID} = req.body;

    const query = `INSERT INTO payment (PaymentID, TotalPayment, PaymentDateTime, PaymentType, OrderID, StaffID) 
    VALUES (?, ?, ?, ?, ?, ?)`;

    db.query(query, [PaymentID, TotalPayment, PaymentDateTime, PaymentType, OrderID, StaffID], (error, results) => {
        if (error) {
            return res.status(500).send({error: 'Database error'});
        }
        res.status(201).send({message: 'payment successfully added'});
    });
});

// read payment
// request order id
// response payment details
paymentRouter.get('/', (req, res) => {
    const OrderID = req.query.OrderID;

    const query = `SELECT * FROM payment WHERE OrderID = ?`;

    db.query(query, [OrderID], (error, results) => {
        if (error) {
            return res.status(500).send({error: 'Database error'});
        }
        if (results.length == 1) {
            res.status(201).send({payment: results});
        }
        else {
            res.status(404).send({message: 'no payment found'});
        }
    });
});

// delete payment
// request payment id
// response message
paymentRouter.delete('/', (req, res) => {
    const paymentID = req.query.PaymentID;

    const query = `DELETE FROM payment WHERE PaymentID = ?`;

    db.query(query, [paymentID], (error, results) => {
        if (error) {
            return res.status(500).send({error: 'Database error'});
        }
        if (results.affectedRows > 0) {
            res.status(201).send({message: 'payment successfully deleted'});
        }
        else {
            res.status(404).send({error: 'the payment is not existed'});
        }
    });
});

module.exports = paymentRouter;