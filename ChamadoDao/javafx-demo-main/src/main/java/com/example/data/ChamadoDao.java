package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Chamado;

public class ChamadoDao {

    private final String USER = "RM99711";
    private final String PASS = "290204";
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    
    public void inserir(Chamado chamado) throws SQLException{
        var conexao = DriverManager.getConnection(URL,USER,PASS);

            var sql = "INSERT INTO tbl_chamados_rm99711 (usuario, codEquipamento, status, categoria) VALUES (?, ?, ?, ?)";
            var comando = conexao.prepareStatement(sql);

            comando.setString(1,chamado.usuario());
            comando.setString(2,chamado.codEquipamento());;
            comando.setString(3,chamado.status());
            comando.setString(4,chamado.categoria());
            comando.executeUpdate();
            conexao.close();
    }

    public List<Chamado> listarTodos() throws SQLException{
        var conexao = DriverManager.getConnection(URL,USER,PASS);
        var chamados = new ArrayList<Chamado>();

        var result = conexao
                            .createStatement()
                            .executeQuery("SELECT * FROM tbl_chamados_rm99711");
        
        while (result.next()){
            chamados.add(new Chamado(result.getString("nome"),
                                    result.getString("codEquipamento"),
                                    result.getString("Status"),
                                    result.getString("categoria")));
        }
        conexao.close();
        return chamados;

    }
}
