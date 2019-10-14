const Measures = require('../models/measures');

const UserDb = require('../models/User');
const MeasuresDb = require('../models/Measures');

// Receive from the arduino and send to the database <- hour by hour will send this to the database

const User = 
{
    async store(req, res)
    {
        const { name, email } = req.body;

        let user = await UserDb.findOne({ email });

        if (!user)
        {
            user = await UserDb.create({ name, email });
        }

        return res.json(user);
    },
    async findUser(req, res)
    {
        const { email } = req.body;
        const user = await UserDb.findOne({ email });
        if(user)
            return true;
        return false;
    }
};

const Measures = 
{
    async store(req, res)
    {
        const user = await UserDb.findOne({ email });
        if (!user)
            return res.json({ error: 'User not founded' });
        let measure = await MeasuresDb.findOne({ owner: user._id });
        if(!measure)
        {
            measure = await MeasuresDb.create({ pot: req.pot, price: req.price, owner: user._id });
        }
        else
        {
            measure.pot = req.pot;
            measure.price = req.price;
            await measure.save();
        }
        return res.json(measure);
    }
}

module.exports = 
{
    User,
    Measures
}