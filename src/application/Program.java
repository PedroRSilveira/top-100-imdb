package application;

import java.io.IOException;
import java.util.Scanner;

import entities.Arquivo;
import entities.Filme;

public class Program {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		if(Arquivo.contarFilmesAssistidos() < 100) {
			System.out.println("\t\t\tSeja bem vindo ao sistema de registros do top 100 filmes mais bem avaliados do IMDb\n"
							  + "\t\t\t\tSeu objetivo é assistir 100 filmes, até agora você assistiu " + Arquivo.contarFilmesAssistidos() + " filmes\n");
		}else {
			System.out.println("\t\t\tSeja bem vindo ao sistema de registros do top 100 filmes mais bem avaliados do IMDb\n"
					  + "\t\t\t\tParabéns, você assistiu os 100 filmes mais bem avaliados do IMDb");
		}
		int opcao;
		do {
			System.out.println("O que quer fazer agora?"
					  + "\n1. Ver lista de filmes assistidos"
					  + "\n2. Adicionar filme a lista de assistidos"
					  + "\n3. Editar lista de filmes assistidos"
					  + "\n4. Sortear um filme para ser assistido"
					  + "\n5. Encerrar programa\n");
			
			opcao = sc.nextInt();
			System.out.println();
			
			switch(opcao) {
			case 1:
				Arquivo.imprimirLista();
				System.out.println();
				break;
				
			case 2:
				sc.nextLine();
				System.out.print("Digite o nome do filme: ");
				String nome = sc.nextLine();
				System.out.print("Digite a posição no ranking do filme: ");
				Integer pos = sc.nextInt();
				Filme filme = new Filme(nome, pos);
				Arquivo.adicionarFilme(filme);
				System.out.println();
				break;
				
			case 3:
				System.out.print("Qual filme (posição) quer editar? ");
				int posfilme = sc.nextInt();
				if(posfilme < 0 || posfilme > 100) {
					System.out.println("Valor inválido, escolha um número entre 1 e 100\n");
				}else {
					System.out.println("Você quer editar o nome/posição ou excluir um filme assistido?"
									  + "\n1. Nome/Posição"
									  + "\n2. Excluir"
									  + "\n3. Cancelar");
					int opc = sc.nextInt();
					if(opc == 1) {
						Arquivo.editarFilme(posfilme);
					}else if(opc == 2) {
						Arquivo.excluirFilme(posfilme);
					}else {
						System.out.println("Operação cancelada");
					}
					
					System.out.println();
				}
				break;
				
			case 4:
				Arquivo.sortear();
				System.out.println();
				break;
			
			case 5:
				System.out.println("Encerrando programa");
				break;
				
			default:
				System.out.println("Opção inválida, escolha um número entre 1 e 5");	
				System.out.println();
			}
			
		}while(opcao != 5);

		System.out.println("Programa finalizado");
		sc.close();
	}
}