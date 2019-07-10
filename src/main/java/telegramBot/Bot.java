package telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Bot extends TelegramLongPollingBot {
    private long chat_id;


    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();

        sendMessage.setText(input(update.getMessage().getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public String input(String msg)
    {
        if(msg.contains("Hi")||msg.contains("Hello")||msg.contains("Привіт"))
        {
            msg="Вітаю, як справи?";
        }

        if(msg.contains("Ще новини")||msg.contains("Новини"))
        {
            News news1 = new News();
            ArrayList news = news1.getNews();
            msg = News.getNews().get(0);
        }
        return msg;
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

