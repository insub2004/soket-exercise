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

package com.nhnacademy.client.event.subject;

import com.nhnacademy.client.event.observer.Observer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO [insub] Observer patter (Subject) -> 옵저버를 등록, 삭제, 옵저버에게 상태변경 알림
public class MessageSubject implements Subject {

    private final List<Observer> observers;

    public MessageSubject() {
        //TODO#1-1 Observer를 등록한 thread list를 생성 합니다. thread safety 해야 합니다.
        observers = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void register(EventType eventType, Observer observer) {
        //TODO#1-2 observers에 observer를 등록 합니다.
        observers.add(observer);
    }

    @Override
    public void remove(EventType eventType, Observer observer) {
        //TODO#1-3 observers 에서 observer를 삭제 합니다.
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventType eventType, String message) {
        /*
            TODO#1-4 모든 observer들에게  알림니다.
           - method type을 고려해야 합니다. SEND,RECV, observer.validate()를 사용하여 검증 합니다.
           - observer.updateMessage(message)를 호출 합니다.
        */
        for(Observer observer : observers){
            if(observer.validate(eventType)) {
                observer.updateMessage(message);
            }
        }
    }
}
