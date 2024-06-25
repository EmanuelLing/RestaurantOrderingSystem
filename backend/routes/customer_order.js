// routes/customer_order.js
const express = require('express');
const customerOrderRouter = express.Router();

// database connection
const db = require('./../db');

customerOrderRouter.use(express.urlencoded({ extended: true }));

// Add new customer order
// request customer order details
// response message
customerOrderRouter.post('/', (req, res) => {
  const {OrderID, OrderDateTime, OrderType, TableNo, Status, TotalPrice, CustomerID} = req.body;

  const query = `INSERT INTO customer_order 
  (OrderID, OrderDateTime, OrderType, TableNo, Status, TotalPrice, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?)`;

  db.query(query, [OrderID, OrderDateTime, OrderType, TableNo || null, Status, TotalPrice, CustomerID], (error, results) => {
    if (error) {
      return res.status(500).send({error: 'Database error'});
    }
    res.status(201).send({"message": "customer added order successfully"});
  });
});

  // read customer order
  // request status
  // response customer order details base on the status
  customerOrderRouter.get('/', (req, res) => {
    const status = req.query.Status;

    const query = `SELECT * FROM customer_order WHERE Status = ?`;

    db.query(query, [status], (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        res.send({customer_order: results});
    });
  });

  customerOrderRouter.get('/personal', (req, res) => {
    const CustomerID = req.query.CustomerID;

    const query = 'SELECT * FROM customer_order WHERE CustomerID = ? ORDER BY OrderDateTime DESC';

    db.query(query, [CustomerID], (error, results) => {
      if (error) {
          return res.status(500).send({ error: 'Database error' });
      }
      res.send({customer_order: results});
    });
  });


  // update customer order
  // request orderID, status and staffID
  // response message
  customerOrderRouter.put('/', (req, res) => {
    const orderID = req.body.OrderID;
    const status = req.body.Status;
    const staffID = req.body.StaffID;

    const query = `UPDATE customer_order SET Status = ?, StaffID = ? WHERE OrderID = ?`;

    db.query(query, [status, staffID, orderID], (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        res.status(201).send({message: 'customer order successfully updated'});
    });
  });


  // delete customer order
  // request customer order id
  // response message
  customerOrderRouter.delete('/', (req, res) => {
    const orderID = req.query.OrderID;

    const query = `DELETE FROM customer_order WHERE OrderID = ?`;

    db.query(query, [orderID], (error, results) => {
        if (error) {
            return res.status(500).send({ error: 'Database error' });
        }
        if (results.affectedRows == 1) {
            res.status(201).send({message: 'customer order successfully deleted'});
        }
        else {
            res.status(404).send({error: 'customer order not found'});
        }
    });
  });

  module.exports = customerOrderRouter;