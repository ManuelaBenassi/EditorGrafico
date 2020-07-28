package server;

import java.io.*;
import bd.daos.*;
import bd.dbos.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;;)
            {
            	Comunicado comunicado = this.usuario.envie();
            	
            	if(comunicado == null)
            		return;
            	
            	if(comunicado instanceof PedidoDeSalvamento)
            	{
            		PedidoDeSalvamento pedido = (PedidoDeSalvamento) comunicado;
            		Desenho desenho = new Desenho(pedido.getEmailDoDono(), pedido.getNomeDesenho());
            		
            		if(Desenhos.cadastrado(desenho.getEmailDoDono(), desenho.getNome()))
            		{
            			Desenho desenhoASerAlterado = Desenhos.getDesenho(desenho.getEmailDoDono(), desenho.getNome());	
            			Desenhos.alterar(desenhoASerAlterado);
            		}
            		else
            		{
            			Desenhos.incluir(desenho);
            		}
            		
            		for(int i = 0; i < pedido.getDesenho().size(); i++)
            			FigurasDosDesenhos.incluir(new FiguraDoDesenho(i+1, pedido.getDesenho().get(i), desenho.getId()));
            		
            		usuario.adeus();
            	}
            	
            	if(comunicado instanceof PedidoDeDesenhoSalvo)
            	{
            		PedidoDeDesenhoSalvo pedido = (PedidoDeDesenhoSalvo) comunicado;
            		
            		Desenho desenho = Desenhos.getDesenho(pedido.getEmailDoDono(), pedido.getNomeDesenho());   				
        				
        			usuario.receba(new DesenhoSalvo(FigurasDosDesenhos.getFiguraDoDesenho(desenho.getId())));
        			
        			usuario.adeus();
            	}
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
