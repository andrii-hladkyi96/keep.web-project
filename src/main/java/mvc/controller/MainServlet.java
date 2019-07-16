package mvc.controller;

import mvc.dao.repository.UserRepository;
import mvc.view.IndexView;
import mvc.view.UserView;
import mvc.dao.entity.User;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import telegramBot.Bot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet",  urlPatterns = {"/"}, loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int chat_id = Integer.parseInt(password);

        if (email != null) {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserByID(chat_id);
            if (user != null) {
               session.setAttribute("email",email);
            }
        }
        response.sendRedirect("/keep_web_project_war_exploded");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        IndexView indexView = IndexView.getInstance();

       if (session.getAttribute("email") != null) {
                out.println("Welcome " + session.getAttribute("email"));
      } else {
               UserView userView = new UserView();
              out.println(userView.getLoginPage());
      }

    }

    @Override
    public void init() throws ServletException {
        super.init();
        // отримуємо посилання на об'єкт сингелтона
        IndexView indexView = IndexView.getInstance();
        // присвоємо змінній сингелтота шлях до папаки html проеку
        indexView.setPath(getServletContext().getRealPath("/html/"));

        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();

        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}