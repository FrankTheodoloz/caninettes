import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;



class Fenetre extends JFrame implements ActionListener
{
	//Bouton
	JButton btnseConnecter, btncaninettesHS, btnquitter;
	
	public Fenetre(String aTitle)
	{
		setTitle(aTitle);
		
		JPanel jpHaut = new JPanel(new BorderLayout());
		add(jpHaut, "North");
		JPanel jpWest = new JPanel();
		jpHaut.add(jpWest,"West");
		
		btnseConnecter = new JButton("Se connecter");
		jpWest.add(btnseConnecter);
		btnseConnecter.addActionListener(this);
		
        btncaninettesHS = new JButton("Caninettes hors service");
		jpWest.add(btncaninettesHS);
		btncaninettesHS.addActionListener(this);
 
		btnquitter = new JButton("Quitter");
		jpWest.add(btnquitter);
		btnquitter.addActionListener(this);   

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(btnquitter))
		{
			System.exit(0);
		}
		

    }
	
					
}

public class CaniCrottes
{
	public static void main (String [] args)
	{
		Fenetre f=new Fenetre("CaniCrottes");
		f.pack();
		f.setVisible(true);
	}
}






