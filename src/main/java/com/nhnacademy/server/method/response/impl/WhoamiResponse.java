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

import ch.qos.logback.core.util.StringUtil;
import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.thread.channel.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.Socket;

@Slf4j
public class WhoamiResponse implements Response {
    @Override
    public String getMethod() {
        //TODO#1-1 method = "whoami" 설정 합니다.
        return "whoami";
    }

    @Override
    public String execute(String value) {
        log.debug("Whoami execute() param : {}", value);
        //TODO#1-2 로그인되어 있지 않다면 "login required!"  반환 합니다.
        if (!Session.isLogin()) {
            return "login required!";
        }
        //TODO#1-3 로그인 되어 있다면 my id is [marco] 형식으로 응답 합니다.
        String id = Session.getCurrentId();
        return "my id is %s".formatted(id);
    }
}
