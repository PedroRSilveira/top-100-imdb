package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Backup {
	
	static Scanner sc = new Scanner(System.in);
	
	public Backup() {
	}
	
	private static String path = "filmes.txt";
	
	public static Filme[] lerLista() {
		Filme[] filmes = new Filme[100];
		String path = "filmes.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			for(int i=0; i<100; i++) {
				if(line != null) {
					String[] dados = line.split(",");
					Integer posicao = Integer.parseInt(dados[0]);
					String nome = dados[1];
	
					Filme filme = new Filme(nome, posicao);
					filmes[posicao - 1] = filme;
					
					line = br.readLine();
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return filmes;
	}
	
	public static void imprimirLista() {
		Filme[] filmes = lerLista();
		for(int i=0; i<100; i++) {
			if(filmes[i] != null) {
				System.out.println(filmes[i]);
			}
		}
	}
	
	private static void salvarLista(Filme[] filmes) throws IOException {
		
		FileWriter fw = new FileWriter(path, false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i=0; i<100; i++) {
			if(filmes[i] != null) {
				bw.write(filmes[i].converterParaString());
				bw.newLine();
			}
		}
		
		bw.close();
		fw.close();
	}
	
	private static boolean[] filmesAssistidos(Filme[] filmes) {
	    boolean[] assistidos = new boolean[100];

	    for (int i = 0; i < 100; i++) {
	        if (filmes[i] != null) {
	            int pos = filmes[i].getPosicao();
	            if (pos < 1 || pos > 100) {
	                System.out.println("Posição inválida encontrada: " + pos);
	            } else {
	                assistidos[pos - 1] = true; // Ajuste para pos - 1
	            }
	        }
	    }
	    return assistidos;
	}
	
	public static void adicionarFilme(Filme filme) throws IOException {
	    Filme[] filmes = lerLista();
	    
	    int pos = filme.getPosicao();
	    
	    if (pos < 1 || pos > filmes.length) {
	        System.out.println("Informe uma posição entre 1 e 100");
	        return;
	    }else {
	    	boolean[] assistidos = filmesAssistidos(filmes);
	    	if (assistidos[pos - 1] == true) {
		        System.out.println("Você já salvou um filme na posição " + pos);
		    } else {        
		        filmes[pos - 1] = filme;
		        assistidos[pos - 1] = true;
		    }
	    }
	    salvarLista(filmes);
	}
	
	public static void sortear() {
		boolean[] assistidos = filmesAssistidos(lerLista());
		int cont = 0;
		for(int i=0; i<100; i++) {
			if(assistidos[i] != false) {
				cont = 1;
			}
		}
		
		if(cont == 1) {
			Random random = new Random();
			int proximoFilme;
			
			do {
				proximoFilme = random.nextInt(100) + 1;
			}while(assistidos[proximoFilme - 1]);
			System.out.println("O próximo filme a ser assistido é: " + proximoFilme);
		}else {
			System.out.println("Você já completou a lista de filmes!");
		}
	}
	
	public static void editarFilme(int pos) throws IOException {
        Filme[] filmes = lerLista();
        boolean[] assistidos = filmesAssistidos(filmes);

        if (pos < 1 || pos > filmes.length) {
            System.out.println("Informe uma posição entre 1 e 100");
            return;
        }

        int index = pos - 1;

        if (!assistidos[index]) {
            System.out.println("O filme na posição " + pos + " ainda não foi assistido");
        } else {
            assistidos[index] = false;
            filmes[index] = null;

            System.out.print("Digite o nome do filme: ");
            String nome = sc.nextLine();

            System.out.print("Em qual posição quer colocar o filme? ");
            int nPos = sc.nextInt();

            
            sc.nextLine();

            if (nPos < 1 || nPos > filmes.length) {
                System.out.println("Informe uma nova posição entre 1 e 100");
                return;
            }

            int newIndex = nPos - 1;

            if (assistidos[newIndex]) {
                System.out.println("Você já salvou um filme na posição " + nPos);
            } else {
                Filme nFilme = new Filme(nome, nPos);
                filmes[newIndex] = nFilme;
                assistidos[newIndex] = true;

                salvarLista(filmes);
                System.out.println("Filme editado com sucesso!");
            }
        }
    }
	
	public static void excluirFilme(int pos) throws IOException {
		boolean[] assistidos = filmesAssistidos(lerLista());
		Filme[] filmes = lerLista();
		
		if (pos < 1 || pos > filmes.length) {
            System.out.println("Informe uma nova posição entre 1 e 100");
            return;
        }
		int index = pos - 1;
		if(assistidos[pos - 1] != false) {
	    	assistidos[index] = false;
	    	filmes[index] = null;
	    	
	    	System.out.println("Filme " + pos + " excluído com sucesso!");
	    }else {
	    	System.out.println("O filme " + pos + " ainda não foi assistido");
	    }
		salvarLista(filmes);
	}
	
	public static int contarFilmesAssistidos() {
		Filme[] filmes = lerLista();
		int cont = 0;
				
		for(Filme filme : filmes) {
			if(filme != null) {
				cont++;
			}
		}
		return cont;
	}
}