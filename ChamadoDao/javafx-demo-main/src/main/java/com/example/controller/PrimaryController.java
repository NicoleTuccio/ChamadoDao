package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.data.ChamadoDao;
import com.example.model.Chamado;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML TextField txtUsuario;
    @FXML TextField txtEquipamento;
    @FXML private Button confirmar;
    @FXML private Button apagar;
    @FXML private Button alterar;
    @FXML private RadioButton computador;
    @FXML private RadioButton impressora;
    @FXML private RadioButton rede;
    @FXML private CheckBox primeiroContato;
    @FXML private CheckBox atendido;
    @FXML private CheckBox encerrado;
    @FXML ListView<String> listaChamados;


    //colections
    ArrayList<Chamado>helpDesk = new ArrayList<>();

    
    public void Confirmar(){
        String usuario = txtUsuario.getText(); 
        String codEquipamento = txtEquipamento.getText();
        String categoria = "";

        if(computador.isSelected()){
            categoria = "Computador";
        }else if (impressora.isSelected()){
            categoria = "Impressora";
        }else if (rede.isSelected()){
            categoria = "Rede";
        }

        String status = "";

        if(primeiroContato.isSelected()){
            status = "Primeiro Contato";
        }else if(atendido.isSelected()){
            status = "Atendido";
        }else if(encerrado.isSelected()){
            status = "Encerrado";
        }

        Chamado chamado = new Chamado(usuario, codEquipamento, categoria, status);
        helpDesk.add(chamado);
        mostraChamado();
    }

    public void mostraChamado(){
        try {
            ChamadoDao.listarTodos().forEach(chamado -> lista.getItems().add(chamado));
        } catch (SQLException e) {
            mostrarMensagem(AlertType.ERROR, "Erro", "Erro ao buscar Chamado." + e.getMessage());
        }
    }


    public void apagar(){
        var chamado = listaChamados.getSelectionModel().getSelectedItem();
        helpDesk.remove(chamado);
        mostraChamado();

        Alert mensagem = new Alert(AlertType.INFORMATION);
        mensagem.setHeaderText("Apagado");
        mensagem.setContentText("Chamado apagado com sucesso");
        mensagem.show();
        
    }

    private void mostrarMensagem(AlertType tipo, String titulo, String mensagem ){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(titulo);
        alerta.setContentText(mensagem);
        alerta.show();
    }

}






    

