//package File_Format;
//
//
////import java.io.BufferedReader;
//import GIS.Meta_data;
//import java.io.BufferedWriter;
////import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import javax.swing.text.Element;
//import GIS.GIS_element;
//
//public class Csv2Kml {
//
//    public static void writeFileKML(ArrayList<String[]> a, String output) {
//        ArrayList<String> content = new ArrayList<String>();
//        ArrayList<String[]> element= new ArrayList<String[]>();
//         
//        String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+"<Document>"
//        		+"<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>"
//		 		+ "<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>"
//		 		+"<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\r\n"  
//		 		+"<Folder>\n";
//        
//        content.add(kmlstart);
//
//        String kmlend ="</Document>\n"+"</kml>";
//        try{
//        	String kmlelement ="";
//            FileWriter fw = new FileWriter(output);
//            BufferedWriter bw = new BufferedWriter(fw);
//            String endFolder="</Folder>\n";
//
//            for (int i = 0; i < a.size(); i++) 
//            {
//                element.add(a.get(i));
//                
//            	for (int j = 0; j < element.size(); j++)
//            	{
//            		String[] s = element.get(j);
//            		
//            		 kmlelement =
//            		 		"<name>Wifi Networks</name>\n"
//            		 		+"<Placemark>\n"
//            		 		+"<name>"+s[1]+"</name>\n" 
//                    		+"<description>"+s[0]+","+s[2]+","+s[4]+","+s[5]+","+s[8] +s[9]+","+s[10]+"</description>\n"
//                    		+"<styleUrl>#red</styleUrl>"
//                    		+"<Point>\n"
//                    		+"<coordinates>"+s[6]+s[7]+"</coordinates>\n"
//                    		+"</Point>\n"
//                    		//+"<TimeStamp>"+s[3]+"</TimeStamp>\n" 
//                    		+"<TimeStamp>\n"+
//    						"<when>"+s[3]+"</when>\n"+
//    						"</TimeStamp>\n"
//                    		+"</Placemark>\n";
//
//                     content.add(kmlelement);
//
//				}      
//
//            }
//            content.add(endFolder);
//
//            content.add(kmlend);
//            String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
//            bw.write(csv);
//            bw.close();
//        } 
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
