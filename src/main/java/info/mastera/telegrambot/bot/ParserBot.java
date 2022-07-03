package info.mastera.telegrambot.bot;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Service
@Setter
@Slf4j
@ToString
public class ParserBot extends SpringWebhookBot {

    private String botPath;
    private String botToken;
    private String botUsername;

    @Autowired
    public ParserBot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            log.info("webhook:" + update);
            Long chatId = update.getMessage().getChatId();
            return new SendMessage(chatId.toString(), "Bot received this message:" + update.getMessage().getText());
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }
}
