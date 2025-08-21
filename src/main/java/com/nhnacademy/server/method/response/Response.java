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

public interface Response {

    String getMethod();

    String execute(String value);

    // TODO [insub] method에 따라서 실행되어야 할 Response 구현체를 식별 하는 방법을 이렇게
    default boolean validate(String method) {
        /*
         * TODO#1-7 Response interface를 구현한 구현체(EchoResponse)의 method가 parameter로 전달되는 method와 일치하면 true를 반환 합니다.
         * - method에 따라서 실행되어야 할 Response 구현체를 식별 합니다.
         * - ex) method 'echo' 인 response를 검증하기 위해서는 echoResponse.validate("echo") ==
         * true
         * - getMethod()를 이용해서 구현하세요.
         */
        return getMethod().equals(method);
    }

}
