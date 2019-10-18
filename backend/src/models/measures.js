const Sequelize = require('sequelize'); // Módulo para o CRUD do banco de dados
const sequelize = require('../db/mysql'); // Drive do MySQL

const Measures = sequelize.define('measures', // Modelo da tabela 'measures'
{
    current:{
        type: Sequelize.DOUBLE // Definindo o tipo da variável como double
    }
});

module.exports = Measures;