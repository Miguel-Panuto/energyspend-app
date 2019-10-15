const User = require('../models/user');
const Measures = require('../models/measures');

module.exports = 
{
    async findUser(req, res)
    {
        const { email } = req.body;
        let user = await User.findOne({ where:{ email } });
        if(!user)
        {
            user = await User.create(req.body);
            await Measures.create({ pot: req.pot, price: req.price, actualPot: req.pot, owner: user.id });
        }
        return res.send(user);
    }
}