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
import java.util.Random;
import java.util.Scanner;

public class Arquivo {
	
	static Scanner sc = new Scanner(System.in);
	
	public Arquivo() {
	}
	
	private static String path = "filmes.txt";
	
	public static List<Filme> lerLista() {
		List<Filme> list = new ArrayList<>();
		String path = "filmes.txt";

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

	static List<Filme> filmes = lerLista();
	
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
	
	private static List<Integer> filmesAssistidos(List<Filme> filmes) {
		List<Integer> assistidos = new ArrayList<Integer>();
		for(Filme filme : filmes) {
			int pos = filme.getPosicao();
			assistidos.add(pos);
		}
		return assistidos;
	}
	
	static List<Integer> assistidos = filmesAssistidos(lerLista());
	
	public static void sortear() {
		if(assistidos.size() < 100) {
			Random random = new Random();
			int proximoFilme;
			
			do {
				proximoFilme = random.nextInt(100) + 1;
			}while(assistidos.contains(proximoFilme));
			
			System.out.println("O próximo filme a ser assistido é: " + proximoFilme);
		}else {
			System.out.println("Você já completou a lista de filmes!");
		}
	}
	
	public static void editarFilme(int pos) throws IOException {
		if(!assistidos.contains(pos)) {
			System.out.println("O filme " + pos + " ainda não foi assistido");
		}else {
			assistidos.remove(pos - 1);
			
			filmes.remove(pos - 1);
			
			System.out.print("Digite o nome do filme: ");
			String nome = sc.nextLine();
			
			System.out.println("Em qual posição quer colocar o filme?");
			int nPos = sc.nextInt();
			
			Filme nFilme = new Filme(nome, nPos);
			adicionarFilme(nFilme);
		}
	}
	
	public static void excluirFilme(int pos) throws IOException {
	    
		if(assistidos.get(pos) != null){	    	
	        System.out.println("Filme removido com sucesso.");
	    } else {
	        System.out.println("Filme não encontrado.");
	    }
	}


	
}