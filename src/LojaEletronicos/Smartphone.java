package LojaEletronicos;

public class Smartphone extends Equipamento {

	private static final long serialVersionUID = 1L;
	private int chip;

	public Smartphone(String nome, String marca, String modelo, int tela, int chip) {
		super(nome, marca, modelo, tela);
		this.chip = chip;
	}

	public int getChip() {
		return chip;
	}
	public String toString() {
		String retorno = super.toString();
		retorno += "Numero de chips: " + this.chip + " \n";

		return retorno;
	}

}
