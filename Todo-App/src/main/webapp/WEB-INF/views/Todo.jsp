<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            .Add-btn{
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
            .todo-list{
                display: flex;
                flex-direction: column;
                margin-top: 10px;
                width: 100%;
                height: 400px;
                overflow: auto;
            }
            .todo-row{
                display: flex;
                justify-content: space-between;
                min-height: 50px;
                align-items: center;
                background: #f7fff6;
            }
            .completed-row{
                display: flex;
                justify-content: space-between;
                min-height: 50px;
                align-items: center;
                background: #f5efef;
            }
            .todo-row > div{
              text-align: left;
              flex-basis: 33.33%;
            }
            .completed-row > div{
              text-align: left;
              flex-basis: 33.33%;
              text-decoration: line-through;
            }
            .check-icon{
                color: green;
			    font-size: larger;
			    background: white;
			    border: none;
            }
            .cross-icon{
                color: #b60000;
			    font-size: larger;
			    background: white;
			    border: none;
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
                  <form method='post' action='AddTodo'>
                    <input type="text" name="todo"/>
                    <button type="submit" class="Add-btn">Add</button>
                  </form>
                 </div>
             </header>
                  <div class="todo-list">
					<div class="todo-row" style="background: gainsboro;">
						<div>Task</div>
						<div>Created On</div>
						<div>Actions</div>
					</div>
					<c:forEach var="todo" items='${todos}'>

						<div class="todo-row">
							<div><c:out value="${todo.text}" /></div>
							<div><c:out value="${todo.createdAt}" /></div>
							<div>
							  <form method='post' action='UpdateTodo'>
							    <input type="hidden" name="id" value="${todo.id}"/>
							    <button type="submit" class="check-icon" name="submit" value="markComplete"> &check;</button>
			                    <button type="submit" class="cross-icon" name="submit" value="delete"> &cross;</button>
			                  </form>						
							</div>
						</div>
					</c:forEach>
					<c:forEach var="todo" items='${completedList}'>

						<div class="completed-row">
							<div><c:out value="${todo.text}" /></div>
							<div><c:out value="${todo.createdAt}" /></div>
							<div>
							  <form method='post' action='UpdateTodo'>
							    <input type="hidden" class="check-icon" name="id" value="${todo.id}"/>
			                    <button type="submit" class="cross-icon" name="submit" value="delete"> &cross;</button>
			                  </form>						
							</div>
						</div>
					</c:forEach>     
                  </div>

        </div>
    </body>
</html>