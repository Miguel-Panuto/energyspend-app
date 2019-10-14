const express = require('express');

const { User, Measures } = require('./controllers/GeneralController');

const arduino = require('./utils/arduino');

const routes = express.Router();

// Create user -> create first time on arduino
// Update arduino values

routes.post('/user', arduino, (req, res) =>
{
    User.store();
    Measures.store();

    res.status(202);
});

routes.get('/arduino', arduino, (req, res) =>
{ 
    if (User.findUser())
        res.send({ pot: req.pot, price: req.price })
    else
        res.status(404).send({ error: 'User not founded' }); 
});