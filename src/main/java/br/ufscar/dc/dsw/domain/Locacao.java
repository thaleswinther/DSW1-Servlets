package br.ufscar.dc.dsw.domain;
import java.time.LocalDateTime;

public class Locacao {

    private Cliente cliente;
    private Locadora locadora;
    private LocalDateTime data_hora;

    public Locacao(Cliente cliente, Locadora locadora, LocalDateTime data_hora) {
        this.cliente = cliente;
        this.locadora = locadora;
        this.data_hora = data_hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }

    public LocalDateTime getDataHora() {
        return data_hora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.data_hora = dataHora;
    }

}
