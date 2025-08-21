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

package com.nhnacademy.server.method.response.impl;

import com.nhnacademy.server.method.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class TimeResponseTest {

    Response response;
    @BeforeEach
    void setUp(){
        response = new TimeResponse();
    }

    @Test
    void getMethod() {
        //TODO#5 getMethod()를 호출 후 time과 일치하는지 검증하세요
        Assertions.assertEquals("time",response.getMethod());
    }

    @Test
    void validate(){
        //TODO#6 response.validate()를 검증하세요
        Assertions.assertTrue(response.validate("time"));
    }

    @Test
    @DisplayName("pattern : yyyy")
    void execute1() {
        String pattern="yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String actual = LocalDateTime.now().format(dateTimeFormatter);
        log.debug("pattern:{},actual:{}",pattern,actual);
        Assertions.assertEquals(actual, response.execute(pattern));
    }

    @Test
    @DisplayName("pattern : yyyy-MM-dd")
    void execute2() {
        //TODO#7 execute1() 테스트를 기반으로 "yyyy-MM-dd" 검증하는 코드를 작성하세요
        String pattern="yyyy-MM-dd";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String actual = LocalDateTime.now().format(dateTimeFormatter);
        log.debug("pattern:{},actual:{}",pattern,actual);
        Assertions.assertEquals(actual, response.execute(pattern));
    }

    @Test
    @DisplayName("pattern : yyyy-MM-dd HH:mm")
    void execute3() {
        //TODO#8 execute1() 테스트를 기반으로 "yyyy-MM-dd HH:mm" 검증하는 코드를 작성하세요.
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String actual = LocalDateTime.now().format(dateTimeFormatter);
        log.debug("pattern:{},actual:{}",pattern,actual);
        Assertions.assertEquals(actual, response.execute(pattern));
    }
}