import javax.swing.JOptionPane;

public class CPU 
{
    private double y = 0;
	private boolean start = true;
	private boolean start2 = true;
	private boolean paren = false;
	private String sign = "";
	private Screen screen;
	
	CPU(Screen s)
	{
		screen = s;
    }
    
	// Method of saving the operands
	public void Categorize(String value, String operator)
	{	
		if(start == true)
		{	
			y = Double.parseDouble(value);
			sign = operator;
			start = false;
		}
		else        
		{	
			screen.set("" + Operation(Double.parseDouble(value), sign, Menu.angle));
			sign = operator;
		}
		paren = true;
	}

	// Method of saving operations in parentheses
	public void Categorize2(String value, String operator)
	{
		if(start2 == true)
		{	
			y = Double.parseDouble(value);
			sign = operator;
			start2 = false;
		}
		else               
		{	
			screen.set("" + Operation(Double.parseDouble(value), sign, Menu.angle));
			sign = operator;
		}
	}
	
	// Method to restore values when using C
	public void restore()
	{
		y = 0;
		start = true;
		start2 = true;
		sign = "";
	}

	public void Parenthesis(boolean parenthesis)
	{
		paren = parenthesis;
	}
	
	// Method in charge of performing the operations
	public double Operation(Double x, String operator, boolean a)
	{	
		if(paren == false && operator != "/")
		{
			double aux = x;
			x = y;
			y = aux;
		}

		if(operator.equals("+"))
			y += x;
		else if(operator.equals("-"))
			y -= x;
		else if(operator.equals("*"))
			y *= x;
		else if(operator.equals("/"))
			if(x == 0)
			{
				JOptionPane.showMessageDialog(null, "No se puede dividir entre 0");
				restore();
				screen.set("");
			}
			else
				y /= x;
		else if(operator.equals("Mod"))
			y %= x;
		else if(operator.equals("xʸ"))
		{
			if(paren == true)
				y = Math.pow(y,x);
			else
				y = Math.pow(x,y);
		}
		else if(operator.equals("√"))
		{
			if(paren == true)
				y = Math.pow(y,1/x);
			else
				y = Math.pow(x,1/y);
		}
		else if(operator.equals("E"))
			y = y*(Math.pow(10, x));
		else if(operator.equals("xˉ¹"))
			y = 1/y;
		
		//Factorial
		else if(operator.equals("x!"))
			y = Factorial(y);
		
		//Logaritmicas y Exponenciales
		else if(operator.equals("ln"))
		{
			if(y < 0)
			{
				JOptionPane.showMessageDialog(null, "value invalido");
				restore();
				screen.set("");
			}
			else
				y = Math.log(y);
		}
		else if(operator.equals("log"))
		{	if(y < 0)
			{
				JOptionPane.showMessageDialog(null, "value invalido");
				restore();
				screen.set("");
			}
			else
				y = Math.log10(y);
		}
		else if(operator.equals("e"))
			y = Math.exp(y);
		
		//Operacion Trigonometricas
		else if(operator.equals("sin"))
		{
			if(Menu.angle == false)
				y = Math.sin(y);
			else
				y = Math.toDegrees(Math.sin(y));
		}
		else if(operator.equals("cos"))
		{
			if(Menu.angle == false)
				y = Math.cos(y);
			else
				y = Math.toDegrees(Math.cos(y));
		}
		else if(operator.equals("tan"))
		{
			if(Menu.angle == false)
				y = Math.tan(y);
			else
				y = Math.toDegrees(Math.tan(y));
		}
		//Operacion Inversas Trigonometricas
		else if(operator.equals("sinˉ¹"))
		{	
			if(y > 1 || y < 0)
			{
				JOptionPane.showMessageDialog(null, "value invalido");
				restore();
				screen.set("");
			}
			else if(Menu.angle == false)
				y = Math.asin(y);
			else
				y = Math.toDegrees(Math.asin(y));
		}
		else if(operator.equals("cosˉ¹"))
		{
			if(y > 1 || y < 0)
			{
				JOptionPane.showMessageDialog(null, "value invalido");
				restore();
				screen.set("");
			}
			else if(Menu.angle == false)
				y = Math.acos(y);
			else
				y = Math.toDegrees(Math.acos(y));
		}
		else if(operator.equals("tanˉ¹"))
		{
			if(Menu.angle == false)
				y = Math.atan(y);
			else
				y = Math.toDegrees(Math.sin(y));
		}
		//Operacion Hiper-Trigonometricas
		else if(operator.equals("sinh"))
		{	
			y = Math.sinh(y);
		}
		else if(operator.equals("cosh"))
		{
			y = Math.cosh(y);
		}
		else if(operator.equals("tanh"))
		{
			y = Math.tanh(y);
		}
		//Operacion HiperInversas-Trigonometricas
		else if(operator.equals("sinhˉ¹"))
		{	
			y = Math.log(y + Math.sqrt(Math.pow(y, 2) + 1));
		}
		else if(operator.equals("coshˉ¹"))
		{
			y = Math.log(y + Math.sqrt(Math.pow(y, 2) - 1));
		}
		else if(operator.equals("tanhˉ¹"))
		{
			y = Math.log(1 + y) / (1 - y) / 2;
		}
		else if(operator.equals("="))
			y = x;
		
		return y;
	}
	
	// Method for calculating the factorial
	public double Factorial(double x)
	{
		int j = 1;
		screen.set2(screen.get() + "!");
		if((int)x != 0 || (int)x != 1)
		{
			for(int i = 2; i <= (int)x; i++)
			{
				j *= i;
			}
		}
		return (double)j;
	}


}
