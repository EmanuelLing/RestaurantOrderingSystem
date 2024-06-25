// routes/customer.js
const express = require('express');
const customerRouter = express.Router();

// database connection
const db = require('./../db');

// read customers details
// request customer name and password
// response cusotmer details
customerRouter.get('/', (req, res) => {
    const customerName = req.query.customerName;
    const password = req.query.password;
  
    db.query('SELECT * FROM customer where CustomerName = ? and Password = ?', [customerName, password], (error, results) => {
      if (error) {
        return res.status(500).send({ error: 'Database error' });
      }
      if (results.length == 1) {
        res.send({customer: results});
      }
      else {
        res.status(404).send({error: 'customer not found'});
      }
    });
  });

  module.exports = customerRouter;