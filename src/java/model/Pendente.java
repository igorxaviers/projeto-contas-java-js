package model;

public class Pendente implements State{
    private final String nome = "Pendente";

    @Override
    public State aprovar() {
        return new Aprovado();
    }

    @Override
    public State reprovar() {
        return new Reprovado();
    }

    @Override
    public String getNome(){
        return nome;
    }
    
}
