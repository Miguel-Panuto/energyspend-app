const express = require ('express');

const routes = require('./routes');

require('./db/mysql');
require('./utils/arduino');

const app = express();

app.use(express.json());
app.use(routes);

app.listen(process.env.PORT, () =>
{
    console.log('Server up in port: ' + process.env.PORT);
});

module.exports = app;