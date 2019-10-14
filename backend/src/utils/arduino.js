const SerialPort = require('serialport');
const Readline = require('@serialport/parser-readline');
const port = new SerialPort(process.argv[2]);

const parser = port.pipe(new Readline({ delimiter: '\r\n' }));

let varData;

parser.on('data', (data) =>
{
    varData = data;
    exportData();
});

const exportData = () =>
{
    console.log(varData);
}
