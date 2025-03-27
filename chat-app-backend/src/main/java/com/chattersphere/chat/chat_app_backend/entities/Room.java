package com.chattersphere.chat.chat_app_backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Room {
    @Id
    private String id; // Unique id in MongoDB for each room
    private String roomId;
    private List<Message> messages = new ArrayList<>();
}
