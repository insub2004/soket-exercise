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

package com.nhnacademy.server.method.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
@Slf4j
public class MethodParser {

    public static MethodAndValue parse(String message) {

        /*
         * TODO#1-1 client로 부터 전송되는 message를 method / value로 분리 합니다.
         * - ex)echo hello -> method : echo , value : hello
         * - ex)echo -> method : echo , value:""
         * - message "" or null 이면 null을 반환 합니다.
         * - 파싱한 결과는 MethodAndValue로 반환 합니다.
         */
        if (StringUtils.isEmpty(message)) {
            return null;
        }

        String[] strings = message.split(" ");
        if (strings.length > 0) {
            if (strings.length == 1) {
                return new MethodAndValue(strings[0], "");
            }
            log.debug("method = {}, value = {}", strings[0], strings[1]);
            return new MethodAndValue(strings[0], strings[1]);
        }
        return null;
    }

    public static class MethodAndValue {
        private final String method;
        private final String value;

        public MethodAndValue(String method, String value) {
            // TODO#1-2 초기화 합니다.
            this.method = method;
            this.value = value;
        }

        public String getMethod() {
            // TODO#1-3 method 반환 하빈다.
            return method;
        }

        public String getValue() {
            // TODO#1-4 value 반환 하빈다.
            return value;
        }
    }
}