package cn.ives.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket")
@Component
public class WebSocketController {

   //与某个客户端的连接会话，需要通过它来给客户端发送数据
   private Session session;
   @OnOpen
   public void onOpen(Session session){
      this.session = session;
      System.out.println("有新连接加入！");
      sendMessage("连接成功");
   }

   @OnClose
   public void onClose(){
      System.out.println("有一连接关闭！");
   }

   @OnMessage
   public void onMessage(String message, Session session) {
      System.out.println("来自客户端的消息:" + message);

   }
   @OnError
   public void onError(Session session, Throwable error){
      System.out.println("发生错误");
   }

   public void sendMessage(String message) {
      try {
         this.session.getBasicRemote().sendText(message);
      }
      catch(IOException e) {
         e.printStackTrace();
      }
   }
}
