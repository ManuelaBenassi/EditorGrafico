package cliente;

public class PedidoDeDesenhoSalvo extends Comunicado
{
	protected static final long serialVersionUID = 1L;
	
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
