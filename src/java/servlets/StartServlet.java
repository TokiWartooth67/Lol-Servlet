 package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class StartServlet extends HttpServlet {


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String name = request.getParameter("firstName");
		if(name==null){
			name=" ";
		}
		
		System.out.println(name);
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter(); 
		out.println("HELLO, " +name+"!");
		out.close();
	
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("firstName");
			if(name==null){
				name=" ";
			}
			
		System.out.println(name);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter(); 
		out.println("HEY, POST, Hello " +name+" !!1!");
		
		
		out.close();

}
}
