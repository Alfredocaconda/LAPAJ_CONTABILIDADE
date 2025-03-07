/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexao.conexao;
import Model.Mcurso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ALFREDO CACONDA
 */
public class Ccurso {
    private PreparedStatement ps;
    private ResultSet rs;
    conexao c = new conexao();

    public void salvar(Model.Mcurso f) {
        String sql = "insert into curso values(default,?,1)";
        try {
            ps = c.conexao().prepareStatement(sql);
            ps.setString(1, f.getNome());
            if (ps.executeUpdate() == 1) {
                c.mensagem("SUCESSO");
            } else {
                c.mensagem("ERRO");
            }
        } catch (Exception e) {
            c.mensagem(e.getMessage());
        }
    }

    public void actualizar(Model.Mcurso f) {
        String sql = "update curso set nome=?,id_funcionario=? WHERE id_curso=?";
        try {
            ps = c.conexao().prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setInt(2, f.getId_funcionario());
            ps.setInt(3, f.getId_curso());
            if (ps.executeUpdate() == 1) {
                c.mensagem("SUCESSO");
            } else {
                c.mensagem("ERRO");
            }
        } catch (Exception e) {
            c.mensagem(e.getMessage());
        }
    }

    public ArrayList<Mcurso> pesquisar(String pesquisar) {
        ArrayList<Mcurso> dados = new ArrayList<>();
        String sql = "select * from curso where nome like '%" + pesquisar + "%'order by id_curso";
        try {
            ps = c.conexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mcurso f = new Mcurso();
                f.setId_funcionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setId_curso(rs.getInt("id_curso"));
                dados.add(f);
            }
        } catch (SQLException ex) {
            c.mensagem(ex.getMessage());
        }
        return dados;
    }
     public void apagar(Mcurso p) {
        //declaramos uma variavel qualquer que vai receber a linha de codigo do mysl que é o delete
        String sql = "delete from curso where id_curso=?";
        // usamos o try para nos dar informação acerca dos erros
        try {
            //preparamos a base de dados e conectamos com a conexão
            ps = c.conexao().prepareStatement(sql);
            ps.setInt(1, p.getId_curso());
            //condção que vai atualizar os dados
            if (ps.executeUpdate() == 1) {
                c.mensagem("sucesso");
            } else {
                c.mensagem("Erro");
            }
        } catch (SQLException ex) {
            //informção se gerar erro com a abase de dados
            c.mensagem(ex.getMessage());
        }
    }
}
