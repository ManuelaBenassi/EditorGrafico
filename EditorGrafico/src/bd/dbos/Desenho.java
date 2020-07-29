package bd.dbos;

/**
 * Classe que define o objeto da tabela Desenhos
 */
public class Desenho {
    private String emailDoDono, nome;
    private Long id;

    /**
     * Construtor da classe
     * 
     * @param emailDoDono
     * @param nome
     * @param id
     * @throws Exception excessao dos sets
     */
    public Desenho(String emailDoDono, String nome, Long id) throws Exception {
        this.setEmailDoDono(emailDoDono);
        this.setNome(nome);
        this.setId(id);
    }

    /**
     * Construtor sem a primary key
     * 
     * @param emailDoDono
     * @param nome
     * @throws Exception
     */

    public Desenho(String emailDoDono, String nome) throws Exception {
        this.setEmailDoDono(emailDoDono);
        this.setNome(nome);
    }

    /**
     * Construtor de cópia
     * 
     * @param modelo modelo a ser copiado
     * @throws Exception quaqndo o modelo for nulo
     */
    public Desenho(Desenho modelo) throws Exception {
        if (modelo == null)
            throw new Exception("modelo n�o pode ser nulo");
        this.emailDoDono = modelo.emailDoDono;
        this.nome = modelo.nome;
        this.id = modelo.id;

    }

    /**
     * Da o valor ao email do dono
     * 
     * @param emailDoDono
     * @throws Exception quando a string tiver mais que 100 caracteres
     */
    public void setEmailDoDono(String emailDoDono) throws Exception {
        if (emailDoDono.length() > 100)
            throw new Exception("o comprimento deve ser menor que 100 caracteres");
        this.emailDoDono = emailDoDono;
    }

    /**
     * Da valor ao nome do dono
     * 
     * @param nome
     * @throws Exception
     */
    public void setNome(String nome) throws Exception {
        if (nome.length() > 100)
            throw new Exception("o comprimento deve ser menor que 100 caracteres");
        this.nome = nome;
    }

    /**
     * Da valor ao id
     * 
     * @param id
     * @throws Exception se o id for negativo
     */
    public void setId(Long id) throws Exception {
        if (id < 1)
            throw new Exception("Id deve ser maior que 0");
        this.id = id;
    }

    /**
     * Retorna o email do dono
     * 
     * @return string- email do dono
     */
    public String getEmailDoDono() {
        return emailDoDono;
    }

    /**
     * Retorna o nome do dono
     * 
     * @return string nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o id do desenho
     * 
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que transforma os parâmetros da classe numa string
     */
    @Override
    public String toString() {
        return "Desenho{" + "emailDoDono='" + emailDoDono + '\'' + ", nome='" + nome + '\'' + ", id = '" + id + '}';
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
        Desenho des = (Desenho) obj;
        if (!des.emailDoDono.equals(this.emailDoDono) || !!des.nome.equals(this.nome) || !(des.id == this.id))
            return false;
        return true;
    }

    /**
     * Método que produz o haschcode da classe
     * 
     * @return um int >0 que representa o haschcode
     */
    public int haschCode() {
        int ret = 666;
        ret = ret * 5 + this.emailDoDono.hashCode();
        ret = ret * 5 + this.nome.hashCode();
        ret = ret + new Long(this.id).hashCode();
        if (ret < 0)
            ret = -ret;

        return ret;
    }

    /**
     * Método que retorna um objeto clone da classe
     */
    public Object clone() {
        Desenho clone = null;
        try {
            clone = new Desenho(this);
        } catch (Exception e) {
        }
        return clone;
    }

}
