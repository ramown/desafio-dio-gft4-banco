package dio.banco.entities;

public class ContaCorrente extends Conta{

    private double limite;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    public ContaCorrente(double limite, Cliente cliente) {
        super(cliente);
        this.limite = limite;
    }

    public ContaCorrente(int agencia, int codBanco, double limite, Cliente cliente) {
        super(agencia, codBanco, cliente);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor){
        if (valor <= 0) return false;
        else if (valor > super.saldo){
            double diferenca = valor - super.saldo;
            if (diferenca > limite) {
                return false;
            }
            super.saldo = 0;
            limite -= diferenca;
            return true;
        }
        super.saldo -= valor;
        return true;
    }

}
