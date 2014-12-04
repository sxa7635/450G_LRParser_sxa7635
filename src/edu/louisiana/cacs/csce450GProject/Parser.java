package edu.louisiana.cacs.csce450Project;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser{
	
	Stack <String> stack= new Stack<String>();
	LinkedList<String> queue= new LinkedList<String>();
	String[][] actionlookup  = new String[12][6];
	String[][] gotolookup = new String[12][3];
	String actionvalue= new String();
	String LHSvalue= new String();
	int lenghtoftheRHS;
	Stack <String> tempstack= new Stack<String>();
	String gotovalue= new String();
	String stackaction= new String();
	Stack <String> parsetreestack= new Stack<String>();
	String[] rules=new String[7];
	public Parser(String fileName){
		System.out.println("File to parse : "+"\n"+fileName);
	
	
	}
	
	public void printParseTree(){
		System.out.println("Hello World from " + getClass().getName());
		if (stack.isEmpty()){
			stack.push("0");
		}
		while(!actionvalue.equals("accept"))
		{
tempstack.clear();			
				actionvalue=actionlookup[returntop(stack.peek())][returnaction(queue.peek())];
									
				if(actionvalue.charAt(0)=='S')
				{
					stackaction=(queue.peek().concat(actionvalue.substring(1)));
					System.out.println(stack+"\t"+queue+"\t"+"["+stack.peek().substring(stack.peek().length()-1)+","+queue.peek()+"]"+"\t"+actionvalue+"\t"+"\t\t"+"\t"+"\t\t"+"\t"+"\t\t"+"\t"+"\t\t"+"\t"+"\t\t"+"\t"+"push"+stackaction);
					 queue.remove();
					 
					 stack.push(stackaction);
					
										
				}
				
				else if(actionvalue.charAt(0)=='R')
				{
					LHSvalue=Character.toString(rules[Integer.parseInt(actionvalue.substring(1))].charAt(0));
					
					lenghtoftheRHS=rules[Integer.parseInt(actionvalue.substring(1))].length()-1;
					if (Integer.parseInt(actionvalue.substring(1))==6)
					{
						lenghtoftheRHS--;
					}
					
					
					for(int i=0;i<stack.size()-lenghtoftheRHS;i++)
					{
					tempstack.push(stack.get(i));
					}
					
				gotovalue=gotolookup[returntop(tempstack.peek())][returnaction(LHSvalue)];
				stackaction=(LHSvalue.concat(gotovalue));
				System.out.println(stack+"\t"+queue+"\t"+"["+stack.peek().substring(stack.peek().length()-1)+","+queue.peek()+"]"+"\t"+actionvalue+"\t"+LHSvalue+"\t"+lenghtoftheRHS+"\t"+tempstack.toString()+"\t"+"["+tempstack.peek().substring(tempstack.peek().length()-1)+","+LHSvalue+"]"+"\t"+gotovalue+"\t"+"push"+stackaction);
				for(int i=1;i<=lenghtoftheRHS;i++)
				{
					stack.pop();
					
				}
				stack.push(stackaction);
				
				}
				
				if(actionvalue.charAt(0)=='a')
				{
				 
				
				
					System.out.println(stack+"\t"+queue+"\t"+"["+stack.peek().substring(stack.peek().length()-1)+","+queue.peek()+"]"+"\t"+actionvalue);
				}
		}
	}

	public void parse()
{
			
		
		queue.add("id");
		queue.add("+");
		queue.add("id");
		queue.add("*");
		queue.add("id");
		queue.add("$");
		
		
		actionlookup [0][0]= "S5";
		actionlookup [0][3]= "S4";
		actionlookup [1][1]= "S6";
		actionlookup [1][5]= "accept";
		actionlookup [2][1]= "R2";
		actionlookup [2][2]= "S7";
		actionlookup [2][4]= "R2";		
		actionlookup [2][5]= "R2";
		actionlookup [3][1]= "R4";
		actionlookup [3][2]= "R4";
		actionlookup [3][4]= "R4";
		actionlookup [3][5]= "R4";
		actionlookup [4][0]= "S5";
		actionlookup [4][3]= "S4";
		actionlookup [5][1]= "R6";
		actionlookup [5][2]= "R6";
		actionlookup [5][4]= "R6";
		actionlookup [5][5]= "R6";
		actionlookup [6][0]= "S5";
		actionlookup [6][3]= "S4";
		actionlookup [7][0]= "S5";
		actionlookup [7][3]= "S4";
		actionlookup [8][1]= "S6";
		actionlookup [8][4]= "S11";
		actionlookup [9][1]= "R1";
		actionlookup [9][2]= "S7";
		actionlookup [9][4]= "R1";
		actionlookup [9][5]= "R1";
		actionlookup [10][1]= "R3";
		actionlookup [10][2]= "R3";
		actionlookup [10][4]= "R3";
		actionlookup [10][5]= "R3";
		actionlookup [11][1]= "R5";
		actionlookup [11][2]= "R5";
		actionlookup [11][4]= "R5";
		actionlookup [11][5]= "R5";
		gotolookup [0][0]= "1";
		gotolookup [0][1]= "2";
		gotolookup [0][2]= "3";
		gotolookup [4][0]= "8";
		gotolookup [4][1]= "2";
		gotolookup [4][2]= "3";
		gotolookup [6][1]= "9";
		gotolookup [6][2]= "3";
		gotolookup [7][2]= "10";
		
		
		rules[1]="EE+T";
		rules[2]="ET";
		rules[3]="TT*f";
		rules[4]="TF";
		rules[5]="F(E)";
		rules[6]="Fid";
		
	  System.out.println("In parse constructor");
       printParseTree();
	}
	int returnaction(String var){
		int index= -1;
		 
		if(var.equals("id"))
			index=0;
		else if(var.equals("+"))
			index=1;
		else if(var.equals("*"))
			index=2;
		else if(var.equals("("))
			index=3;
		else if(var.equals(")"))
			index=4;
		else if(var.equals("$"))
			index=5;
		else if(var.equals("E"))
			index=0;
		else if(var.equals("T"))
			index=1;
		else if(var.equals("F"))
			index=2;
		
	
		return index;
	}
	int returntop(String var1){
		Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
		Matcher matcher = pattern.matcher(var1);
		int stk=-1;
		if(matcher.find()){
		    stk=Integer.parseInt(matcher.group());
			System.out.println(stk);
		}
			return stk;
		}
	
	
	
	
	
	
}

	
	
	


