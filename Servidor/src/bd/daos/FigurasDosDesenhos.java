package bd.daos;

import bd.core.*;
import bd.dbos.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FigurasDosDesenhos {
    public static boolean cadastrado(long id) throws Exception {
        boolean retorno = false;
        String sql;
        try {
            sql = "SELECT * " +
                    "FROM FIGURASDOSDESENHOS " +
                    "WHERE IDDESENHO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setLong(1, id);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro){
            throw new Exception("Erro ao procurar Figura");

        }
        return retorno;
    }
    public static void incluir(FiguraDoDesenho figura) throws Exception {
        if (figura == null)
            throw new Exception("figura não pode ser nulo");
        if(FigurasDosDesenhos.cadastrado(figura.getId()))
            throw new Exception("Esse figura já foi cadastrado.");
        try
        {
            String sql;
            sql = "insert into FigurasDosDesenhos values(?, ?, ?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setLong(1,figura.getIdDoDesenho());
            BDSQLServer.COMANDO.setLong(2, figura.getId());
            BDSQLServer.COMANDO.setString(3, figura.getFigura());
            

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao cadastrar desenho");
        }
    }
    public static void excluir(int id) throws Exception {
        if(!FigurasDosDesenhos.cadastrado(id))
            throw new Exception("Esse desenho não foi cadastrado, portanto é impossivel excluir ele.");
        try
        {
            String sql;

            sql = "DELETE FROM FIGURADOSDESENHOS " +
                    "WHERE ID=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir figura do desenho");
        }

    }
    public static void alterar (FiguraDoDesenho fds) throws Exception
    {
        if (fds==null)
            throw new Exception ("Figura nao fornecido");

        if (!cadastrado (fds.getId()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE FIGURADOSDESENHOS " +
                    "SET ID =?, " +
                    "FIGURA =?, " +
                    "WHERE IDDESENHO =?";

            BDSQLServer.COMANDO.prepareStatement (sql);


            BDSQLServer.COMANDO.setInt(1,1);
            BDSQLServer.COMANDO.setString(2, fds.getFigura());
            BDSQLServer.COMANDO.setLong(3, fds.getIdDoDesenho());


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //BDSQLServer.COMANDO.rollback ();
            JOptionPane.showMessageDialog(null,erro.getMessage());
            throw new Exception ("Erro ao atualizar dados de desenho");
        }
    }
    public static List<FiguraDoDesenho> selecionarFigurasDosDesenhos() throws Exception
    {
        List<FiguraDoDesenho> ret = new ArrayList<FiguraDoDesenho>();
        FiguraDoDesenho fds;

        try
        {
            String sql = "SELECT * FROM FIGURASDOSDESENHOS";
            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(resultado.next())
            {
                fds = new FiguraDoDesenho(resultado.getInt("Id"), resultado.getString("figura"),resultado.getLong("IdDoDesenho"));

                ret.add(fds);
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar figuras");
        }

        return ret;
    }
    public static Vector<String> getFiguraDoDesenho(long id) throws Exception
    {
        Vector<String> retorno = new Vector<String>();
        
        try
        {
            String sql = "SELECT * FROM FIGURASDOSDESENHOS WHERE IDDESENHO = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setLong(1, id);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if(!resultado.first())
            	return null;
            
            while(resultado.next())
                retorno.add(resultado.getString("figura"));

        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar desenho");
        }
        return retorno;
    }
}