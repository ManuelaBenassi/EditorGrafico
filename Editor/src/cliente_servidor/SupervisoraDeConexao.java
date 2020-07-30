package cliente_servidor;

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
        catch (Exception erro)
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
            synchronized(this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }

            for(;;)
            {
                Comunicado comunicado = this.usuario.envie(); //espera que o usuario envie o comunicado
            	
            	if(comunicado == null)
            	{
                    return;
            	}
            	
            	if(comunicado instanceof PedidoDeSalvamento) //caso seja um pedido de salvamento
            	{
                    
                    PedidoDeSalvamento pedido = (PedidoDeSalvamento) comunicado;
                    Desenho desenho = new Desenho(pedido.getEmailDoDono(), pedido.getNomeDesenho()); //instancio um novo desenho
            		
                    try
                    {
            		if(Desenhos.cadastrado(desenho.getEmailDoDono(), desenho.getNome())) //se desenho já estiver cadastrado
            		{   
                            Desenho desenhoASerAlterado = Desenhos.getDesenho(desenho.getEmailDoDono(), desenho.getNome()); //pesquiso ele no banco e guardo	
                            desenho = (Desenho)desenhoASerAlterado.clone(); //armazeno o clone dele na variavel desenho
                            FigurasDosDesenhos.excluir(desenho.getId());//apago as figuras salvas anteriormente refrenetes aquele desenho
            		}
                        else //se não for cadastrado
            		{
                            Desenhos.incluir(desenho); //incluo ele na tabela
                            Desenho desenhoASerAlterado = Desenhos.getDesenho(desenho.getEmailDoDono(), desenho.getNome());//pesquiso o desenho que acabei de incluir	
                            desenho = (Desenho) desenhoASerAlterado.clone(); //guardo seu clone na variavel desenho	
            		}
            		
            		for(int i = 0; i < pedido.getDesenho().size(); i++) //for para percorrer todo o Vector que foi enviado através do pedido
                        {
                            FigurasDosDesenhos.incluir(new FiguraDoDesenho(i+1, pedido.getDesenho().get(i), desenho.getId())); //incluo uma a uma na tabela FigurasDosDesenhos
                                                                        //i + 1,  String com dados do desenho, Id do dono      
                        }
            		
                        usuario.receba(new EnvioDeResultado("Desenho salvo com sucesso!")); //aviso que o desenho foi salvo
            		this.usuarios.remove(usuario);//removo o usuario do Array
            		usuario.adeus(); //desconecto o usuario
                        return; //termino o forever
                    }
                    catch(Exception erro)
                    {
                        usuario.receba(new EnvioDeResultado(erro.getMessage())); //caso de erro, envio o problema para o Cliente
                        this.usuarios.remove(usuario); //e desconecto ele também
                        usuario.adeus();
                        return;
                    }
            	}
            	
            	if(comunicado instanceof PedidoDeDesenhoSalvo) //agora se for pedir um desenho salvo
            	{
                    PedidoDeDesenhoSalvo pedido = (PedidoDeDesenhoSalvo) comunicado;
                    
                    try
                    {
                        Desenho desenho = Desenhos.getDesenho(pedido.getEmailDoDono(), pedido.getNomeDesenho()); //pesquiso na tabela o desenho solicitado  				

                        usuario.receba(new DesenhoSalvo(FigurasDosDesenhos.getFiguraDoDesenho(desenho.getId()))); //mando para o usuario o Vector<String> contendo as strings do 'toString()'
                        
                        this.usuarios.remove(usuario);//removo o usuario e o desconecto
                        usuario.adeus();
                        return; //termino o for
                    }
                    catch(Exception erro)
                    {
                        usuario.receba(new EnvioDeResultado(erro.getMessage())); //caso de erro informo o problema ao cliente
                        this.usuarios.remove(usuario);//removo e o desconecto
                        usuario.adeus();
                        return; //fim do for
                    }
            	}
            }
        }
        catch (Exception erro)
        {
            try
            {
                usuario.receba(new EnvioDeResultado(erro.getMessage()));
                transmissor.close ();
                receptor   .close ();      
            }
            catch (Exception a)
            {} // so tentando fechar antes de acabar a thread
        }
    }
}
