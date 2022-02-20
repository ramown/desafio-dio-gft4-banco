package dio.banco.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayTest {
	
	private Conta conta;
	private Cliente cliente;

	@BeforeEach
	void setUp() {
		cliente = new Cliente("José da Silva");
		conta = new ContaPoupanca(cliente);
	}

	@Test
	void testDeveRetornarFalseSeValorDoSaqueIgualAZero() {
		boolean result = conta.sacar(0);
		assertFalse(result);
	}

	@Test
	void testDeveRetornarFalseSeValorDoSaqueMenorQueZero() {
		boolean result = conta.sacar(-1.0);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorDoSaqueMaiorQueSaldo() {
		boolean result = conta.sacar(conta.getSaldo() + 1.0);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorDoDepositoIgualAZero() {
		boolean result = conta.depositar(0);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorDoDepositoMenorQueZero() {
		boolean result = conta.depositar(-1.0);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarTrueSeValorDoSaqueMaiorQueZero() {
		boolean result = conta.depositar(200.0);
		assertTrue(result);
	}
	
	@Test
	void testSaldoDeveTer200AposDeposito() {
		double expected = 200.0;
		conta.depositar(expected);
		double result = conta.getSaldo();
		
		assertEquals(expected, result, 0);
	}
	
	@Test
	void testSaldoDeveTer75AposDepositoESaque() {
		double expected = 25.0;
		conta.depositar(100);
		conta.sacar(75.0);
		double result = conta.getSaldo();
		
		assertEquals(expected, result, 0);
	}
	
	@Test
	void testDeveRetornarFalseSeValorMaiorQueSaldo() {
		Cliente cli = new Cliente("Maria José");
		Conta contaDestino = new ContaPoupanca(cli);
		
		conta.depositar(300);
		boolean result = conta.transferir(conta.getSaldo() + 1, contaDestino);
		
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorMenorQueZero() {
		Cliente cli = new Cliente("Maria José");
		Conta contaDestino = new ContaPoupanca(cli);
		
		conta.depositar(300);
		boolean result = conta.transferir(-1, contaDestino);
		
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorIguaAeZero() {
		Cliente cli = new Cliente("Maria José");
		Conta contaDestino = new ContaPoupanca(cli);
		
		conta.depositar(300);
		boolean result = conta.transferir(0, contaDestino);
		
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornaRFalseSeContaDestinoNaoExistir() {
		Conta contaDestino = null;
		
		conta.depositar(300);
		boolean result = conta.transferir(1.0, contaDestino);
		
		assertFalse(result);
	}
	@Test
	void testSaldoDoClienteDeveTer100AposTransferenciaE200NaOutraConta() {
		Cliente cli = new Cliente("Maria José");
		Conta contaDestino = new ContaPoupanca(cli);
		
		double expected = 100.0;
		conta.depositar(300);
		conta.transferir(200, contaDestino);
		double result = conta.getSaldo();
		
		assertEquals(expected, result, 0);
		assertEquals(200, contaDestino.getSaldo(), 0);
	}
	
	
	@Test
	void testDevePossui4Contas() {
		Banco banco = new Banco("Banco DIO");
		int agencia = 123;
		
		Conta cc1 = new ContaCorrente(cliente);
		Conta cc2 = new ContaCorrente(agencia, banco.getCodigo(), 0, cliente);
		Conta cp1 = new ContaPoupanca(agencia, banco.getCodigo(), cliente);
		
		banco.getContas().addAll(Arrays.asList(cc1, cc2, conta, cp1));
		
		int expected = 4;
		int result = banco.getContas().size();
		
		assertEquals(expected, result);
	}

	@Test
	void testDeveRetornarFalseSeValorDoSaqueIgualAZeroContaCorrente() {
		Conta cc = new ContaCorrente(1000, cliente);
		boolean result = cc.sacar(0);
		assertFalse(result);
	}

	@Test
	void testDeveRetornarFalseSeValorDoSaqueMenorQueZeroContaCorrente() {
		Conta cc = new ContaCorrente(1000, cliente);
		boolean result = cc.sacar(-1);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarFalseSeValorDoSaqueMaiorQueSaldoELimiteContaCorrente() {
		ContaCorrente cc = new ContaCorrente(1000, cliente);
		boolean result = cc.sacar(cc.getLimite() + cc.getSaldo() + 1);
		assertFalse(result);
	}
	
	@Test
	void testDeveRetornarTrueSeValorDoSaqueMaiorQueSaldoEMenorQueLimiteContaCorrente() {
		ContaCorrente cc = new ContaCorrente(1000, cliente);
		boolean result = cc.sacar(cc.getLimite() - 10 + cc.getSaldo());
		assertTrue(result);
	}

	@Test
	void testDeveRetornarTrueSeValorDoSaqueMenorQueSaldoContaCorrente() {
		ContaCorrente cc = new ContaCorrente(1000, cliente);
		cc.depositar(250.0);
		boolean result = cc.sacar(cc.getSaldo()-100.0);
		assertTrue(result);
	}
	
	@Test
	void testDeveRetornar101NaCriacaoDoPrimeiroBanco() {
		Banco banco = new Banco();
		assertEquals(101, banco.getCodigo());
	}
}
