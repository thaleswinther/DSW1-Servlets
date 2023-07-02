package br.ufscar.dc.dsw.domain;

public class Locadora extends Usuario{

    private String CNPJ;
    private String cidade;
    

    public Locadora(Long id, String email, String senha, String nome, String papel, String CNPJ, String cidade) {
        super(id, email, senha, nome, papel);
        this.CNPJ = CNPJ;
		this.cidade = cidade;
       
	}


    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
