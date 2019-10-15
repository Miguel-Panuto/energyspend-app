const Sequelize = require('sequelize');

const sequelize = new Sequelize('teste', 'root', '', {
    host:"localhost",
    dialect: 'mysql'
});

const Post = sequelize.define('Post',
{
    nome:{
        type: Sequelize.STRING
    },
    email:{
        type: Sequelize.STRING
    }
});

Post.sync({ force: true });