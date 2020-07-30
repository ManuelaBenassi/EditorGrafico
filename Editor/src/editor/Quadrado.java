package editor;

import java.awt.*;

public class Quadrado extends Figura{
    protected Ponto p1, p2;
    protected int altura, largura;
    
    public Quadrado(int x1, int x2, int y1, int y2, Color cor)
    {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);

    }
    public Quadrado (String desenho) 
    {
    	
    }
    public void setP1(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public void setP2(int x, int y)
    {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public Ponto getP1()
    {
        return this.p1;
    }

    public Ponto getP2()
    {
        return this.p2;
    }
    public void torneSeVisivel(Graphics g,Color c)
    {
        g.setColor(super.cor);
        calculaTamanho();// caclcula as dimensoes do quadrado
        g.drawRect(this.p1.getX(), this.p1.getY(), largura, altura);
        g.setColor(c);
        g.fillRect(this.p1.getX(), this.p1.getY(), largura, altura);
    }
    private void calculaTamanho()
    { int deltaX, deltaY;

        deltaX = this.p1.getX() - this.p2.getX();

        if(deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY();

        if(deltaY < 0)
            deltaY = -deltaY;

        if(deltaX >= deltaY) //vejo se a distância entre os dois pontos é maior no eixo X ou no eixo Y
        {
            this.altura = deltaX;                               //sendo no eixo x:
                                                                //-usarei por parametro de distancia o deltaX;
            this.largura = this.altura;                         //-farei ambos os lados do quadrado igauis;
        }
        else //senão, uso o deltaY como parametro para o tamanho do quadrado
        {
            this.altura = deltaY;

            this.largura = this.altura;  
        }
    }
    public String toString()
    {
        return "q:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
               ";";
    }

    //métodos obrigatorios
    @Override
    public Object clone()
    {
        Quadrado clone = null;
        try
        {
            clone = new Quadrado(this);
        }
        catch(Exception e)
        {}      
        return clone;
    }
    public Quadrado(Quadrado modelo) throws Exception{
        if(modelo==null){
            throw new Exception("não pode ser nulo");
        }
        this.p1 = modelo.p1;
        this.p2 = modelo.p2;
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
        Quadrado quadrado = (Quadrado) obj;
        if(!this.p1.equals(quadrado.p1) || !this.p2.equals(quadrado.p2) ||
        this.altura != quadrado.altura || this.largura != quadrado.largura)
            return false;
        return true;
    }
    @Override
    public int hashCode()
    {
        int ret = 111;
        ret = ret *5 + this.p1.hashCode();
        ret = ret * 5 + this.p2.hashCode();
        ret = ret * 5 + new Integer(this.altura).hashCode();
        ret = ret * 5 + new Integer(this.largura).hashCode();
        if(ret<0)
            ret = -ret;

        return ret;
    }
}
