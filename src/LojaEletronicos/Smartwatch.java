package LojaEletronicos;

public class Smartwatch extends Equipamento {

	private static final long serialVersionUID = 1L;
	private String pulseira;

	public Smartwatch(String nome, String marca, String modelo, int tela, String pulseira) {
		super(nome, marca, modelo, tela);
		this.pulseira = pulseira;
	}

	public String getPulseira() {
		return pulseira;
	}

	public String toString() {
		String retorno = super.toString();
		retorno += "Tipo da pulseira: " + this.pulseira + " \n";

		return retorno;
	}
}
