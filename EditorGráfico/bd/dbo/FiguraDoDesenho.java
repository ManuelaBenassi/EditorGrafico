
package bd.dbo;
public class FiguraDoDesenho{
    private String figura;
    private Long idDoDesenho;
    private int id;

    public FiguraDoDesenho(int id, String figura,Long idDoDesenho) throws Exception {
        this.setId(id);
        this.setFigura(figura);
        this.setIdDoDesenho(idDoDesenho);
    }

    public FiguraDoDesenho(FiguraDoDesenho modelo) throws Exception {
        if(modelo == null)
                throw new Exception("modelo não pode ser nulo");
        this.id = modelo.id;
        this.figura = modelo.figura;
        this.idDoDesenho = modelo.idDoDesenho;

    }

    public void setId(int id) throws Exception {
        if(id<1)
            throw new Exception("id deve ser maior que 0");
        this.id = id;
    }

    public void setFigura(String figura) throws Exception {
        if(figura.length() > 100)
            throw new Exception("o comprimento deve ser menor que 50 caracteres");
        this.figura = figura;
    }
    public void setIdDoDesenho(Long idDoDesenho) throws Exception{
        if(idDoDesenho < 1)
            throw new Exception("IdDoDesenho deve ser maior que 1");
        this.idDoDesenho = idDoDesenho;
    }


    public int getId() {
        return id;
    }

    public String getFigura() {
        return figura;
    }
    public Long getIdDoDesenho(){
        return idDoDesenho;
    }
   

    @Override
    public String toString() {
        return "FiguraDoDesenho{" +
                "id do desenho='" + idDoDesenho + '\'' +
                ", figura='" + figura + '\'' +
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
        FiguraDoDesenho fdd = (FiguraDoDesenho) obj;
        if( !(fdd.id == this.id) || !!fdd.figura.equals(this.figura) || !(fdd.idDoDesenho == this.idDoDesenho))
            return false;
        return true;
    }
    public int haschCode()
    {
        int ret = 666;
        ret = ret + new Integer(this.id).hashCode();
        ret = ret * 5 + this.figura.hashCode();
        ret = ret + new Long(this.idDoDesenho).hashCode();
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