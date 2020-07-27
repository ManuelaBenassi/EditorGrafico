package cliente;

public class Resultado extends Comunicado
{
	protected static final long serialVersionUID = 1L;
	
    private double valorResultante;

    public Resultado (double valorResultante)
    {
        this.valorResultante = valorResultante;
    }

    public double getValorResultante ()
    {
        return this.valorResultante;
    }
    
    public String toString ()
    {
		return (""+this.valorResultante);
	}

}
