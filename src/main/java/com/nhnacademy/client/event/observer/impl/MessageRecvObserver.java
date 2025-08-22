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

package com.nhnacademy.client.event.observer.impl;

import com.nhnacademy.client.event.action.MessageAction;
import com.nhnacademy.client.event.observer.Observer;
import com.nhnacademy.client.event.subject.EventType;

import java.util.Objects;

public class MessageRecvObserver implements Observer {
    private final MessageAction messageAction;

    public MessageRecvObserver(MessageAction messageAction) {
        //TODO#1-8 MessageRecvObserver초기화 합니다.
        if(Objects.isNull(messageAction)){
            throw new IllegalArgumentException("messageAction is null");
        }
        this.messageAction = messageAction;
    }

    @Override
    public EventType getEventType() {
        //TODO#1-9 EventType.RECV 반환 합니다.
        return EventType.RECV;
    }

    @Override
    public void updateMessage(String message) {
        //TODO#1-10 실질적인 recv event에ㅔ 대한 처리를 담당하는 messageAction.execute() method를 호출 합니다.
        messageAction.execute(message);
    }

}
