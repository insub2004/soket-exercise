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

package com.nhnacademy.server.method.response;

import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.method.response.impl.EchoResponse;

import java.util.ArrayList;
import java.util.Objects;

public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>() {
        {
            // TODO#1-8 EchoResponse 객체를 성성해서 추가 합니다.
            // 인스턴스 초기화 블럭
            // 1. new ArrayList<>() { ... }로 익명 하위 클래스 정의 + 인스턴스 생성
            // 2. super()(= ArrayList 생성자) 실행
            // 3. 인스턴스 이니셜라이저 블록 실행 → add(new EchoResponse())
            // 4. 생성 완료 → responseList에 그 객체가 대입
            add(new EchoResponse());
        }
    };

    public static Response getResponse(String method) {
        /*
         * TODO#1-9 responseList에서 parameter로 전달된 method에 해당된 구현체를 반환 합니다.
         * response가 존재하지 않다면 ResponseNotFoundException을 발생합니다.
         */
        /*
        for (Response o : responseList) {
            if (o.validate(method)) {
                return o;
            }
        }
        throw new ResponseNotFoundException();
        */
        Response response = responseList.stream()
                .filter(o->o.validate(method))
                .findFirst()
                .orElse(null);
        if(Objects.isNull(response)){
            throw new ResponseNotFoundException();
        }
        return response;
    }
}
