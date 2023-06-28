package entd;

public class Contrato {
	
	private int id;
	private String descricao;
	private int orcamento;
	private int prioridade;
	private Construtor construtor;
	
	
	public Contrato(  String descricao,int orcamento , int prioridade, Construtor construtor ) {
		this.descricao = descricao;
		this.orcamento = orcamento;
		this.prioridade = prioridade;
		this.construtor = construtor;
	}
	
	public Contrato(int id, String descricao,int orcamento , int prioridade,Construtor construtor ) {
		this.id = id;
		this.descricao = descricao;
		this.orcamento = orcamento;
		this.prioridade = prioridade;
		
		this.construtor = construtor;
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getOrcamento() {
		return orcamento;
	}
	
	public void setOrcamento(int orcamento) {
		this.orcamento = orcamento;
	}
	
	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	
	

	public Construtor getCont() {
		return construtor;
	}

	public void setCont(Construtor construtor) {
		this.construtor = construtor;
	}
	
	
	

}
