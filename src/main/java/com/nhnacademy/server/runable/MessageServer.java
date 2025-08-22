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

import com.nhnacademy.server.thread.channel.MethodJob;
import com.nhnacademy.server.thread.channel.RequestChannel;
import com.nhnacademy.server.thread.pool.RequestHandler;
import com.nhnacademy.server.thread.pool.WorkerThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class MessageServer implements Runnable {
    private static final int DEFAULT_PORT=8888;
    private final int port;
    private final ServerSocket serverSocket;
    private final WorkerThreadPool workerThreadPool;
    private final RequestChannel requestChannel;

    //TODO#1-1 - client Socket을 저장할 map 저장소를, ConcurrentHashMap을 이용해서 생성 합니다.
    private static final Map<String,Socket> clientMap = new ConcurrentHashMap<>();

    public MessageServer(){
        this(DEFAULT_PORT);
    }

    public MessageServer(int port) {
        if(port <= 0){
            throw new IllegalArgumentException(String.format("port:%d",port));
        }

        this.port = port;

        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        requestChannel = new RequestChannel();
        workerThreadPool = new WorkerThreadPool(new RequestHandler(requestChannel));
    }

    @Override
    public void run() {
        //thread pool start
        workerThreadPool.start();

        while(true) {
            try{
                Socket client = serverSocket.accept();
                requestChannel.addJob(new MethodJob(client));
            }catch (Exception e){
                log.debug("{}",e.getMessage(),e);
            }
        }
    }//end method

    public static boolean addClient(String id, Socket client){
        //TODO#1-2, clientMap에 id가 이미 존재하면 로그를 출력하고, false를 반환 합니다.
        if (clientMap.containsKey(id)) {
            log.debug("id:{}, aready exist client socket!", id);
        }

        //TODO#1-3 id, client(Socket)을 map에 등록 하고 true를 반환 합니다.
        clientMap.put(id, client);
        return true;
    }

    public static List<String> getClientIds(){
        //TODO#1-4 clientMap의 key(id)를 List<String>으로 반환 합니다.
        return new ArrayList<>(clientMap.keySet());
    }

    public static Socket getClientSocket(String id){
        //TODO#1-5 id를 이용해서 Client Socket을 반환 합니다.
        return clientMap.get(id);
    }

    public static void removeClient(String id){
        //TODO#1-6 id를 이용해서 clientMap에서 client Socket을 제거 합니다.
        clientMap.remove(id);
    }
}