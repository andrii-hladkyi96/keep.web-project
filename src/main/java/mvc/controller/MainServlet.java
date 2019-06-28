package mvc.controller;

import mvc.view.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet",  urlPatterns = {"/"}, loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        IndexView indexView = IndexView.getInstance();
        out.println(indexView.getIndex());
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // отримуємо посилання на об'єкт сингелтона
        IndexView indexView = IndexView.getInstance();
        // присвоємо змінній сингелтота шлях до папаки html проеку
        indexView.setPath(getServletContext().getRealPath("/html/"));
    }
}