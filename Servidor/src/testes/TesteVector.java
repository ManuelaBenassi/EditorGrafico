package testes;

import java.util.Vector;

public class TesteVector {
	public static void main (String args[])
	{
		try
		{
			Vector<String> teste = new Vector<String>();
			
			String primeira = "p:12:123:189:891:221;p:14:15:12:1234:324;";

			String[] quebrado = primeira.split(";");
			
			for(int i = 0; i < quebrado.length; i++)
				teste.add(quebrado[i]);
				
			System.out.println(teste);
			System.out.println(teste.size());
			
    		for(int i = 0; i < teste.size(); i++)
    		{
    			System.out.println(i);    		}
		}
		catch(Exception e)
		{}
	}
}
