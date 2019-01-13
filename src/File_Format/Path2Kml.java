package File_Format;
import Game.Fruit;
import Game.Game;
import Game.Path;

import java.awt.List;
//import java.io.BufferedReader;
//import GIS.Meta_data;
import java.io.BufferedWriter;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.Locale;

import Algorithms.ShortestPathAlgo;

public class Path2Kml 
{
	static ArrayList<Path> path= new ArrayList<>();
	ArrayList<Fruit> frt= new ArrayList<>();
	
	
	
	public Path2Kml() 
	{
		Game game= new Game();
    	ShortestPathAlgo time= new ShortestPathAlgo(game);
    	//time.setTime(gameTimeSec);
	}

	
    public static void writeFileKML(ArrayList<String[]> a, String output) 
    {
    
    	    ArrayList<String> lstTime = new ArrayList();
    		for (int i = 0; i < path.size(); i++) 
    		{
                if(path.size()<=a.size())
                {
    			long timeFruit= (long) path.get(i).GetTimeT()*1000;
    			getTimeFruit(timeFruit);
    			SimpleDateFormat newDate = new SimpleDateFormat("YYYY-MM-DD HH:mm:SS", Locale.getDefault());
                String newTimeFrt= newDate.format(new Date(timeFruit));
                lstTime.add(newTimeFrt);
                }
    		}
    	
        ArrayList<String> content = new ArrayList<String>();
        ArrayList<String[]> element= new ArrayList<String[]>();
         
        String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+"<Document>"
        		+"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>"
		 		+ "<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>"
		 		+"<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\r\n"  
		 		+"<Folder>\n";
        
        content.add(kmlstart);

        String kmlend ="</Document>\n"+"</kml>";
        try{
        	//Timestamp time= new Timestamp(, signerCertPath)
        	
        	String kmlelement ="";
            FileWriter fw = new FileWriter(output);
            BufferedWriter bw = new BufferedWriter(fw);
            String endFolder="</Folder>\n";

            
            for (int i = 0; i < a.size(); i++) 
            {
                element.add(a.get(i));
                
            	for (int j = 0; j < element.size(); j++)
            	{
            		String[] s = element.get(j);
            		
            		
            		 kmlelement =
            		 		"<name>Wifi Networks</name>\n"
            		 		+"<Placemark>\n"
            		 		+"<name>"+s[0]+"</name>\n" 
                    		+"<description>"+s[1]+","+s[5]+","+s[6]+"</description>\n"
                    		+"<styleUrl>#red</styleUrl>"
                    		+"<Point>\n"
                    		+"<coordinates>"+s[2]+","+s[3]+"</coordinates>\n"
                    		+"</Point>\n"
                    		//+"<TimeStamp>"+lstTime.get(i)+"</TimeStamp>\n" 
                    		+"<TimeStamp>\n"+
    						"<when>"+"</when>\n"+
    						"</TimeStamp>\n"
                    		+"</Placemark>\n";

                     content.add(kmlelement);

				}      

            }
            content.add(endFolder);

            content.add(kmlend);
            String csv = content.toString().replace("[", "").replace("]", "");
            bw.write(csv);
            bw.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }




	private static void getTimeFruit(long timeFruit) {
		
		long time = timeFruit*1000; //time of fruit is in second
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSXXX");
		String dateString = formatter.format(new Date(time));
		
	}

}
//}
