const express = require('express');
const app = express();
const port = 3000;

app.use(express.json());

app.get('/users',(req,res)=>{
    res.json({message:'Retrive message from users'});
});

app.post('/users',(req,res)=>{
    const newUser = req.body;
    res.json({message:'New User Created :',user:newUser});
});

app.put('/users/:id',(req,res)=>{
    const userId = req.params.id;
    const updatedUser = req.body;
    res.json({message:`User with ${userId} Updated`,updatedUser});
});

app.delete('/users/:id',(req,res)=>{
    const userId = req.params.id;
    res.json({message:`user with ${userId} Deleted.`});
});
app.listen(port,()=>{
    console.log(`The server ir running in port : ${port}`);
});