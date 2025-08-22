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

package com.nhnacademy.client.runable;

import com.nhnacademy.client.event.subject.Subject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

// TODO [insub] 클라이언트가 메시지를 수신하는 스레드
@Slf4j
public class ReceivedMessageClient implements Runnable {
    private final Socket socket;
    private final Subject subject;

    public ReceivedMessageClient(Socket socket, Subject subject) {
        /*TODO#2-1 message를 수신하는 Thread입니다.
            - {socket , subject} is null 이면 IllegalArgumentException이 발생 합니다.
         */
        if (Objects.isNull(socket) || Objects.isNull(subject)) {
            throw new IllegalArgumentException();
        }

        //TODO#2-2 초기화 합니다.
        this.socket = socket;
        this.subject = subject;
    }

    // 소켓의 InputStream을 통해 들어오는 입력 -> 출력
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){

            //TODO#2-3 {inputStream, clientIn} 객체를 생성하세요
            try(InputStream inputStream = socket.getInputStream();
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(inputStream));
            ){

                String line;
                //TODO#2-4 clientIn.readLine() 호출해서 message를 읽습니다.
                while((line = clientIn.readLine())!=null){
                    log.debug("recv-message:{}",line);

                    //TODO#2-5 line(message)를 observer들을 관리하는 subject의 receiveMessage()를 호출 합니다., 즉 message 수신 event가 등록되었습니다.
                    subject.receiveMessage(line);
                    /*
                        TODO [insub]
                        1. Subject의 default receiveMessage(line) 실행
                        2. notifyObservers(EventType.RECV,message);    // EventType.RECV 여기서 고정
                        3. MessageSubject의 notifyObservers(..) 실행 -> 옵저버리스트 순회하면서 EventType이 RECV인 옵저버.update(message)
                        4. RecvObsever의 update() 실행 -> messageAction.execute(message) -> RecvMessageAction.execute()실행
                        5. 최종적으로 출력
                     */
                }
            } catch (IOException e) {
                log.error("receivedMessage Error:{}",e.getMessage(),e);
                throw new RuntimeException(e);
            }

        }
    }

}
