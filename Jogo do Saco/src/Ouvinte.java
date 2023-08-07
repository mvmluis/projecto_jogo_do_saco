public class Ouvinte {
    private int id;
    private String nome;
    private int telefone;
    private int idade;
    private String localidade;
    private int participacoes;
    private int vitorias;
    private int ultimoConcursoVencido;

//---------------------------------------------------- CONSTRUCTORS --------------------------------------------------//
    public Ouvinte() {
        id = 0;
        nome = "";
        telefone = 0;
        idade = 0;
        localidade = "";
        participacoes = 0;
        vitorias = 0;
        ultimoConcursoVencido = 0;
    }

    public Ouvinte(int id, String nome, int telefone, int idade, String localidade, int participacoes, int vitorias, int ultimoConcursoVencido) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.localidade = localidade;
        this.participacoes = participacoes;
        this.vitorias = vitorias;
        this.ultimoConcursoVencido = ultimoConcursoVencido;
    }

//-------------------------------------------------- FIM CONSTRUCTORS ------------------------------------------------//

//----------------------------------------------------- GET AND SET --------------------------------------------------//
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public int getParticipacoes() {
        return participacoes;
    }

    public void setParticipacoes(int participacoes) {
        this.participacoes = participacoes;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getUltimoConcursoVencido() {
        return ultimoConcursoVencido;
    }

    public void setUltimoConcursoVencido(int ultimoConcursoVencido) {
        this.ultimoConcursoVencido = ultimoConcursoVencido;
    }


//--------------------------------------------------- FIM GET AMD SET ------------------------------------------------//

//------------------------------------------------------ OVERRIDES ---------------------------------------------------//

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
       return "\nOuvinte: " + id + "\t\tNome: " + nome +"\t\tTelefone: "+ telefone + "\t\tIdade: "+ idade
                    + "\t\tLocalidade: " + localidade
                    + "\t\tParticipações: " + participacoes
                    + "\t\tVitórias: " + vitorias
                    + "\t\tUltimo concurso vencido: "+ ultimoConcursoVencido;
    }

//---------------------------------------------------- FIM OVERRIDES -------------------------------------------------//
}



