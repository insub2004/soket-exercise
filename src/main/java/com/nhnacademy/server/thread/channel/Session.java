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

import org.apache.commons.lang3.StringUtils;

import java.net.Socket;
import java.util.Objects;

public class Session {
    //TODO#2-1 current thread에서 사용하고 있는 client Socket을 저장하기 위해서 currentSocketLocal 변수를 초기화 합니다.
    private static final ThreadLocal<Socket> currentSocketLocal = new ThreadLocal<>();

    //TODO#2-2 current thread의 client id를 저장하기 위해서 currentIdLocal 변수를 초기화 합니다.
    private static final ThreadLocal<String> currentIdLocal = new ThreadLocal<>();

    public static void initializeSocket(Socket socket){
        //TODO#2-3 current thread 에 연결되어 있는 client Socket을 currentSocketLocal 변수에 설정 합니다.
        currentSocketLocal.set(socket);
    }

    public static void initializeId(String id){
        //TODO#2-4 current thread에 연결되어 있는 client id를 currentIdLocal 변수에 설정 합니다.
        currentIdLocal.set(id);
    }

    public static void reset(){
        //TODO#2-5 currentIdLocal, currentSocketLocal 초기화 합니다.
        currentSocketLocal.remove();
        currentIdLocal.remove();
    }

    public static Socket getCurrentSocket(){
        //TODO#2-6 current thread에 연결되어 있는 cleint Socket을 반환 합니다.
        return currentSocketLocal.get();
    }

    public static String getCurrentId(){
        //TODO#2-6 current thread에 연결되어 있는 cleint id를 반환 합니다.
        return getCurrentId();
    }

    public static boolean isLogin(){
        //TODO#2-7 로그인 여부를 반환 합니다.
        return Objects.nonNull(currentIdLocal.get());
    }
}
