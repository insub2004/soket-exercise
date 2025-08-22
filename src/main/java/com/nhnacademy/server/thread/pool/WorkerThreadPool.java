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

import java.util.Objects;

public class WorkerThreadPool {
    private final int poolSize;

    private final static int DEFAULT_POOL_SIZE = 5;

    private final Thread[] workerThreads;
    private final Runnable runnable;

    public WorkerThreadPool(Runnable runnable) {
        //TODO#4-1 생성자를 초기화 합니다.
        this(DEFAULT_POOL_SIZE, runnable);
    }

    public WorkerThreadPool(int poolSize, Runnable runnable) {

        //TODO#4-2 poolSize <1 or runnable isNull 다면 IllegalArgumentException이 발생합니다.
        if (poolSize < 1 || Objects.isNull(runnable)) {
            throw new IllegalArgumentException();
        }
        //TODO#4-3 poolSize, runnable 초기화 합니다.
        this.poolSize = poolSize;
        this.runnable = runnable;

        //TODO#4-4 workerThreads 초기화 합니다.
        workerThreads = new Thread[poolSize];

        //TODO#4-5 initilizePool() 호출 합니다.
        initilizePool();
    }

    private void initilizePool() {
        //TODO#4-6 runnable를 이용해서 thread를 생성 합니다.
        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = new Thread(runnable);
        }
    }

    public synchronized void start() {
        //TODO#4-7 workerThreads에 초가화된 모든 Thread를 start 합니다.
        //start()는 동기화 처리되어야 합니다.
        for (Thread workerThread : workerThreads) {
                workerThread.start();
        }
    }

    public synchronized void stop() {
        //TODO#4-8 interrupt()를 발생시켜,  thread를 종료시킵니다.
        //stop() 동기화 처리되어야 합니다.
        for (Thread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }
}
