package servlets.api;

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


@WebServlet("/api")
public class secondServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Получаем объект Session до любой
        // посылки клиенту.
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HEAD><TITLE> SessionPeek ");
        out.println(" </TITLE></HEAD><BODY>");
        out.println("<h1> SessionPeek </h1>");
        // Простой щетчик обращений для этой сессии.
        Integer ival = (Integer) session.getAttribute("sesspeek.cntr");
        if (ival == null)
            ival = new Integer(1);
        else
            ival = new Integer(ival.intValue() + 1);
        session.setAttribute("sesspeek.cntr", ival);


        out.println("You have hit this page <b>" + ival + "</b> times.<p>");
        out.println("<h2>");
        out.println("Saved Session Data </h2>");
        // Цикл по всем данным сессии:
        Enumeration sesNames = session.getAttributeNames();
        while (sesNames.hasMoreElements()) {
            String name = sesNames.nextElement().toString();
            Object value = session.getAttribute(name);
            out.println(name + " = " + value + "<br>");
        }
        out.println("<h3> Session Statistics </h3>");
        out.println("Session ID: " + session.getId() + "<br>");
        out.println("New Session: " + session.isNew() + "<br>");
        out.println("Creation Time: " + session.getCreationTime());
        out.println("<I>(" + new Date(session.getCreationTime()) + ")</I><br>");
        out.println("Last Accessed Time: " + session.getLastAccessedTime());
        out.println("<I>(" + new Date(session.getLastAccessedTime())
                + ")</I><br>");
        out.println("Session Inactive Interval: "
                + session.getMaxInactiveInterval());
        out.println("Session ID in Request: " + request.getRequestedSessionId()
                + "<br>");
        out.println("Is session id from Cookie: "
                + request.isRequestedSessionIdFromCookie() + "<br>");
        out.println("Is session id from URL: "
                + request.isRequestedSessionIdFromURL() + "<br>");
        out.println("Is session id valid: " + request.isRequestedSessionIdValid()
                + "<br>");
        out.println("</BODY>");

        String n = ("Toki");
        out.print("Welcome " + n);

        Cookie ck = new Cookie("login", n);//creating cookie object
        response.addCookie(ck);//adding cookie in the response
    }

    public String getServletInfo() {
        return "A session tracking servlet";
    }


}