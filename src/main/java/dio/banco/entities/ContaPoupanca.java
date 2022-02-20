package dio.banco.entities;

public class ContaPoupanca extends Conta{

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public ContaPoupanca(int agencia, int codBanco, Cliente cliente) {
        super(agencia, codBanco, cliente);
    }

}
