const Sequelize = require('sequelize'); // Módulo para utilizar o bd

const sequelize = new Sequelize('measuredb', 'root', '', { // Nome do banco, usuário, senha
    host:"localhost", // Servidor, no meu caso, minha própria máquina
    dialect: 'mysql' // Tipo do banco
});

module.exports = sequelize; // Exportação da função do sql