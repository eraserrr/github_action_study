const express = require('express')
const app = express()
const path = require('path')
const pt = path.join(__dirname, "index.html")
app.use(express.static(pt))
app.get("/", (req, res) => res.sendFile(pt))

app.listen(3000, () => {
    console.log('Server listening on port 3000')
})