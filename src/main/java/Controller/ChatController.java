package Controller;

import Services.ChatService;
import Services.ServicesImpl.ChatServiceImpl;

public class ChatController {
    private ChatService service;
    public ChatController() {
        service = new ChatServiceImpl();
    }
    public Boolean sendMessage(String sender, String receiver, String mes) {
        return null;
    }
}
