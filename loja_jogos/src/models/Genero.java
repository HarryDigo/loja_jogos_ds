package models;

public class Genero {
    private int id;
    private String nome;

    //construtores
    
    public Genero(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Genero(String nome) {
        this.nome = nome;
    }

    //getters e setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Gênero: { id: \""+id+"\", nome: \""+nome+"\" }";
    }
}