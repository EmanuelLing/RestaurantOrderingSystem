// using Express
const express = require('express');
const app = express();

// set up port
const port = process.env.PORT || 3000;

const customerRouter = require('./routes/customer.js');
const staffRouter = require('./routes/staff.js')
const customerOrderRouter = require('./routes/customer_order.js');
const mealRouter = require('./routes/meal.js');
const orderMealRouter = require('./routes/order_meal.js');
const paymentRouter = require('./routes/payment.js');

// parse json
app.use(express.json());

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.use('/customer', customerRouter);
app.use('/staff', staffRouter)
app.use('/customer_order', customerOrderRouter);
app.use('/meal', mealRouter);
app.use('/order_meal', orderMealRouter);
app.use('/payment', paymentRouter);

// start up server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});