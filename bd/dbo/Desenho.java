package bd.dbo;
public class Desenho{
    private String emailDoDono,nome;
    private int numero;

    public Desenho(String emailDoDono, String nome) throws Exception {
        this.setEmailDoDono(emailDoDono);
        this.setNome(nome);
    }

    public Desenho(Desenho modelo) throws Exception {
        if(modelo == null)
                throw new Exception("modelo não pode ser nulo");
        this.emailDoDono = modelo.emailDoDono;
        this.nome = modelo.nome;

    }

    public void setEmail(String emailDoDono) throws Exception {
        if(emailDoDono.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.emailDoDono = emailDoDono;
    }

    public void setNome(String nome) throws Exception {
        if(nome.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.nome = nome;
    }


    public String getEmail() {
        return emailDoDono;
    }

    public String getNome() {
        return nome;
    }

   

    @Override
    public String toString() {
        return "Desenho{" +
                "emailDoDono='" + emailDoDono + '\'' +
                ", nome='" + nome + '\'' +
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
        if( !des.emailDoDono.equals(this.emailDoDono) || !!des.nome.equals(nome) )
            return false;
        return true;
    }
    public int haschCode()
    {
        int ret = 666;
        ret = ret * 5 + this.emailDoDono.hashCode();
        ret = ret * 5 + this.nome.hashCode();
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