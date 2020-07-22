package bd.dbo;
public class FiguraDoDesenho{
    private String email,nome,estado,cidade,bairro,rua,complemento;
    private int numero;

    public FiguraDoDesenho(String email, String nome, String estado, String cidade, String bairro, String rua, String complemento, int numero) throws Exception {
        this.setEmail(email);
        this.setNome(nome);
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setRua(rua);
        this.setComplemento(complemento);
        this.setNumero(numero);
    }

    public FiguraDoDesenho(FiguraDoDesenho modelo) throws Exception {
        if(modelo == null)
                throw new Exception("modelo não pode ser nulo");
        this.email = modelo.email;
        this.nome = modelo.nome;
        this.estado = modelo.estado;
        this.cidade = modelo.cidade;
        this.bairro = modelo.bairro;
        this.rua = modelo.rua;
        this.numero = modelo.numero;
        this.complemento = modelo.complemento;
    }

    public void setEmail(String email) throws Exception {
        if(email.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.email = email;
    }

    public void setNome(String nome) throws Exception {
        if(nome.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.nome = nome;
    }

    public void setEstado(String estado) throws Exception {
        if(estado.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.estado = estado;
    }

    public void setCidade(String cidade) throws Exception {
        if(cidade.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.cidade = cidade;
    }

    public void setBairro(String bairro) throws Exception {
        if(bairro.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.bairro = bairro;
    }

    public void setRua(String rua) throws Exception {
        if(rua.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.rua = rua;
    }

    public void setComplemento(String complemento) throws Exception {
        if(complemento.length() > 50)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.complemento = complemento;
    }

    public void setNumero(int numero) throws Exception {
        if(numero < 0)
            throw new Exception("o numero deve ser positivo");
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "FiguraDoDesenho{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numero=" + numero +
                '}';
    }
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        FiguraDoDesenho cli = (FiguraDoDesenho) obj;
        if(!cli.bairro.equals(this.bairro) || !cli.cidade.equals(this.cidade)|| !cli.rua.equals(this.rua)
                || cli.numero != this.numero || !cli.email.equals(this.email) || !cli.complemento.equals(complemento)
                || !cli.estado.equals(this.estado) || !!cli.nome.equals(nome) )
            return false;
        return true;
    }
    public int haschCode()
    {
        int ret = 666;
        ret = ret * 5 + this.email.hashCode();
        ret = ret * 5 + this.nome.hashCode();
        ret = ret * 5 + this.estado.hashCode();
        ret = ret * 5 + this.cidade.hashCode();
        ret = ret * 5 + this.bairro.hashCode();
        ret = ret * 5 + this.rua.hashCode();
        ret = ret* 5 + new Integer(this.numero).hashCode();
        if(ret<0)
            ret = -ret;

        return ret;
    }
    public Object clone(){
        FiguraDoDesenho clone = null;
        try{
            clone = new FiguraDoDesenho(this);
        }
        catch (Exception e)
        {}
        return clone;
    }

}