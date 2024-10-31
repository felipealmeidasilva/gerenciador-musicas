package com.ufal.arapiraca.gerenciadormusicas.janela;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel menuPanel;

    public MainFrame() {
        setTitle("Gerenciador de Músicas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // Painel de Menu Principal
        menuPanel = new JPanel(new GridLayout(3, 1));
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLogin = new JButton("Login");
        JButton btnSair = new JButton("Sair");

        // Adicionando componentes ao painel
        menuPanel.add(btnCadastrar);
        menuPanel.add(btnLogin);
        menuPanel.add(btnSair);

        // Adicionando painel ao Frame
        add(menuPanel, "Menu");

        // Painéis de cadastro e login
        CadastroPanel cadastroPanel = new CadastroPanel(this);
        LoginPanel loginPanel = new LoginPanel(this);

        // Alternando telas
        btnCadastrar.addActionListener(e -> switchPanel(cadastroPanel));
        btnLogin.addActionListener(e -> switchPanel(loginPanel));
        btnSair.addActionListener(e -> System.exit(0));
    }

    // Método genérico para alternar os painéis
    public void switchPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public void showMenu() {
        switchPanel(menuPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
