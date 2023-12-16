package LojaEletronicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class LojaEletronicos {
	private ArrayList<Equipamento> equipamentos;

	public LojaEletronicos() {
		this.equipamentos = new ArrayList<Equipamento>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Registre " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Smartphone leSmartphone (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Marca", "Modelo", "Tamanho da tela", "Quantidade de chips suportados"};
		valores = leValores (nomeVal);

		int chip = this.retornaInteiro(valores[4]);
		int tela = this.retornaInteiro(valores[3]);

		Smartphone smartphone = new Smartphone (valores[0],valores[1],valores[2],tela,chip);
		return smartphone;
	}

	public Notebook leNotebook (){

		String [] valores = new String [7];
		String [] nomeVal = {"Nome", "Marca", "Modelo", "Tamanho da tela", "Processador", "Memoria RAM", "HD"};
		valores = leValores (nomeVal);

		int tela = this.retornaInteiro(valores[3]);
		int ram = this.retornaInteiro(valores[5]);
		int hd = this.retornaInteiro(valores[6]);

		Notebook notebook = new Notebook (valores[0],valores[1],valores[2],tela,valores[4],ram,hd);
		return notebook;
	}

	public Smartwatch leSmartwatch () {

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Marca", "Modelo", "Tamanho da tela", "Tipo de pulseira"};
		valores = leValores (nomeVal);

		int tela = this.retornaInteiro(valores[3]);

		Smartwatch smartwatch = new Smartwatch (valores[0],valores[1],valores[2],tela,valores[4]);
		return smartwatch;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta transformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		//int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaEquipamentos (ArrayList<Equipamento> equipamentos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\lojaeletronicos.dados"));
			for (int i=0; i < equipamentos.size(); i++)
				outputStream.writeObject(equipamentos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Fecha o ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Equipamento> recuperaEquipamentos (){
		ArrayList<Equipamento> equipamentosTemp = new ArrayList<Equipamento>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\lojaeletronicos.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Equipamento) {
					equipamentosTemp.add((Equipamento) obj);
				}   
			}          
		} catch (EOFException ex) { //Quando o fim do arquivo (EOF) acontecer
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com equipamentos NAO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Fecha o ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return equipamentosTemp;
		}
	}

	public void menuLojaEletronicos (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Loja de Eletronicos\n" +
					"Opções:\n" + 
					"1. Registrar equipamento\n" +
					"2. Exibir equipamento\n" +
					"3. Limpar equipamento\n" +
					"4. Gravar equipamentos\n" +
					"5. Recuperar equipamentos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de equipamentos\n" +
						"Opções:\n" + 
						"1. Notebook\n" +
						"2. Smartphone\n"+
						"3. Smartwatch\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: equipamentos.add((Equipamento)leNotebook());
				break;
				case 2: equipamentos.add((Equipamento)leSmartphone());
				break;
				case 3: equipamentos.add((Equipamento)leSmartwatch());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Equipamento para entrada NAO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Registre equipamentos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < equipamentos.size(); i++)	{
					dados += equipamentos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Registre equipamentos primeiramente");
					break;
				}
				equipamentos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Registre equipamentos primeiramente");
					break;
				}
				salvaEquipamentos(equipamentos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				equipamentos = recuperaEquipamentos();
				if (equipamentos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo LOJA DE ELETRONICOS");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		LojaEletronicos equip = new LojaEletronicos();
		equip.menuLojaEletronicos();

	}

}
