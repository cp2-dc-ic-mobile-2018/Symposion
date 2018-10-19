package br.g12.cp2.sympsion;

public class dadosPalestra {
    int id;
    String nome;
    String horario;
    int duracao;
    int limiteP;
    String lugar;
    String descricao;

    public  dadosPalestra() {

    }

    public dadosPalestra(int _id, String _nome, String _horario, int _duracao, int _limiteP,
                     String _lugar, String _descricao)
    {
        this.id = _id;
        this.nome = _nome;
        this.horario = _horario;
        this.duracao = _duracao;
        this.limiteP = _limiteP;
        this.lugar = _lugar;
        this.descricao = _descricao;
    }

    public dadosPalestra(String _nome, String _horario, int _duracao, int _limiteP, String _lugar,
        String _descricao)
    {

        this.nome = _nome;
        this.horario = _horario;
        this.duracao = _duracao;
        this.limiteP = _limiteP;
        this.lugar = _lugar;
        this.descricao = _descricao;

    }
    // -------------------------------------------------------------------------------------
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getLimiteP() {
        return limiteP;
    }

    public void setLimiteP(int limiteP) {
        this.limiteP = limiteP;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
