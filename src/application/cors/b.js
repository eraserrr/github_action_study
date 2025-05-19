const express = require('express')
const app = express()
const cors = require('cors')

const corsOptions = {
    origin: 'http://localhost:3000',
    methods: 'POST'
}

app.use(cors(corsOptions))
app.get('/api',
    (req, res) =>
        res.json({data: '살민 살아졍'})
)

app.listen(3001, () => {
    console.log('Server listening on port 3001')
})