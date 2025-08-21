/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.server.runable;

import com.nhnacademy.server.method.parser.MethodParser;
import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.method.response.ResponseFactory;
import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

@Slf4j
public class MessageServer implements Runnable {
    private static final int DEFAULT_PORT = 8888;
    private final int port;
    private final ServerSocket serverSocket;

    public MessageServer() {
        this(DEFAULT_PORT);
    }

    public MessageServer(int port) {
        if (port <= 0) {
            throw new IllegalArgumentException(String.format("port:%d", port));
        }

        this.port = port;

        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (Socket client = serverSocket.accept();
                    BufferedReader clientIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), false);) {
                InetAddress inetAddress = client.getInetAddress();
                log.debug("ip:{},port:{}", inetAddress.getAddress(), client.getPort());

                String recvMessage = null;

                while ((recvMessage = clientIn.readLine()) != null) {
                    System.out.println("[server]recv-message:" + recvMessage);
                    // TODO#1-10 MethodParser를 이용해서 recvMessage를 파싱 합니다.

                    MethodParser.MethodAndValue methodAndValue = MethodParser.parse(recvMessage);

                    log.debug("method:{},value:{}", methodAndValue.getMethod(), methodAndValue.getValue());

                    // TODO#1-11 ResponseFactory를 이용해서 methodAndValue.getMethod()에 해당되는 response를
                    // 얻습니다.
                    Response response = null;
                    try {
                        // TODO [insub] step2 단계에선, 임의로 Factory의 list안에 EchoResponse 하나를 넣어놨음
                        response = ResponseFactory.getResponse(methodAndValue.getMethod());
                    }catch (ResponseNotFoundException re){
                        //ResponseNotFoundException 발생하면 로그로 남김니다.
                        log.debug("response not found : {}", re.getMessage());
                    }

                    String sendMessage;
                    if (Objects.nonNull(response)) {
                        // TODO#1-12 methodAndValue.getValue() 이용해서 response를 실행 합니다.
                        sendMessage = methodAndValue.getValue();
                    } else {
                        // TODO#1-13 response가 null 이면 sendMessage를 "{echo} method not found" 로 설정 합니다.
                        sendMessage = "{%s} method not found".formatted(methodAndValue.getMethod());
                    }
                    out.println(sendMessage);
                    out.flush();
                }
            } catch (Exception e) {
                log.debug("{}", e.getMessage(), e);
            }
        }
    }// end method
}