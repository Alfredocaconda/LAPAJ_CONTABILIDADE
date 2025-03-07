/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexao.conexao;
import Model.Mfuncionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ALFREDO CACONDA
 */
public class Cfuncionario {

    private PreparedStatement ps;
    private ResultSet rs;
    conexao c = new conexao();

    public void salvar(Model.Mfuncionario f) {
        String sql = "insert into funcionario values(default,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = c.conexao().prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getGenero());
            ps.setString(3, f.getEspecialidade());
            ps.setString(4, f.getN_bi());
            ps.setString(5, f.getTelefone());
            ps.setString(10, f.getCargo());
            ps.setString(6, f.getData_contrato());
            ps.setString(9, f.getSenha());
            ps.setString(7, f.getStatu());
            ps.setString(8, f.getEmail());
            if (ps.executeUpdate() == 1) {
                c.mensagem("SUCESSO");
            } else {
                c.mensagem("ERRO");
            }
        } catch (Exception e) {
            c.mensagem(e.getMessage());
        }
    }

    public void actualizar(Model.Mfuncionario f) {
        String sql = "update funcionario set nome=?,genero=?,"
                + "especialidade=?,n_bi=?,telefone=?,data_contrato=?,cargo=?,senha=?,statu=?,"
                + "email=? WHERE id_funcionario=?";
        try {
            ps = c.conexao().prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getGenero());
            ps.setString(3, f.getEspecialidade());
            ps.setString(4, f.getN_bi());
            ps.setString(5, f.getTelefone());
            ps.setString(10, f.getCargo());
            ps.setString(6, f.getData_contrato());
            ps.setString(9, f.getSenha());
            ps.setString(7, f.getStatu());
            ps.setString(8, f.getEmail());
            ps.setInt(11, f.getId_funcionario());
            if (ps.executeUpdate() == 1) {
                c.mensagem("SUCESSO");
            } else {
                c.mensagem("ERRO");
            }
        } catch (Exception e) {
            c.mensagem(e.getMessage());
        }
    }

    public ArrayList<Mfuncionario> pesquisar(String pesquisar) {
        ArrayList<Mfuncionario> dados = new ArrayList<>();
        String sql = "select * from funcionario where nome or n_bi like '%" + pesquisar + "%'order by id_funcionario";
        try {
            ps = c.conexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mfuncionario f = new Mfuncionario();
                f.setId_funcionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setGenero(rs.getString("genero"));
                f.setN_bi(rs.getString("n_bi"));
                f.setEspecialidade(rs.getString("especialidade"));
                f.setSenha(rs.getString("senha"));
                f.setData_contrato(rs.getString("data_contrato"));
                f.setStatu(rs.getString("statu"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setTelefone(rs.getString("telefone"));
                dados.add(f);
            }
        } catch (SQLException ex) {
            c.mensagem(ex.getMessage());
        }
        return dados;
    }
}
