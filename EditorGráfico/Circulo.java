import java.awt.*;

public class Circulo extends Figura
{
    protected Ponto p1, p2;
    protected int altura, largura;

    public Circulo(int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
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

    public void torneSeVisivel(Graphics g)
    {
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do quadrado
        g.drawOval(this.p1.getX(), this.p1.getY(), //ponto inicial clicado
                   largura, //largura do quadrado
                   altura); //altura do quadrado
    }

    private void calculaTamanho()
    {
        int larguraTriangulo = this.p1.getX() - this.p2.getX(); //delta x
        int alturaTriangulo = this.p1.getY() - this.p2.getY(); //delta y
        
        int hipotenusa = (alturaTriangulo * alturaTriangulo) + (larguraTriangulo * larguraTriangulo); //calculo basico de pitagoras

        this.altura = (int)Math.sqrt(hipotenusa); //pega a parte inteira da raiz quadrada da hipotenusa
        this.largura = this.altura; //define as dimensoes
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
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}