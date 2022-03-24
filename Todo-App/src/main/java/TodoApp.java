

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Todo;
import util.TodoDBUtil;

/**
 * Servlet implementation class Home
 */
@WebServlet("/")
public class TodoApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TodoApp() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/list") || path.equals("/")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Todo-list.jsp");
			List<Todo> todos = TodoDBUtil.listTodos();
			List<Todo> completedList = TodoDBUtil.listCompleted();
			request.setAttribute("todos", todos);
			request.setAttribute("completedList", completedList);
			dispatcher.forward(request, response);
		}
		else if(path.equals("/edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("todoId", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Edit-Todo.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
       
		try {
			if(path.equals("/AddTodo")) {
				String todo = request.getParameter("todo");
				// validate string length
				if(todo.length() > 1) {
					TodoDBUtil.createTodo(todo, null);
				}
			}
			else if(path.equals("/UpdateTodo")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String type = request.getParameter("submit");
				System.out.println(type + "type");
				switch(type) {
				 case "markComplete":
					 TodoDBUtil.markComplete(id);
					 break;
				 case "markIncomplete":
					 TodoDBUtil.markTodo(id);
					 break;
				 case "delete":
					 TodoDBUtil.deleteTodo(id);
					 break;
				 case "edit":
					 String todo = request.getParameter("to-do");
					 System.out.println(todo);
					 TodoDBUtil.editTodo(id, todo);	
					 break;
				}								
			}
		
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			response.sendRedirect("list");	
		}
	}

}
