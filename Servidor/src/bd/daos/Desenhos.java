package bd.daos;

import java.util.List;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

import bd.core.*;
import bd.dbos.Desenho;

public class Desenhos {
    public static boolean cadastrado(String email, String nome) throws Exception {
        boolean retorno = false;
        String sql;
        try {
            sql = "SELECT * " +
                    "FROM DESENHOS " +
                    "WHERE eMailDoDono = ? and" +
                    	  "Nome = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, email);
            BDSQLServer.COMANDO.setString(2, nome);


            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro){
            throw new Exception("Erro ao procurar desenho");

        }
        return retorno;
    }
    public static void incluir(Desenho desenho) throws Exception {
        if (desenho == null)
            throw new Exception("desenho não pode ser nulo");
        if(Desenhos.cadastrado(desenho.getEmailDoDono(), desenho.getNome()))
            throw new Exception("Esse desenho já foi cadastrado.");
        try
        {
            String sql;
            sql = "insert into Desenhos values(?, ?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, desenho.getEmailDoDono());
            BDSQLServer.COMANDO.setString(2, desenho.getNome());
            

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao cadastrar desenho");
        }
    }
    public static void excluir(String email, String nome) throws Exception {
        if(!Desenhos.cadastrado(email, nome))
            throw new Exception("Esse desenho não foi cadastrado, portanto é impossivel excluir ele.");
        try
        {
            String sql;

            sql = "DELETE FROM DESENHOS " +
                    "WHERE EMAILDODONO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, email);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir desenho");
        }

    }
    public static void alterar (Desenho desenho) throws Exception
    {
        if (desenho==null)
            throw new Exception ("Desenho nao fornecido");

        if (!cadastrado (desenho.getEmailDoDono(), desenho.getNome()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE DESENHOS " +
                    "SET NOME=?, " +
                    "EMAILDODONO =?, " +
                    "WHERE ID =?";

            BDSQLServer.COMANDO.prepareStatement (sql);


            BDSQLServer.COMANDO.setString(1, desenho.getNome ());
            BDSQLServer.COMANDO.setString(2, desenho.getEmailDoDono ());


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
    public static List<Desenho> selecionarDesenhos() throws Exception
    {
        List<Desenho> ret = new ArrayList<Desenho>();
        Desenho desenho;

        try
        {
            String sql = "SELECT * FROM DESENHOS";
            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(resultado.next())
            {
                desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"),resultado.getLong("Id"));

                ret.add(desenho);
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar desenhos");
        }

        return ret;
    }
    public static Desenho getDesenho(String email, String nome) throws Exception
    {
        Desenho desenho = null;
        try
        {
            String sql = "SELECT * FROM DESENHOS WHERE EMAILDODONO = ? and NOME = ? ";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, email);
            BDSQLServer.COMANDO.setString(2, nome);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if(!resultado.first())
                throw new Exception("Nao cadastrado");

            desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"), resultado.getLong("id"));
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar desenho");
        }
        return desenho;
    }
}
