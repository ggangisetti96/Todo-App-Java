<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todo Manager - Edit</title>
<style type="text/css">
   .edit-container{
     display: flex;
     align-items: center;
     height: 90vh;
     justify-content: center;
   }
   input{
     padding: 5px;
     border-color: none;
     
   }
   button{
       padding: 5px;
       text-transform: uppercase;
   }
   .red-btn{
       background: #fff;
       border: 2px solid #b60000;
       color: #b60000;
       text-transform: uppercase;
       font-weight: 700;
       padding: 5px;
    }
   button{
       padding: 5px;
       text-transform: uppercase;
   }
</style>
</head>
<body>
   <div class="edit-container">
     <div>
	     <form method="post" action="UpdateTodo">
	       <input type="hidden" name="id" value='${todoId}'/>
	       <input type="text" name="to-do" placeholder=" Enter the new description.. "/>
	       <button type="submit" name="submit" value="edit" class="red-btn">Save</button>
	       <button type="submit" >Cancel</button>
	     </form>
     </div>
   </div>
</body>
</html>