/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import VIEW.cadLivro;
import UTIL.Conexao;



/**
 *
 * @author DELL
 */
public class livroDAO {
    PreparedStatement st;
    
    
    
    
        public int salvarLivro(livroDTO livro){
        int status;
        
        try {
            Conexao conexao = new Conexao();
            Connection conn = Conexao.conectar();
            
            st = conn.prepareStatement("INSERT INTO livro(titulo,genero,autor,editora,ano,qtde) VALUES(?,?,?,?,?,?)");
            st.setString(1,livro.getTitulo());
            st.setString(2,livro.getGenero());
            st.setString(3,livro.getAutor());
            st.setString(4,livro.getEditora());
            st.setInt(5,livro.getAno());
            st.setInt(6,livro.getQtde());
            status = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Livro cadastrado!");
            return status; 
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados.");
            return ex.getErrorCode();
        }
    }
  public List<livroDTO> buscarTitulo(String titulo) {
     List<livroDTO> livros = new ArrayList<>();

    try {
        Conexao conexao = new Conexao();
        Connection conn = Conexao.conectar();
        st = conn.prepareStatement(
            "SELECT * FROM livro WHERE titulo LIKE ?"
        );
        st.setString(1, "%" + titulo + "%");
       ResultSet rs = st.executeQuery();

        while (rs.next()) {
            livroDTO livro = new livroDTO();
            livro.setID(rs.getInt("id")); //botei agora
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
            livro.setGenero(rs.getString("genero"));
            livro.setAno(rs.getInt("ano"));
            livro.setQtde(rs.getInt("qtde"));
          

            livros.add(livro);
        

        }
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar: " + ex.getMessage());
        JOptionPane.showMessageDialog(null,"O livro procurado não existe!");
    }

    return livros;
}       

 public List<livroDTO> buscarAutor(String autor) {
     List<livroDTO> livros = new ArrayList<>();

    try {
        Conexao conexao = new Conexao();
        Connection conn = Conexao.conectar();
        st = conn.prepareStatement(
            "SELECT * FROM livro WHERE autor LIKE ?"
        );
        st.setString(1, "%" + autor + "%");
       ResultSet rs = st.executeQuery();

        while (rs.next()) {
            livroDTO livro = new livroDTO();
            livro.setID(rs.getInt("id")); //botei agora
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
            livro.setGenero(rs.getString("genero"));
            livro.setAno(rs.getInt("ano"));
            livro.setQtde(rs.getInt("qtde"));
          

            livros.add(livro);
        

        }
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar: " + ex.getMessage());
        JOptionPane.showMessageDialog(null,"O livro procurado não existe!");
    }

    return livros;
}       
     
public List<livroDTO> buscarGenero(String genero) {
     List<livroDTO> livros = new ArrayList<>();

    try {
        Conexao conexao = new Conexao();
        Connection conn = Conexao.conectar();
        st = conn.prepareStatement(
            "SELECT * FROM livro WHERE genero LIKE ?"
        );
        st.setString(1, "%" + genero + "%");
       ResultSet rs = st.executeQuery();

        while (rs.next()) {
            livroDTO livro = new livroDTO();
            livro.setID(rs.getInt("id")); //botei agora
            livro.setTitulo(rs.getString("titulo"));
            livro.setGenero(rs.getString("genero"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
            livro.setAno(rs.getInt("ano"));
            livro.setQtde(rs.getInt("qtde"));
          

            livros.add(livro);
        

        }
    } catch (SQLException ex) {
        System.out.println("Erro ao buscar: " + ex.getMessage());
        JOptionPane.showMessageDialog(null,"O livro procurado não existe!");
    }

    return livros;
}       
     
 public void alugarLivro(int idLivro, int idUsuario) {

    try {
        Connection conn = Conexao.conectar();

        PreparedStatement check = conn.prepareStatement(
            "SELECT qtde FROM Livro WHERE id = ?"
        );
        check.setInt(1, idLivro);
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            int qtde = rs.getInt("qtde");

            if (qtde <= 0) {
                JOptionPane.showMessageDialog(null, "Livro sem estoque!");
                return;
            }
        }
PreparedStatement dupCheck = conn.prepareStatement(
    "SELECT COUNT(*) FROM Aluguel WHERE idlivro = ? AND idleitor = ?"
);
dupCheck.setInt(1, idLivro);
dupCheck.setInt(2, idUsuario);
ResultSet rsDup = dupCheck.executeQuery();
if (rsDup.next() && rsDup.getInt(1) > 0) {
    JOptionPane.showMessageDialog(null, "Você já possui este livro alugado.");
    return;
}
        
        PreparedStatement update = conn.prepareStatement(
            "UPDATE Livro SET qtde = qtde - 1 WHERE id = ?"
        );
        update.setInt(1, idLivro);
        update.executeUpdate();

        
        java.util.Date hoje = new java.util.Date();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(hoje);
        cal.add(java.util.Calendar.DAY_OF_MONTH, 7);

        java.util.Date prazo = cal.getTime();

        
        PreparedStatement insert = conn.prepareStatement(
            "INSERT INTO Aluguel (idlivro, idleitor, data_emprestimo, data_devolucao) VALUES (?,?,?,?)"
        );

        insert.setInt(1, idLivro);
        insert.setInt(2, idUsuario);
        insert.setDate(3, new java.sql.Date(hoje.getTime()));
        insert.setDate(4, new java.sql.Date(prazo.getTime()));

        insert.executeUpdate();

        JOptionPane.showMessageDialog(null, "Livro alugado com sucesso!");

    } catch (Exception e) {
        e.printStackTrace();
    }
} 
 
 public List<livroDTO> listarDisponiveis() {

    List<livroDTO> lista = new ArrayList<>();

    try {
        Connection conn = Conexao.conectar();

        PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM Livro WHERE qtde > 0"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            livroDTO l = new livroDTO();
            l.setID(rs.getInt("id"));
            l.setTitulo(rs.getString("titulo"));
            l.setGenero(rs.getString("genero"));
            l.setAutor(rs.getString("autor"));
            l.setEditora(rs.getString("editora"));
            l.setAno(rs.getInt("ano"));
            l.setQtde(rs.getInt("qtde"));

            lista.add(l);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
  
}
