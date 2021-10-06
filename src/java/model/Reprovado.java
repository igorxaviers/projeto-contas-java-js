package model;

public class Reprovado implements State{
    private final String nome = "Reprovado";

    @Override
    public State aprovar() {
        //Não pode porque já está recusado.
        return this;
    }

    @Override
    public State reprovar() {
        //A conta já está reprovada.
        return this;
    }

    @Override
    public String getNome(){
        return nome;
    }
    
}
