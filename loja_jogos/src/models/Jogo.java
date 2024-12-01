package models;

import java.sql.Blob;
import java.sql.Date;

public class Jogo {
    private int id;
    private String titulo;
    private java.sql.Blob imagem;
    private boolean tipo; //0 físico, 1 digital
    private int classificacao;
    private String desenvolvedor;
    private double preco;
    private java.sql.Date lancamento;

    //construtores

    public Jogo(int id, String titulo, Blob imagem, boolean tipo, int classificacao, String desenvolvedor, double preco, Date lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.imagem = imagem;
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.desenvolvedor = desenvolvedor;
        this.preco = preco;
        this.lancamento = lancamento;
    }

    public Jogo(String titulo, Blob imagem, boolean tipo, int classificacao, String desenvolvedor, double preco, Date lancamento) {
        this.titulo = titulo;
        this.imagem = imagem;
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.desenvolvedor = desenvolvedor;
        this.preco = preco;
        this.lancamento = lancamento;
    }

    //getters e setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public java.sql.Blob getImagem() {
        return imagem;
    }
    public void setImagem(java.sql.Blob imagem) {
        this.imagem = imagem;
    }
    public boolean isTipo() {
        return tipo;
    }
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    public int getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
    public String getDesenvolvedor() {
        return desenvolvedor;
    }
    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public java.sql.Date getLancamento() {
        return lancamento;
    }
    public void setLancamento(java.sql.Date lancamento) {
        this.lancamento = lancamento;
    }

    @Override
    public String toString() {
        return "Jogo: { id: \""+id+"\", titulo: \""+titulo+"\", tipo: \""+tipo+"\", classificacao: \""+classificacao+"\", desenvolvedor: \""+desenvolvedor+"\", preco: \""+preco+"\", lancamento: \""+lancamento+"\" }";
    }
}