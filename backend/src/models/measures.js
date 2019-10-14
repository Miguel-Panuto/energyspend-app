const mongoose = require('mongoose');

const measureSchema = mongoose.Schema({
    pot:{
        type: Double
    },
    price:{
        type: Double
    },
    owner:{
        type: mongoose.Schema.Types.ObjectId,
        require: true
    }
});

const Measures = mongoose.model('Measures', measureSchema);

module.exports = Measures;