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
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PortResponse implements Response {
    private final static String METHOD = "port";

    @Override
    public String getMethod() {
        return METHOD;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String execute(String value) {
        /* TODO#1 OS로부터 오픈되어 있는 Prot를 조회후 반환 합니다.
         - value(port) 에 값이 존재 하지 않는다면 열려있는 모든 port를 반환 합니다.
         - value(port) 값이 존재 한다면 해당 port에 해당되는 값을 반환 합니다.
         - 다음과 같은 형식으로 반환 됩니다.
        TCP *:49742
        TCP *:49742
        TCP *:7000
        TCP *:7000
        TCP *:5000
        TCP *:5000
        TCP *:7797
        TCP *:7797
        TCP *:55920
        TCP 127.0.0.1:16105
        TCP 127.0.0.1:16115
        TCP 127.0.0.1:16107
        TCP 127.0.0.1:16117
        TCP *:19875
        TCP 127.0.0.1:19876
        TCP 127.0.0.1:63342
        TCP 127.0.0.1:52304
        TCP *:8888
        TCP 127.0.0.1:64120
        TCP 127.0.0.1:3376
        TCP 127.0.0.1:62451
        TCP 127.0.0.1:64913
        */
        
        /*
            TODO [insub] 전체 출력해보면
Notes       672 insub   48u  IPv6 0x299135768e686a18      0t0  TCP [2001:2d8:2024:d735:3d33:b06:d352:7bf8]:53136->[64:ff9b::1139:9823]:imaps (ESTABLISHED)
Notes       672 insub   49u  IPv6  0x3ad2f8c046ed7e1      0t0  TCP [2001:2d8:2024:d735:3d33:b06:d352:7bf8]:53138->[64:ff9b::1139:9823]:imaps (ESTABLISHED)
Postman     687 insub   81u  IPv6 0x46b6f67057f1d0fa      0t0  TCP *:15611 (LISTEN)
ControlCe   691 insub    8u  IPv4 0x8e4bdd5aa45b5bf0      0t0  TCP *:afs3-fileserver (LISTEN) 
         */
        StringBuilder sb = new StringBuilder();
        Map<String, String> resultMap = new HashMap<>();
        String line = "";
        try {
            Process process = Runtime.getRuntime().exec("lsof -n -i");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bufferedReader.readLine(); // 한 줄 버리기
            while ((line=bufferedReader.readLine()) != null) {
                if (line.contains("LISTEN")) {
                    String[] strings = line.trim().split("\\s+");
                    String protocal = strings[7];
                    String port = strings[8];
                    String result = String.format("%s %s %s", protocal, port, System.lineSeparator());
                    log.debug("result:{}",result);
                    if(StringUtils.isEmpty(value)){
                        sb.append(result);
                    }else if(StringUtils.isNotEmpty(value) && port.contains(value)){
                        sb.append(result);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
