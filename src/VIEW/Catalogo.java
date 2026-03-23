package VIEW;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import MODEL.livroDAO;
import MODEL.livroDTO;
import UTIL.Session;
import javax.swing.JOptionPane;

public class Catalogo extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(Catalogo.class.getName());

    public Catalogo() {
        initComponents();
        carregarTabela();
        aplicarRenderer();
        if (!"LEITOR".equals(Session.getPerfil())) {
    jButton2.setVisible(false);
}
    }

    // ── Carrega a tabela de acordo com o perfil ──────────────────────────────
    private void carregarTabela() {
        livroDAO dao = new livroDAO();
        List<livroDTO> livros;

        String perfil = Session.getPerfil();

        // LEITOR só vê livros disponíveis; GESTOR e ADMIN veem tudo
        if ("LEITOR".equals(perfil)) {
            livros = dao.listarDisponiveis();
        } else {
            livros = dao.buscarTitulo(""); // todos
        }

        preencherModelo(livros);
    }

    private void preencherModelo(List<livroDTO> livros) {
        DefaultTableModel model = (DefaultTableModel) Catalogo.getModel();
        model.setRowCount(0);

        for (livroDTO f : livros) {
            model.addRow(new Object[]{
                f.getID(),
                f.getTitulo(),
                f.getGenero(),
                f.getAutor(),
                f.getEditora(),
                f.getAno(),
                f.getQtde()
            });
        }
    }

    // ── Renderer: pinta cinza e itálico quando qtde == 0 ─────────────────────
    private void aplicarRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                Object qtdeObj = table.getModel().getValueAt(row, 6); // coluna Unidades
                int qtde = (qtdeObj instanceof Integer) ? (Integer) qtdeObj : 0;

                if (qtde == 0) {
                    setBackground(new Color(200, 200, 200)); // cinza
                    setForeground(Color.GRAY);
                } else {
                    setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                    setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                }
                return this;
            }
        };

        for (int i = 0; i < Catalogo.getColumnCount(); i++) {
            Catalogo.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    // ── Busca com filtro por tipo ─────────────────────────────────────────────
    private void buscarLivros() {
        String termo = jTextField1.getText().trim();
        String tipo  = String.valueOf(jComboBox1.getSelectedItem());
        livroDAO dao = new livroDAO();
        List<livroDTO> livros;

        switch (tipo) {
            case "Título":   livros = dao.buscarTitulo(termo);  break;
            case "Autor":    livros = dao.buscarAutor(termo);   break;
            case "Gênero":   livros = dao.buscarGenero(termo);  break;
            default:         livros = dao.buscarTitulo(termo);  break;
        }

        preencherModelo(livros);
        aplicarRenderer();
    }

    // ── Ação do botão Alugar ──────────────────────────────────────────────────
    private void alugarSelecionado() {
        int linhaSelecionada = Catalogo.getSelectedRow();

        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um livro na tabela.");
            return;
        }

        Object qtdeObj = Catalogo.getModel().getValueAt(linhaSelecionada, 6);
        int qtde = (qtdeObj instanceof Integer) ? (Integer) qtdeObj : 0;

        if (qtde == 0) {
            JOptionPane.showMessageDialog(null, "Este livro está indisponível.");
            return;
        }

        Object idObj = Catalogo.getModel().getValueAt(linhaSelecionada, 0);
        int idLivro  = (idObj instanceof Integer) ? (Integer) idObj : 0;
        int idLeitor = Session.getIdUsuario();

        livroDAO dao = new livroDAO();
        dao.alugarLivro(idLivro, idLeitor);

        // Atualiza a tabela imediatamente após o aluguel
        carregarTabela();
        aplicarRenderer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Catalogo = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Catalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Gênero", "Autor", "Editora", "Ano", "Unidades"
            }
        ));
        jScrollPane1.setViewportView(Catalogo);

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setText("Buscar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Alugar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Título", "Gênero", "Autor", " " }));

        jLabel1.setText("Buscar por:");

        jButton3.setText("Voltar");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarLivros();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        alugarSelecionado();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Catalogo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Catalogo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
