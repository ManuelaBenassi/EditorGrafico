package cliente_servidor;

import java.util.*;

public class PedidoDeSalvamento extends Comunicado
{
    /*
        Essa classe tem por objetivo indicar que o cliente esá fazendo um pedido para ter um desenho salvo
        @params:
            String emailDoDono,
            String nomeDesenho,
            Vector<String> desenho
    */
	
    private String emailDoDono;
    private String nomeDesenho;
    private Vector<String> desenho;
    
    public PedidoDeSalvamento (String emailDoDono, String nomeDesenho, Vector<String> desenho)
    {
        this.emailDoDono = emailDoDono;
        this.nomeDesenho = nomeDesenho;
        this.desenho = desenho;
    }
    
    public String getEmailDoDono()
    {
    	return this.emailDoDono;
    }
    
    public String getNomeDesenho()
    {
    	return this.nomeDesenho;
    }
    
    public Vector<String> getDesenho()
    {
    	return this.desenho;
    }
    
    public String toString ()
    {
        return ("" + this.emailDoDono + "||" + this.nomeDesenho + "||" + this.desenho.toString());
    }
}

