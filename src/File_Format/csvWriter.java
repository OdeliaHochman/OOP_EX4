package File_Format;

//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.ArrayList;

public class csvWriter 
{
	public static void main(String[] args)
	{
//		String fileName = "f16.csv";//"game_1543684662657save.csv";
//		PrintWriter pw = null;
//		
//		try 
//		{
//			pw = new PrintWriter(new File(fileName));
//		} 
//		catch (FileNotFoundException e) 
//		{
//			e.printStackTrace();
//			return;
//		}
//
//	
//   		
//		StringBuilder sb = new StringBuilder();
//		sb.append("\"<Placemark><name><![CDATA]\"");
//		sb.append("]]></name><description><![CDATA]");
//		sb.append("</b><br/>Frequency: <b>");
//		sb.append("</b><br/>Date: <b>");
//		sb.append("</b>]]></description><styleUrl>#");
//		sb.append("</styleUrl><Point><coordinates>");
//		sb.append("</coordinates></Point></Placemark>");
//		sb.append('\n');
//
//		pw.write(sb.toString());
//		pw.close();
//		System.out.println("done!");
	}
	public static void WriteFile(String csvFileName,ArrayList<String[] > arr)
    {
		//ArrayList<String[]> m_arr =new ArrayList<>();
		
        //String csvFile = csvFileName;//"output.csv";
       // String line = "";
        //String cvsSplitBy = ",";
        //int count=0;
        
        PrintWriter pw = null;
		
		try 
		{
			pw = new PrintWriter(new File(csvFileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Type"+",");
		sb.append("ID"+",");
		sb.append("Lat"+",");
		sb.append("Lon"+",");
		sb.append("Alt"+",");
		sb.append("Speed/Weight"+",");
		sb.append("Radius");
		sb.append('\n');
		for (String[] strings : arr) {
			for (String string : strings) {
				sb.append(string+",");
			} 
			sb.append('\n');
		}
		
	
   		/*
		StringBuilder sb = new StringBuilder();
		sb.append("\"<Placemark><name><![CDATA]\"");
		sb.append("]]></name><description><![CDATA]");
		sb.append("</b><br/>Frequency: <b>");
		sb.append("</b><br/>Date: <b>");
		sb.append("</b>]]></description><styleUrl>#");
		sb.append("</styleUrl><Point><coordinates>");
		sb.append("</coordinates></Point></Placemark>");
		sb.append('\n');
        */
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
/*
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
        //return m_arr;
    }
*/
  }
}



