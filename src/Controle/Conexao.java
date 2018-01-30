
package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Conexao {
    
    private String url;
    private String driver;
    private String user;
    private String pws;
    
    public Connection con = null;

  
    
    //este método retorna uma conexao
    public Connection conecta(){

        //caminho do localhost + nome do banco        
        url = "jdbc:mysql://localhost:3306/hospital";
        driver  = "com.mysql.jdbc.Driver"; // driver de conexao com o banco
        user = "root"; 
        pws = "admin_021";
        
           try {
                //cria a conexao com o banco e atribui a con
                Class.forName(driver);
                con = DriverManager.getConnection(url,user,pws);
                System.out.println("Conectado com sucesso");
                
            } catch (ClassNotFoundException Driver) {
                JOptionPane.showMessageDialog(null, "Erro não foi possível achar o Driver de conexão "+Driver);
                
            } catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Erro não foi possivel conectar ao DB "+erro);
            }        
            return con;
           
    }
    

       
        
       
    } 
  

    


    

