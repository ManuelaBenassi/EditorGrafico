package cliente_servidor;

import java.net.*;
import java.util.*;
import java.io.*;
import editor.Janela;

public class Cliente
{
    /*
        Classe cliente. Tem como objetivo fazer a conexão com o servidor, enviar e receber os dados do mesmo
        @params:
           String emailDoDono,
           String nomeDesenho,
           Vector<String> desenho,
           Janela janela --> janela chamante da classe, para ele saber onde enviar os dados recebidos
    */
	public Cliente(String emailDoDono, String nomeDesenho, Vector<String> desenho, Janela janela)
	{	
        Socket conexao=null;
        try
        {
            String host = "localhost";
            int    porta= 8000;
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
            if (desenho != null) //se algum desenho foi passado, é porque queremos salvar um desenho
            {
                servidor.receba (new PedidoDeSalvamento (emailDoDono, nomeDesenho, desenho)); //envio um pedido de salvamento
                EnvioDeResultado envioDeResultado = (EnvioDeResultado) servidor.envie(); //peço para o servidor me enviar o resultado

                janela.recebaResultado(envioDeResultado.getResultado()); //envio o resultado para a janela chamante
            }
            else //caso não haja desenho é porque eu quero resgatar um desenho
            {
                servidor.receba (new PedidoDeDesenhoSalvo (emailDoDono, nomeDesenho)); //peço um desenho desse dono

                Comunicado comunicado = servidor.envie (); //servidor me envia um comunicado

                if(comunicado instanceof EnvioDeResultado) //se for um EnvioDeResultado, é porque não deu certo
                {
                    EnvioDeResultado envioDeResultado = (EnvioDeResultado) comunicado;
                    janela.recebaResultado(envioDeResultado.getResultado());
                }

                if(comunicado instanceof DesenhoSalvo) // se for de DesenhoSalvo é porque deu certo
                {
                    DesenhoSalvo resultado = (DesenhoSalvo) comunicado;
                    janela.recebaDesenho(resultado.getDesenho()); //devolvo o desenho
                    janela.recebaResultado("Desenho resgatado com sucesso!");//aviso a janela que deu certo
                }				
            }
        }
        catch (Exception erro)
        {
            janela.recebaResultado("Erro de comunicação com o servidor");
        }
    }
}