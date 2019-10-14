const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    name:{
        type: String,
        require: true,
        trim: true
    },
    email:{
        type: String,
        unique: true, //Just one email per user
        require: true,
        validate(value) //That is a function, that will validade something
        {
            if (!validator.isEmail(value))
            {
                throw new Error('Email is invalid');
            }
        }
    }
});

const User = mongoose.model('User', userSchema);

module.exports = User;