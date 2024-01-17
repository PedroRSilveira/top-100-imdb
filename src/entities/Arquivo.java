package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	
	public Arquivo() {
	}
	
	static List<Filme> list = new ArrayList<>();

	private static List<Filme> lerLista() {
		String path = "C:\\temp\\filmes.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] dados = line.split(",");
				Integer posicao = Integer.parseInt(dados[0]);
				String nome = dados[1];

				Filme filme = new Filme(nome, posicao);
				list.add(filme);
				
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public static void imprimirLista() {
		
		Arquivo.lerLista();
		
		for (Filme filme : list) {
			System.out.println(filme);
		}
	}
	
}