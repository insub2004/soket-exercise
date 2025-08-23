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

import com.nhnacademy.server.method.response.impl.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>(){{
        add(new EchoResponse());
        add(new PortResponse());
        add(new TimeResponse());
        add(new LoginResponse());
        //TODO#2-1 BroadCastResponse()를 추가하세요.
        add(new BroadCastResponse());
    }};

    public static Response getResponse(String method){
        Response response = responseList.stream()
                .filter(o->o.validate(method))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(response)){
            log.error("response not found : {}",method);
        }
        return response;
    }
}
