import java.awt.*;
import javax.swing.*;
public class Cor extends JFrame{

   public  Cor()
   {}
    public Color escolherCor(){
        Color retorno = Color.black;
        try {
         retorno = JColorChooser.showDialog( Cor.this,
         "Selecione uma cor", retorno );
 
            
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        } 
        return retorno;
    }
}