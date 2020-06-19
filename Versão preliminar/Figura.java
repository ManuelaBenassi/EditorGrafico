import java.awt.*;

public abstract class Figura
{
    protected Color cor;
	  
    protected Figura ()
    {
        this (Color.BLACK);
    }
	  
    protected Figura (Color cor)
    {
        this.cor = cor;
    }
	  
    public void setCor (Color cor)
    {
        this.cor = cor;
    }
	  
    public Color getCor()
    {
    	return this.cor;
    }

  //public abstract boolean equals         (Object obj);
  //public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
}