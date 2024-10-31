package com.ufal.arapiraca.gerenciadormusicas.janela;

import javax.swing.*;
import java.awt.*;

public class CadastroPanel extends JPanel {

    public CadastroPanel(MainFrame mainFrame) {
        setLayout(new GridLayout(7, 2));
        
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        
        JLabel lblIdade = new JLabel("Idade:");
        JTextField txtIdade = new JTextField();
        
        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField();
        
        JLabel lblNacionalidade = new JLabel("Nacionalidade:");
        JTextField txtNacionalidade = new JTextField();
        
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        btnCadastrar.addActionListener(e -> {
            System.out.println("Usuário cadastrado com sucesso!");
        });

        btnVoltar.addActionListener(e -> mainFrame.showMenu());

        add(lblNome); add(txtNome);
        add(lblIdade); add(txtIdade);
        add(lblCpf); add(txtCpf);
        add(lblNacionalidade); add(txtNacionalidade);
        add(lblSenha); add(txtSenha);
        add(btnCadastrar); add(btnVoltar);
    }
}
