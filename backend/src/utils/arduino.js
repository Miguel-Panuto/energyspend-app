const SerialPort = require('serialport');
const Readline = require('@serialport/parser-readline');
const port = new SerialPort('COM5');

// const { Measures } = require('../controllers/GeneralController');

const parser = port.pipe(new Readline({ delimiter: '\r\n' }));

let varData;
let pot;
let price;

parser.on('data', (data) =>
{
    varData = data;
    pot = varData * 127;
    price = (pot/1000) * 0.2465;
});

const setPotPrice = async (req, res, next) =>
{
    req.pot = pot;
    req.price = price;
    next();
}

// setInterval(() =>
// {
//     Measures.store();
// }, 3600000);


module.exports = setPotPrice;