const Sequelize = require('sequelize');
const sequelize = require('../db/mysql');

const Measures = sequelize.define('measures', 
{
    current:{
        type: Sequelize.DOUBLE
    }
});

//pot price owner

module.exports = Measures;