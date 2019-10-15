const express = require('express');

const { findUser } = require('./controllers/MeasureController');

const User = require('./models/user');

const arduino = require('./utils/arduino');

const routes = express.Router();

// Create user -> create first time on arduino
// Update arduino values

routes.post('/user/create', arduino, findUser);

// routes.get('/arduino', arduino, (req, res) =>
// { 
//     if (User.findUser())
//         res.send({ pot: req.pot, price: req.price })
//     else
//         res.status(404).send({ error: 'User not founded' }); 
// });

module.exports = routes;