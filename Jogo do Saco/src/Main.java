import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Random rnd = new Random();
    static int idProximoOuvinte;
    static List<Ouvinte> listaOuvintes;
    static List<Ouvinte> listaOuvintesAJogo;

    static int numeroConcurso = 1;

    public static void main(String[] args) {
        listaOuvintes = new ArrayList<>();
        Init();
        int op = -1;
        do {
            try {
                in = new Scanner(System.in);
                System.out.println("===================================");
                System.out.println("    Bem-vindo ao Jogo do Saco!");
                System.out.println("===================================");
                System.out.println("[1] Jogar");
                System.out.println("[2] Ouvintes");
                System.out.println("[3] Ver Ranking");
                System.out.println("[4] Consultar regras de jogo");
                System.out.println("[5] Sobre Jogo do Saco");
                System.out.println("[0] Sair");
                System.out.println("===================================");

                String valorInserido = in.nextLine().trim();
                if (valorInserido.length() == 1 && valorInserido.matches("\\d")) {
                    op = Integer.parseInt(valorInserido);
                } else {
                    System.out.println("Por favor, insira um número válido.");
                    continue;
                }

                switch (op) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> Jogar();
                    case 2 -> MenuOuvintes();
                    case 3 -> VerRanking();
                    case 4 -> ConsultarRegras();
                    case 5 -> SobreOJogoDoSaco();

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                in.nextLine();
            }
        } while (op != 0);

    }

//-------------------------------------------------------- MENU ------------------------------------------------------//
    private static void Jogar() {
        int op = -1;
        do {
            try {
                in = new Scanner(System.in);
                System.out.println("===================================");
                System.out.println("    Bem-Vindo ao Menu Jogar!");
                System.out.println("===================================");
                System.out.println("[1] Simule um jogo");
                System.out.println("[2] Introduza o valor pelos ouvintes");
                System.out.println("[0] Retroceder");
                System.out.println("===================================");

                String valorInserido = in.nextLine().trim();
                if (valorInserido.length() == 1 && valorInserido.matches("\\d")) {
                    op = Integer.parseInt(valorInserido);
                } else {
                    System.out.println("Por favor, insira um número válido.");
                    continue;
                }

                switch (op) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> JogoSimulacao();
                    case 2 -> JogoUtilizador();

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                in.nextLine();
            }
        } while (op != 0);

    }

    private static void VerRanking() {
        System.out.println("===================================================================================================================");
        System.out.println("                                                   Ranking dos Ouvintes");
        System.out.println("===================================================================================================================");
        System.out.printf("%6s %19s %12s %6s %18s %10s %14s %20s\n", "Ranking", "Nome", "Telefone", "Idade", "Localidade", "Vitorias", "Partipações", "Última vitória");
        System.out.printf("%6s %19s %12s %6s %18s %10s %14s %20s\n", "|", "|", "|", "|", "|", "|", "|", "|");
        System.out.println("===================================================================================================================");


        List<Ouvinte> rankingOuvintes = new ArrayList<>(listaOuvintes);

      /*  Collections.sort(rankingOuvintes, new Comparator<Ouvinte>() {
            @Override
            public int compare(Ouvinte o1, Ouvinte o2) {
                if (o1.getVitorias() > o2.getVitorias()) {
                    return -1;
                } else if (o1.getVitorias() < o2.getVitorias()) {
                    return 1;
                } else {
                    if (o1.getParticipacoes() < o2.getParticipacoes()) {
                        return -1;
                    } else if (o1.getParticipacoes() > o2.getParticipacoes()) {
                        return 1;
                    } else {
                        if (o1.getUltimoConcursoVencido() < o2.getUltimoConcursoVencido()) {
                            return -1;
                        } else if (o1.getUltimoConcursoVencido() > o2.getUltimoConcursoVencido()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }); */

        rankingOuvintes.sort(Comparator.comparingInt(Ouvinte::getVitorias).reversed()
                .thenComparingInt(Ouvinte::getParticipacoes)
                .thenComparingLong(Ouvinte::getUltimoConcursoVencido));


        if (rankingOuvintes.isEmpty() || rankingOuvintes.get(0).getVitorias() == 0) {
            System.out.println();
            System.out.format("%60s", "Lista vazia.\n");
            System.out.format("%72s", "Não há ouvintes com vitórias no concurso!\n");
        } else {
            int posicaoNoRanking = 1;
            int vitoriasTemp = rankingOuvintes.get(0).getVitorias();
            int participacoesTemp = rankingOuvintes.get(0).getParticipacoes();
            int ultimoConcursoTemp = rankingOuvintes.get(0).getUltimoConcursoVencido();
            for (Ouvinte ouvinte : rankingOuvintes) {
                if (ouvinte.getVitorias() > 0) {
                    if (vitoriasTemp != ouvinte.getVitorias() || participacoesTemp != ouvinte.getParticipacoes() || ultimoConcursoTemp != ouvinte.getUltimoConcursoVencido()) {
                        vitoriasTemp = ouvinte.getVitorias();
                        participacoesTemp = ouvinte.getParticipacoes();
                        ultimoConcursoTemp = ouvinte.getUltimoConcursoVencido();
                        posicaoNoRanking++;
                        System.out.println("-------------------------------------------------------------------------------------------------------------------");
                    }
                    System.out.format("%6s %19s %12s %6s %18s %10s %14s %20s", posicaoNoRanking + "º", ouvinte.getNome(),
                            ouvinte.getTelefone(), ouvinte.getIdade(), ouvinte.getLocalidade(), ouvinte.getVitorias(),
                            ouvinte.getParticipacoes(), ouvinte.getUltimoConcursoVencido() + "ª edição");
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("===================================================================================================================");
    }

    private static void MenuOuvintes() {
        int op = -1;
        do {
            try {
                in = new Scanner(System.in);
                System.out.println("=====================================");
                System.out.println("   Bem-Vindo ao Menu de Ouvintes!");
                System.out.println("=====================================");
                System.out.println("[1] Lista de Ouvintes");
                System.out.println("[2] Consultar dados de ouvinte por ID");
                System.out.println("[3] Criar Ouvinte");
                System.out.println("[4] Editar Ouvinte");
                System.out.println("[5] Eliminar Ouvinte");
                System.out.println("[0] Retroceder");
                System.out.println("=====================================");

                String valorInserido = in.nextLine().trim();
                if (valorInserido.length() == 1 && valorInserido.matches("\\d")) {
                    op = Integer.parseInt(valorInserido);
                } else {
                    System.out.println("Por favor, insira um número válido.");
                    continue;
                }

                switch (op) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> ListarOuvintes();
                    case 2 -> DadosOuvinte();
                    case 3 -> CriarOuvinte();
                    case 4 -> EditarOuvinte();
                    case 5 -> EliminarOuvinte();

                    default -> {
                        System.out.println("Opção inválida");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                in.nextLine();
            }
        } while (op != 0);

    }

    private static void ConsultarRegras() {
        System.out.println("============================================================================================================");
        System.out.println("                                          Regras Jogo do Saco");
        System.out.println("============================================================================================================");
        System.out.println("                                          Condições de Jogo");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("No inicio de cada jogo o locutor anuncia o peso que pode variar entre 1 a 5kg.\n" +
                "os participantes têm depois que dar palpipes acima do valor que o locutor anunciou, sendo que o palpite maximo\n" +
                "nao pode exceder em mais de 150g o valor que o locutor anunciou. por exemplo: se o locutor anuncia um peso de 2.5Kg\n" +
                "o valor maximo que os participantes podem apostar será de 2.65kg\n" +
                "" + "o numero de ouvintes chamados a participar é definido em cada jogo. no minimo participam 2 ouvintes, no maximo 5.\n" +
                "para haver jogo tera de haver portanto pelo menos 2 ouvintes a dar palpites e pelo menos 5 ouvintes na base de dados.\n" +
                "o ouvinte so pode ser selecionado uma vez por jogo. ou seja, o mesmo ouvinte nao pode ser selecionado mais que uma vez\n" +
                "por jogo para dar palpites  " + "Os ouvintes são chamados a participar pela ordem que são selecionados.\n");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                          Tipo de jogo");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("jogar: simular e inserir valores");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                          Condições de Vitória");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("o ouvinte que der o valor certo ganha. se nenhum dos ouvintes acertar no valor correcto, o vencedor é o ouvinte que\n" +
                "apostou o valor mais proximo do peso exacto que é anunciado no fim do programa.                  ");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                          Condições em caso de empate");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Só pode haver um vencedor. Em caso de empate o vencedor é o primeiro jogador a dar o palpite. por exemplo\n" +
                "o peso certo é 1.100kg. O jose deu o palpite de 1.075kg e a maria 1.125Kg. Apesar de ambos estarem a 25g do peso correcto\n" +
                "como o jose foi o primeira a ser selecionado, é o vencedor. ou seja, a ordem pela qual os ouvintes são chamados a participar\n" +
                "serve como factor de desempate.                           ");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Ranking");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("ranking / vitorias e factor de desempate. classificacao");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                Ouvintes");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("ouvintes");
        System.out.println("============================================================================================================");
    } // escrever texto com regas de jogo

    private static void SobreOJogoDoSaco() {
        System.out.println("============================================================================================================");
        System.out.println("                                          Sobre Jogo do Saco");
        System.out.println("============================================================================================================");
        System.out.println("                                          Informações Legais");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Este projeto foi elaborado no âmbito de uma disciplina do Curso Software Developer e não se destina a fins\n" +
                "comerciais. O projeto 'Jogo do Saco' teve como objetivo permitir que os estudantes desenvolvessem habilidades\n" +
                "práticas e teóricas na área da programação orientada a objetos, aplicando os conhecimentos adquiridos durante\n" +
                "o curso. Qualquer uso comercial deste projeto é expressamente proibido sem o consentimento e autorização\n" +
                "por escrito do Presidente da República Portuguesa Prof. Dr. Marcelo Rebelo de Sousa.");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("                                          Informações Adicionais");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Instituição:\nCESAE Digital\n----------------------------------");
        System.out.println("Curso:\nSoftware Developer\n----------------------------------");
        System.out.println("Disciplina:\nProgramação Orientada a Objetos\n----------------------------------");
        System.out.println("Projeto:\nJogo do Saco\nVersão: 1.0.0\nÚltima atualização: 03/01/2023\n----------------------------------");
        System.out.println("Formador:\nBruno Santos\n----------------------------------");
        System.out.println("Alunos:\nFilipe Ramos\nLuís Macieira\n----------------------------------");
        System.out.println("Agradecimento especial:\nAnabela de Malhadas");
        System.out.println("============================================================================================================");
        ;
    }

//----------------------------------------------------- FIM MENU -----------------------------------------------------//


//------------------------------------------------------ JOGAR -------------------------------------------------------//
    private static void JogoSimulacao() {
        if (listaOuvintes.size() > 4) {
            DecimalFormat f = new DecimalFormat("#.000");
            double pesoCertoConcurso = rnd.nextDouble(1, 5);
            double pesoMinConcurso = pesoCertoConcurso - 0.0750;
            double pesoMaxConcurso = pesoCertoConcurso + 0.0750;
            String vencedor = ""; // guarda o nome do vencedor
            int idVencedor = 0; // guarda o id do vencedor
            int contador = 1; // contador para ver se há empates

            System.out.println("======================================================================================================================");
            System.out.println("                                              " + numeroConcurso + "ª Edição Jogo do Saco");
            System.out.println("======================================================================================================================");
            System.out.println("Os ouvintes terão de adivinhar o peso certo de hoje. É pedido aos ouvintes que dêem palpites acima de " + f.format(pesoMinConcurso) + "Kg.");

            int ouvintesAJogo = rnd.nextInt(2, 5);
            listaOuvintesAJogo = new ArrayList<>();
            double apostaVencedora = 0;

            System.out.println("Foram escolhidos " + ouvintesAJogo + " ouvintes para participar na edição " + numeroConcurso + " do Jogo do Saco!");
            for (int i = 0; i < ouvintesAJogo; i++) {
                int id = rnd.nextInt(0, listaOuvintes.size());
                Ouvinte ouvintesTemp = listaOuvintes.get(id);
                if (!listaOuvintesAJogo.contains(ouvintesTemp)) { // verifica se o id é repetido (se for não entra)

                    listaOuvintesAJogo.add(ouvintesTemp);
                    listaOuvintes.get(id).setParticipacoes(listaOuvintes.get(id).getParticipacoes() + 1);

                    double apostaOuvinte = rnd.nextDouble(pesoMinConcurso, pesoMaxConcurso);
                    System.out.println("O/A " + (i + 1) + "º ouvinte foi " + listaOuvintes.get(id).getNome() + " de "
                            + listaOuvintes.get(id).getLocalidade() + " que deu o palpite de peso: "
                            + f.format(apostaOuvinte) + "Kg.");

                    if (Math.abs(pesoCertoConcurso - apostaOuvinte) < Math.abs(pesoCertoConcurso - apostaVencedora)) {
                        apostaVencedora = apostaOuvinte;
                        vencedor = listaOuvintes.get(id).getNome();
                        idVencedor = id;
                    } else if (Math.abs(pesoCertoConcurso - apostaOuvinte) == Math.abs(pesoCertoConcurso - apostaVencedora)) {
                        contador++;
                    }

                    if (i == ouvintesAJogo - 1) { // verifica se é a última iteneração
                        if (contador == 1) {
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                            System.out.println("O peso certo era " + f.format(pesoCertoConcurso)
                                    + "Kg. O/A ouvinte vencedor(a) foi " + vencedor + " que deu o palpite de "
                                    + f.format(apostaVencedora) + "Kg.");
                            System.out.println("======================================================================================================================");
                            listaOuvintes.get(idVencedor).setVitorias(listaOuvintes.get(idVencedor).getVitorias() + 1);
                            listaOuvintes.get(idVencedor).setUltimoConcursoVencido((numeroConcurso));
                            numeroConcurso++;
                        } else {
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Houve empate entre " + contador + " ouvintes! O peso certo era " + f.format(pesoCertoConcurso)
                                    + "Kg.\nO/A ouvinte vencedor(a) foi " + vencedor + " que deu o palpite de "
                                    + f.format(apostaVencedora) + "Kg e participou antes dos outros concorrentes.");
                            System.out.println("======================================================================================================================");
                            listaOuvintes.get(idVencedor).setVitorias(listaOuvintes.get(idVencedor).getVitorias() + 1);
                            listaOuvintes.get(idVencedor).setUltimoConcursoVencido((numeroConcurso));
                            numeroConcurso++;
                        }
                    }
                } else i--;
            }
        } else {
            System.out.println("Não existem ouvintes suficientes para realizar o Jogo do Saco!\n" +
                    "Teremos que ter pelo menos a participação de dois ouvintes e 5 ouvintes na base de dados.");
            System.out.println("Adicione/Crie ouvintes através do Menu Ouvintes para realizar Novo Jogo.");
        }
    }

    private static void JogoUtilizador() {
        if (listaOuvintes.size() > 4) {
            DecimalFormat f = new DecimalFormat("#.000");
            double pesoCertoConcurso = rnd.nextDouble(1, 5);
            double pesoMinConcurso = pesoCertoConcurso - 0.0750;
            double pesoMaxConcurso = pesoCertoConcurso + 0.0750;
            String vencedor = "";
            int idVencedor = 0;


            System.out.println("======================================================================================================================");
            System.out.println("                                              " + numeroConcurso + "ª Edição Jogo do Saco");
            System.out.println("======================================================================================================================");
            System.out.println("Os ouvintes terão de adivinhar o peso certo de hoje. É pedido aos ouvintes que dêem palpites acima de " + f.format(pesoMinConcurso) + "Kg.");

            int ouvintesAJogo = rnd.nextInt(2, 5);
            listaOuvintesAJogo = new ArrayList<>();
            double apostaVencedora = 0;
            int contador = 1;

            System.out.println("Foram escolhidos " + ouvintesAJogo + " ouvintes para participar na edição " + numeroConcurso + " do Jogo do Saco!");
            for (int i = 0; i < ouvintesAJogo; i++) {
                int id = rnd.nextInt(0, listaOuvintes.size());
                Ouvinte ouvintesTemp = listaOuvintes.get(id);
                if (!listaOuvintesAJogo.contains(ouvintesTemp)) { // so entra se id nao for repetido

                    listaOuvintesAJogo.add(ouvintesTemp);
                    listaOuvintes.get(id).setParticipacoes(listaOuvintes.get(id).getParticipacoes() + 1);

                    boolean valorInseridoValido = false;
                    double apostaOuvinte = 0;
                    while (!valorInseridoValido) {
                        System.out.println(listaOuvintes.get(id).getNome() + " de "
                                + listaOuvintes.get(id).getLocalidade() + " é o/a " + (i + 1) + "º selecionado/a de hoje. Qual é o seu palpite? O valor tem que ser entre " + f.format(pesoMinConcurso) + " e " + f.format(pesoMaxConcurso) + "!");
                        in = new Scanner(System.in);
                        if (in.hasNextDouble()) {
                            apostaOuvinte = in.nextDouble();
                            if (apostaOuvinte >= pesoMinConcurso && apostaOuvinte <= pesoMaxConcurso) {
                                valorInseridoValido = true;
                            } else {
                                System.out.println("Valor inválido! Tem que escolher um peso entre " + f.format(pesoMinConcurso) + " e " + f.format(pesoMaxConcurso) + "Kg.");
                            }
                        } else {
                            System.out.println("Valor inválido! Tem que escolher um peso entre " + f.format(pesoMinConcurso) + " e " + f.format(pesoMaxConcurso) + "Kg.");
                        }
                    }


                    System.out.println("Muito bem! O/A " + listaOuvintes.get(id).getNome() + " de "
                            + listaOuvintes.get(id).getLocalidade() + " deu o palpite de "
                            + f.format(apostaOuvinte) + "Kg.");

                    if (Math.abs(pesoCertoConcurso - apostaOuvinte) < Math.abs(pesoCertoConcurso - apostaVencedora)) {
                        apostaVencedora = apostaOuvinte;
                        vencedor = listaOuvintes.get(id).getNome();
                        idVencedor = id;
                    } else if (Math.abs(pesoCertoConcurso - apostaOuvinte) == Math.abs(pesoCertoConcurso - apostaVencedora)) {
                        contador++;
                    }


                    if (i == ouvintesAJogo - 1) {
                        if (contador == 1) {
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                            System.out.println("O peso certo era " + f.format(pesoCertoConcurso)
                                    + "Kg. O/A ouvinte vencedor(a) foi " + vencedor + " que deu o palpite de "
                                    + f.format(apostaVencedora) + "Kg.");
                            System.out.println("======================================================================================================================");
                            listaOuvintes.get(idVencedor).setVitorias(listaOuvintes.get(idVencedor).getVitorias() + 1);
                            listaOuvintes.get(idVencedor).setUltimoConcursoVencido((numeroConcurso));
                            numeroConcurso++;
                        } else {
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Houve empate entre " + contador + " ouvintes! O peso certo era " + f.format(pesoCertoConcurso)
                                    + "Kg.\nO/A ouvinte vencedor(a) foi " + vencedor + " que deu o palpite de "
                                    + f.format(apostaVencedora) + "Kg e participou antes dos outros concorrentes.");
                            System.out.println("======================================================================================================================");
                            listaOuvintes.get(idVencedor).setVitorias(listaOuvintes.get(idVencedor).getVitorias() + 1);
                            listaOuvintes.get(idVencedor).setUltimoConcursoVencido((numeroConcurso));
                            numeroConcurso++;
                        }
                    }
                } else i--;
            }
        } else {
            System.out.println("Não existem ouvintes suficientes para realizar o Jogo do Saco!\n" +
                    "Teremos que ter pelo menos a participação de dois ouvintes e 5 ouvintes na base de dados.");
            System.out.println("Adicione/Crie ouvintes através do Menu Ouvintes para realizar Novo Jogo.");
        }
    }

//----------------------------------------------------- FIM JOGAR ----------------------------------------------------//


//------------------------------------------------------ OUVINTE -----------------------------------------------------//
    private static void EliminarOuvinte() {
        if (listaOuvintes.size() > 0) {
            in = new Scanner(System.in);
            while (true) {
                int id = 0;
                boolean idValido = false;
                while (!idValido) {
                    System.out.println("Qual o ID do Ouvinte que pretende eliminar? (Digite 0 para retroceder)");
                    String valorInserido = in.nextLine().trim();
                    try {
                        id = Integer.parseInt(valorInserido);
                        idValido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, insira um ID válido (apenas números). (Digite 0 para retroceder)");
                    }
                }
                if (id == 0) return;

                if (listaOuvintes.isEmpty()) { // redundante. não entra aqui porque existe a validação na primeira linha
                    System.out.println("A lista de ouvintes está vazia.");
                    return;
                }

                int idTemp = id;
                Optional<Ouvinte> ouvinte = listaOuvintes.stream()
                        .filter(o -> o.getId() == idTemp)
                        .findFirst();

                if (ouvinte.isPresent()) {
                    listaOuvintes.remove(ouvinte.get());
                    System.out.println("Ouvinte removido com sucesso!");
                    break;
                } else {
                    System.out.println("Erro ao eliminar o Ouvinte! O ID que selecionou não existe na base de dados.");
                }
            }
        } else {
            System.out.println("A lista de ouvintes está vazia.");
        }
    }

    private static void EditarOuvinte() {
        if (listaOuvintes.size() > 0) {
            in = new Scanner(System.in);
            int id;
            System.out.println("Qual o ID do Ouvinte que pretende editar? (Digite 0 para retroceder)");
            while (true) {
                String valorInserido = in.nextLine().trim();
                if (valorInserido.length() < 9 && valorInserido.matches("\\d+")) {
                    id = Integer.parseInt(valorInserido);
                    break;
                } else {
                    System.out.println("Por favor, insira um ID válido (apenas números).");
                }
            }
            if (id == 0) return;

            for (int i = 0; i < listaOuvintes.size(); i++) {
                if (listaOuvintes.get(i).getId() == id) {

                    System.out.println("============================================================================================================");
                    System.out.println("                                           Dados do Ouvinte");
                    System.out.println("============================================================================================================");
                    System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "ID", "Nome", "Telefone", "Idade", "Localidade", "Partipações", "Vitorias");
                    System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "|", "|", "|", "|", "|", "|", "|");
                    System.out.println("============================================================================================================");
                    System.out.format("%4s %25s %15s %7s %18s %10s %7s", listaOuvintes.get(i).getId(), listaOuvintes.get(i).getNome(), listaOuvintes.get(i).getTelefone(),
                            listaOuvintes.get(i).getIdade(), listaOuvintes.get(i).getLocalidade(), listaOuvintes.get(i).getParticipacoes(), listaOuvintes.get(i).getVitorias());
                    System.out.println();
                    System.out.println("============================================================================================================");
                    int op;
                    do {
                        System.out.println("Que campo pretende atualizar?");
                        System.out.println("1-Nome");
                        System.out.println("2-Telefone");
                        System.out.println("3-Idade");
                        System.out.println("4-Localidade");
                        System.out.println("0-Sair");

                        op = in.nextInt();

                        switch (op) {
                            case 0: {
                                return;
                            }
                            case 1: {
                                in = new Scanner(System.in);
                                String nome;
                                while (true) {
                                    System.out.println("Qual o nome do Ouvinte? (Introduza o nome e o apelido(opcional))");
                                    String nomeTemp = in.nextLine();
                                    if (nomeTemp.length() < 19 && nomeTemp.matches("([a-zA-Z]+\\s)*[a-zA-Z]*")) {
                                        nome = nomeTemp;
                                        break;
                                    } else {
                                        System.out.println("Introduza apenas letras (máximo de 18 caracteres).");
                                    }
                                }
                                listaOuvintes.get(i).setNome(nome);
                                System.out.println("Campo atualizado com sucesso!");
                                System.out.println("O/A ouvinte agora chama-se " + listaOuvintes.get(i).getNome() + ".");
                                break;
                            }
                            case 2: {
                                in = new Scanner(System.in);
                                int telefone;
                                while (true) {
                                    System.out.println("Introduza um novo número de telefone com 9 digitos (Ex: 211211211): ");
                                    String telefoneTemp = in.nextLine().trim();
                                    if (telefoneTemp.length() == 9 && telefoneTemp.matches("[0-9]+")) {
                                        telefone = Integer.parseInt(telefoneTemp);
                                        break;
                                    } else {
                                        System.out.println("Dados incorrectos. Não introduziu 9 digitos sem espaços!");
                                    }
                                }
                                listaOuvintes.get(i).setTelefone(telefone);
                                System.out.println("Campo atualizado com sucesso!");
                                System.out.println("O/A ouvinte mudou o seu número de telefone para " + listaOuvintes.get(i).getTelefone() + ".");
                                break;
                            }
                            case 3: {
                                in = new Scanner(System.in);
                                int idade;
                                while (true) {
                                    System.out.println("Qual nova idade do Ouvinte? (Defina uma idade entre 18 e 120 anos. Ex: 91)");
                                    String valorInserido = in.nextLine().trim();
                                    try {
                                        int idadeTemp = Integer.parseInt(valorInserido);
                                        if (idadeTemp >= 18 && idadeTemp <= 120) {
                                            idade = idadeTemp;
                                            break;
                                        } else {
                                            System.out.println("Valor inválido. Insira uma idade entre 18 e 120 anos sem espaços.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valor inválido. Insira uma idade entre 18 e 120 anos sem espaços.");
                                    }
                                }
                                listaOuvintes.get(i).setIdade(idade);
                                System.out.println("Campo atualizado com sucesso!");
                                System.out.println("O/A ouvinte tem agora " + listaOuvintes.get(i).getIdade() + " anos.");
                                break;
                            }
                            case 4: {
                                in = new Scanner(System.in);
                                String localidade;
                                while (true) {
                                    System.out.println("Qual a localidade do Ouvinte?");
                                    String localidadeTemp = in.nextLine();
                                    if (localidadeTemp.length() < 16 && localidadeTemp.matches("([a-zA-Z]+\\s)*[a-zA-Z]*")) {
                                        localidade = localidadeTemp;
                                        break;
                                    } else {
                                        System.out.println("Introduza apenas letras (máximo de 15 caracteres)..");
                                    }
                                }
                                listaOuvintes.get(i).setLocalidade(localidade);
                                System.out.println("Campo atualizado com sucesso!");
                                System.out.println("O/A ouvinte mudou a sua residência para " + listaOuvintes.get(i).getLocalidade() + ".");
                                break;
                            }
                            default: {
                                System.out.println("Opção inválida");
                            }
                        }
                    }
                    while (op != 0);
                    System.out.println("Dados do Ouvinte atualizados com sucesso!");
                    return;
                }
            }
            System.out.println("Erro ao editar o Ouvinte! O ID que selecionou não existe na base de dados.");
            EditarOuvinte();
        } else {
            System.out.println("A lista de ouvintes está vazia.");
        }
    }

    private static void CriarOuvinte() {
        in = new Scanner(System.in);
        String nome;
        while (true) {
            System.out.println("Qual o nome do Ouvinte? (Introduza o nome e o apelido(opcional). Ex: Anabela Malhadas)");
            String nomeTemp = in.nextLine();
            if (nomeTemp.length() < 19 && nomeTemp.matches("([a-zA-Z]+\\s)*[a-zA-Z]*")) {
                nome = nomeTemp;
                break;
            } else {
                System.out.println("Introduza apenas letras (máximo de 18 caracteres).");
            }
        }

        in = new Scanner(System.in);
        int telefone;
        while (true) {
            System.out.println("Introduza um numero de telefone com 9 digitos (Ex: 211211211): ");
            String telefoneTemp = in.nextLine().trim();
            if (telefoneTemp.length() == 9 && telefoneTemp.matches("[0-9]+")) {
                telefone = Integer.parseInt(telefoneTemp);
                break;
            } else {
                System.out.println("Dados incorrectos. Por favor, introduza 9 digitos sem espaços!");
            }
        }

        in = new Scanner(System.in);
        int idade;
        while (true) {
            System.out.println("Qual a idade do Ouvinte? (Defina uma idade entre 18 e 120 anos. Ex: 91)");
            String valorInserido = in.nextLine().trim();
            try {
                int idadeTemp = Integer.parseInt(valorInserido);
                if (idadeTemp >= 18 && idadeTemp <= 120) {
                    idade = idadeTemp;
                    break;
                } else {
                    System.out.println("Valor inválido. Insira uma idade entre 18 e 120 anos sem espaços.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Insira uma idade entre 18 e 120 anos sem espaços.");
            }
        }

        in = new Scanner(System.in);
        String localidade;
        while (true) {
            System.out.println("Qual a localidade do Ouvinte? (Ex. Porto)");
            String localidadeTemp = in.nextLine();
            if (localidadeTemp.length() < 16 && localidadeTemp.matches("([a-zA-Z]+\\s)*[a-zA-Z]*")) {
                localidade = localidadeTemp;
                break;
            } else {
                System.out.println("Introduza apenas letras (máximo de 15 caracteres).");
            }
        }
        int id = idProximoOuvinte;
        listaOuvintes.add(new Ouvinte(id, nome, telefone, idade, localidade, 0, 0, 0));
        idProximoOuvinte++;
        System.out.println("Ouvinte adicionado com sucesso!");

        System.out.println("============================================================================================================");
        System.out.println("                                         Dados do novo Ouvinte");
        System.out.println("============================================================================================================");
        System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "ID", "Nome", "Telefone", "Idade", "Localidade", "Partipações", "Vitorias");
        System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "|", "|", "|", "|", "|", "|", "|");
        System.out.println("============================================================================================================");
        System.out.format("%4s %25s %15s %7s %18s %10s %7s", id, nome, telefone, idade, localidade, 0, 0);
        System.out.println();
        System.out.println("============================================================================================================");
    }

    private static void DadosOuvinte() {
        if (listaOuvintes.size() > 0) {
            in = new Scanner(System.in);
            int id;
            System.out.println("Indique o ID do Ouvinte que pretende consultar (Digite 0 para sair)");
            while (true) {
                String valorInserido = in.nextLine().trim();
                if (valorInserido.length() < 9 && valorInserido.matches("\\d+")) {
                    id = Integer.parseInt(valorInserido);
                    break;
                } else {
                    System.out.println("Por favor, insira um ID válido (apenas números).");
                }
            }
            if (id == 0) return;

            Ouvinte ouvinte = null;
            for (Ouvinte o : listaOuvintes) {
                if (o.getId() == id) {
                    ouvinte = o;
                    break;
                }
            }
            if (ouvinte != null) {
                System.out.println("============================================================================================================");
                System.out.println("                                           Dados do Ouvinte");
                System.out.println("============================================================================================================");
                System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "ID", "Nome", "Telefone", "Idade", "Localidade", "Partipações", "Vitorias");
                System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "|", "|", "|", "|", "|", "|", "|");
                System.out.println("============================================================================================================");
                System.out.format("%4s %25s %15s %7s %18s %10s %7s", ouvinte.getId(), ouvinte.getNome(),
                        ouvinte.getTelefone(), ouvinte.getIdade(), ouvinte.getLocalidade(),
                        ouvinte.getParticipacoes(), ouvinte.getVitorias());
                System.out.println();
                System.out.println("============================================================================================================");
            } else {
                System.out.println("O ID que introduziu não correspondente a nenhum ouvinte existente na nossa base de dados!");
                DadosOuvinte();
            }
        } else {
            System.out.println("A lista de ouvintes está vazia.");
        }
    }

    private static void ListarOuvintes() {
        if (listaOuvintes.size() > 0) {
            System.out.println("============================================================================================================");
            System.out.println("                                           Lista de Ouvintes");
            System.out.println("============================================================================================================");
            System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "ID", "Nome", "Telefone", "Idade", "Localidade", "Partipações", "Vitorias");
            System.out.printf("%4s %25s %15s %7s %18s %10s %7s\n", "|", "|", "|", "|", "|", "|", "|");
            System.out.println("============================================================================================================");

            for (int i = 0; i < listaOuvintes.size(); i++) {
                System.out.format("%4s %25s %15s %7s %18s %10s %7s", listaOuvintes.get(i).getId(), listaOuvintes.get(i).getNome(),
                        listaOuvintes.get(i).getTelefone(), listaOuvintes.get(i).getIdade(), listaOuvintes.get(i).getLocalidade(), listaOuvintes.get(i).getParticipacoes(),
                        listaOuvintes.get(i).getVitorias());
                System.out.println();
            }
            System.out.println("============================================================================================================");
        } else {
            System.out.println("A lista de ouvintes está vazia.");
        }
    }

//---------------------------------------------------- FIM OUVINTE ---------------------------------------------------//


//----------------------------------------------- BASE DE DADOS OUVINTE ----------------------------------------------//
    private static void Init() {
        listaOuvintes.add(new Ouvinte(1, "Manuel Lopes", 240003571, 69, "Braga", 0, 0, 0));            //0
        listaOuvintes.add(new Ouvinte(2, "Manuel Teixeira", 250987456, 36, "Bragança", 0, 0, 0));      //1
        listaOuvintes.add(new Ouvinte(3, "Anabela Santos", 251254879, 61, "Malhadas", 0, 0, 0));    //2
        listaOuvintes.add(new Ouvinte(4, "Graciete Silva", 237456901, 71, "Leiria", 0, 0, 0));         //3
        listaOuvintes.add(new Ouvinte(5, "Dona Lurdes", 255567890, 81, "Aveiro", 0, 0, 0));            //4
        listaOuvintes.add(new Ouvinte(6, "Margarida Fonseca", 224897123, 66, "Porto", 0, 0, 0));    //5
        listaOuvintes.add(new Ouvinte(7, "Vitor Espadinha", 273897456, 77, "Faro", 0, 0, 0));          //6
        listaOuvintes.add(new Ouvinte(8, "Professor Marcelo", 252645871, 99, "Ovar", 0, 0, 0));        //7
        listaOuvintes.add(new Ouvinte(9, "António Costa", 262097456, 59, "Viseu", 0, 0, 0));           //8
        listaOuvintes.add(new Ouvinte(10, "Pedro Pedinte", 295512345, 64, "Caminha", 0, 0, 0));        //9
        listaOuvintes.add(new Ouvinte(11, "Joaquim Esteves", 221123183, 88, "Matosinhos", 0, 0, 0));   //10
        idProximoOuvinte = 12;
    }

//--------------------------------------------- FIM BASE DE DADOS OUVINTE --------------------------------------------//
}