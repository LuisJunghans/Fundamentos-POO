package LojaEletronicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LojaEletronicos1 {

	private ArrayList<Equipamento> equipamentos;


	public LojaEletronicos1( ) {
		this.equipamentos = new ArrayList<Equipamento>();
	}

	public void adicionaEquipamento(Equipamento equi) {
		this.equipamentos.add(equi);
	}

	public void listarEquipamentos() {
		for(Equipamento equi:equipamentos) {
			System.out.println(equi.toString());
		}
		System.out.println("Total = " + this.equipamentos.size() + " equipamentos listados com sucesso!\n");
	}
	
	public void excluirEquipamento(Equipamento equi) {
		if (this.equipamentos.contains(equi)) {
			this.equipamentos.remove(equi);
			System.out.println("[Equipamento " + equi.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Equipamento inexistente!\n");
	}

	public void excluirEquipamentos() {
		equipamentos.clear();
		System.out.println("Equipamentos excluidos com sucesso!\n");
	}
	public void gravarEquipamentos()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\equipamentos.dat"));
			for(Equipamento equi:equipamentos) {
				outputStream.writeObject(equi);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void recuperarEquipamentos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\equipamentos.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Smartphone)  
					this.equipamentos.add((Smartphone)obj);
				else if (obj instanceof Notebook)  
					this.equipamentos.add((Notebook)obj); 
				else if (obj instanceof Smartwatch)
					this.equipamentos.add((Smartwatch)obj);
					
			}
		}catch (EOFException ex) {     // Quando o programa chegar ao final
			System.out.println ("Final do programa alcançado");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Equipamentos recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

	}

}
