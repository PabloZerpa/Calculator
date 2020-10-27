
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class Menu extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	public static boolean angle = false;
	private Screen screen;
	private CPU cpu;
	private Calculator calc;
	private Keyboard keyboard;
	private JMenuBar bar;
	private JMenu options;
	private ButtonGroup group_options;
	private int inv = -1, hip = -1;
	private JButton inverse,hyperbolic,rad,deg;
	private JPanel grid,p1,p2,p3,p4;
	
	Menu(Keyboard k, CPU c, Screen s)
	{
		// Menu configuration
		this.setLayout(new BorderLayout());

		screen = s;
		keyboard = k;
		k = new Keyboard(screen,cpu,calc);
		cpu = new CPU(screen);

		bar = new JMenuBar();
		options = new JMenu("Options");
		group_options = new ButtonGroup();
		
		Categorize("Estandar");
		Categorize("Cientifica");
		Categorize("Programador");

		bar.setBackground(Color.BLACK);
		bar.setBorderPainted(false);
		bar.add(options);
		options.setForeground(Color.WHITE);
		this.add(BorderLayout.NORTH, bar);

		grid = new JPanel();
		grid.setBackground(Color.BLACK);
		grid.setLayout(new GridLayout(1,4));
		grid.setVisible(true);
		//this.add(BorderLayout.CENTER, grid);
		p4 = new JPanel();
		p4.setBackground(Color.BLACK);
		this.add(BorderLayout.CENTER, p4);

		inverse = new JButton("Inv");
		hyperbolic = new JButton("Hip");
		rad = new JButton("Rad");
		deg = new JButton("Deg");
		Configuration(inverse, grid);
		Configuration(hyperbolic, grid);
		Configuration(rad, grid);
		Configuration(deg, grid);

		p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p1.setVisible(false);
		p2 = new JPanel();
		p2.setBackground(Color.BLACK);
		p3 = new JPanel();
		p3.setBackground(Color.BLACK);
		this.add(BorderLayout.SOUTH, p1);
		this.add(BorderLayout.EAST, p2);
		this.add(BorderLayout.WEST, p3);

	}

	// Method for setting trigonometric buttons
	public void Configuration(JButton button, JPanel grid)
	{
		button.addActionListener(this);
		button.setBackground(Color.LIGHT_GRAY);
		button.setVisible(true);
		grid.add(button);
	}
	
	// Method to categorize the menus and submenus
	public void Categorize(String name)
	{
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
		item.setBackground(Color.BLACK);
		item.setForeground(Color.WHITE);
		item.addActionListener(this);
		if(name == "Estandar" || name == "Cientifica" || name == "Programador")
		{
			group_options.add(item);
			options.add(item);
			if(name == "Estandar")
				item.setSelected(true);
			else if(name == "Programador")
				item.setEnabled(false);;
		}
	}

	//Actions from each menu
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == "Cientifica")
		{
			keyboard.Mode(2);
			inverse.setBackground(Color.LIGHT_GRAY);
			hyperbolic.setBackground(Color.LIGHT_GRAY);
			rad.setBackground(new Color(120,225,120));
			deg.setBackground(Color.WHITE);
			grid.setVisible(true);
			this.add(BorderLayout.CENTER, grid);
		}
		else if(e.getActionCommand() == "Estandar")
		{
			keyboard.Mode(1);
			grid.setVisible(false);
			this.add(BorderLayout.CENTER, p4);
		}
		
		if(e.getActionCommand() == "Rad")
		{
			angle = false;
			rad.setBackground(new Color(120,225,120));
			deg.setBackground(Color.WHITE);
		}
		else if(e.getActionCommand() == "Deg")
		{
			angle = true;
			deg.setBackground(new Color(120,225,120));
			rad.setBackground(Color.WHITE);
		}
		
		if(e.getActionCommand() == "Inv")
		{
			inv *= -1;
			if(inv > 0 && hip < 0)
                keyboard.Mode(3);
			else if(inv > 0 && hip > 0)
                keyboard.Mode(5);
			else if(inv < 0 && hip < 0)
                keyboard.Mode(2);
			else if(inv < 0 && hip > 0)
                keyboard.Mode(4);

			if(inv == 1)
				inverse.setBackground(new Color(120,225,120));
			else
				inverse.setBackground(Color.LIGHT_GRAY);
		}
		else if(e.getActionCommand() == "Hip")
		{
			hip *= -1;
			if(hip > 0 && inv < 0)
                keyboard.Mode(4);
			else if(hip > 0 && inv > 0)
                keyboard.Mode(5);
			else if(hip < 0 && inv < 0)
                keyboard.Mode(2);
			else if(hip < 0 && inv > 0)
				keyboard.Mode(3);
			
			if(hip == 1)
				hyperbolic.setBackground(new Color(120,225,120));
			else
				hyperbolic.setBackground(Color.LIGHT_GRAY);
		}
	}
    
}