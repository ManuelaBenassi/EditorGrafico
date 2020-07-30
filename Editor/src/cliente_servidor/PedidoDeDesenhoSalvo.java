package cliente_servidor;

public class PedidoDeDesenhoSalvo extends Comunicado
{
    /*
        Essa clase tem como objetivo fazer a solicitação de um desenho de um determinado email
        @params:
            String emailDoDono,
            String nomeDesenho
    */
	
	private String emailDoDono, nomeDesenho;
	
	public PedidoDeDesenhoSalvo(String email, String nome)
	{
            this.emailDoDono = email;
            this.nomeDesenho = nome;
	}
	
	public String getEmailDoDono()
	{
            return this.emailDoDono;
	}
	
	public String getNomeDesenho()
	{
            return this.nomeDesenho;
	}
	
	public String toString()
	{
            return ("" + this.emailDoDono + "||" + this.nomeDesenho);
	}
}
