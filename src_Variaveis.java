public class Variaveis{

    private volatile String a;

    // Jogador 1 variáveis
    private volatile int alt1 = 580;    // Posição y inicial
    private volatile int ataq1 = 200;   // Posição x inicial
    private volatile int vida1 = 4;     // Quantidade de vidas
    private volatile int estadoA = 0;   // Estados de ação -> 0: Sem ação, 1: Pulo, 2: Ataca
    private volatile int winner1 = 0;   // Vencedor se 1, Perdedor se 0

    // Jogador 2 variáveis
    private volatile int alt2 = 580;    // Posição y inicial
    private volatile int ataq2 = 720;   // Posição x inicial
    private volatile int vida2 = 4;     // Quantidade de vidas
    private volatile int estadoB = 0;   // Estados de ação -> 0: Sem ação, 1: Pulo, 2: Ataca
    private volatile int winner2 = 0;   // Vencedor se 1, Perdedor se 0

    public synchronized String getA() {
        return a;
    }

    public synchronized void setA(String a) {
        this.a = a;
    }

    // Variáveis de controle de estado: A = Jogador 1, B = Jogador 2
    public synchronized int getEstadoA() {
        return estadoA;
    }

    public synchronized void setEstadoA(int estado) {
        this.estadoA = estado;
    }

    public int getEstadoB() {
        return estadoB;
    }

    public void setEstadoB(int estadoB) {
        this.estadoB = estadoB;
    }

    // Altera e mostra posição vertical do salto dos Jogadores
    public synchronized int getAlt1() {
        return alt1;
    }

    public synchronized int getAlt2() {
        return alt2;
    }

    public synchronized void setAlt1(int alt1) {
        this.alt1 = alt1;
    }

    public synchronized void setAlt2(int alt2) {
        this.alt2 = alt2;
    }

    // Altera e mostra ataque dos Jogadores
    public synchronized int getAtaq1() {
        return ataq1;
    }

    public synchronized int getAtaq2() {
        return ataq2;
    }

    public synchronized void setAtaq1(int ataq1) {
        this.ataq1 = ataq1;
    }

    public synchronized void setAtaq2(int ataq2) {
        this.ataq2 = ataq2;
    }

    // Altera e mostra vida dos Jogadores
    public synchronized int getVida1() {
        return vida1;
    }

    public synchronized int getVida2() {
        return vida2;
    }

    public synchronized void setVida1(int vida1) {
        this.vida1 = vida1;
    }

    public synchronized void setVida2(int vida2) {
        this.vida2 = vida2;
    }

    // Controle para indicar quem foi o vencedor
    public synchronized int getWinner1() {
        return winner1;
    }

    public synchronized int getWinner2() {
        return winner2;
    }

    public synchronized void setWinner1(int winner1) {
        this.winner1 = winner1;
    }

    public synchronized void setWinner2(int winner2) {
        this.winner2 = winner2;
    }

}