const SerialPort = require('serialport');
const Readline = require('@serialport/parser-readline');
const port = new SerialPort('COM3');
const fs = require('fs');

const { saveMeasure, loadMeasures } = require('../controllers/MeasureController');

const parser = port.pipe(new Readline({ delimiter: '\r\n' }));

let pot = 0;
loadMeasures().then((data) => {
    pot = data;
});


parser.on('data', (data) =>
{
    if (data === 'PRONTO')
        return;
    data = parseFloat(data);
    pot += data;
    fs.writeFileSync(__dirname + '../../../../desktop-app/data.txt', data);
});

setInterval(() => 
{
    saveMeasure(pot) 
    fs.writeFileSync(__dirname + '../../../../desktop-app/pot.txt', pot);
}, 10000);