const Measures = require('../models/measures'); // Pega o modelo do banco de dados, no caso o que vai ser salvo

module.exports = // Métodos a ser exportado desse script
{
    async saveMeasure(data) // Função para salvar no banco de dados
    {
        const measure = await Measures.findOne({ where: { id: 1 } }); // Verifica se existe um dado de id 1, pois neste caso estamos utilizando somente 1 perfil
        if(!measure) // Se não houver
        {
            return await Measures.create({ current: data }) // Deve ser criado no banco de dados, com o return para parar o método.
        }
        await measure.update({ current: data }); // Se houver, é atualizado no banco de dados.
    },
    async loadMeasures() // Função para carregar do banco de dados, utilizado somente no inicio, para atribuir à potência atual
    {
        const measure = await Measures.findOne({ where: { id: 1 } }); // Procura no banco o primeiro registro
        if(!measure) // Caso não há
        {
            return 0; // É retorna 0, pois este é o primeiro registro
        }
        return parseFloat(measure.current); // Caso há, retorna o valor atual
    }
}