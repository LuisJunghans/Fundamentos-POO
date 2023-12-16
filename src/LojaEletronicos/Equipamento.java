package LojaEletronicos;

import java.io.Serializable;

public abstract class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String marca;
	private String modelo;
	private int tela;
	protected String tipo;

	public Equipamento(String nome, String marca, String modelo, int tela) {
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.tela = tela;
	}

	public String toString() {
		String retorno = "";
		retorno += "Nome: " + this.nome + "\n";
		retorno += "Marca: " + this.marca + "\n";
		retorno += "Modelo: " + this.modelo + "\n";
		retorno += "Tamanho da tela: " + this.tela + " polegadas\n";

		return retorno;
	}
}
