	package Algorithms;
	
	import java.io.File;
import java.util.ArrayList;
	
		public class MultiCSV 
		{
			
			
			String m_dirPath ="";
			public MultiCSV(String dirPath)
			{
				m_dirPath = dirPath;
			}
		    
			public  ArrayList<File> getFiles()
			{
				
				return fillFilesRecursively(new File(m_dirPath));
			}
			ArrayList<File> arrFiles=new ArrayList<>();// create Gis project
			private  ArrayList<File> fillFilesRecursively(File file) 
			{
				
	           if (file.isFile()) 
	           {
	        	   arrFiles.add(file);
	   
	           System.out.print(file+"\n");
	            } 
	              else {
                        for (File child : file.listFiles()) 
                        {
	                     fillFilesRecursively(child);
	                     }    
	                  }
	           
	           return arrFiles;
	           
	          
			}
		}