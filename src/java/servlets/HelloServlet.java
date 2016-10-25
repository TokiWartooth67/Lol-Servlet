package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Object;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;

@WebServlet("/")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Получаем объект Session до любой
        //

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Cookie cok[] = request.getCookies();
            out.println("<h1>Hello " + cok[1].getValue() + "</h1>");

            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

