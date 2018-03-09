package com.jsanca;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
@ClientEndpoint
public class App {
    private static Object waitLock = new Object();

    @OnMessage

    public void onMessage(String message) {

//the new USD rate arrives from the websocket server side.

        System.out.println("Received msg: " + message);

    }

    private static void wait4TerminateSignal() {

        synchronized (waitLock) {
            try {
                waitLock.wait();
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {

        WebSocketContainer container = null;//

        Session session = null;

        try {

            //Tyrus is plugged via ServiceLoader API. See notes above

            container = ContainerProvider.getWebSocketContainer();

//WS1 is the context-root of my web.app

//ratesrv is the  path given in the ServerEndPoint annotation on server implementation

            session = container.connectToServer(App.class, URI.create("ws://localhost:8080//api/ws/v1/system/events"));

            wait4TerminateSignal();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (session != null) {

                try {

                    session.close();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

    }
/*
    public static void main(String[] args) {

    }

        try

    {
        final CountDownLatch messageLatch = new CountDownLatch(1);

        ClientManager client = createClient();
        client.connectToServer(new Endpoint() {
            @Override
            public void onOpen(Session session, EndpointConfig config) {
                try {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            if (message.equals(PONG_RECEIVED)) {
                                messageLatch.countDown();
                            }
                        }
                    });
                    session.getBasicRemote().sendText("ping-initiator");
                } catch (IOException e) {
                    // do nothing.
                }
            }
        }, ClientEndpointConfig.Builder.create().build(), getURI(PingPongEndpoint.class));

        assertTrue(messageLatch.await(2, TimeUnit.SECONDS));
        System.out.println("Hello World!");
    }*/
}
