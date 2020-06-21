import java.awt.*;

public class Elipse extends Figura
{
    Ponto p1, p2, p3;
    int altura, largura;

    public Elipse(int x1, int y1, int x2, int y2, int x3, int y3, Color cor)
    {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
        this.p3 = new Ponto(x3, y3);
    }

    public void setP1(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setP2(int x, int y)
    {
        this.p2.setX(x);
        this.p2.setY(y);
    }

    public void setP3(int x, int y)
    {
        this.p3.setX(x);
        this.p3.setY(y);
    }

    public Ponto getP1()
    {
        return this.p1;
    }

    public Ponto getP2()
    {
        return this.p2;
    }

    public Ponto getP3()
    {
        return this.p3;
    }

    public void torneSeVisivel(Graphics g)
    {
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do retangulo
        g.drawOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                   largura, //largura do retangulo
                   altura); //altura do retangulo
    }

    private void calculaTamanho(){
        int baseTriangulo, alturaTriangulo, hipotenusa;

        // calculo da hipotenusa do 'primeiro' triangulo
        baseTriangulo = this.p1.getX() - this.p2.getX();
        alturaTriangulo = this.p1.getY() - this.p2.getY();

        hipotenusa = (baseTriangulo * baseTriangulo) + (alturaTriangulo * alturaTriangulo);

        this.largura = (int) Math.sqrt(hipotenusa);
        
        //calculo da hipotenusa do 'segundo' triangulo
        baseTriangulo = this.p1.getX() - this.p3.getX();
        alturaTriangulo = this.p1.getY() - this.p3.getY();

        hipotenusa = (baseTriangulo * baseTriangulo) + (alturaTriangulo * alturaTriangulo);

        this.altura  = (int) Math.sqrt(hipotenusa);
    }

    public String toString()
    {
        return "r:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.p3.getX() +
               ":" +
               this.p3.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
    @Override
    public Object clone()
    {
        Elipse clone = null;
        try
        {
            clone = new Elipse(this);
        }
        catch(Exception e)
        {}      
        return clone;
    }
    public Elipse(Elipse modelo) throws Exception{
        if(modelo==null){
            throw new Exception("não pode ser nulo");
        }
        this.p1 = modelo.p1;
        this.p2 = modelo.p2;
        this.p3 = modelo.p3;
        this.altura = modelo.altura;
        this.largura = modelo.largura;

    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        Elipse elipse = (Elipse) obj;
        if(!this.p1.equals(elipse.p1) || !this.p2.equals(elipse.p2) || !this.p3.equals(elipse.p3) ||
        this.altura != elipse.altura || this.largura != elipse.largura)
            return false;
        return true;
    }
    @Override
    public int hashCode()
    {
        int ret = 111;
        ret = ret *5 + this.p1.hashCode();
        ret = ret * 5 + this.p2.hashCode();
        ret = ret * 5 + this.p3.hashCode();
        ret = ret * 5 + new Integer(this.altura).hashCode();
        ret = ret * 5 + new Integer(this.largura).hashCode();
        if(ret<0)
            ret = -ret;

        return ret;
    }
}
