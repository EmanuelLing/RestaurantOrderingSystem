// routes/staff.js
const express = require('express');
const staffRouter = express.Router();

// database connection
const db = require('./../db');


// read staff details
// request staff name and password
// response staff details
staffRouter.get('/', (req, res) => {
    const staffName = req.query.staffName;
    const password = req.query.password;
  
    db.query('SELECT * FROM staff where staffName = ? and password = ?', [staffName, password], (error, results) => {
      if (error) {
        return res.status(500).send({ error: 'Database error' });
      }
      if (results.length == 1) {
        res.send({staff: results});
      }
      else {
        res.status(404).send({error: 'staff not found'});
      }
    });
  });

  module.exports = staffRouter;