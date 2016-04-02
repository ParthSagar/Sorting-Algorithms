package algorithms;
/*
 * Author: Parth Manoj Sagar
 */
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MergeSort {
	
	private int[] sortedArray;
	private int[] tempArray;	
	public static int comparecount = 0;

	public static void main(String args[])
	{
		
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
	        
	        MergeSort mergesort_obj = new MergeSort();
			
			
			long timer_start = System.nanoTime();		
			mergesort_obj.mergeSort(inputArray);
			long timer_stop = System.nanoTime();		
			double elapsedtime = (timer_stop - timer_start)/1000000F;			
			
			System.out.println("Sorted Array is: \n");	 
			for(int i = 0; i<inputArray.length; i++){
	            System.out.println(inputArray[i]+" ");            
	        }	
			System.out.println("\nTotal Time taken for Sorting: " + elapsedtime + " milliseconds");
			System.out.println("Total No. of key comparison is: "+comparecount);
			
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
	}
	
	public void mergeSort(int[] sortingArray)
	{	
		
		if(sortingArray.length <= 1 || sortingArray == null)
			return;
		
		else
		{
			this.sortedArray = sortingArray;
			this.tempArray = new int[sortingArray.length];
			processMergeSort(0, sortingArray.length - 1);				
		}
	}
	
	public void processMergeSort(int leftIndex, int rightIndex)
	{	
		
		 if(doCompare(leftIndex, rightIndex, 3))
		{						
			int midPoint = leftIndex + (rightIndex - leftIndex)/2;					
									
			processMergeSort(leftIndex, midPoint);
			processMergeSort(midPoint+1, rightIndex);
			mergeArrays(leftIndex, midPoint, rightIndex);							
		}		
	}
	
	public void mergeArrays(int leftIndex, int midPoint, int rightIndex)
	{		
		for (int i = leftIndex; i <= rightIndex; i++) 
		{
            tempArray[i] = sortedArray[i];
        }
		
		int i = leftIndex;
		int j = midPoint + 1;
		int k = leftIndex; 
		
		while(i <= midPoint && j <= rightIndex)
		{
			
			if(doCompare(tempArray[i], tempArray[j], 4)) 
			{				
				sortedArray[k] = tempArray[i];
				i++;
			}
			else
			{				
				sortedArray[k] = tempArray[j];
				j++;
			}
			k++;
		}				
		while(i<=midPoint)
		{
			sortedArray[k] = tempArray[i];
			k++;
			i++;
		}
	}
	public static boolean doCompare(int value1, int value2, int comparecode)
	{
		comparecount++;
		
		switch(comparecode)
		 {
			case 0:
				return value1 == value2 ? true : false;
			case 1:
				return value1 > value2 ? true : false;
			case 2:
				return value1 >= value2 ? true : false;
			case 3:
				return value1 < value2 ? true : false;
			case 4:
				return value1 <= value2 ? true : false;
			case 5:
				return value1 != value2 ? true : false;
			default:
				return false;
		}
	}
}