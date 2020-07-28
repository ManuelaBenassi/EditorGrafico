package server;

public class PedidoDeSalvamento extends Comunicado
{
	protected static final long serialVersionUID = 1L;
	
    private String emailDoDono;
    private String nomeDesenho;
    private String desenho;
    
    public PedidoDeSalvamento (String emailDoDono, String nomeDesenho, String desenho)
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
    
    public String getDesenho()
    {
    	return this.desenho;
    }
    
    public String toString ()
    {
        return ("" + this.emailDoDono + "||" + this.nomeDesenho + "||" + this.desenho);
    }
}

