package com.chattersphere.chat.chat_app_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chattersphere.chat.chat_app_backend.entities.Message;
import com.chattersphere.chat.chat_app_backend.entities.Room;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.chattersphere.chat.chat_app_backend.repositories.RoomRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private RoomRepository RoomRepository;
    public RoomController(RoomRepository RoomRepository) {
        this.RoomRepository = RoomRepository;
    }
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        if(RoomRepository.findByRoomId(roomId) == null) {
            Room room = new Room();
            room.setRoomId(roomId);
            Room savedRoom = RoomRepository.save(room);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
        }
        else{
            return ResponseEntity.badRequest().body("Room already exists");
        }
       
    }

    @GetMapping("/{roomId}") 
    public ResponseEntity<?> getRoom(@PathVariable String roomId) {
        Room room = RoomRepository.findByRoomId(roomId);
        if(room != null) {
            return ResponseEntity.ok(room);
        }
        else {
            return ResponseEntity.badRequest().body("Room does not exist");
        }
    }


    @GetMapping("/{roomId}/messages") 
    public ResponseEntity<List<Message>> getMessages(
        @PathVariable String roomId,
        @RequestParam(value = "page", defaultValue = "0",required = false) int page,
        @RequestParam(value = "size", defaultValue = "20",required = false) int size
    ) {
        Room room = RoomRepository.findByRoomId(roomId);
        if(room != null) {
            List<Message> message = room.getMessages();
            int start = Math.max(0,message.size() - (page + 1) * size);
            int end = Math.min(message.size(), start + size);
            return ResponseEntity.ok(message.subList(start, end));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        
    }
    

}
