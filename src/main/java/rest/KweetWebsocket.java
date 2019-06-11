package rest;

import Models.Kweet;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/KweetWebsocket")
public class KweetWebsocket {
        private static final Logger logger = Logger.getLogger(KweetWebsocket.class.getName());
        public static Set<Session> peers = Collections.synchronizedSet(new HashSet<>());
        @OnOpen
        public void open(Session session) {
            peers.add(session);
            System.out.println("Session has been connected" + session.getId());
        }

        @OnClose
        public void close(Session session) {
            peers.remove(session);
        }

        @OnError
        public void onError(Session session, Throwable error) {
            peers.remove(session);
        }

        @OnMessage
        public void handleMessage(String message, Session session) {
            sendMessages();
        }

        public void sendMessages() {
            try {
                for (Session session : peers) {

                    if(session.isOpen()) {
                        session.getBasicRemote().sendText("GetKweets");
                    }

                }
            } catch (Exception e) {
                logger.info("Error bij verzenden ws message " + e.getMessage());
            }
        }
    }

