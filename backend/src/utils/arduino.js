const SerialPort = require('serialport');
const Readline = require('@serialport/parser-readline');
const port = new SerialPort('COM5');

// const { Measures } = require('../controllers/GeneralController');

const parser = port.pipe(new Readline({ delimiter: '\r\n' }));

let varData;
let actualPot;
let pot = 0;
let price;

parser.on('data', (data) =>
{
    varData = data;
    actualPot = varData * 127;
    pot += actualPot;
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