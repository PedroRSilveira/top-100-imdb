package entities;

public class Filme {
	
	private String name;
	private Integer posicao;
	
	public Filme(String name, Integer posicao) {
		this.name = name;
		this.posicao = posicao;
	}
	
	//Vai ser usado para salvar os filmes da forma correta no arquivo:
	public String converterParaString() {
		return posicao + "," + name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPosicao() {
		return posicao;
	}
	
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	@Override
	public String toString() {
		return posicao + ". " + name;
	}
}