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

package com.nhnacademy.server.thread.channel;

import com.nhnacademy.server.method.parser.MethodParser;
import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.method.response.ResponseFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

/*
    TODO [insub]
 */
@Slf4j
public class MethodJob implements Executable {
    private final Socket client;

    public MethodJob(Socket client) {
        //TODO#2-1 client null 이거나 closed 상태이면 IllegalArgumentException 발생 합니다.
        if (Objects.isNull(client)) {
            throw new IllegalArgumentException();
        }
        this.client = client;
    }

    @Override
    public void execute() {
        //TODO#2-2 BufferedReader, PrintWriter 초기화 합니다.
        try (
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), false);
        ) {
            InetAddress inetAddress = client.getInetAddress();
            log.debug("ip:{},port:{}", inetAddress.getAddress(), client.getPort());

            String recvMessage;

            while ((recvMessage = clientIn.readLine()) != null) {
                System.out.println("recv-message: " + recvMessage);
                //TODO#2-3 client로 부터 전달된 message를 MethodParser를 이용해서 파싱 합니다.
                MethodParser.MethodAndValue methodAndValue = MethodParser.parse(recvMessage);
                log.debug("method:{},value:{}", methodAndValue.getMethod(), methodAndValue.getValue());
                //TODO#2-4 ResponseFactory를 이용해서 실행할 Response를 생성 합니다.
                Response response = ResponseFactory.getResponse(methodAndValue.getMethod());

                //TODO#2-5 response 실행하고 결과를 client에게 응답 합니다.
                String sendMessage;
                if (Objects.nonNull(response)) {
                    sendMessage = response.execute(methodAndValue.getValue());
                } else {
                    sendMessage = String.format("{%s} method not found!", methodAndValue.getMethod());
                }

                out.println(sendMessage);
                out.flush();
            }
        } catch (Exception e) {
            log.debug("thread-error:{}", e.getMessage(), e);
        } finally {
            //TODO#2-6 client socket이 null 아니면 client.close()를 호출 합니다.
            try {
                if (Objects.nonNull(client)) {
                    client.close();
                }
            } catch (IOException e) {
                log.error("error-client-close : {}",e.getMessage(),e);
            }
        }
    }
}
