/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import UTIL.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class aluguelDAO {
     // -------------------------------------------------------
    // Usado pelo LEITOR: lista apenas os aluguéis do próprio
    // -------------------------------------------------------
    public List<aluguelDTO> listarAlugueisDoLeitor(int idLeitor) {
 
        List<aluguelDTO> lista = new ArrayList<>();
 
        String sql =
            "SELECT a.id, a.idlivro, a.idleitor, " +
            "       l.titulo, " +
            "       a.data_emprestimo, a.data_devolucao " +
            "FROM Aluguel a " +
            "JOIN Livro   l ON a.idlivro  = l.id " +
            "WHERE a.idleitor = ? " +
            "ORDER BY a.data_devolucao ASC";
 
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idLeitor);
            ResultSet rs = st.executeQuery();
 
            while (rs.next()) {
                aluguelDTO a = new aluguelDTO();
                a.setId(rs.getInt("id"));
                a.setIdLivro(rs.getInt("idlivro"));
                a.setIdLeitor(rs.getInt("idleitor"));
                a.setTituloLivro(rs.getString("titulo"));
                a.setDataEmprestimo(rs.getDate("data_emprestimo"));
                a.setDataDevolucao(rs.getDate("data_devolucao"));
                lista.add(a);
            }
 
            rs.close();
            st.close();
            conn.close();
 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar aluguéis: " + e.getMessage());
        }
 
        return lista;
    }
 
    // -------------------------------------------------------
    // Usado pelo GESTOR / ADMIN: lista todos os aluguéis
    // com nome do leitor e título do livro
    // -------------------------------------------------------
    public List<aluguelDTO> listarTodosAlugueis() {
 
        List<aluguelDTO> lista = new ArrayList<>();
 
        String sql =
            "SELECT a.id, a.idlivro, a.idleitor, " +
            "       CONCAT(u.nome, ' ', u.sobrenome) AS nomeLeitor, " +
            "       l.titulo, " +
            "       a.data_emprestimo, a.data_devolucao " +
            "FROM Aluguel  a " +
            "JOIN Livro    l ON a.idlivro  = l.id " +
            "JOIN Usuario  u ON a.idleitor = u.id " +
            "ORDER BY a.data_devolucao ASC";
 
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
 
            while (rs.next()) {
                aluguelDTO a = new aluguelDTO();
                a.setId(rs.getInt("id"));
                a.setIdLivro(rs.getInt("idlivro"));
                a.setIdLeitor(rs.getInt("idleitor"));
                a.setNomeLeitor(rs.getString("nomeLeitor"));
                a.setTituloLivro(rs.getString("titulo"));
                a.setDataEmprestimo(rs.getDate("data_emprestimo"));
                a.setDataDevolucao(rs.getDate("data_devolucao"));
                lista.add(a);
            }
 
            rs.close();
            st.close();
            conn.close();
 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar aluguéis: " + e.getMessage());
        }
 
        return lista;
    }
}
