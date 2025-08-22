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

package com.nhnacademy.client.ui.listener;

import com.nhnacademy.client.ui.form.MessageClientForm;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class SendButtonEventListener implements ActionListener {
    private final MessageClientForm messageClientForm;

    public SendButtonEventListener(MessageClientForm messageClientForm) {
        //TODO#3-4-1 기본 생성자를 초기화 합니다.
        this.messageClientForm = messageClientForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.debug("button actionCommand:{}",e.getActionCommand());
        /*TODO#3-4-2 messageClientForm.getSubject().sendMessage()를 이용해서 messageClientForm.getInputField().getText()를 보냅니다.
            - 즉, 메시지 전송 이벤트를 발생 시킴니다.
         */
        // TODO [insub] sendMessage(..) -> notifyObservers(EventType.SEND,message);
        messageClientForm.getSubject().sendMessage(messageClientForm.getInputField().getText());

        //TODO#3-4-3 messageClientForm.getInputField()의 text를 ""로 초기화 합니다.
        messageClientForm.getInputField().setText("");
    }
}
