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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResponseFactoryTest {

    @Test
    @DisplayName("getResponse By method")
    void getResponse() {
        Response response = ResponseFactory.getResponse("echo");
        // TODO#1-18 response가 EchoResponse의 구현체인지 검증 합니다.
        Assertions.assertInstanceOf(EchoResponse.class, response);
    }

    @Test
    @DisplayName("getResponse by something")
    void getResponseByNotExistMethodName() {
        // TODO#1-19 존재하지 않는 response를 요청했을 때 ResponseNotFoundException가 발생하는지 검증 합니다.
        Assertions.assertThrows(ResponseNotFoundException.class,()->{
            ResponseFactory.getResponse("something");
        });
    }
}