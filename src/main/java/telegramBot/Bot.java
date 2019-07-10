package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;

public class Bot extends TelegramLongPollingBot {
    private long chat_id;
    private String userName;
    News newsColection = new News();
    LinkedList<String> news = newsColection.getNews();


    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        chat_id = update.getMessage().getChatId();
        userName = update.getMessage().getAuthorSignature();
        String userMessage = update.getMessage().getText();
        sendMessage(input(userMessage));
    }

    public String input(String msg)
    {
        if(msg.contains("Hi")||msg.contains("Hello")||msg.contains("Привіт"))
        {
            msg = ("Вітаю, як справи?");
        }

        if(msg.contains("Давай новини")||msg.contains("Новини"))
        {
            msg = "Тримай новини ";
            for (int i=0; i < 5; i++)
            {
                if(news.size()!=0) {
                    sendMessage(news.remove());
                }
                else {
                    sendMessage("Нажаль більше актуальних новин немає.Спробуйте пізніше");
                }
            }
        }

        if(msg.contains("Кількість новин"))
        {
            String size = " Я маю " + news.size() + " цікавих новин";
            msg = (size);
        }
        return msg;
    }


    private void sendMessage(String massege)
    {
        SendMessage sendMessage = new SendMessage().setChatId(chat_id);
        sendMessage.setText(massege);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@NewsFranBot";
    }

    @Override
    public String getBotToken() {
        return "832037925:AAH4Xreuxc3quklW7hXUCnqaTVWflvnvi8A";
    }
}

