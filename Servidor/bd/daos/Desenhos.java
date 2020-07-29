package bd.daos;

import java.util.List;

import javax.swing.*;

import java.sql.*;
import java.util.ArrayList;

import bd.core.*;
import bd.dbos.Desenho;

/**
 * Essa classe é a classe que acessa a tabela Desenhos.
 */
public class Desenhos {
    /**
     * Esse método verifica se um desenho ja está cadastrado
     * 
     * @param email do dono do desenho
     * @param nome  do dono do desenho
     * @return uma boolean para ver se está ou não cadastrado
     * @throws Exception quando o sql da erro ao procurar
     */
    public static boolean cadastrado(String email, String nome) throws Exception {
        boolean retorno = false;
        String sql;
        try {
            sql = "SELECT * " + "FROM DESENHOS " + "WHERE EMAILDODONO = ? and " + "NOME = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, email);
            BDSQLServer.COMANDO.setString(2, nome);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar desenho");

        }
        return retorno;
    }

    /**
     * Esse método inclui no banco de dados um objeto do tipo desenho
     * 
     * @param desenho o desenho a ser incluido
     * @throws Exception se ele ja foi incluido,se for nulo ou ainda se der erro no
     *                   banco de dados
     */
    public static void incluir(Desenho desenho) throws Exception {
        if (desenho == null)
            throw new Exception("desenho n�o pode ser nulo");
        if (Desenhos.cadastrado(desenho.getEmailDoDono(), desenho.getNome()))
            throw new Exception("Esse desenho j� foi cadastrado.");
        try {
            String sql;
            sql = "insert into Desenhos values(?, ?)";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, desenho.getEmailDoDono());
            BDSQLServer.COMANDO.setString(2, desenho.getNome());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        } catch (Exception ex) {
            throw new Exception("Erro ao cadastrar desenho");
        }
    }

    /**
     * Exclui um desenho
     * 
     * @param email email do dono a ser excluido
     * @param nome  nome do dono a ser excluido
     * @throws Exception quando o desenho não existir(não foi incluido) ou quando
     *                   der erro no banco de dados
     */
    public static void excluir(String email, String nome) throws Exception {
        if (!Desenhos.cadastrado(email, nome))
            throw new Exception("Esse desenho n�o foi cadastrado, portanto � impossivel excluir ele.");
        try {
            String sql;

            sql = "DELETE FROM DESENHOS " + "WHERE EMAILDODONO=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, email);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        } catch (SQLException erro) {
            // BDSQLServer.COMANDO.rollback ();
            throw new Exception("Erro ao excluir desenho");
        }

    }

    /**
     * Método que altera um desenho
     * 
     * @param desenho desenho com as novas caracteristicas
     * @throws Exception quando o novo desenho for nulo,quando o desenho não tiver
     *                   sido cadastrado ou quando der erro no banco de dados
     */

    public static void alterar(Desenho desenho) throws Exception {
        if (desenho == null)
            throw new Exception("Desenho nao fornecido");

        if (!cadastrado(desenho.getEmailDoDono(), desenho.getNome()))
            throw new Exception("Nao cadastrado");

        try {
            String sql;

            sql = "UPDATE DESENHOS " + "SET NOME=?, " + "EMAILDODONO =?, " + "WHERE ID =?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, desenho.getNome());
            BDSQLServer.COMANDO.setString(2, desenho.getEmailDoDono());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        } catch (SQLException erro) {
            // BDSQLServer.COMANDO.rollback ();
            JOptionPane.showMessageDialog(null, erro.getMessage());
            throw new Exception("Erro ao atualizar dados de desenho");
        }
    }

    /**
     * Método que constrói uma lista a partir dos desenhos da tabela
     * 
     * @return a lista com os desenhos
     * @throws Exception quando der erro no banco de dados
     */
    public static List<Desenho> selecionarDesenhos() throws Exception {
        List<Desenho> ret = new ArrayList<Desenho>();
        Desenho desenho;

        try {
            String sql = "SELECT * FROM DESENHOS";
            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while (resultado.next()) {
                desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"),
                        resultado.getLong("Id"));

                ret.add(desenho);
            }
        } catch (Exception ex) {
            throw new Exception("Erro ao procurar desenhos");
        }

        return ret;
    }

    /**
     * Método que encontra o deseho procurado
     * 
     * @param email email do dono do desenho
     * @param nome  do dono do desenho
     * @return Desenho - objeto procurado
     * @throws Exception Quando o desenho não está cadastrado ou quando da erro no
     *                   banco de dados
     */

    public static Desenho getDesenho(String email, String nome) throws Exception {
        Desenho desenho = null;
        try {
            String sql = "SELECT * FROM DESENHOS WHERE EMAILDODONO = ? and NOME = ? ";
            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setString(1, email);
            BDSQLServer.COMANDO.setString(2, nome);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if (!resultado.first())
                throw new Exception("Nao cadastrado");

            desenho = new Desenho(resultado.getString("email"), resultado.getString("nome"), resultado.getLong("id"));
        } catch (Exception ex) {
            throw new Exception("Erro ao procurar desenho");
        }
        return desenho;
    }
}
