package models;

public class JogoGenero {
    private int id;
    private int jogoId;
    private int generoId;

    //construtores

    public JogoGenero(int jogoId, int generoId) {
        this.jogoId = jogoId;
        this.generoId = generoId;
    }

    public JogoGenero(int id, int jogoId, int generoId) {
        this.id = id;
        this.jogoId = jogoId;
        this.generoId = generoId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getJogoId() {
        return jogoId;
    }
    public void setJogoId(int jogoId) {
        this.jogoId = jogoId;
    }
    public int getGeneroId() {
        return generoId;
    }
    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }   

    @Override
    public String toString() {
        return "JogoGenero: { id: \""+id+"\", jogo_id: \""+jogoId+"\", jogo_id: \""+generoId+"\" }";
    }
}