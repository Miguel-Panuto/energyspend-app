const Sequelize = require('sequelize');
const sequelize = require('../db/mysql');

const User = sequelize.define('users', 
{
    name:{
        type: Sequelize.STRING,
        allowNull: false,
        validate:{
            notEmpty: true
        }
    },
    email:{
        type: Sequelize.STRING,
        allowNull: false,
        unique: true,
        validate:{
            notEmpty: true,
            isEmail:{
                msg: "Email apenas"
            }
        }
    }
});

module.exports = User;