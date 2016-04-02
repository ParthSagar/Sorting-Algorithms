package algorithms;
/*
 * Author: Parth Manoj Sagar
 */

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QuickSort {
	
	private int[] sortedArray;
	
	public static void main(String[] args) {
		
		
		String filePath = "InputFile_2_5.xml";
		//String filePath = "InputFile_2_10.xml";
		//String filePath = "InputFile_2_15.xml";
		//String filePath = "InputFile_2_20.xml";
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder;
	    try {
	        dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();	        
	        NodeList nodeList = doc.getElementsByTagName("Value");
	        
	        int[] inputArray= new int[nodeList.getLength()];
	        //int[] inputArray={0, 1, 2, 2, 3, 3, 3, 4, 6, 7, 9, 9, 9, 9, 13, 13, 13, 15, 18, 19, 20, 21, 22, 22, 24, 24, 25, 26, 26, 28, 29, 31}; //Sorted Array
	        //int[] inputArray={31, 29, 28, 26, 26, 25, 24, 24, 22, 22, 21, 20, 19, 18, 15, 13, 13, 13, 9, 9, 9, 9, 7, 6, 4, 3, 3 ,3 ,2 ,2 ,1 ,0}; //Reversely Sorted
	        for (int i = 0; i < nodeList.getLength(); i++) {
                inputArray[i]= Integer.parseInt(nodeList.item(i).getTextContent());
                //For sorted and reversely sorted comment this inputArray[i] 
	        }
	        
	        QuickSort quicksort_obj = new QuickSort();
			
			
			long timer_start = System.nanoTime();		
			quicksort_obj.quickSort(inputArray);	
			long timer_stop = System.nanoTime();		
			double elapsedtime = (timer_stop - timer_start)/1000000F;			
			
			System.out.println("Sorted Array is: \n");	 
			for(int i = 0; i<inputArray.length; i++){
	            System.out.println(inputArray[i]+" ");            
	        }	
			System.out.println("\nTotal Time taken for Sorting: " + elapsedtime + " milliseconds");
			System.out.println("Total no. of key comparison is: "+Compare.comparecount);
	       
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }		
	}
	
	public void quickSort(int[] sorting_array)
	{		
		if(sorting_array.length <= 1 || sorting_array == null)		
			return;		
		else
		{
			this.sortedArray = sorting_array;
			processQuickSort(0, sorting_array.length - 1);
			
			
		}
	}
	
	public void processQuickSort(int leftIndex, int rightIndex)
	{
		int i = leftIndex;
		int j = rightIndex;
		
		if(Compare.doCompare(leftIndex, rightIndex, 2))	// leftIndex >= rightIndex) //
			return;
		
		
		Random random = new Random();
				
		int pivot_value = this.sortedArray[leftIndex + random.nextInt(rightIndex - leftIndex + 1)];
	
		while(i<=j)
		{
			while(Compare.doCompare(this.sortedArray[i], pivot_value, 3))
			{
				i++;
			}
			
			while(Compare.doCompare(this.sortedArray[j], pivot_value, 1))
			{
				j--;
			}
			
			if(i <= j) 
			{
				swap(i, j);
				i++;
				j--;
			}
		}
		if(leftIndex < j)		
			processQuickSort(leftIndex, j);
		if(i<rightIndex)
			processQuickSort(i, rightIndex);				
	}
	
	public void swap(int i, int j)
	{
		int temp = this.sortedArray[i];
		this.sortedArray[i] = this.sortedArray[j];
		this.sortedArray[j] = temp;
	}
}
