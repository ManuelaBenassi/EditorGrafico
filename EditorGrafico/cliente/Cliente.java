package cliente;

import java.net.*;
import java.util.*;
import java.io.*;
import editor.Janela;

public class Cliente
{
	public Cliente(String emailDoDono, String nomeDesenho, Vector<String> desenho, Janela janela)
	{	
        Socket conexao=null;
        try
        {
            String host = "localhost";
            int    porta= 2000;
            conexao = new Socket (host, porta);
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor=null;
        try
        {
            transmissor =
            new ObjectOutputStream(
            conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
        }

        ObjectInputStream receptor=null;
        try
        {
            receptor =
            new ObjectInputStream(
            conexao.getInputStream());
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
        }

        Parceiro servidor=null;
        try
        {
            servidor =
            new Parceiro (conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

		try
		{
			if (desenho != null)
			{
				servidor.receba (new PedidoDeSalvamento (emailDoDono, nomeDesenho, desenho));
				System.out.println("Enviou o pedido");
			}
			else 
			{
				servidor.receba (new PedidoDeDesenhoSalvo (emailDoDono, nomeDesenho));
				
				DesenhoSalvo resultado = (DesenhoSalvo)servidor.envie ();
				janela.recebaDesenho(resultado.getDesenho());
			}
		}
		catch (Exception erro)
		{
			System.err.println ("Erro de comunicacao com o servidor;");
			System.err.println ("Tente novamente!");
			System.err.println ("Caso o erro persista, termine o programa");
			System.err.println ("e volte a tentar mais tarde!\n");
		}
    }
}