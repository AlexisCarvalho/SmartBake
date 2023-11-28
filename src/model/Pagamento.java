package model;

import model.bean.ProdutoVendido;
import util.RetornarTroco;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Pagamento {
    BigDecimal saldo, troco;

    public Pagamento() {
        saldo = new BigDecimal("0");
        troco = new BigDecimal("0");
    }

    public void calcularValorAReceber(List<ProdutoVendido> produtoVendidoList) {
    	saldo = BigDecimal.ZERO;
    	for (ProdutoVendido produto : produtoVendidoList) {
            saldo = saldo.add(new BigDecimal(produto.getValorPago()));
        }
    }
    
    public void setSaldo(String saldo) {
    	this.saldo = new BigDecimal(saldo);
    }

    public String getValorAReceber() {
        saldo = saldo.setScale(2, RoundingMode.HALF_EVEN);
        return String.valueOf(saldo);
    }

    public String getTroco() {
        troco = troco.setScale(2, RoundingMode.HALF_EVEN);
        return String.valueOf(troco);
    }

    public void receber(String valor) throws RetornarTroco {
        saldo = saldo.subtract(new BigDecimal(valor));
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RetornarTroco();
        }
    }

    public void calcularTroco() {
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            troco = saldo.multiply(new BigDecimal("-1"));
            saldo = new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        }
    }
}
