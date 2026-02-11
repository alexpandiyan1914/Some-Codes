const express = require('express');
const app = express();
const port = 8080;
const mysql = require('mysql2');
const dotenv = require('dotenv').config();

app.use(express.json());

const db = mysql.createConnection({
    host : "localhost",
    user : "root",
    password : process.env.SQL_PASS,
    database : "testdbb"
});

db.connect((err)=>{
    if(err){
        console.log("Sql connection failed...");
    }else{
        console.log("connected to mysql");
    }
});

app.post('/users',(req,res)=>{
    const {name, email} = req.body;

    const sql = "INSERT INTO users (name, email) VALUES (?, ?)";

    db.query(sql, [name,email], (err,result)=>{
        if(err){
            return res.status(500).json({error:err});
        }

        res.json({message:`Values are Inserted Sucessfully`});
    });
});

app.get('/users',(req,res)=>{
    const sql = "SELECT * FROM users";

    db.query(sql,(err,result)=>{
        if(err){
            return res.status(500).json({error: err});
        }

        res.json(result);
    });
});

app.listen(port,()=>{
    console.log(`The server runs on port:${port}`);
});