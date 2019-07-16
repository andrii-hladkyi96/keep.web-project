package telegramBot;
import mvc.dao.entity.User;
import mvc.dao.repository.UserRepository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    private long chat_id;
    private String rssfeed = "Галицький кореспондент";
    private int amountNews = 1;
    //UserRepository userRepository =  new UserRepository();
    News newsColection = new News();
    FeedMap feedMap = new FeedMap();
    HashMap <String,String> newsURL = (HashMap<String, String>) feedMap.getNewsUrl();
    LinkedList<String> news = newsColection.getNews(newsURL.get(rssfeed));

    public Bot() {
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            chat_id = update.getMessage().getChatId();
            /*User user = userRepository.getUserByID(chat_id);
            //if (user != null) {
              // rssfeed = user.getFeedURL();
            //amountNews = (int) user.getNumberOfNews();
            //}else {
              //  userRepository.saveUser(new User(0,chat_id," "," ",rssfeed,1,amountNews));
            }*/
            input(userMessage);
        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            chat_id = update.getCallbackQuery().getMessage().getChatId();
            callDataEquals(call_data);
        }
    }

    public void input(String msg)
    {
        if(msg.equals("Hi")||msg.equals("Hello")||msg.equals("Привіт"))
        {
            sendMessage("Вітаю, як справи?");
        }

        if(msg.contains("Давай новини")||msg.contains("Новини")||msg.contains("новини"))
        {
            for (int i=1; i <= amountNews; i++)
            {
                if(news.size()!=0) {
                    sendMessage(news.remove());
                }else{
                    news = newsColection.getNews(newsURL.get(rssfeed));
                }
            }
            sendMessage("Тримай новини ");
        }

        if(msg.contains("Кількість новин"))
        {
            String size = " Я маю " + news.size() + " цікавих новин";
            sendMessage(size);
        }

        if(msg.contains("help")||msg.contains("допомога")||msg.contains("Допомога")||msg.contains("Help"))
        {
            sendMessage("Ви маєте можливість використовувати такі команди:");
            sendMessage("новини - для виводу заданої кількості новин iз вибраного вами ресурсу.");
            sendMessage("налаштування каналу новин або налаштування каналу - для вибору каналу з якого бот стягуватиме новини");
            sendMessage("налаштування кількості новин або налаштування кількості - для вибору обсягу новин ,який буде надсилатися за один раз");
        }

        if(msg.equals("Налаштування каналу")||msg.equals("Налаштування каналу новин")||msg.equals("налаштування каналу новин")||msg.equals("налаштування каналу"))
        {
                sendInlineKeyBoardMessageFeed();
        }

        if(msg.equals("Налаштування кількості новин")||msg.equals("Налаштування кількості")||msg.equals("налаштування кількості новин")||msg.equals("налаштування кількості"))
        {
                sendInlineKeyBoardMessageAmount();
        }
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


    public void sendInlineKeyBoardMessageFeed() {
        SendMessage message = new SendMessage().setChatId(chat_id).setText("Виберіть сервіс з якого ви будете отримувати новини.");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();

        rowInline1.add(new InlineKeyboardButton().setText("Галицький кореспондент").setCallbackData("Галицький кореспондент"));
        rowInline2.add(new InlineKeyboardButton().setText("Правда").setCallbackData("Правда"));
        rowInline1.add(new InlineKeyboardButton().setText("Кориспондент").setCallbackData("Кориспондент"));
        rowInline2.add(new InlineKeyboardButton().setText("Zaxid.net").setCallbackData("Zaxid.net"));
        rowInline3.add(new InlineKeyboardButton().setText("Radio svoboda").setCallbackData("Radio svoboda"));
        rowInline3.add(new InlineKeyboardButton().setText("Українські новини").setCallbackData("Українські новини"));
        rowInline4.add(new InlineKeyboardButton().setText("Репортер").setCallbackData("Репортер"));
        rowInline4.add(new InlineKeyboardButton().setText("Тиждень").setCallbackData("Тиждень"));
        rowInline2.add(new InlineKeyboardButton().setText("5 канал").setCallbackData("5 канал"));
        //Set the keyboard to the markup
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInlineKeyBoardMessageAmount() {
        SendMessage message = new SendMessage().setChatId(chat_id).setText("Виберіть кількість новин ,які ви будете отримувати за один раз.");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        for(int i=1;i<=10;i++)
        {
            String number = "" + i;
            if(i<=5) {
                rowInline1.add(new InlineKeyboardButton().setText(number).setCallbackData(number));
            }
            else{
                rowInline2.add(new InlineKeyboardButton().setText(number).setCallbackData(number));
            }
        }
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void callDataEquals(String call_data)
    {
        for(HashMap.Entry<String, String> entry: newsURL.entrySet()){
            if (call_data.equals(entry.getKey())) {
                rssfeed = entry.getKey();
                sendMessage("Ви обрали для підбору новин " + entry.getKey());
                news = newsColection.getNews(newsURL.get(rssfeed));
            }
        }

        for(int i=1;i<=10;i++)
        {
            String number = "" + i;
            if(call_data.equals(number))
            {
                amountNews = Integer.parseInt(call_data);
                switch (amountNews) {
                    case  1:
                        sendMessage("Кожного разу ви будете отримувати по " + number + " новині.");
                        break;
                    case 2: case 3:case 4:
                        sendMessage("Кожного разу ви будете отримувати по " + number + " новини.");
                        break;
                    case 5: case 6: case 7: case 8: case 9: case 10:
                        sendMessage("Кожного разу ви будете отримувати по " + number + " новин.");
                        break;
                }
            }
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

