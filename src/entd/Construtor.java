package entd;

public class Construtor {
	
	private int id;
	private String email;
	private String senha;
	
	public Construtor(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Construtor(int id, String email, String senha) {
		this.id = id;
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
