const express = require ('express');

require('./utils/arduino');
require('./db/mongoose');

const app = express();

app.use(express.json());

module.exports = app;