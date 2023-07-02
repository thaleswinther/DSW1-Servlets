package br.ufscar.dc.dsw.domain;

public class Usuario { 

	private Long id;
	private String email;
	private String senha;
	private String nome;
	private String papel;
	
	public Usuario(long id) {
		this.id = id;
	}
	
	public Usuario(String email, String senha, String nome, String papel) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
	}
	
	public Usuario(Long id, String email, String senha, String nome, String papel) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String login) {
		this.email = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String password) {
		this.senha = password;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
}