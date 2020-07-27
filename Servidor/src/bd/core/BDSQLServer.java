package bd.core;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
    		comando = new MeuPreparedStatement(
    				"com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD19175", 
    				"BD19184", "CtcRA(159)");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0);
        }
        
        COMANDO = comando;
    }
}