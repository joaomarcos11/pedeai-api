package org.jfm.domain.entities;

import java.time.Instant;

public class Pagamento {
    private int id;
    private Instant efetuacao;
    private Instant confirmacao;

    public Pagamento() {
        super();
    }

    public Pagamento(int id, Instant efetuacao, Instant confirmacao) {
        this.id = id;
        this.efetuacao = efetuacao;
        this.confirmacao = confirmacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getEfetuacao() {
        return efetuacao;
    }

    public void setEfetuacao(Instant efetuacao) {
        this.efetuacao = efetuacao;
    }

    public Instant getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(Instant confirmacao) {
        this.confirmacao = confirmacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagamento other = (Pagamento) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
