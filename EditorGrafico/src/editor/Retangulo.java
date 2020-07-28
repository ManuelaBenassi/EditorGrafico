package editor;

import java.awt.*;

public class Retangulo extends Figura {

    Ponto p1, p2, p3;
    int altura = 0, largura = 0;

    /**
     * Construtor da classe Retângulo
     * 
     * @param x1  - posição 1 da reta x
     * @param y1  - posição 1 da reta y
     * @param x2  - posição 2 da reta x
     * @param y2  - posição 2 da reta y
     * @param x3  - posição 3 da reta x
     * @param y3  - posição 3 da reta y
     * @param cor - cor definida pra fora da figura
     */
    public Retangulo(int x1, int y1, int x2, int y2, int x3, int y3, Color cor) {
        super(cor);

        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
        this.p3 = new Ponto(x3, y3);
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
        this.p2.setX(x);
        this.p2.setY(y);
    }

    /**
     * Da o valor dos parâmetros para o ponto3
     * 
     * @param x posição na reta x
     * @param y posição na reta y
     */
    public void setP3(int x, int y) {
        this.p3.setX(x);
        this.p3.setY(y);
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
     * Retorna o ponto 3
     * 
     * @return Ponto - ponto3
     */
    public Ponto getP3() {
        return this.p3;
    }

    /**
     * Método que desenha o retângulo
     * 
     * @param g graphics - objeto que desenha o circulo
     * @param c cor que vai dentro
     */

    public void torneSeVisivel(Graphics g, Color c) {
        g.setColor(this.cor);
        calculaTamanho();// caclcula as dimensoes do retangulo
        g.drawRect(this.p1.getX(), this.p1.getY(), // ponto inicial clicado
                largura, // largura do retangulo
                altura); // altura do retangulo
        g.setColor(c);// define a cor para pintar o fundo
        g.fillRect(this.p1.getX(), this.p1.getY(), // ponto inicial clicado
                largura, // largura do retangulo
                altura);// altura do retangulo
    }

    /**
     * Método que calcula o tamanho que o retângulo terá
     */
    private void calculaTamanho() {
        // Novo m�todo, funciona melhor
        int deltaX, deltaY;

        // calculando o primeiro ponto
        deltaX = this.p1.getX() - this.p2.getX(); // achando o deltaX

        if (deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p2.getY(); // achando o deltaY

        if (deltaY < 0)
            deltaY = -deltaY;

        if (deltaX >= deltaY) // verifico qual dos Deltas devo utilizar
            if (this.largura == 0)
                this.largura = deltaX;
            else
                this.altura = deltaX;
        else if (this.altura == 0)
            this.altura = deltaY;
        else
            this.largura = deltaY;

        // calculando o segundo ponto
        deltaX = this.p1.getX() - this.p3.getX();

        if (deltaX < 0)
            deltaX = -deltaX;

        deltaY = this.p1.getY() - this.p3.getY();

        if (deltaY < 0)
            deltaY = -deltaY;

        if (deltaX >= deltaY)
            if (this.largura == 0)
                this.largura = deltaX;
            else
                this.altura = deltaX;
        else if (this.altura == 0)
            this.altura = deltaY;
        else
            this.largura = deltaY;
    }

    /**
     * Método que define a string da classe
     * 
     * @return String com todas as informações
     */
    public String toString() {
        return "r:" + this.p1.getX() + ":" + this.p1.getY() + ":" + this.p2.getX() + ":" + this.p2.getY() + ":"
                + this.p3.getX() + ":" + this.p3.getY() + ":" + this.getCor().getRed() + ":" + this.getCor().getGreen()
                + ":" + this.getCor().getBlue();
    }

    /**
     * Método que cria um clone dessa classe
     * 
     * @return Circulo- clone dessa classe
     */
    @Override
    public Object clone() {
        Retangulo clone = null;
        try {
            clone = new Retangulo(this);
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
    public Retangulo(Retangulo modelo) throws Exception {
        if (modelo == null) {
            throw new Exception("n�o pode ser nulo");
        }
        this.p1 = modelo.p1;
        this.p2 = modelo.p2;
        this.p3 = modelo.p3;
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
        Retangulo retangulo = (Retangulo) obj;
        if (!this.p1.equals(retangulo.p1) || !this.p2.equals(retangulo.p2) || !this.p3.equals(retangulo.p3)
                || this.altura != retangulo.altura || this.largura != retangulo.largura)
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
        ret = ret * 5 + this.p3.hashCode();
        ret = ret * 5 + new Integer(this.altura).hashCode();
        ret = ret * 5 + new Integer(this.largura).hashCode();
        if (ret < 0)
            ret = -ret;

        return ret;
    }

}
