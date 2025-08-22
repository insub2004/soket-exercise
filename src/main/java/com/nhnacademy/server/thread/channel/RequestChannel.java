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

import java.util.LinkedList;
import java.util.Queue;

public class RequestChannel {
    private final Queue<Executable> requestQueue;
    private long QUEUE_MAX_SIZE = 10;

    public RequestChannel() {
        //TODO#1-1 responseQueue를 LinkedList를 이용해서 초기화 합니다.
        this.requestQueue = new LinkedList<>();
    }

    public synchronized void addJob(Executable executable){
        //TODO#1-2 where 조건이 requestQueue.size() >= QUEUE_MAX_SIZE 이면 대기 합니다.
        while(requestQueue.size() >= QUEUE_MAX_SIZE){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //TODO#1-3 requestQueue에 executable을 추가히고 대기하고 있는 thread를 깨웁니다.
        requestQueue.add(executable);
        notify();
    }

    public synchronized Executable getJob(){
        //TODO#1-4 requestQueue.isEmpty() 이면 대기 합니다.
        while(requestQueue.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //TODO#1-5 requestQueue에서 작업을 반환하고 대기하고 있는 thread를 깨웁니다.
        notify();
        return requestQueue.poll();
    }

}
