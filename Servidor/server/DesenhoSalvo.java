package server;

import java.util.*;

public class DesenhoSalvo extends Comunicado
{
	protected static final long serialVersionUID = 1L;
	
    private Vector<String> desenho;

    public DesenhoSalvo (Vector<String> desenho)
    {
        this.desenho = desenho;
    }

    public Vector<String> getDesenho()
    {
        return this.desenho;
    }
    
    public String toString ()
    {
		return this.desenho.toString();
	}

}
