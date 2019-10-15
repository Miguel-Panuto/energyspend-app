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
            await Measures.create({ pot: req.pot, price: req.price, actualPot: req.actualPot, owner: user.id });
        }
        return res.send(user);
    },
    async saveMeasure(req, res)
    {
        const { id } = req.headers;
        await User.findOne({ where: { id } })
            .then(() =>
            {
                Measures.update({
                    pot: req.pot,
                    price: req.price,
                    actualPot: req.actualPot
                }, {where: { owner: id }});
                return res.send({
                    pot: req.pot,
                    price: req.price,
                    actualPot: req.actualPot
                });
            })
            .catch((e) => res.status(400).send({ error: e}));
    }
}