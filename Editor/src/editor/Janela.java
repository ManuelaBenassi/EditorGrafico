package editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cliente_servidor.Cliente;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto     = new JButton ("Ponto"),
                      btnLinha     = new JButton ("Linha"),
                      btnCirculo   = new JButton ("Circulo"),
                      btnElipse    = new JButton ("Elipse"),
                      btnQuadrado  = new JButton ("Quadrado"),
                      btnRetangulo = new JButton ("Retangulo"),
                      btnCorFora   = new JButton ("Fora"),
                      btnCorDentro = new JButton ("Dentro"),
                      btnAbrir     = new JButton ("Abrir"),
                      btnSalvar    = new JButton ("Salvar");
                      
    protected MeuJPanel pnlDesenho = new MeuJPanel ();

    
    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, 
                      esperaInicioCirculo, esperaFimCirculo,
                      esperaInicioElipse, esperaMeioElipse, esperaFimElipse,
                      esperaInicioQuadrado,esperaFimQuadrado,
                      esperaInicioRetangulo,esperaMeioRetangulo,esperaFimRetangulo;

    protected Color corFora = Color.BLACK;
   

    protected Color corDentro = Color.WHITE;
    protected Ponto p1, p2;
    
    protected Vector<Figura> figuras = new Vector<Figura>();
    protected Janela janela = this;
    
    public void recebaDesenho(Vector<String> desenho)
    {
        figuras.removeAllElements();
        pnlDesenho.removeAll();
        
    	for(int i = 0; i < desenho.size(); i++)
    	{
    		switch(desenho.get(i).charAt(0))
    		{
	    		case 'c': figuras.add(new Circulo(desenho.get(i)));break;
	    		case 'e': figuras.add(new Elipse(desenho.get(i)));break;
	    		case 'l': figuras.add(new Linha(desenho.get(i)));break;
	    		case 'p': figuras.add(new Ponto(desenho.get(i)));break;
	    		case 'q': figuras.add(new Quadrado(desenho.get(i)));break;
	    		case 'r': figuras.add(new Retangulo(desenho.get(i)));break;
    		}
                
            figuras.get(i).torneSeVisivel(pnlDesenho.getGraphics(), corDentro);
    	}
    }
    
    protected void conectarCliente(String emailDoDono, String nomeDesenho, boolean vaiSalvar)
    {
    	Vector<String> desenho = new Vector<String>();
    	
    	for(int i = 0; i < figuras.size(); i++)
    		desenho.add(figuras.get(i).toString());
    	if(vaiSalvar)
    		new Cliente(emailDoDono, nomeDesenho, desenho, janela);
    	else
    		new Cliente(emailDoDono, nomeDesenho, null, janela);
    }
    
    public void recebaResultado(String resultado)
    {
        JOptionPane.showMessageDialog(null, resultado, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public Janela ()
    {
        super("Editor Grafico");
        this.getContentPane().setBackground(Color.WHITE);

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("/resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("/resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("/resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("/resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("/resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("/resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorForaImg = ImageIO.read(getClass().getResource("/resources/cores.jpg"));
            btnCorFora.setIcon(new ImageIcon(btnCorForaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorDentroImg = ImageIO.read(getClass().getResource("/resources/cores.jpg"));
            btnCorDentro.setIcon(new ImageIcon(btnCorDentroImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("/resources/quadrado.jpeg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo quadrado.jpeg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try
        {
            Image btnCorDentroImg = ImageIO.read(getClass().getResource("/resources/cores.jpg"));
            btnCorDentro.setIcon(new ImageIcon(btnCorDentroImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpeg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("/resources/retangulo.jpeg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpeg nao foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnQuadrado.addActionListener(new DesenhoDeQuadrado());
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());
        btnCorFora.addActionListener(new CorFora());
        btnCorDentro.addActionListener(new CorDentro());
        btnSalvar.addActionListener(new NovoSalvamento());
        btnAbrir.addActionListener(new AbrirDesenho());

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnCorFora);
        pnlBotoes.add (btnCorDentro);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (1200,600);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
        static final long serialVersionUID = 2000; //variavel apenas para tirar a marca��o de aviso
        
	    public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g,corDentro);
        }
        
        public void mousePressed (MouseEvent e)
        {
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corFora));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                esperaPonto = false;
            }
            else
                if (esperaInicioReta)
                {
                    p1 = new Ponto (e.getX(), e.getY(), corFora);
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Mensagem: clique o ponto final da reta");    
                }
                else
                    if (esperaFimReta)
                    {
                        esperaInicioReta = false;
                        esperaFimReta = false;
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                        statusBar1.setText("Mensagem:");    
                    }
                else
                    if(esperaInicioCirculo)
                    {
                        p1 = new Ponto (e.getX(), e.getY(), corFora);
                        esperaInicioCirculo = false;
                        esperaFimCirculo = true;
                        statusBar1.setText("Mensagem: estabele�a o diametro do circulo"); 
                    }
                    else
                        if(esperaFimCirculo)
                        {
                            esperaFimCirculo = false;
                            esperaInicioCirculo = false;
                            figuras.add(new Circulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                            statusBar1.setText("Mensagem:");
                        }
                        else
                            if(esperaInicioElipse)
                            {
                                p1 = new Ponto (e.getX(), e.getY(), corFora);
                                esperaInicioElipse = false;
                                esperaMeioElipse = true;
                                esperaFimElipse = false;
                                statusBar1.setText("Mensagem: estabele�a um dos diametros da elipse"); 
                            }
                            else
                                if(esperaMeioElipse)
                                {
                                    p2 = new Ponto (e.getX(), e.getY(), corFora);
                                    esperaInicioElipse = false;
                                    esperaMeioElipse = false;
                                    esperaFimElipse = true;
                                    statusBar1.setText("Mensagem: estabele�a o outro diametro diametro da elipse");
                                }
                                else
                                    if(esperaFimElipse)
                                    {
                                        esperaFimElipse = false;
                                        esperaMeioElipse = false;
                                        esperaInicioElipse = false;
                                        figuras.add(new Elipse(p1.getX(), p1.getY(), //primeiro ponto
                                                             p2.getX(), p2.getY(), //altura
                                                             e.getX(), e.getY(), //largura
                                                             corFora)); 
                                        figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                                        statusBar1.setText("Mensagem:");
                                    }
                                    else
                                        if(esperaInicioQuadrado){
                                            p1 = new Ponto (e.getX(), e.getY(), corFora);
                                            esperaInicioQuadrado = false;
                                            esperaFimQuadrado = true;
                                            statusBar1.setText("Mensagem: estabele�a o Lado do Quadrado"); 
                                        }
                                         else
                                                if(esperaFimQuadrado){
                                                    esperaFimQuadrado = false;
                                                    esperaInicioQuadrado = false;
                                                     figuras.add(new Quadrado(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora));
                                                    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                                                  statusBar1.setText("Mensagem:");
                                             }
                                             else
                                                if(esperaInicioRetangulo)
                                                    {
                                                       p1 = new Ponto (e.getX(), e.getY(), corFora);
                                                       esperaInicioRetangulo = false;
                                                       esperaMeioRetangulo = true;
                                                       esperaFimRetangulo = false;
                                                        statusBar1.setText("Mensagem: estabele�a um lados do ret�ngulo"); 
                                                    }
                                                    else
                                                     if(esperaMeioRetangulo)
                                                     {
                                                          p2 = new Ponto (e.getX(), e.getY(), corFora);
                                                         esperaInicioRetangulo = false;
                                                          esperaMeioRetangulo = false;
                                                         esperaFimRetangulo = true;
                                                         statusBar1.setText("Mensagem: estabele�a o outro lado do ret�ngulo");
                                                        }
                                                     else
                                                         if(esperaFimRetangulo)
                                                         {
                                                             esperaFimRetangulo = false;
                                                                esperaMeioRetangulo = false;
                                                             esperaInicioRetangulo = false;
                                                                figuras.add(new Retangulo(p1.getX(), p1.getY(), //primeiro ponto
                                                                                    p2.getX(), p2.getY(), //altura
                                                                                    e.getX(), e.getY(), //largura
                                                                                    corFora)); 
                                                                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics(),corDentro);
                                                                statusBar1.setText("Mensagem:");
                                                         }
        }
        
        public void mouseReleased (MouseEvent e)
        {}
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
        public void actionPerformed(ActionEvent e)    
        {
            esperaPonto = true;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;

            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaInicioCirculo = true;
            esperaFimCirculo = false;

            statusBar1.setText("Mensagem: clique no inicio do circulo");
        }
    }

    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaInicioCirculo = false;
            esperaFimCirculo = false;
            esperaInicioElipse = true;
            esperaFimElipse = false;

            statusBar1.setText("Mensagem: clique no inicio da elipse");
        }
    }
    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaInicioQuadrado = true;
            esperaFimQuadrado = false;

            statusBar1.setText("Mensagem: clique no inicio do quadrado");
        }
    }
    protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = true;
            esperaFimRetangulo = false;

            statusBar1.setText("Mensagem: clique no inicio da retangulo");
        }
    }
    protected class CorFora implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
                
                try {
                 corFora = JColorChooser.showDialog( Janela.this,
                 "Selecione uma cor", corFora );
         
                    
                } catch (Exception ex) {
                    
                    System.out.println(ex.getMessage());
                }
        }
    }
    protected class CorDentro implements ActionListener{
        public void actionPerformed(ActionEvent e){
            
                
            try {
             corDentro = JColorChooser.showDialog( Janela.this,
             "Selecione uma cor", corDentro );
     
                
            } catch (Exception ex) {
                
                System.out.println(ex.getMessage());
            }
            
    }
    }
    
    protected class NovoSalvamento implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		try {
    			new JanelaDeSalvamento(janela);
    		}
    		catch(Exception a)
    		{}
    	}
    }
    
    protected class AbrirDesenho implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		try {
    			new JanelaParaAbrir(janela);
    		}
    		catch(Exception a)
    		{}
    	}
    }
    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}