package com.daelim.view;

import com.daelim.chat.ChatDAO;
import com.daelim.chat.Client;
import com.daelim.chat.MessageHandler;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.daelim.view.Login.nick;


public class Chat extends JPanel {
    MyChat jf;
    static JTextArea chatTa;
    static Client c;

    ChatDAO c_dao = new ChatDAO();
    List<String> list = c_dao.select();

    public Chat(MyChat myChat) {
        c = new Client("ws://61.83.168.88:4877", new MessageHandler() {
            @Override
            public void handleMessage(String message) {
                Chat.chatTa.append(message);
            }
        });
        c.start();
        jf = myChat;

        setLayout(null);
        JScrollPane jScrollPane = new JScrollPane();
        chatTa = new JTextArea();
        chatTa.setEditable(false);
        JTextField chatTf = new JTextField();
        JButton chatSendBt = new JButton("보내기");
        JButton chatExitBt = new JButton("나가기");

        chatTa.setBounds(50, 50, 500, 250);
        chatTf.setBounds(50, 300, 300, 100);
        chatSendBt.setBounds(350, 300, 200, 50);
        chatExitBt.setBounds(350, 350, 200, 50);
        jScrollPane.setViewportView(chatTa);
        jScrollPane.setBounds(50, 50, 500, 250);

        for(int i=0; i<list.size();i++) {
            String str = list.get(i);
            chatTa.append(str);
        }

        chatSendBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( !chatTf.getText().equals("")){
                    JSONObject jsono = new JSONObject();
                    jsono.put("name", nick);
                    jsono.put("data", chatTf.getText());
                    c.sendMsg(jsono.toString());
                }
                chatTf.setText("");
                chatTf.requestFocus();
                chatTa.setCaretPosition(chatTa.getDocument().getLength());  // 맨아래로 스크롤한다.

            }
        });

        chatExitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.end();
                jf.getContentPane().removeAll();
                jf.getContentPane().add(new Login(jf));
                jf.revalidate();
                jf.repaint();
                System.out.println("로그인 페이지로 이동");
            }
        });

        this.add(jScrollPane);
        this.add(chatTf);
        this.add(chatSendBt);
        this.add(chatExitBt);
    }
}
