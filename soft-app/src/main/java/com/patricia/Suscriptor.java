package com.patricia;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Suscriptor {
    private static final String QUEUE_NAME = "Publicador1";
    private static final String QUEUE_NAME2 = "Publicador2";
    private static final String QUEUE_NAME3 = "Publicador3";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        //Creacion de conexion
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        channel.queueDeclare(QUEUE_NAME3, false, false, false, null);

        channel.basicConsume(QUEUE_NAME, true, (s, delivery) -> {
            String m = new String(delivery.getBody(), "UTF-8");
            System.out.println("Mensaje recibido = " + m);

        }, s -> {});
        channel.basicConsume(QUEUE_NAME2, true, (s, delivery) -> {
            String m = new String(delivery.getBody(), "UTF-8");
            System.out.println("Mensaje recibido = " + m);

        }, s -> {});

        channel.basicConsume(QUEUE_NAME3, true, (s, delivery) -> {
            String m = new String(delivery.getBody(), "UTF-8");
            System.out.println("Mensaje recibido = " + m);

        }, s -> {});


    }
}
