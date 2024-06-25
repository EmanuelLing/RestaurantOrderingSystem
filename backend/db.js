// db.js
const mysql = require('mysql');

// 创建数据库连接池
const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'foodorderingsystem',
  multipleStatements: true
});

// 导出连接池
module.exports = pool;