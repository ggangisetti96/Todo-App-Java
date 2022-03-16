<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todo Manager</title>
    <style>
            body {
                font-family: "Roboto", sans-serif;
                line-height: 1;
                letter-spacing: normal;
                margin-top: 10vh;
            }
            #root {
               margin: auto;
               width: 55%;
            }
            header{
                background: #0A0A0A;
                font-size: 1rem;
                color: azure;
                height: 100px;
                display: flex; 
                flex-direction: column;
                justify-content: center;       
            }
            button{
                background: #fff;
                border: 2px solid #b60000;
                color: #b60000;
                text-transform: uppercase;
                font-weight: 700;
                padding: 5px;
            }
            input{
                padding: 5px;
                border-radius: 0px;
                width: 65%;
                border: none;
            }
            .title{
                margin-bottom: 10px;
                text-align: center;
            }
            .input-section{
                display: flex;
                justify-content: center;
                width: 100%;
            }
    </style>
</head>
    <body>
        <div id="root">
            <header>
                <div class="title">
                   To-do Manager
                </div>
                <div class="input-section">
                  <form method='post' action='Home'>
                    <input type="text" name="todo"/>
                    <button type="submit">Add</button>
                  </form>
                </div>
            </header>

        </div>
    </body>
</html>