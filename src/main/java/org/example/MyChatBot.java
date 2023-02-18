package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MyChatBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        System.out.println(message);
        SendMessage response = new SendMessage(String.valueOf(chatId), message);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Указание имени бота
        return "ChatBot";
    }

    @Override
    public String getBotToken() {
        // Указание токена бота
        return "here must be your token";
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyChatBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

