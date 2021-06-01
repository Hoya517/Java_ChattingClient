package com.daelim.view;

import com.daelim.member.MemberDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    MyChat jf;
    static String nick;

    public Login(MyChat myChat) {
        jf = myChat;
        MemberDAO m_dao = new MemberDAO();

        setLayout(null);

        JLabel loginIdLb = new JLabel("아이디: ");
        JTextField loginIdTf = new JTextField();
        JLabel loginPwLb = new JLabel("비밀번호: ");
        JPasswordField loginPwTf = new JPasswordField();
        JButton loginLoginBt = new JButton("로그인");
        JButton loginJoinBt = new JButton("회원가입");

        loginIdLb.setBounds(150,100,100,50);
        loginIdTf.setBounds(250,100,200,50);
        loginPwLb.setBounds(150,150,100,50);
        loginPwTf.setBounds(250,150,200,50);
        loginLoginBt.setBounds(150,250,150,50);
        loginJoinBt.setBounds(300,250,150,50);
        loginLoginBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = loginIdTf.getText();
                String password = loginPwTf.getText();
                int result = m_dao.isLoginCheck(id, password);

                if (result == 1) {

                    jf.getContentPane().removeAll();
                    jf.getContentPane().add(new Chat(jf));
                    jf.revalidate();
                    jf.repaint();
                    System.out.println("로그인 성공");

                    nick = m_dao.getNickname(id);
                    System.out.println(nick);
                } else {
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요.");
                    System.out.println("로그인 실패");
                }
            }
        });

        loginJoinBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.getContentPane().removeAll();
                jf.getContentPane().add(new Join(jf));
                jf.revalidate();
                jf.repaint();
                System.out.println("회원가입 페이지로 이동");
            }
        });

        this.add(loginIdLb);
        this.add(loginIdTf);
        this.add(loginPwLb);
        this.add(loginPwTf);
        this.add(loginLoginBt);
        this.add(loginJoinBt);
    }

}
