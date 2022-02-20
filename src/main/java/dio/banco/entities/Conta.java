package dio.banco.entities;

public abstract class Conta {
    private static int SEQUENCIAL = 1001;
    protected int numero;
    protected int agencia;
    protected double saldo;
    protected int codBanco;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public Conta(int agencia, int codBanco, Cliente cliente) {
        this.numero = SEQUENCIAL++;;
        this.agencia = agencia;
        this.saldo = 0;
        this.codBanco = codBanco;
        this.cliente = cliente;
    }

    public int getNumero() {
        return numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(int codBanco) {
        this.codBanco = codBanco;
    }

    public boolean sacar(double valor) {
        if (valor <= 0 || valor > saldo){
            return false;
        }
        saldo -= valor;
        return true;
    }

    public boolean depositar(double valor){
        if(valor <= 0) return false;

        saldo += valor;
        return true;
    }

    public boolean transferir(double valor, Conta destinataria){
        if (valor <= 0) return false;
        else if (valor > saldo) return false;
        else if (destinataria == null) return false;

        sacar(valor);
        destinataria.depositar(valor);
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
