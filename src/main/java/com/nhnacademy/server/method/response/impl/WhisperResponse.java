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

package com.nhnacademy.server.method.response.impl;

import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.runable.MessageServer;
import com.nhnacademy.server.thread.channel.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

@Slf4j
public class WhisperResponse implements Response {
    @Override
    public String getMethod() {
        //TODO#2-1 method = "whisper" 반환 합니다.
        return "whisper";
    }

    @Override
    public String execute(String value) {
        //whisper marco hello 형태로 호출됩니다.
        //method는 whisper, value는 marco hello <-- 입니다.

        //TODO#2-2 로기인 되어있지 않다면 "login required!" 반환 합니다.
        if (!Session.isLogin()) {
            return "login required!";
        }

        //TODO#2-3 value null or "" 이면 "empty message!" 반환 합니다.
        if (Objects.isNull(value) || value.isEmpty()) {
            return "empty message!";
        }

        /*TODO#2-4 value 형식이
            {clientId} {message} 아니라면 "empty message!" 반환 합니다.
            - marco hello (O)
            - marco (X)
            -"" (X)
            - marco nice to meet you (O)

         */
        if (value.trim().contains("\\s+")) {
            log.debug("value is Not : {clientId} {message} format!");
        }

        //TODO#2-4 value 가 marco hello 라면 marco아이디를 사용하는 cleint에게 hello message를 응답합니다.
        String[] strings = value.trim().split("//s+");
        String clientId = strings[0];
        String message = value.substring(clientId.length());

        Socket clientSocket = MessageServer.getClientSocket(clientId);
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("[%s] %s".formatted(Session.getCurrentId(), message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO#2-5 메시지 전송이 완료되면, "[whisper][marco]" hello 형태로 반환 합니다.
        return "[%s] [%s] %s".formatted(getMethod(), clientId, message);
    }
}
