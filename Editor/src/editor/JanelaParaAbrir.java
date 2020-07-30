package editor;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class JanelaParaAbrir extends JFrame 
{
	protected static final long serialVersionUID = 1L;
	
	private JPanel[] panelField = new JPanel[2];
	private JTextField[] field = new JTextField[2];
	
	private JPanel[] panelButton = new JPanel[1];
	private JButton[] button = new JButton[2];

	private String[] label = {"Email: ", "Nome Do Desenho: "};
	private String[] buttonName = {"Abrir", "Cancelar"};

	public JanelaParaAbrir(Janela janela) 
	{
		super("Abrir Desenho");

		this.setLayout(new GridLayout(3, 2));
	        
		for(int i = 0; i < panelField.length; i++){
			panelField[i] = new JPanel();
			panelField[i].setLayout(new GridLayout(1, 0));

			field[i] = new JTextField(15);

			panelField[i].add(new JLabel(label[i]));
			panelField[i].add(field[i]);

			this.add(panelField[i]);
		}

		for(int i = 0, j = 0; i < panelButton.length; i++)
		{
            panelButton[i] = new JPanel();
            panelButton[i].setLayout(new GridLayout(1, 2));

            button[j] = new JButton(buttonName[j]);
            button[j+1] = new JButton(buttonName[j+1]);

            panelButton[i].add(button[j]);
            panelButton[i].add(button[j+1]);

            this.add(panelButton[i]);
        }

		button[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				janela.conectarCliente(field[0].getText(), field[1].getText(), false);
				JanelaParaAbrir.this.dispose();
            }
		});
		button[1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JanelaParaAbrir.this.dispose();
            }
		});

        this.setSize (300,150);
        this.setLocationRelativeTo(null);
        this.setVisible (true);
	}
}
