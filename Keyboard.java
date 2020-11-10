import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Keyboard extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private String value = "";
	private boolean start = false;
	private String auxValue = "";
	public boolean parenthesis = false;
	private boolean x = true;
	private String pausedValue = "";
	private String pausedSign = "";
	private JPanel keyboard1, keyboard2,keyboard3,keyboard4,keyboard5;
	private String standard[] = {"C","CE","←","±","7", "8","9","/","4","5","6","*","1","2","3","-",".","0","=","+"};
	private String scientific[] = {"sin","x!","Mod","C","CE","←","±","cos","e","√","7","8","9","/","tan","ln","xʸ",
										 "4","5","6","*","π","log","xˉ¹","1","2","3","-","E","(",")","0",".","=","+"};
	private String[] trigo = {"sinˉ¹","cosˉ¹","tanˉ¹","sinh","cosh","tanh","sinhˉ¹","coshˉ¹","tanhˉ¹"};
	private Calculator calc;
	private CPU cpu;
	private Screen screen;
	
	Keyboard(Screen s, CPU c, Calculator ca)
	{
		// Keyboard configuration
		this.setLayout(new BorderLayout());

		keyboard1 = new JPanel();
		keyboard2 = new JPanel();
		keyboard3 = new JPanel();
		keyboard4 = new JPanel();
		keyboard5 = new JPanel();
			
		Configure(keyboard1, 1);
		Configure(keyboard2, 2);
		Configure(keyboard3, 3);
		Configure(keyboard4, 4);
		Configure(keyboard5, 5);

		screen = s;
		calc = ca;
		cpu = new CPU(screen);
		
	}

	Keyboard(Screen s)
	{
		screen = s;
		cpu = new CPU(screen);
	}
	
	// Method for Configuring Keyboards
	public void Configure(JPanel keyboard, int x)
	{
		keyboard.setBackground(Color.BLACK);
		if(x == 1)
		{
			keyboard.setLayout(new GridLayout(5,4));
			for(int i = 0; i < 20; i++)
				Buttons(standard[i], keyboard);
			add(BorderLayout.CENTER, keyboard);
		}
		else if(x == 2)
		{
			keyboard.setLayout(new GridLayout(5,7));
			for(int i = 0; i < 35; i++)
				Buttons(scientific[i], keyboard);
		}
		else if(x > 2)
		{
			keyboard.setLayout(new GridLayout(5,7));
			for(int i = 0; i < 35; i++)
			{
				//Para inversas trigonometricas
				if(i == 0 && x == 3)
					Buttons(trigo[0], keyboard);
				else if(i == 7 && x == 3)
					Buttons(trigo[1], keyboard);
				else if(i == 14 && x == 3)
					Buttons(trigo[2], keyboard);
				//Para hiper-trigonometricas
				else if(i == 0 && x == 4)
					Buttons(trigo[3], keyboard);
				else if(i == 7 && x == 4)
					Buttons(trigo[4], keyboard);
				else if(i == 14 && x == 4)
					Buttons(trigo[5], keyboard);
				//Para inversas hiper-trigonometricas
				else if(i == 0 && x == 5)
					Buttons(trigo[6], keyboard);
				else if(i == 7 && x == 5)
					Buttons(trigo[7], keyboard);
				else if(i == 14 && x == 5)
					Buttons(trigo[8], keyboard);
				else
					Buttons(scientific[i], keyboard);
			}
		}
	}

	// Method for Configuring Buttons
	public void Buttons(String nombre, JPanel keyboard)
	{
		JButton button = new JButton(nombre);
		if (nombre.equals("="))
			button.setBackground(new Color(255,244,43));
		else if (nombre.equals("+") || nombre.equals("-") || nombre.equals("*") || nombre.equals("/"))
			button.setBackground(new Color(30,30,225));
		else if (nombre.equals("C") || nombre.equals("CE") || nombre.equals("←"))
			button.setBackground(new Color(215,75,1));
		else
			button.setBackground(Color.GRAY);
		button.setForeground(Color.black);
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		button.addActionListener(this);
		button.addKeyListener(new Key());
		keyboard.add(button);
	}

	// Method for selecting operations
	public void Selection(String value)
	{	
		if(value.equals("("))
		{
			parenthesis = true;
			start = true;
			cpu.Parenthesis(parenthesis);

			if(screen.get2() == "")
				screen.set2(value);
			else
				screen.set2(screen.get2() + " " + value);
		}
		else if(value.equals("C"))
		{	
			screen.set2("");
			screen.set("");
			parenthesis = false;
			cpu.restore();
		}
		else if(value.equals("CE"))
            screen.set("");
		else if(value.equals("←"))
		{
			if(screen.get().length() == 0)
                screen.set("");
			else
                screen.set(screen.get().substring(0, screen.get().length() - 1));
		}
		else if(value.equals("±"))
		{ 
			if(screen.get().equals(""))
                screen.set("-");
			else if(screen.get().equals("-"))
                screen.set("");
			else
				if(Double.parseDouble(screen.get()) < 0)
                    screen.set(screen.get().substring(1, screen.get().length()));
				else
                    screen.set("-" + screen.get());
		}
		else if(value.equals("="))
		{
			cpu.Categorize(screen.get(), value);
			screen.set2("");
			parenthesis = false;
		}
		else if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/") || value.equals("Mod") ||
				value.equals("xʸ") || value.equals("√") || value.equals("E"))
		{
			if(auxValue.equals("+") == false || auxValue.equals("-") == false || auxValue.equals("*") == false || auxValue.equals("/") == false || 
			   auxValue.equals("Mod") == false || auxValue.equals("xʸ") == false || auxValue.equals("√") == false || auxValue.equals("E") == false)
			{
				if(parenthesis == true)
				{
					start = true;
					if(value.equals("xʸ"))
                        screen.set2(screen.get2() + screen.get() + "^");
					else if(value.equals("√"))
                        screen.set2(screen.get2() + "ʸ√" + screen.get());
					else
						screen.set2(screen.get2() + screen.get() + value);

					cpu.Categorize2(screen.get(), value);
				}
				else
				{
					cpu.Categorize(screen.get(), value);
					start = true;
					if(value.equals("xʸ"))
                        screen.set2(screen.get() + "^");
					else if(value.equals("√"))
                        screen.set2("ʸ√" + screen.get());
					else
                        screen.set2(screen.get() + value);
					
					pausedValue = screen.get();
					pausedSign = value;
				}
			}
		}
		else if(value.equals("sin") || value.equals("cos") || value.equals("tan") || value.equals("e") || value.equals("ln") || 
				value.equals("log") || value.equals("xˉ¹") || value.equals("x!") || value.equals("sinˉ¹") || value.equals("cosˉ¹") || 
				value.equals("tanˉ¹") || value.equals("sinh") || value.equals("cosh") || value.equals("tanh") || 
				value.equals("sinhˉ¹") ||value.equals("coshˉ¹") || value.equals("tanhˉ¹"))
		{
			if(auxValue.equals("sin")  == false || auxValue.equals("cos") == false || auxValue.equals("tan") == false || auxValue.equals("e") == false || 
			   auxValue.equals("ln") == false || auxValue.equals("log") == false || auxValue.equals("xˉ¹") == false || auxValue.equals("x!") == false || 
			   auxValue.equals("sinˉ¹") == false || auxValue.equals("cosˉ¹") == false || auxValue.equals("tanˉ¹") == false || auxValue.equals("sinh") == false || 
			   auxValue.equals("cosh") == false || auxValue.equals("tanh") == false || auxValue.equals("sinhˉ¹") == false ||auxValue.equals("coshˉ¹") || 
			   auxValue.equals("tanhˉ¹") == false)
			   {
				if(parenthesis == true)
				{
					start = true;
					cpu.Categorize2(screen.get(), value);
					if(value.equals("x!"))
                        screen.set2(screen.get2() + screen.get() + "!");
					else if(value.equals("xˉ¹"))
                        screen.set2(screen.get2() + screen.get() + "ˉ¹");
					else
                        screen.set2(screen.get2() + value + "(" + screen.get() + ")");
				}
				else
				{
					cpu.Categorize(screen.get(), value);
					start = true;
					if(value.equals("x!"))
                        screen.set2(screen.get() + "!");
					else if(value.equals("xˉ¹"))
                        screen.set2(screen.get() + "ˉ¹");
					else
                        screen.set2(value + "(" + screen.get() + ")");

					pausedValue = screen.get();
					pausedSign = value;
				}
			}
		}
		else if(value.equals("π")) 
            screen.set("3.14");
		else if (start == true)
		{
			screen.set(value);
			start = false;
		}
		else if(value.equals(")"))
		{
			if(parenthesis == true)
			{
				parenthesis = false;
				cpu.Parenthesis(parenthesis);

				screen.set(screen.get().substring(0, screen.get().length()));
				screen.set2(screen.get2() + screen.get() + value);

				cpu.Categorize2(screen.get(), value);
				screen.set("" + cpu.Operation(Double.parseDouble(pausedValue), pausedSign, Menu.angle));
			}
		}
		else
            screen.set(screen.get() + value);

		auxValue = value;
	}
	
	// Event method for each operator's actions
	public void actionPerformed(ActionEvent e) 
	{	
		value = e.getActionCommand();

		Selection(value);
	}
	
	// Method to switch from standard-scientific-programmer mode
	public void Mode(int value)
	{
		calc.Size(value);
		
		if(value == 2)
			Visibility(keyboard2);
		else if(value == 3)
			Visibility(keyboard3);	
		else if(value == 4)
			Visibility(keyboard4);
		else if(value == 5)
			Visibility(keyboard5);
		else
			Visibility(keyboard1);
	}
	
	//Desactive - Active keyboards
	public void Visibility(JPanel keyboard)
	{
		keyboard1.setVisible(false);
		keyboard2.setVisible(false);
		keyboard3.setVisible(false);
		keyboard4.setVisible(false);
		keyboard5.setVisible(false);
		add(BorderLayout.CENTER, keyboard);
		keyboard.setVisible(true);
	}

	//Keyboard events
	class Key extends KeyAdapter
	{
		public void keyReleased(KeyEvent e)
		{	
			String value = Character.toString(e.getKeyChar());

			if(value.equals("0") || value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5") || 
			value.equals("6") || value.equals("7") || value.equals("8") || value.equals("9") || value.equals("."))
			{
				if(x == false)
				{
					screen.set("");
				}
				screen.set(screen.get() + value);
				x = true;
			}
			else if(value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/"))
			{
				if(screen.get2().equals("") == false)
					Selection("=");
				if(screen.get().equals("") == false)
				{
					Selection(value);
					screen.set2(screen.get() + value);
					x = false;
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				Selection("=");
			}
			else if(e.getKeyCode() == KeyEvent.VK_DELETE)
			{
				Selection("C");
			}
			else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
			{
				Selection("←");
			}

		}
	}

}
