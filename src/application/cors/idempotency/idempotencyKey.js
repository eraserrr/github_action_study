const express = require('express')
const app = express()
app.use(express.json())
const idempotencyKeyStore = {}
app.post('/order', (req, res) => {
    const idempotencyKey = req.headers['idempotency-key']
    if (!idempotencyKey) {
        return res.status(400).json({
            error: '멱등성에 관한 키가 필요합니다.'
        })
    }
    if (idempotencyKeyStore[idempotencyKey]) {
        return res.status(200).json({
            message: '동이한 요청을 받았습니다.',
            result: idempotencyKeyStore[idempotencyKey]
        })
    }
    const order = {
        id: Math.floor(Math.random() * 1000000),
        items: req.body.items,
        total: req.body.total
    }
    idempotencyKeyStore[idempotencyKey] = order
    res.status(201).json(order)
})

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
    console.log(`Server running on ${PORT}`)
})