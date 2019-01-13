package File_Format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.io.File;
//import Algorithms.MultiCSV;
//import GIS.Meta_data;
import GIS.MyGIS_project;

public class CSVReader {

	
	public static void main(String[] args)
    {
		String dirPath ="C:\\dataGame";
		MyGIS_project gp = new MyGIS_project(dirPath);
		gp.WriteLayers2Kml();
    }
	
	public static ArrayList<String[] > ReadFile(String csvFileName)
    {
		ArrayList<String[]> m_arr =new ArrayList<>();
		
        String csvFile = csvFileName;//"output.csv";
        String line = "";
        String cvsSplitBy = ",";
        int count=0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
        	while ((line=(br.readLine()))!= null)
        	{
        	count++;
        	
            if(count>1) 
            {
                String[] userInfo = line.split(cvsSplitBy);
                m_arr.add(userInfo);

            }
        	}  

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return m_arr;
    }

}