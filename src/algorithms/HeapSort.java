package algorithms;
/*
 *  Author: Parth Manoj Sagar
 */
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HeapSort {

	private int[] sorting_Array;
	private static int num_Element;
    private static int leftTree;
    private static int rightTree;
    private static int root;
	
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
	        
	        int[] inputArray = new int[nodeList.getLength()];
	        //int[] inputArray={0, 1, 2, 2, 3, 3, 3, 4, 6, 7, 9, 9, 9, 9, 13, 13, 13, 15, 18, 19, 20, 21, 22, 22, 24, 24, 25, 26, 26, 28, 29, 31}; //Sorted Array
	        //int[] inputArray={31, 29, 28, 26, 26, 25, 24, 24, 22, 22, 21, 20, 19, 18, 15, 13, 13, 13, 9, 9, 9, 9, 7, 6, 4, 3, 3 ,3 ,2 ,2 ,1 ,0}; //Reversely Sorted
	        for (int i = 0; i < nodeList.getLength(); i++) {
                inputArray[i]= Integer.parseInt(nodeList.item(i).getTextContent());
              //For sorted and reversely sorted comment this inputArray[i]
	        }
	        HeapSort heapsort_obj=new HeapSort();        
	        		
		
			long timer_start = System.nanoTime();		
			heapsort_obj.heapsort(inputArray);
			long timer_stop = System.nanoTime();		
			double elapsedtime = (timer_stop - timer_start)/1000000F;			
			
			System.out.println("Sorted Array is: \n");	 
			for(int i = 0; i<inputArray.length; i++){
	            System.out.print(inputArray[i]+" ");            
	        }	
			System.out.println("\nTotal Time taken for Sorting: " + elapsedtime + " milliseconds");
			System.out.println("Total no. of key comparison is: "+Compare.comparecount);
	       
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
	}
	
	public void heapsort(int[] sorting_array)
	{
		this.sorting_Array = sorting_array;
		buildHeap(sorting_Array);
		
		for(int i=num_Element ; i>0 ; i--)
        {
            swap(0, i);
            num_Element--;
            maxHeap(sorting_Array, 0);
        }
	}
	
	public void buildHeap(int[] sorting_Array)
    {
		
		
        num_Element=sorting_Array.length-1;
        for(int i=num_Element/2;i>=0;i--)
        {
        	
    		Compare.comparecount++;
    		
            maxHeap(sorting_Array,i);
        }
    }
	
	public void maxHeap(int[] sorting_Array, int i)
    { 
		
		Compare.comparecount++;
		
        leftTree=2*i;
        rightTree=2*i+1;
        if(leftTree <= num_Element && sorting_Array[leftTree] > sorting_Array[i])
        {
            root=leftTree;
        }
        else
        {
            root=i;
        }
        
        if(rightTree <= num_Element && sorting_Array[rightTree] > sorting_Array[root])
        {
            root=rightTree;
        }
        if(root!=i)
        {
            swap(i,root);
            maxHeap(sorting_Array, root);
        }
    }
	
	public void swap(int i, int j)
	{
		int temp = this.sorting_Array[i];
		this.sorting_Array[i] = this.sorting_Array[j];
		this.sorting_Array[j] = temp;
	}
	

}
