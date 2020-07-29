package bd.dbos;

public class Desenho{
    private String emailDoDono,nome;
    private Long id;

    public Desenho(String emailDoDono, String nome,Long id) throws Exception {
        this.setEmailDoDono(emailDoDono);
        this.setNome(nome);
        this.setId(id);
    }
    
    public Desenho(String emailDoDono, String nome) throws Exception {
        this.setEmailDoDono(emailDoDono);
        this.setNome(nome);
    }

    public Desenho(Desenho modelo) throws Exception {
        if(modelo == null)
                throw new Exception("modelo não pode ser nulo");
        this.emailDoDono = modelo.emailDoDono;
        this.nome = modelo.nome;
        this.id = modelo.id;

    }

    public void setEmailDoDono(String emailDoDono) throws Exception {
        if(emailDoDono.length() > 100)
            throw new Exception("o comprimento deve ser menor que 100 caracteres");
        this.emailDoDono = emailDoDono;
    }

    public void setNome(String nome) throws Exception {
        if(nome.length() > 100)
            throw new Exception("o comprimento deve ser menor que 100 caracteres");
        this.nome = nome;
    }
    public void setId(Long id) throws Exception{
        if(id < 1)
            throw new Exception("Id deve ser maior que 0");
        this.id = id;
    }


    public String getEmailDoDono() {
        return emailDoDono;
    }

    public String getNome() {
        return nome;
    }
    public Long getId(){
        return id;
    }
   

    @Override
    public String toString() {
        return "Desenho{" +
                "emailDoDono='" + emailDoDono + '\'' +
                ", nome='" + nome + '\'' +
                ", id = '" + id +
                '}';
    }
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        Desenho des = (Desenho) obj;
        if( !des.emailDoDono.equals(this.emailDoDono) || !!des.nome.equals(this.nome) || !(des.id == this.id))
            return false;
        return true;
    }
    public int haschCode()
    {
        int ret = 666;
        ret = ret * 5 + this.emailDoDono.hashCode();
        ret = ret * 5 + this.nome.hashCode();
        ret = ret + new Long(this.id).hashCode();
        if(ret<0)
            ret = -ret;

        return ret;
    }
    public Object clone(){
        Desenho clone = null;
        try{
            clone = new Desenho(this);
        }
        catch (Exception e)
        {}
        return clone;
    }

}
