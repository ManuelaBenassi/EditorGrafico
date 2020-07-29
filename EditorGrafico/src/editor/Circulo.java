package editor;

import java.awt.*;

/**
 * Essa clase faz o desenho da figura Circulo
 * 
 * @version 1.1
 * @author Guilherme Brentan e Manuela Benassi
 * @since Primeira versão da aplicação
 */
public class Circulo extends Figura {
    protected Ponto p1, p2;
    protected int altura, largura;

    /**
     * Construtor da classe Circulo
     * 
     * @param x1  - posição 1 da reta x
     * @param y1  - posição 1 da reta y
     * @param x2  - posição 2 da reta x
     * @param y2  - posição 2 da reta y
     * @param cor - cor definida pra fora da figura
     */
    public Circulo(int x1, int y1, int x2, int y2, Color cor) {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
    }

    public Circulo(String dados) {

    }

    /**
     * Da o valor dos parâmetros para o ponto1
     * 
     * @param x posição na reta x
     * @param y posição na reta y
     */
    public void setP1(int x, int y) {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    /**
     * Da o valor dos parâmetros para o ponto2
     * 
     * @param x posição na reta x
     * @param y posição na reta y
     */

    public void setP2(int x, int y) {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    /**
     * Retorna o ponto 1
     * 
     * @return Ponto - ponto1
     */
    public Ponto getP1() {
        return this.p1;
    }

    /**
     * Retorna o ponto 2
     * 
     * @return Ponto - ponto2
     */

    public Ponto getP2() {
        return this.p2;
    }

    /**
     * Método que desenha a circunferência
     * 
     * @param g graphics - objeto que desenha o circulo
     * @param c cor que vai dentro
     */

    public void torneSeVisivel(Graphics g, Color c) {
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do quadrado
        g.drawOval(this.p1.getX(), this.p1.getY(), // ponto inicial clicado
                largura, // largura do quadrado
                altura); // altura do quadrado
        g.setColor(c);// define a cor para pintar o fundo
        g.fillOval(this.p1.getX(), this.p1.getY(), // ponto inicial clicado
                largura, // largura do quadrado
                altura);// altura do quadrado
    }

    /**
     * Método que calcula o tamanho que o cícrculo terá
     */

    private void calculaTamanho() {
        // m�todo antigo, n�o funcional
        /*
         * int larguraTriangulo = this.p1.getX() - this.p2.getX(); //delta x int
         * alturaTriangulo = this.p1.getY() - this.p2.getY(); //delta y
         * 
         * int hipotenusa = (alturaTriangulo * alturaTriangulo) + (larguraTriangulo *
         * larguraTriangulo); //calculo basico de pitagoras
         * 
         * this.altura = (int)Math.sqrt(hipotenusa); //pega a parte inteira da raiz
         * quadrada da hipotenusa this.largura = this.altura; //define as dimensoes
         */

        // novo m�todo, funciona melhor
        int deltaX, deltaY;

        deltaX = this.p1.getX() - this.p2.getX();

        if (deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY();

        if (deltaY < 0)
            deltaY = -deltaY;

        if (deltaX >= deltaY) // vejo se a dist�ncia entre os dois pontos � maior no eixo X ou no eixo Y
        {
            this.altura = deltaX; // sendo no eixo x:
                                  // -usarei por parametro de distancia o deltaX;
            this.largura = this.altura; // -farei ambos os lados do quadrado igauis;
        } else // sen�o, uso o deltaY como parametro para o tamanho do quadrado
        {
            this.altura = deltaY;

            this.largura = this.altura;
        }
    }

    /**
     * Método que define a string da classe
     * 
     * @return String com todas as informações
     */
    public String toString() {
        return "c:" + this.p1.getX() + ":" + this.p1.getY() + ":" + this.p2.getX() + ":" + this.p2.getY() + ":"
                + this.getCor().getRed() + ":" + this.getCor().getGreen() + ":" + this.getCor().getBlue();
    }

    /**
     * Método que cria um clone dessa classe
     * 
     * @return Circulo- clone dessa classe
     */
    @Override
    public Object clone() {
        Circulo clone = null;
        try {
            clone = new Circulo(this);
        } catch (Exception e) {
        }
        return clone;
    }

    /**
     * Construtor de cópia- constroi uma cópia da classe passada no parametro
     * 
     * @param modelo classe a ser copiada
     * @throws Exception quando a classe passada é vazia
     */

    public Circulo(Circulo modelo) throws Exception {
        if (modelo == null) {
            throw new Exception("n�o pode ser nulo");
        }
        this.p1 = modelo.p1;
        this.p2 = modelo.p2;
        this.altura = modelo.altura;
        this.largura = modelo.largura;

    }

    /**
     * Verifica se a classe passada como parametro é igual
     * 
     * @param obj classe a ser comparada
     * @return se as classes são iguais ou não
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != this.getClass())
            return false;
        Circulo circulo = (Circulo) obj;
        if (!this.p1.equals(circulo.p1) || !this.p2.equals(circulo.p2) || this.altura != circulo.altura
                || this.largura != circulo.largura)
            return false;
        return true;
    }

    /**
     * Retorna o haschcode da classe
     * 
     * @return haschcode
     */
    @Override
    public int hashCode() {
        int ret = 111;
        ret = ret * 5 + this.p1.hashCode();
        ret = ret * 5 + this.p2.hashCode();
        ret = ret * 5 + new Integer(this.altura).hashCode();
        ret = ret * 5 + new Integer(this.largura).hashCode();
        if (ret < 0)
            ret = -ret;

        return ret;
    }
}
