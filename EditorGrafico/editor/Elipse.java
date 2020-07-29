package editor;

import java.awt.*;

public class Elipse extends Figura
{
    Ponto p1, p2, p3;
    int altura = 0, largura = 0;

    public Elipse(int x1, int y1, int x2, int y2, int x3, int y3, Color cor)
    {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
        this.p3 = new Ponto(x3, y3);
    }
    
    public Elipse(String desenho)
    {
    	
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

    public void torneSeVisivel(Graphics g,Color c)
    {
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do retangulo
        g.drawOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                   largura, //largura do retangulo
                   altura); //altura do retangulo
        g.setColor(c);//define a cor para pintar o fundo
        g.fillOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                              largura, //largura do retangulo
                              altura);//altura do retangulo
    }

    private void calculaTamanho(){

        /* M�todo antigo, n�o muito bom
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

        this.altura  = (int) Math.sqrt(hipotenusa);*/

        //Novo m�todo, funciona melhor
        int deltaX, deltaY;
        
        //calculando o primeiro ponto
        deltaX = this.p1.getX() - this.p2.getX(); //achando o deltaX

        if(deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY(); //achando o deltaY

        if(deltaY < 0)
            deltaY = -deltaY;

        if(deltaX >= deltaY) //verifico qual dos Deltas devo utilizar
            if(this.largura == 0)
                this.largura = deltaX;
            else
                this.altura = deltaX;
        else
            if(this.altura == 0)
                this.altura = deltaY;
            else
                this.largura = deltaY;

        //calculando o segundo ponto
        deltaX = this.p1.getX() - this.p3.getX();

        if(deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p3.getY();

        if(deltaY < 0)
            deltaY = -deltaY;

        if(deltaX >= deltaY)
            if(this.largura == 0)
                this.largura = deltaX;
            else
                this.altura = deltaX;
        else
            if(this.altura == 0)
                this.altura = deltaY;
            else
                this.largura = deltaY;
    }

    public String toString()
    {
        return "e:" +
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
            throw new Exception("n�o pode ser nulo");
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
