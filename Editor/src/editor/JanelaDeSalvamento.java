package editor;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class JanelaDeSalvamento extends JFrame 
{
    /*
        Essa janela tem por objetivo conlher dados para que seja possivel salvar um desenho
        @params:
            Janela janela --> para eu saber qual é a janela chamante e retornar as informações para ela
    */
    private JPanel[] panelField = new JPanel[2];
    private JTextField[] field = new JTextField[2];

    private JPanel[] panelButton = new JPanel[1];
    private JButton[] button = new JButton[2];

    private String[] label = {"Email: ", "Nome Do Desenho: "};
    private String[] buttonName = {"Salvar", "Cancelar"};

    public JanelaDeSalvamento(Janela janela) 
    {
            super("Salvar Desenho");

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

            button[0].addActionListener(new ActionListener() //botao de 'Salvar' for clicado
            {
                    public void actionPerformed(ActionEvent e)
                    {
                            janela.conectarCliente(field[0].getText(), field[1].getText(), true); //envio o texto digitado
                            JanelaDeSalvamento.this.dispose();//fecho a janela de salvamento
        }
            });
            button[1].addActionListener(new ActionListener() //caso clique em 'Cancelar'
            {
                    public void actionPerformed(ActionEvent e)
                    {
                            JanelaDeSalvamento.this.dispose();//fecho a janela
        }
            });

        this.setSize (300,150);
        this.setLocationRelativeTo(null);
        this.setVisible (true);
    }
}
