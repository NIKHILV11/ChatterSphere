package com.chattersphere.chat.chat_app_backend.controllers;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.time.LocalDateTime;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.chattersphere.chat.chat_app_backend.entities.Message;
import com.chattersphere.chat.chat_app_backend.payload.MessageRequest;
import com.chattersphere.chat.chat_app_backend.entities.Room;
import com.chattersphere.chat.chat_app_backend.repositories.RoomRepository;


@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request) {
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setSender(request.getSender());
        message.setContent(request.getContent());
        message.setTimestamp(LocalDateTime.now());

        if(room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
            return message;
            
        }
        else{
            throw new RuntimeException("Room not found");
        }
        
       
    }


}
