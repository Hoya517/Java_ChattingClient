package com.daelim.view;

import com.daelim.member.MemberDAO;
import com.daelim.member.MemberVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join extends JPanel {
    MyChat jf;

    public Join(MyChat myChat) {
        jf = myChat;
        MemberDAO m_dao = new MemberDAO();
        MemberVO m_vo = new MemberVO();

        setLayout(null);

        JLabel joinIdLb = new JLabel("아이디: ");
        JLabel joinPwLb = new JLabel("비밀번호: ");
        JLabel joinPwCheckLb = new JLabel("비밀번호 확인: ");
        JLabel joinNameLb = new JLabel("이름: ");
        JLabel joinNicknameLb = new JLabel("닉네임: ");
        JLabel joinPhoneLb = new JLabel("전화번호: ");
        JTextField joinIdTf = new JTextField();
        JPasswordField joinPwTf = new JPasswordField();
        JPasswordField joinPwCheckTf = new JPasswordField();
        JTextField joinNameTf = new JTextField();
        JTextField joinNicknameTf = new JTextField();
        JTextField joinPhoneTf = new JTextField();
        JButton joinJoinBt = new JButton("회원가입");
        JButton joinCancleBt = new JButton("취소");
        JButton idCheckBt = new JButton("중복확인");

        joinIdLb.setBounds(150, 50, 100, 50);
        joinPwLb.setBounds(150, 100, 100, 50);
        joinPwCheckLb.setBounds(150, 150, 100, 50);
        joinNameLb.setBounds(150, 200, 100, 50);
        joinNicknameLb.setBounds(150, 250, 100, 50);
        joinPhoneLb.setBounds(150,300,100,50);
        joinIdTf.setBounds(250, 50, 200, 50);
        joinPwTf.setBounds(250, 100, 200, 50);
        joinPwCheckTf.setBounds(250, 150, 200, 50);
        joinNameTf.setBounds(250, 200, 200, 50);
        joinNicknameTf.setBounds(250, 250, 200, 50);
        joinPhoneTf.setBounds(250, 300, 200, 50);
        joinJoinBt.setBounds(150, 350, 150, 50);
        joinCancleBt.setBounds(300, 350, 150, 50);
        idCheckBt.setBounds(450, 50,100,50);

        joinJoinBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = joinIdTf.getText();
                String password = joinPwTf.getText();
                String passwordCheck = joinPwCheckTf.getText();
                String name = joinNameTf.getText();
                String nickname = joinNicknameTf.getText();
                String phone = joinPhoneTf.getText();

                if (!id.equals("") && !password.equals("") && !passwordCheck.equals("") && !name.equals("") && !nickname.equals("") && !phone.equals("")) {
                    if (password.equals(passwordCheck) && m_dao.idCheck(id) == 0) {
                        m_vo.setId(id);
                        m_vo.setPassword(password);
                        m_vo.setName(name);
                        m_vo.setNickname(nickname);
                        m_vo.setPhone(phone);

                        m_dao.insert(m_vo);

                        jf.getContentPane().removeAll();
                        jf.getContentPane().add(new Login(jf));
                        jf.revalidate();
                        jf.repaint();
                        System.out.println("로그인 페이지로 이동");
                    } else if (!password.equals(passwordCheck)) {
                        JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                    } else if (m_dao.idCheck(id) == 1) {
                        JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "빈칸없이 입력해주세요.");
                }
            }
        });

        joinCancleBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.getContentPane().removeAll();
                jf.getContentPane().add(new Login(jf));
                jf.revalidate();
                jf.repaint();
                System.out.println("로그인 페이지로 이동");
            }
        });

        idCheckBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = joinIdTf.getText();
                int result = m_dao.idCheck(id);

                if (result == 0) {
                    JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
                }

            }
        });

        this.add(joinIdLb);
        this.add(joinPwLb);
        this.add(joinPwCheckLb);
        this.add(joinNameLb);
        this.add(joinNicknameLb);
        this.add(joinPhoneLb);
        this.add(joinIdTf);
        this.add(joinPwTf);
        this.add(joinPwCheckTf);
        this.add(joinNameTf);
        this.add(joinNicknameTf);
        this.add(joinPhoneTf);
        this.add(joinJoinBt);
        this.add(joinCancleBt);
        this.add(idCheckBt);

    }
}
