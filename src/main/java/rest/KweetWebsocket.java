package rest;

import Models.Kweet;
import Models.User;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/KweetWebsocket/{username}")
public class KweetWebsocket {
        private static final Logger logger = Logger.getLogger(KweetWebsocket.class.getName());
        public static Set<Session> peers = Collections.synchronizedSet(new HashSet<>());
        @Inject
         UserService userService;
        private List<String> users = new ArrayList<>();

        @OnOpen
        public void open(Session session,@PathParam("username")String username) {
            peers.add(session);
            users.add(username);
            //this.users.add(userService.getUser(username));

            System.out.println("Session has been connected" + session.getId());
        }

        @OnClose
        public void close(Session session,@PathParam("username") String username) {
            peers.remove(session);
            User u = this.userService.getUser(username);
            if(this.users.contains(u)){
                this.users.remove(u);
            }
        }

        @OnError
        public void onError(Session session, Throwable error) {
            peers.remove(session);
        }

        @OnMessage
        public void onMessage(String message,Session session,@PathParam("username") String username){
            sendMessages(userService.getUser(username));
        }

        public void sendMessages(User sendUser) {
            try {
                for (Session session : peers) {
                    for(User u : sendUser.getFollowers()) {
                        if (session.isOpen()&&this.users.contains(u.getUsername())) {
                            session.getBasicRemote().sendText("GetKweets");
                        }
                    }
                }
            } catch (Exception e) {
                logger.info("Error bij verzenden ws message " + e.getMessage());
            }
        }
    }

