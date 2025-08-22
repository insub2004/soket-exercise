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

package com.nhnacademy.client.event.action.impl;

import com.nhnacademy.client.event.action.MessageAction;

import java.io.PrintWriter;

// executable 같은 앤가?
public class SendMessageAction implements MessageAction {
    private final PrintWriter printWriter;

    public SendMessageAction(PrintWriter printWriter) {
        //TODO#1-11 message 전송하기 위해서 printWriter를 사용 합니다. 초기화 해주세요
        this.printWriter = printWriter;
    }

    @Override
    public void execute(String message) {
        //TODO#1-12 printWriter를 이용해서 client ->  server로 message를 전송 합니다.
        printWriter.println(message);
    }
}
