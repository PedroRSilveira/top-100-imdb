package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Arquivo {
	
	public Arquivo() {
	}
	
	private static String path = "C:\\temp\\filmes.txt";
	
	private static List<Filme> lerLista() {
		List<Filme> list = new ArrayList<>();
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
		
		List<Filme> filmes = lerLista();
		Collections.sort(filmes, Comparator.comparing(Filme::getPosicao));
		
		for (Filme filme : filmes) {
			System.out.println(filme);
		}
	}
	
	private static void salvarLista(List<Filme> filmes) throws IOException {
		
		FileWriter fw = new FileWriter(path, false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(Filme filme : filmes) {
			bw.write(filme.converterParaString());
			bw.newLine();
		}
		
		bw.close();
		fw.close();
	}
	
	public static void adicionarFilme(Filme filme) throws IOException {
		List<Filme> filmes = lerLista();
		filmes.add(filme);
		salvarLista(filmes);
	}
	
}