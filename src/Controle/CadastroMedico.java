package Controle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class CadastroMedico {

    
    private String crm;
    private String mnome;
    private String tel;
    private String especialidade;

    public CadastroMedico() {
    }

    public CadastroMedico( String crm, String nome, String tel, String endereco, String especialidade) {
        
        this.crm = crm;
        this.mnome = nome;
        this.tel = tel;
        this.especialidade = especialidade;

    }


    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public String getNome() {
        return mnome;
    }
    public void setNome(String nome) {
        this.mnome = nome;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    Conexao conectar = new Conexao();
    public void Inserir() {

        try {

            conectar.conecta();
            //cria um preparedStatemente recebe a conexao e cria uma query
            PreparedStatement stm = conectar.con.prepareStatement("insert into cadmedico (crm,nome,tel,especialidade) values (?,?,?,?)");
            stm.setString(1, crm);
            stm.setString(2, mnome);
            stm.setString(3, tel);
            stm.setString(4, especialidade);
            //executa a query 
            stm.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!!!");
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel excluir" + e);
        }
    }
        /**
         *
         * @return
         */
    public ArrayList ExibirMedico() {
        ArrayList<CadastroMedico> dadosmedico = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            conectar = new Conexao();
            //cria um preparedStatement e recebe a conexao e cria uma query
            stm = conectar.conecta().prepareStatement("select * from cadmedico");
            ResultSet infoMedico = stm.executeQuery();
            while (infoMedico.next()) {
                CadastroMedico medico = new CadastroMedico();
                medico.setCrm(infoMedico.getString("crm"));
                medico.setNome(infoMedico.getString("nome"));
                medico.setTel(infoMedico.getString("tel"));
                medico.setEspecialidade(infoMedico.getString("especialidade"));
                dadosmedico.add(medico);

            }
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel executar esta seleção! " + e + "quey: " + stm.toString());
        }
        return dadosmedico;
    }

    public void EditarMedico(String crm, String nome, String tel, String especialidade) throws SQLException{
        try {
        conectar.conecta();
        PreparedStatement stm = conectar.con.prepareStatement("update cadmedico  set nome = ?, tel = ?, especialidade = ?,  where crm = ?");

        stm.setString(1, crm);
        stm.setString(2, mnome);
        stm.setString(3, tel);
        stm.setString(4, especialidade);
        
        stm.executeQuery();
        
        JOptionPane.showMessageDialog(null,"Dados atualizados com sucesso!");
        
        }catch(Exception erro){
        JOptionPane.showMessageDialog(null,"Erro ao atualizar dados!");    
        }
    }
    public void ExcluirMedico() { 
            PreparedStatement stm = null;
            try {
            conectar.conecta();
            //cria um preparedStatemente recebe a conexao e cria uma query
            stm = conectar.con.prepareStatement("delete from cadmedico where crm =?");
            stm.setString(1, crm);
            //executa a query 
            stm.execute();
            JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso!!!");
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel excluir" + e + " query: " + stm.toString());
        }
    
    }

    //public void EditarMedico() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public void EditarMd(String crm, String nome, String tel, String especialidade) {
        
        PreparedStatement stm = null;
        try {
            conectar.conecta();
            stm = conectar.con.prepareStatement("update cadmedico set nome = ?, tel = ?, especialidade = ? where crm = ?");
            
            stm.setString(1, nome);
            stm.setString(2, tel);
            stm.setString(3, especialidade);
            stm.setString(4, crm);
            System.out.println("teste de inserçao");
            stm.execute();

            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!!!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar dados" + erro +  "quey: " + stm.toString());
        }
    }
}
