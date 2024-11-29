public class JogoGenero {
    private int jogoId;
    private int generoId;

    //construtor

    public JogoGenero(int jogoId, int generoId) {
        this.jogoId = jogoId;
        this.generoId = generoId;
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
}