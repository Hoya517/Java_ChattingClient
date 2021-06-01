package com.daelim.view;

import javax.swing.*;

public class MyChat extends JFrame {
    public JFrame jf;
    Login jp_login;

    public MyChat() {
        jf = this;

        setTitle("MyChat");
        jp_login = new Login(this);

        add(jp_login);

        setSize(600, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
