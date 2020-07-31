package cliente_servidor;

/**
 * Essa classe tem por objetivo enviar ao cliente qual foi o resultado de sua busca no servidor
 * @params String resultado
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
