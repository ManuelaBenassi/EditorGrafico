/*CREATE TABLE Desenhos (

Id BIGINT IDENTITY(1,1) PRIMARY KEY,

eMailDoDono VARCHAR(100) NOT NULL,

Nome VARCHAR(100) NOT NULL,

UNIQUE(eMailDoDono,Nome))
*/
package bd.dao;
import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbo.Desenho;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Desenhos {
    public static boolean cadastrado(String email) throws Exception {
        boolean retorno = false;
        String sql;
        try {
            sql = "SELECT * " +
                    "FROM DESENHOS " +
                    "WHERE email = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, email);

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
        if(Desenhos.cadastrado(desenho.getEmailDoDono()))
            throw new Exception("Esse desenho já foi cadastrado.");
        try
        {
            String sql;
            sql = "insert into Desenho values(?, ?, ?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, desenho.getEmailDoDono());
            BDSQLServer.COMANDO.setString(2, desenho.getNome());
            BDSQLServer.COMANDO.SetInt(3,desenho.getId());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao cadastrar desenho");
        }
    }
    public static void excluir(String email) throws Exception {
        if(!Desenhos.cadastrado(email))
            throw new Exception("Esse desenho não foi cadastrado, portanto é impossivel excluir ele.");
        try
        {
            String sql;

            sql = "DELETE FROM CLIENTE " +
                    "WHERE EMAIL=?";

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

        if (!cadastrado (desenho.getEmailDoDono()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE CLIENTE " +
                    "SET NOME=?, " +
                    "EMAIL=?, " +
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
            String sql = "SELECT * FROM CLIENTE";
            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(resultado.next())
            {
                desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"),resultado.getBigInt("Id"));

                ret.add(desenho);
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar desenhos");
        }

        return ret;
    }
    public static Desenho getDesenho(String email) throws Exception
    {
        Desenho desenho = null;
        try
        {
            String sql = "SELECT * FROM CLIENTE WHERE email = ?";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, email);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if(!resultado.first())
                throw new Exception("Nao cadastrado");

            desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"));
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao procurar desenho");
        }
        return desenho;
    }
}