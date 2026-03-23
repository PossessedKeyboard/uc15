
package UTIL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author DELL
 */
public class Conexao {
    Connection conn;
    
    public static Connection conectar(){
    Connection conn = null;
    try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc15","root","fr33B1RD");
    }catch(ClassNotFoundException| SQLException erro){
    JOptionPane.showMessageDialog(null,"Ocorreu o seguinte erro: " +erro.getMessage());
    }
    return conn;
    }

    public static void desconectar(Connection conn){
    try{conn.close();}catch(SQLException erro){
    //JOptionPane.showMessageDialog(""+erro);
    }
    }
}
    
