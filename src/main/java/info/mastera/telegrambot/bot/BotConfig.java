package info.mastera.telegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;

@Slf4j
@Configuration
public class BotConfig {

    @Value("${telegram.webhook-host}")
    private String webhookHost;
    @Value("${telegram.endpoint}")
    private String botEndpoint;
    @Value("${telegram.token}")
    private String botToken;
    @Value("${telegram.username}")
    private String botUsername;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder()
                .url(webhookHost)
                .build();
    }

    @Bean
    public ParserBot getBot(SetWebhook setWebhook) {
        ParserBot parserBot = new ParserBot(setWebhook);
        parserBot.setBotPath(botEndpoint);
        parserBot.setBotToken(botToken);
        parserBot.setBotUsername(botUsername);
        try {
            DefaultWebhook defaultWebhook = new DefaultWebhook();
            defaultWebhook.setInternalUrl("http://localhost:80");

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class, defaultWebhook);
            log.info("SetWebhook from bot {}", setWebhook);

            telegramBotsApi.registerBot(parserBot, parserBot.getSetWebhook());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't register telegram bot", e);
        }
        return parserBot;
    }
}
