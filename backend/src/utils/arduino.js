const SerialPort = require('serialport'); // Módulo importado para comunicação com o arduino
const Readline = require('@serialport/parser-readline'); // Parte do módulo feita pra ler as saídas da porta serial
const port = new SerialPort(process.argv[2]); // A porta onde se está conecatado, no momento de iniciar deve especificar (Se é a COM1, COM2, etc.)
const fs = require('fs'); // Módulo nativo feito para escrever/ler arquivos externos

const { saveMeasure, loadMeasures } = require('../controllers/MeasureController'); // Script para salvar e ler do banco de dados MySQL

const parser = port.pipe(new Readline({ delimiter: '\r\n' })); // A forma de como será escrito a saída serial

let pot = 0; // Potencia atual (ela vai se somando com o tempo)
loadMeasures().then((data) => {
    pot = data; // Caso haja algum valor no banco de dados vai ser atribuido a este
});


parser.on('data', (data) => // Função que le em si os dados
{
    if (data === 'PRONTO')
        return console.log('READY!'); // O que é escrito quando se está calibrado
    data = parseFloat(data); //  Atribui o valor a essa variavel
    pot += data; // A potencia se adicionando com o tempo
    fs.writeFileSync(__dirname + '../../../../desktop-app/data.txt', data); // Criando um arquivo com o valor em si, no diretório do aplicativo java
});

setInterval(() => // De 10 em 10s é atualizado no banco de dados o valor atual da potência e dentro do diretório da aplicação java
{
    saveMeasure(pot); // Dentro do próprio controller para enviar ao model 
    fs.writeFileSync(__dirname + '../../../../desktop-app/pot.txt', pot); // Diretório da aplicação java
}, 10000);