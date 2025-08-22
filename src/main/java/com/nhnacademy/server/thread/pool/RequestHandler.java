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

package com.nhnacademy.server.thread.pool;

import com.nhnacademy.server.thread.channel.Executable;
import com.nhnacademy.server.thread.channel.RequestChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class RequestHandler implements Runnable {
    private final RequestChannel requestChannel;

    public RequestHandler(RequestChannel requestChannel) {
        //TODO#3-1 requestChannel을 초기화 합니다.
        if (Objects.isNull(requestChannel)) {
            throw new IllegalArgumentException();
        }
        this.requestChannel = requestChannel;
    }

    @Override
    public void run() {
        //TODO#3-2 thread interupted가 발생하면 종료 됩니다. while 조건을 수정 하세요.
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //TODO#3-3 requestChannel로 부터 실행할 작업을 획득 후 execute method를 호출 해서 실행 합니다.
                Executable executable = requestChannel.getJob();
                executable.execute();

            }catch (Exception e){
                if(e instanceof InterruptedException){
                    log.debug("thread 종료!");
                    Thread.currentThread().interrupt();
                }
                log.error("thread-exception : {}", e.getMessage(),e);
            }
        }
    }
}
