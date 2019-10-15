const Sequelize = require('sequelize');

const sequelize = new Sequelize('measuredb', 'root', '', {
    host:"localhost",
    dialect: 'mysql'
});

module.exports = sequelize;