package bd.dbos;

/**
 * Classe que define o objeto FiguraDoDesenho
 */
public class FiguraDoDesenho {
    private String figura;
    private Long idDoDesenho;
    private int id;

    /**
     * Construtor da classe
     * 
     * @param id
     * @param figura
     * @param idDoDesenho
     * @throws Exception refere a excessão dos métodos set
     */
    public FiguraDoDesenho(int id, String figura, Long idDoDesenho) throws Exception {
        this.setId(id);
        this.setFigura(figura);
        this.setIdDoDesenho(idDoDesenho);
    }

    /**
     * Construtor de cópia
     * 
     * @param modelo modelo de figura que sera copiado
     * @throws Exception quando o modelo é nulo
     */

    public FiguraDoDesenho(FiguraDoDesenho modelo) throws Exception {
        if (modelo == null)
            throw new Exception("modelo n�o pode ser nulo");
        this.id = modelo.id;
        this.figura = modelo.figura;
        this.idDoDesenho = modelo.idDoDesenho;

    }

    /**
     * Atribui valor ao id da figura
     * 
     * @param id
     * @throws Exception quando o id é menor que 1
     */
    public void setId(int id) throws Exception {
        if (id < 1)
            throw new Exception("id deve ser maior que 1");
        this.id = id;
    }

    /**
     * Atribui valor a string que descreve a figura
     * 
     * @param figura
     * @throws Exception se o comprimento da string for maior que 50
     */
    public void setFigura(String figura) throws Exception {
        if (figura.length() > 100)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.figura = figura;
    }

    /**
     * Atribui valor ao id do desenho
     * 
     * @param idDoDesenho
     * @throws Exception se o id do desenho for menor que 1
     */
    public void setIdDoDesenho(Long idDoDesenho) throws Exception {
        if (idDoDesenho < 1)
            throw new Exception("IdDoDesenho deve ser maior que 1");
        this.idDoDesenho = idDoDesenho;
    }

    /**
     * Método que retorna o id da figura
     * 
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que retorna o nome da figura
     * 
     * @return string id do desenho
     */
    public String getFigura() {
        return figura;
    }

    /**
     * Método que retorna o id do desenho
     * 
     * @return int id do desenho
     */
    public Long getIdDoDesenho() {
        return idDoDesenho;
    }

    /**
     * Método que transforma os parâmetros da classe numa string
     */
    @Override
    public String toString() {
        return "FiguraDoDesenho{" + "id do desenho='" + idDoDesenho + '\'' + ", figura='" + figura + '\'' + ", id = '"
                + id + '}';
    }

    /**
     * Método que compara uma classe com a outra dada como paramêtro
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != this.getClass())
            return false;
        FiguraDoDesenho fdd = (FiguraDoDesenho) obj;
        if (!(fdd.id == this.id) || !!fdd.figura.equals(this.figura) || !(fdd.idDoDesenho == this.idDoDesenho))
            return false;
        return true;
    }

    /**
     * Método que produz o haschcode da classe
     * 
     * @return
     */
    public int haschCode() {
        int ret = 666;
        ret = ret + new Integer(this.id).hashCode();
        ret = ret * 5 + this.figura.hashCode();
        ret = ret + new Long(this.idDoDesenho).hashCode();
        if (ret < 0)
            ret = -ret;

        return ret;
    }

    /**
     * Método que retorna um objeto clone da classe
     */
    public Object clone() {
        FiguraDoDesenho clone = null;
        try {
            clone = new FiguraDoDesenho(this);
        } catch (Exception e) {
        }
        return clone;
    }

}
