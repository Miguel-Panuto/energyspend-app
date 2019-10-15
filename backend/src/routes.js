const express = require('express');

const { findUser, saveMeasure } = require('./controllers/MeasureController');

const arduino = require('./utils/arduino');

const routes = express.Router();

// Create user -> create first time on arduino
// Update arduino values

routes.post('/user', arduino, findUser);

routes.patch('/arduino', arduino, saveMeasure);

module.exports = routes;