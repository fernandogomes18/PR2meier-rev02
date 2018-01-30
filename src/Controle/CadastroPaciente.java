
package Controle;
/**
 *
 * @author Fernando
 */
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CadastroPaciente 
{
    private String cpf;
    private String pnome;
    private String tel;
    private String endereco;
    public Conexao conectar = new Conexao();


    public CadastroPaciente(){
    }


    public String getNome(){
        return pnome;
    }
    public void setNome(String nome){
        this.pnome = nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getTel(){
        return tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    public String getEndereco(){
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void Inserir(){
        PreparedStatement stm = null;
        try {

            conectar = new Conexao();
            //cria um preparedStatement e recebe a conexao e cria uma query
            stm = conectar.conecta().prepareStatement("insert into cadpaciente (cpf,nome,tel,endereco) values (?,?,?,?)");
            
            stm.setString(1, cpf);
            stm.setString(2, pnome);
            stm.setString(3, tel);
            stm.setString(4, endereco);       

            //executa a query 
            stm.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!!!");
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel inserir! /n" + e + "quey: /n" + stm.toString());
        }
    }
    
        public void EditarPc(String cpf, String nome, String tel, String endereco) {
        
        PreparedStatement stm = null;
        try {
            conectar.conecta();
            stm = conectar.con.prepareStatement("update cadpaciente set nome = ?, tel = ?, endereco = ? where cpf = ?");
            stm.setString(1, nome);
            stm.setString(2, tel);
            stm.setString(3, endereco);
            stm.setString(4, cpf);

            stm.execute();

            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!!!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar dados" + erro +  "quey: " + stm.toString());
        }
    }

    public ArrayList ExibirPaciente()
    {
        ArrayList<CadastroPaciente> dadospaciente = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            conectar = new Conexao();

            //cria um preparedStatement e recebe a conexao e cria uma query
            stm = conectar.conecta().prepareStatement("select * from cadpaciente");
            ResultSet infoPacientes = stm.executeQuery();
            while (infoPacientes.next()) {
                CadastroPaciente paciente = new CadastroPaciente();
                paciente.setCpf(infoPacientes.getString("cpf"));
                paciente.setNome(infoPacientes.getString("nome"));
                paciente.setTel(infoPacientes.getString("tel"));
                paciente.setEndereco(infoPacientes.getString("endereco"));
                dadospaciente.add(paciente);

            }
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel executar esta seleção! " + e + "quey: " + stm.toString());
        }
        return dadospaciente;
    }
    
    public void ExcluirPaciente()
    { 
        PreparedStatement stm = null;
        try {
            conectar = new Conexao();
            conectar.conecta();
            //cria um preparedStatemente recebe a conexao e cria uma query
            stm = conectar.con.prepareStatement("delete from cadpaciente where cpf =?");
            stm.setString(1, cpf);
            //executa a query 
            stm.execute();
            JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso!!!");
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel excluir" + e + " query: " + stm.toString());
        }
    
    }
    
    
    
    
    
}
