package com.aston.orderserviceapp.handler;

import com.aston.protomodule.user.UserMessageHandlerGrpc;
import com.aston.protomodule.user.UserMessageHandlerOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class GrpcMessageHandler extends UserMessageHandlerGrpc.UserMessageHandlerImplBase {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void message(UserMessageHandlerOuterClass.Message requestMessage,
                        StreamObserver<UserMessageHandlerOuterClass.Message> responseMessage) {
        String message = requestMessage.getMessage();

        switch (requestMessage.getType()) {
            case INFO: {
                LOGGER.info(message);
                break;
            }
            case ERROR: {
                LOGGER.error(message);
                break;
            }
        }

        UserMessageHandlerOuterClass.Message response =
                UserMessageHandlerOuterClass.Message.newBuilder()
                    .setType(UserMessageHandlerOuterClass.MessageType.INFO)
                    .setMessage("The message was delivered.")
                    .build();

        responseMessage.onNext(response);
        responseMessage.onCompleted();
    }
}