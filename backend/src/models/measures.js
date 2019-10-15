const Sequelize = require('sequelize');
const sequelize = require('../db/mysql');

const Measures = sequelize.define('measures', 
{
    pot:{
        type: Sequelize.DOUBLE
    },
    price:{
        type: Sequelize.DOUBLE
    },
    actualPot:{
        type: Sequelize.DOUBLE
    },
    owner:{
        type: Sequelize.INTEGER,
        allowNull: false
    }
});

//pot price owner

module.exports = Measures;