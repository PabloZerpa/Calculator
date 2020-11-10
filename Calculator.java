
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Calculator extends JFrame
{
    private static final long serialVersionUID = 1L;
    private Screen screen;
    private CPU cpu;
    private Keyboard keyboard;
    private Menu menu;
	private JPanel panel;
	private JPanel p1,p2,p3;

    Calculator()
    {
        this.setTitle("Calculator");
		this.setBounds(400,250,300,400);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
        
        cpu = new CPU(screen);
		screen = new Screen();
		keyboard = new Keyboard(screen,cpu,this);
		menu = new Menu(keyboard,cpu,screen);

		p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p2 = new JPanel();
		p2.setBackground(Color.BLACK);
		p3 = new JPanel();
		p3.setBackground(Color.BLACK);
		
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.NORTH, menu);
		this.add(BorderLayout.EAST, p1);
		this.add(BorderLayout.WEST, p2);
		this.add(BorderLayout.SOUTH, p3);
		panel.add(BorderLayout.NORTH, screen);
		panel.add(BorderLayout.CENTER, keyboard);

    }

    //Method for change window size
	public void Size(int value)
	{	
		if(value > 1)
			setSize(600,400);
		else
			setSize(300,400);
	}

    public static void main(String[] args) 
    {
        Calculator calc = new Calculator();
        calc.setVisible(true);
        calc.setResizable(false);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}