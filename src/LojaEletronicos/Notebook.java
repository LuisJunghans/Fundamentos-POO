package LojaEletronicos;

public class Notebook extends Equipamento {

	private static final long serialVersionUID = 1L;
	private String processador;
	private int ram;
	private int hd;

	public Notebook(String nome, String marca, String modelo, int tela, String processador, int ram, int hd) {
		super(nome, marca, modelo, tela);
		this.processador = processador;
		this.ram = ram;
		this.hd = hd;
	}

	public String getProcessador() {
		return processador;
	}

	public int getRam() {
		return ram;
	}

	public int getHd() {
		return hd;
	}

	public String toString() {
		String retorno = super.toString();
		retorno += "Tamanho do HD: " + this.hd + " \n";
		retorno += "Processador: " + this.processador + "\n";
		retorno += "Memoria RAM: " + this.ram + "\n";

		return retorno;
	}
}
