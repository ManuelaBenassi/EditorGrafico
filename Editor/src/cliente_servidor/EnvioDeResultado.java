/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor;

/**
 *
 * @author Guilherme
 */
public class EnvioDeResultado extends Comunicado
{
    private String resultado;
    
    public EnvioDeResultado(String erro)
    {
        this.resultado = erro;
    }
    
    public String getResultado()
    {
        return this.resultado;
    }
}
