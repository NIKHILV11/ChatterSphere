package com.chattersphere.chat.chat_app_backend.payload;
// import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private String roomId;
    private String sender;
    private String content;
  
}
