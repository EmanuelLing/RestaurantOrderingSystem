// db.js
const mysql = require('mysql');

// Create a database connection pool
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'foodorderingsystem',
  multipleStatements: true
});

// export connection pool
module.exports = pool;
