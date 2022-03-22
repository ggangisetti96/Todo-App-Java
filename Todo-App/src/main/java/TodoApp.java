

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
		if(path.equals("/list") || path.equals("/") || path.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Todo.jsp");
			List<Todo> todos = TodoDBUtil.listTodos();
			request.setAttribute("todos", todos);
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
				TodoDBUtil.createTodo(todo, false);
			}
			else if(path.equals("/DeleteTodo")) {
				int id = Integer.parseInt(request.getParameter("id"));
				TodoDBUtil.deleteTodo(id);
			}
		
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			response.sendRedirect("list");	
			System.out.println("path" + path);
		}
	}

}
