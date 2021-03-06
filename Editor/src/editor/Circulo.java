package editor;

import java.awt.*;
import java.util.StringTokenizer;

public class Circulo extends Figura
{
    protected Ponto p1, p2;
    protected int altura, largura;
   
    //
    

    public Circulo(int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
    }
    
    public Circulo(String dados)
    {
        StringTokenizer quebrador = new StringTokenizer(dados,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.cor = cor;
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
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do quadrado
        g.drawOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                   largura, //largura do quadrado
                   altura); //altura do quadrado
        g.setColor(c);//define a cor para pintar o fundo
        g.fillOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                   largura, //largura do quadrado
                   altura);//altura do quadrado
    }

    private void calculaTamanho()
    {
        //m�todo antigo, n�o funcional
        /*int larguraTriangulo = this.p1.getX() - this.p2.getX(); //delta x
        int alturaTriangulo = this.p1.getY() - this.p2.getY(); //delta y
        
        int hipotenusa = (alturaTriangulo * alturaTriangulo) + (larguraTriangulo * larguraTriangulo); //calculo basico de pitagoras

        this.altura = (int)Math.sqrt(hipotenusa); //pega a parte inteira da raiz quadrada da hipotenusa
        this.largura = this.altura; //define as dimensoes*/

        //novo m�todo, funciona melhor
        int deltaX, deltaY;

        deltaX = this.p1.getX() - this.p2.getX();

        if(deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY();

        if(deltaY < 0)
            deltaY = -deltaY;

        if(deltaX >= deltaY) //vejo se a dist�ncia entre os dois pontos � maior no eixo X ou no eixo Y
        {
            this.altura = deltaX;                               //sendo no eixo x:
                                                                //-usarei por parametro de distancia o deltaX;
            this.largura = this.altura;                         //-farei ambos os lados do quadrado igauis;
        }
        else //sen�o, uso o deltaY como parametro para o tamanho do quadrado
        {
            this.altura = deltaY;

            this.largura = this.altura;  
        }
    }

    public String toString()
    {
        return "c:" +
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
               this.getCor().getBlue();
    }

    //m�todos obrigatorios
    @Override
    public Object clone()
    {
        Circulo clone = null;
        try
        {
            clone = new Circulo(this);
        }
        catch(Exception e)
        {}      
        return clone;
    }
    public Circulo(Circulo modelo) throws Exception{
        if(modelo==null){
            throw new Exception("n�o pode ser nulo");
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
        Circulo circulo = (Circulo) obj;
        if(!this.p1.equals(circulo.p1) || !this.p2.equals(circulo.p2) ||
        this.altura != circulo.altura || this.largura != circulo.largura)
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
