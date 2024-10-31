package com.ufal.arapiraca.gerenciadormusicas.janela;

import javax.swing.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField(15);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);

        JButton btnLogin = new JButton("Login");
        JButton btnVoltar = new JButton("Voltar");

        btnLogin.addActionListener(e -> {
            String cpf = txtCpf.getText();
            String senha = new String(txtSenha.getPassword());
            System.out.println("Usuário logado com sucesso!");
        });

        btnVoltar.addActionListener(e -> mainFrame.showMenu());

        add(lblCpf);
        add(txtCpf);
        add(lblSenha);
        add(txtSenha);
        add(btnLogin);
        add(btnVoltar);
    }
}
