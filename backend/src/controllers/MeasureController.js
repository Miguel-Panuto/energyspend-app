const Measures = require('../models/measures');

module.exports = 
{
    async saveMeasure(data)
    {
        const measure = await Measures.findOne({ where: { id: 1 } });
        if(!measure)
        {
            return await Measures.create({ current: data })
        }
        await measure.update({ current: data });
    },
    async loadMeasures()
    {
        const measure = await Measures.findOne({ where: { id: 1 } });
        if(!measure)
        {
            return 0;
        }
        return parseFloat(measure.current);
    }
}