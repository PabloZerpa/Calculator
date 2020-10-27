
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Screen extends JPanel //implements KeyListener
{
    private static final long serialVersionUID = 1L;
    private JTextField screen;
	private JTextField screen2;
	private JPanel p1;
	
	Screen()
	{
		this.setLayout(new BorderLayout());
		
		screen = new JTextField();
		screen2 = new JTextField();
		Configure(screen,20);
		Configure(screen2,12);

		this.add(BorderLayout.NORTH, screen2);
		this.add(BorderLayout.CENTER, screen);

		p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		this.add(BorderLayout.SOUTH, p1);
		
	}

//Method to configure the screens
public void Configure(JTextField screen, int number)
{
    screen.setBackground(Color.WHITE);
    screen.setFont(new Font("Arial", 1, number));
    screen.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    screen.setEditable(false);
	screen.setHorizontalAlignment(SwingConstants.RIGHT);
	//screen.addKeyListener(this);
}

	public void set(String message) 
	{
		screen.setText(message);
	}
	
	public void set2(String message) 
	{
		screen2.setText(message);
	}
	
	public String get() 
	{
		return screen.getText();
	}
	
	public String get2() 
	{
		return screen2.getText();
	}
}