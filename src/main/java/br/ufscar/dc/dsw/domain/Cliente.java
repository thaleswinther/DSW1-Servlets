package br.ufscar.dc.dsw.domain;
import java.util.Date;

public class Cliente extends Usuario {

    private String CPF;
    private String telefone;
    private String sexo;
    private Date data_nascimento;

    public Cliente(Long id, String email, String senha, String nome, String papel, String CPF, String telefone, String sexo, Date data_nascimento) {
        super(id, email, senha, nome, papel);
        this.CPF = CPF;
		this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
	}

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.data_nascimento = dataNascimento;
    }

}
