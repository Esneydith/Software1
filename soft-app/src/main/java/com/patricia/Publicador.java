package com.patricia;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class Publicador {
    private static final String QUEUE_NAME = "Publicador1";
    private static final String QUEUE_NAME2 = "Publicador2";
    private static final String QUEUE_NAME3 = "Publicador3";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        //Creacion de conexion
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
            channel.queueDeclare(QUEUE_NAME3, false, false, false, null);

            //mensaje
            String mensaje = "Hola como estas?" + LocalDateTime.now();
            channel.basicPublish("", QUEUE_NAME, null, mensaje.getBytes());
            channel.basicPublish("", QUEUE_NAME2, null, mensaje.getBytes());
            channel.basicPublish("", QUEUE_NAME3, null, mensaje.getBytes());
            System.out.println("El mensaje ha sido enviado");

        }

    }


}
