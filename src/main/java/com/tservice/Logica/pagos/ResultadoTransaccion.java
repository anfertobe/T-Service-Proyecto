/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tservice.Logica.pagos;

/**
 *
 * @author andres
 */
public class ResultadoTransaccion {

    private String resultado;
    private int codigo;

    public ResultadoTransaccion(String resultado, int codigo) {
        this.resultado = resultado;
        this.codigo = codigo;
    }
    
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
 

    
}
