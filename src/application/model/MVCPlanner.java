package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MVCPlanner {
	public static String checklistsubmit(boolean add,String item, String q)
	{
		int n = 0;
		if(item.isEmpty())
			return "Item name must not be empty.";
		else if(q.isEmpty())
			return "Quantity must not be empty.";
			try {
				n = Integer.parseInt(q);
			} catch(NumberFormatException e){
				return "Quantity must be an integer.";
			}
		if(n < 1)
			return "Quantity must be greater than 0.";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> itemlist = new ArrayList<String>();
		ArrayList<String> quantitylist = new ArrayList<String>();
		try {
			list = readChecklistFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "File not found";
		}
		
		for(String current : list)
		{
			String currentsplit[];
			currentsplit = current.split(",");
			currentsplit[1] = currentsplit[1].replaceAll("\\s", "");
			itemlist.add(currentsplit[0]);
			quantitylist.add(currentsplit[1]);
		}
		if(add)
			addChecklistItem(itemlist,quantitylist,item,n);
		else
			if(itemlist.contains(item))
			{
				if(n <= Integer.parseInt(quantitylist.get(itemlist.indexOf(item))))
				{
					subtractChecklistItem(itemlist,quantitylist,item,n);
				}
				else
					return "Quantity given is greater than in checklist.";
			}
			else
				return "Item not found.";
		return "Success!";
	}
	public static void clearFile(String n) throws IOException {
		FileWriter clear = new FileWriter(n,false);
		PrintWriter clearer = new PrintWriter(clear,false);
		clearer.flush();
		clearer.close();
	}
	public static ArrayList<String> readChecklistFile() throws FileNotFoundException
	{
		String current;
		String currentsplit[];
		Scanner filereader = new Scanner (new File("Checklist.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while(filereader.hasNextLine())
		{
			current = filereader.nextLine();
			currentsplit = current.split(",");
			currentsplit[1] = currentsplit[1].replaceAll("\\s", "");
			if(!currentsplit[1].contains("-") && !currentsplit[1].equals("0"))
			{
				list.add(current);
			}
		}
		return list;
	}
	public static void writeChecklistFile(ArrayList<String> list,ArrayList<String> listnums) throws IOException
	{
		//Function to rewrite to file with updated lists
		//Clear file
		clearFile("Checklist.txt");
		//Create writers
		BufferedWriter bw = new BufferedWriter(new FileWriter("Checklist.txt",true));
		PrintWriter pw = new PrintWriter(bw);
		for(int i=0;i<list.size();i++)
		{
			//If 0 or negative, do not write to list
			if(listnums.get(i).equals("0")||listnums.get(i).equals("-"))
				continue;
			//Otherwise, write to list
			pw.println(list.get(i)+", "+listnums.get(i));
		}
		bw.close();
	}
	public static void addChecklistItem(ArrayList<String> list,ArrayList<String> listnums, String n, int m) {
		//Give function to read both arraylists, print a message, and update inventory.txt
		int temp;
		//Test list to see if item is already in list
		if(list.contains(n))
		{
			temp = Integer.parseInt(listnums.get(list.indexOf(n)));
			listnums.set(list.indexOf(n),String.valueOf(temp+m));
		}
		else //Otherwise add new item to list
		{
			list.add(n);
			listnums.add(String.valueOf(m));
		}
		//Write lists to file
		try {
			writeChecklistFile(list,listnums);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void subtractChecklistItem(ArrayList<String> list,ArrayList<String> listnums,String n, int m)
	{
		int temp = Integer.parseInt(listnums.get(list.indexOf(n)));
		listnums.set(list.indexOf(n),String.valueOf(temp-m));
		//Rewrite items and nums to inventory.txt
		try {
			writeChecklistFile(list,listnums);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
