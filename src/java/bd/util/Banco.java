package bd.util;

public class Banco {
    private static Conexao con;

    private Banco() {
        con = null;
    }

    public static Conexao getConexao() {
        if (con == null)
            con = new Conexao();
        return con;
    }
}
