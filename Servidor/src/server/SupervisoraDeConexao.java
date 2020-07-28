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
            		
            		
            		if(!FigurasDosDesenhos.cadastrado(desenho.getId()))
            			FigurasDosDesenhos.incluir(new FiguraDoDesenho(1, pedido.getDesenho(), desenho.getId()));
            		else
            			FigurasDosDesenhos.alterar(new FiguraDoDesenho(1, pedido.getDesenho(), desenho.getId()));
            		
            		usuario.adeus();
            	}
            	
            	if(comunicado instanceof PedidoDeDesenhoSalvo)
            	{
            		PedidoDeDesenhoSalvo pedido = (PedidoDeDesenhoSalvo) comunicado;
            		
            		Desenho desenho = Desenhos.getDesenho(pedido.getEmailDoDono(), pedido.getNomeDesenho());
            		FiguraDoDesenho figura = FigurasDosDesenhos.getFiguraDoDesenho(desenho.getId());
            		
            		Vector<String> retorno = new Vector<String>();
            		String[] quebrado = figura.getFigura().split(";");
        			
        			for(int i = 0; i < quebrado.length; i++)
        				retorno.add(quebrado[i]);
        				
        			usuario.receba(new DesenhoSalvo(retorno));
        			
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
