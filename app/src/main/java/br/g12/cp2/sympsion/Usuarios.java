package br.g12.cp2.sympsion;

public class Usuarios {

    enum Papel {
        PALESTRANTE,
        VOLUNTARIO,
        VISITANTE
    }

    int id;
    String nome;
    String email;
    String cpf;
    String senha;

    public Usuarios() {

    }

    public Usuarios(int _id, String _nome, String _email, String _cpf, String _senha)
    {
        this.id = _id;
        this.nome = _nome;
        this.email = _email;
        this.cpf = _cpf;
        this.senha = _senha;
    }

    public Usuarios(String _nome, String _email, String _cpf, String _senha)
    {

        this.nome = _nome;
        this.email = _email;
        this.cpf = _cpf;
        this.senha = _senha;

    }

    //=============================================================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;} {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
