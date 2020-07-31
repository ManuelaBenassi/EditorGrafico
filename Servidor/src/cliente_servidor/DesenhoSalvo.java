package cliente_servidor;

import java.util.*;

public class DesenhoSalvo extends Comunicado
{
    /*
        Classe que extends de Comnicado.
        Serve para orientar que tipo de troca de dados será feita entre o cliente e o servidor.
        @params:
            Vector<String> desenho --> contém todos os 'toString()' de cada figura que será printada na tela
   */   
	
    private Vector<String> desenho;

    public DesenhoSalvo (Vector<String> desenho)
    {
        this.desenho = desenho;
    }

    public Vector<String> getDesenho()
    {
        return this.desenho;
    }
    
    @Override
    public String toString ()
    {
		return this.desenho.toString();
	}

}
