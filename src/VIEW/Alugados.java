package VIEW;

import MODEL.aluguelDAO;
import MODEL.aluguelDTO;
import UTIL.Session;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Alugados extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(Alugados.class.getName());

    public Alugados() {
        initComponents();
        carregarAlugueis();
        aplicarRendererVermelho();
    }

    // ── Decide qual método do DAO chamar conforme o perfil ───────────────────
    private void carregarAlugueis() {
        aluguelDAO dao = new aluguelDAO();
        List<aluguelDTO> lista;

        String perfil = Session.getPerfil();

        if ("LEITOR".equals(perfil)) {
            lista = dao.listarAlugueisDoLeitor(Session.getIdUsuario());
        } else {
            // GESTOR e ADMIN veem todos os aluguéis
            lista = dao.listarTodosAlugueis();
        }

        DefaultTableModel model = (DefaultTableModel) Alugados.getModel();
        model.setRowCount(0);

        if ("LEITOR".equals(perfil)) {
    model.setColumnCount(0);
    model.addColumn("Título");
    model.addColumn("Empréstimo");
    model.addColumn("Devolução");
} else {
    model.setColumnCount(0);
    model.addColumn("Leitor");
    model.addColumn("Título");
    model.addColumn("Empréstimo");
    model.addColumn("Devolução");
}
        
        for (aluguelDTO a : lista) {
            if ("LEITOR".equals(perfil)) {
                model.addRow(new Object[]{
                    a.getTituloLivro(),
                    a.getDataEmprestimo(),
                    a.getDataDevolucao()
                });
            } else {
                model.addRow(new Object[]{
                    a.getNomeLeitor(),
                    a.getTituloLivro(),
                    a.getDataEmprestimo(),
                    a.getDataDevolucao()
                });
            }
        }

        // Guarda a lista para o renderer acessar isAtrasado()
        this.listaAlugueis = lista;
    }

    // ── Renderer: pinta a linha de vermelho se o prazo passou ────────────────
    private void aplicarRendererVermelho() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (listaAlugueis != null && row < listaAlugueis.size()) {
                    aluguelDTO aluguel = listaAlugueis.get(row);
                    if (aluguel.isAtrasado()) {
                        setBackground(new Color(255, 102, 102)); // vermelho
                        setForeground(Color.WHITE);
                        return this;
                    }
                }

                setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                return this;
            }
        };

        for (int i = 0; i < Alugados.getColumnCount(); i++) {
            Alugados.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    // Lista mantida em memória para o renderer consultar isAtrasado()
    private List<aluguelDTO> listaAlugueis;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Alugados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Alugados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Leitor", "Livro", "Prazo"
            }
        ));
        jScrollPane1.setViewportView(Alugados);

        jButton1.setText("Voltar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Alugados().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Alugados;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
