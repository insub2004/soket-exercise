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

package com.nhnacademy.client.ui.form;

import com.nhnacademy.client.event.action.MessageAction;
import com.nhnacademy.client.event.action.impl.RecvMessageAction;
import com.nhnacademy.client.event.observer.Observer;
import com.nhnacademy.client.event.observer.impl.MessageRecvObserver;
import com.nhnacademy.client.event.subject.EventType;
import com.nhnacademy.client.event.subject.Subject;
import com.nhnacademy.client.ui.listener.InputFieldKeyListener;
import com.nhnacademy.client.ui.listener.MessageAreaChangeListener;
import com.nhnacademy.client.ui.listener.SendButtonEventListener;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class MessageClientForm {
    private JPanel panel;
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;
    private JScrollPane scrollPane;

    private final Subject subject;
    public MessageClientForm(Subject subject) {

        this.subject = subject;
        frame = new JFrame();
        panel = new JPanel();
        messageArea = new JTextArea();
        inputField = new JTextField();
        sendButton = new JButton();
        scrollPane = new JScrollPane(messageArea);

        //TODO#3-1 initializeUi()를 호출해서 ui를 초기화 합니다.
        initializeUi();
        //TODO#3-2 configureEvent()를 호출해서 event를 설정 합니다.
        configureEvent();
        //TODO#3-3 configureRecvObserver() 수신 event를 설정 합니다.
        configureRecvObserver();
    }

    public JFrame getFrame() {
        return frame;
    }
    public JPanel getPanel() {
        return panel;
    }
    public JTextArea getMessageArea() {
        return messageArea;
    }
    public JTextField getInputField() {
        return inputField;
    }
    public JButton getSendButton() {
        return sendButton;
    }
    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public Subject getSubject() {
        return subject;
    }

    private void initializeUi(){

        messageArea.setEditable(false);
        //자동 줄바꿈, 가로 스크롤 생김 방지
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setRows(50);
        messageArea.setColumns(100);

        inputField.setColumns(100);

        sendButton.setText("Send");

        panel.setLayout(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.setTitle("Message Client");
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 화면 크기 가져오기
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 프레임 크기 가져오기
        Dimension frameSize = frame.getSize();
        // 프레임을 화면 가운데로 이동
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    private void configureEvent(){

        /*TODO#3-4 sendButton : event, send button을 클릭시 발생하는 이벤트 핸들러를 등록 합니다.
            - event listener : SendButtonEventListener
         */
        sendButton.addActionListener(new SendButtonEventListener(this));

        /*TODO#3-5 inputField : key event, inputbox에 message를 입력 후 enter key를 누르면 메시지가 전송 됩니다.
            -  event listener : InputFieldKeyListener
         */
        inputField.addKeyListener(new InputFieldKeyListener(this));

        /*TODO#3-6 messageArea : text change event, 서버로부터 message를 수신하면 UI에 message가 append 됩니다. 이 때 scroll을 맨 하단으로 이동시켜 UI접근성을 향상시키기 위해서 해당 이벤트를 등록 합니다.
            - event listener : MessageAreaChangeListener
         */
        messageArea.getDocument().addDocumentListener(new MessageAreaChangeListener(this));
    }

    private void configureRecvObserver(){
        /*TODO#TODO#3-3-1 server로 부터 message를 수신하면 MessageRecvObserver에 의해서 messageAction를 호출하여 UI(MessageClientForm)에 반영 합니다.
            - message를 수신한 Observer를 subject에 등록 하세요
            - EventType.RECV 입니다.
         */
        // TODO [insub] RecvMessageAction에서 UI Form을 이용해서 execute로 메시지를 보여줌
        MessageAction messageAction = new RecvMessageAction(this);
        Observer observer = new MessageRecvObserver(messageAction);
        subject.register(EventType.RECV, observer);
    }

    public static void showUI(Subject subject){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //TODO#3-7 MessageClientForm를 생성 합니다.
                new MessageClientForm(subject);
            }
        });
    }
}
