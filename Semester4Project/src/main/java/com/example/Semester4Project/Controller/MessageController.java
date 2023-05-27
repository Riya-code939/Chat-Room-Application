package com.example.Semester4Project.Controller;

import com.example.Semester4Project.Model.Message;
import jakarta.annotation.Resource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MessageController
{
    @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message getContent(@RequestBody Message message)
    {
        try
        {
            Thread.sleep(2000);

        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        return message;
    }
}
