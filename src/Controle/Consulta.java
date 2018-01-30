/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */

public class Consulta {

    private int id;
    private String crm;
    private String cpf;
    private String data;
    private String especialidade;
    Conexao conectar = new Conexao();
    
   public void getConsulta(String id){
        PreparedStatement stm = null;
        try {
            conectar = new Conexao();

            //cria um preparedStatement e recebe a conexao e cria uma query
            stm = conectar.conecta().prepareStatement("select * from ag_consulta c, cadpaciente p, cadmedico m Where m.crm = c.crm_med and p.cpf = c.cpf_paci and id_con = ?");
            stm.setInt(1, Integer.parseInt(id));
            System.out.println("stm: " + stm.toString());
            ResultSet infoConsulta = stm.executeQuery();

            while (infoConsulta.next()) {
                this.setId(infoConsulta.getInt("id_con"));
                this.setCpf(infoConsulta.getString("cpf_paci"));
                this.setCrm(infoConsulta.getString("crm_med"));
                this.setData(infoConsulta.getString("data_con"));
                this.setEspecialidade(infoConsulta.getString("Especialidade_med"));               
            }
            stm.close(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "getConsulta - Nao foi possivel executar esta seleção! 222" + e + "quey: " + stm.toString());
        }
        
   }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCrm() {
        return crm;
    }

    public String getData() {
        return data;
    }
    public String getEspecialidade(){
        return especialidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setData(String Data) {
        this.data = Data;
    }
    public void setEspecialidade(String Especialidade){
        this.especialidade = Especialidade;
    }

    //este método insere os dados do cliente no banco 
    public boolean Inserir(String d) {

        try {
            conectar.conecta();
            //cria um preparedStatemente recebe a conexao e cria uma query
            PreparedStatement stm = conectar.con.prepareStatement("insert into ag_consulta ( crm_med,Especialidade_med,cpf_paci,data_con) values (?,?,?,?)");
            //stm.setInt(1, id);
            stm.setString(1, crm);
            stm.setString(2, especialidade);
            stm.setString(3, cpf);
            stm.setString(4, d);


            //executa a query 
            stm.execute();
            JOptionPane.showMessageDialog(null, "metodo inserir da classe Consulta - Dados Inseridos com Sucesso!!!");
            stm.close();
            return true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "metodo inserir da classe Consulta - Não foi possivel inserir " + erro);
            return false;
        }

    }

    //este método retorna uma consulta por meio de um ArrayList 
    public ArrayList busca() {

        //Cria um ArrayList de objetos cliente     
        ArrayList<Consulta> dados = new ArrayList<Consulta>();

        try {
            conectar.conecta();
            PreparedStatement stm = conectar.con.prepareStatement("select * from ag_consulta");
            ResultSet rs = stm.executeQuery();

            //enquanto existir informações no rs atribuir dados para classe cliente 
            while (rs.next()) {
                Consulta c1 = new Consulta();
                c1.setId(rs.getInt("id_con"));
                c1.setCpf(rs.getString("cpf_paci"));
                c1.setCrm(rs.getString("crm_med"));
                c1.setData(rs.getString("data_con"));
                c1.setEspecialidade(rs.getString("especialidade"));

                dados.add(c1);

            }
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar dados" + e);
        }
        return dados;

    }
    
    public ArrayList consultaCount()
    {
        ArrayList<ConsultaCount> count = new ArrayList<ConsultaCount>();
        try     
        {
            conectar.conecta();
            PreparedStatement stm = conectar.con.prepareStatement("select ag_consulta.crm_med, cadmedico.nome, count(ag_consulta.crm_med) as qnt from ag_consulta jOIN cadmedico ON ag_consulta.crm_med= cadmedico.crm group by ag_consulta.crm_med");
            ResultSet rs = stm.executeQuery();
            
            
            while(rs.next())
            {
                ConsultaCount cCount = new ConsultaCount();
                cCount.nome = rs.getString("nome");
                cCount.crm = rs.getString("crm_med");
                cCount.qnt = rs.getString("qnt");
                
                count.add(cCount);
            }
            return count;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro ao retornar dados" + e);
        }
        return count;
    }

    //este método atualiza os campos do cliente a partir do id 
    public void EditarAg(String _id, String _cpf, String _crm, String _epecialidade,String _data) {
        
        PreparedStatement stm = null;
        try {
            conectar.conecta();
            stm = conectar.con.prepareStatement("update ag_consulta set data_con = ?, cpf_paci= ?, crm_med= ? where id_con = ?");
            stm.setString(1, _data);
            stm.setString(2, _cpf);
            stm.setString(3, _crm);
            stm.setString(4, _epecialidade);
            stm.setInt(5, Integer.parseInt(_id));

            stm.execute();

            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!!!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar dados" + erro +  "quey: " + stm.toString());
        }
    }

    //este método exclui os campos no banco a partir do id
    public void ExcluirAg(String _id) {

        try {
            conectar.conecta();
            //cria a query 
            PreparedStatement stm = conectar.con.prepareStatement("delete from ag_consulta where id_con = ? ");
            stm.setString(1, _id);
            stm.execute();

            JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel excluir" + e);
        }
    }

    public ArrayList ExibirConsultas() 
    {         
        ArrayList<Consulta> dadosconsulta = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            conectar = new Conexao();
            //cria um preparedStatement e recebe a conexao e cria uma query
            stm = conectar.conecta().prepareStatement("select * from ag_consulta");
            ResultSet infoConsulta = stm.executeQuery();
            while (infoConsulta.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(infoConsulta.getInt("id_con"));
                consulta.setCrm(infoConsulta.getString("crm_med"));
                consulta.setCpf(infoConsulta.getString("cpf_paci"));
                consulta.setData(infoConsulta.getString("Data_con"));
                consulta.setEspecialidade(infoConsulta.getString("Especialidade_med"));
                dadosconsulta.add(consulta);
            }
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel executar esta seleção! " + e + "quey: " + stm.toString());
        }
        return dadosconsulta;
    }
    private static class consulta {

        public consulta() {
        }
    }

}
