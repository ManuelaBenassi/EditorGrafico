package cliente_servidor;

import java.util.*;


public class Servidor
{
    public static String PORTA_PADRAO = "8000";
	
    public static void main (String[] args)
    {
        String porta=Servidor.PORTA_PADRAO;

        ArrayList<Parceiro> usuarios =
        new ArrayList<Parceiro> ();

        AceitadoraDeConexao aceitadoraDeConexao=null;
        try
        {
            aceitadoraDeConexao =
            new AceitadoraDeConexao (porta, usuarios);
            aceitadoraDeConexao.start();
            System.out.println("O servidor est� no ar na porta " + Servidor.PORTA_PADRAO + ". Aproveite!");
        }
        catch (Exception erro)
        {
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }

    }
}

