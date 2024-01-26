package application;

import java.io.IOException;
import java.util.Scanner;

import entities.Arquivo;
import entities.Filme;

public class Program {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		Arquivo.imprimirLista();
		
		System.out.println();
		System.out.println("Teste de inserção:");
		
		System.out.print("Digite o nome do filme: ");
		String nome = sc.nextLine();
		System.out.print("Digite a posição no ranking do filme: ");
		Integer pos = sc.nextInt();
		
		Filme filme = new Filme(nome, pos);
		
		Arquivo.adicionarFilme(filme);
		
		System.out.println();
		
		
		Arquivo.imprimirLista();
		
		sc.close();
	}
}