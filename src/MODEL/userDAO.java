/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import VIEW.cadLeitor;
import VIEW.cadBiblio;
import MODEL.userDTO;
import UTIL.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
/**
 *
 * @author DELL
 */
public class userDAO {
    String username;
    PreparedStatement st;
    
       /* public String criarUser (String nome, String sobrenome){
        username = nome + sobrenome;
        return username;
        }*/
    
        public int salvarUser1(userDTO user){
        int status;
        
        try {
            Conexao conexao = new Conexao();
            Connection conn = Conexao.conectar();
            
            st = conn.prepareStatement("INSERT INTO usuario(username,nome,sobrenome,nascimento,genero,CPF,tel,email,endereço,senha_SHA2,perfil) VALUES(?,?,?,?,?,?,?,?,?,SHA2(?,256),?)");
            st.setString(1,user.getUsername());
            st.setString(2,user.getNome());
            st.setString(3,user.getSobrenome());
            st.setDate(4, new java.sql.Date(user.getNascimento().getTime()));
            st.setString(5,user.getGenero());
            st.setString(6,user.getCPF());
            st.setString(7,user.getTel());
            st.setString(8,user.getEmail());
            st.setString(9,user.getEndereco());
            st.setString(10,user.getSenha());
            st.setString(11,"LEITOR");
            
            status = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário cadastrado!");
            return status; 
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados.");
            return ex.getErrorCode();
        }
    }    
    
    public int salvarUser2(userDTO user){
        int status;
        
        try {
            Conexao conexao = new Conexao();
            Connection conn = Conexao.conectar();
            
            st = conn.prepareStatement("INSERT INTO usuario(username,nome,sobrenome,nascimento,genero,CPF,tel,email,endereço,senha_SHA2,perfil) VALUES(?,?,?,?,?,?,?,?,?,SHA2(?,256),?)");
            st.setString(1,user.getUsername());
            st.setString(2,user.getNome());
            st.setString(3,user.getSobrenome());
            st.setDate(4, new java.sql.Date(user.getNascimento().getTime()));
            st.setString(5,user.getGenero());
            st.setString(6,user.getCPF());
            st.setString(7,user.getTel());
            st.setString(8,user.getEmail());
            st.setString(9,user.getEndereco());
            st.setString(10,user.getSenha());
            st.setString(11,"GESTOR");
            
            status = st.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário cadastrado!");
            return status; 
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados.");
            return ex.getErrorCode();
        }
    }    
    
}
