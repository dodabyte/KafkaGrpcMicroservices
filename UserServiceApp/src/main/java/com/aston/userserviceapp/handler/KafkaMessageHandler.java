package com.aston.userserviceapp.handler;

import com.aston.userserviceapp.dto.entity.order.OrderKafkaMessageDto;
import com.aston.userserviceapp.dto.entity.user.UserResponseDto;
import com.aston.userserviceapp.exception.EntityNotFoundException;
import com.aston.userserviceapp.service.UserService;
import com.aston.protomodule.user.UserMessageHandlerGrpc;
import com.aston.protomodule.user.UserMessageHandlerOuterClass;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @GrpcClient("order-service")
    private UserMessageHandlerGrpc.UserMessageHandlerBlockingStub blockingStub;

    @KafkaListener(topics = "order-created-message-topic")
    public void handle(OrderKafkaMessageDto dto) throws EntityNotFoundException {
        String message;
        UserMessageHandlerOuterClass.MessageType messageType;

        if (dto == null || dto.getId() == 0 || dto.getUserId() == 0) {
            message = "The user ID is not specified. The order history has not been updated {}";
            LOGGER.error(message, dto);
            messageType = UserMessageHandlerOuterClass.MessageType.ERROR;
        }
        else {
            LOGGER.info("Received event: {}", dto);

            UserResponseDto userResponseDto = userService.addOrderToHistory(dto);

            message = "The user's order history has been updated: {}";
            LOGGER.info(message, userResponseDto);

            messageType = UserMessageHandlerOuterClass.MessageType.INFO;
        }

        UserMessageHandlerOuterClass.Message requestMessage =
                UserMessageHandlerOuterClass.Message.newBuilder()
                .setType(messageType)
                .setMessage(message.replaceAll("\\{}", dto.toString()))
                .build();

        blockingStub.message(requestMessage);
    }
}