#!/usr/bin/env node

const amqp = require('amqplib/callback_api')

amqp.connect('amqp://localhost', (err, conn) => {
   conn.createChannel((err, ch) => {
      const q = 'hello'
      ch.assertQueue(q, { durable: false })
      ch.consume( q, msg => {
         console.log(`[x] Receive ${msg.content.toString()}` )
      }, { noAck: true})
   })
})