package testes;
import bd.daos.*;
import bd.dbos.*;
public class Teste {
	 public static void main (String args[])
	{
		try
		{
			Desenho desenho = new Desenho("brentan@gmail.com", "teste", (long)1);
			
			Desenhos.incluir(desenho);
			System.out.println("Funfou!");
		}
		catch(Exception a) 
		{
			System.err.println(a.getMessage());
		}	
	}
}
