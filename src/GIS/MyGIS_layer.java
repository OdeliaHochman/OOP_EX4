package GIS;

import java.util.ArrayList;

import java.util.Iterator;

import Algorithms.MultiCSV;
import File_Format.CSVReader;
//import File_Format.Csv2Kml;
import File_Format.Path2Kml;
import Geom.Geom_element;
import Geom.Point3D;
	
	public class MyGIS_layer extends ArrayList<GIS_element> implements GIS_layer
	
	{
		private static final long serialVersionUID = 1L;
		private String m_csvFileName = null;
		
		@Override
		public Meta_data get_Meta_data() 
		{
			long timeUtc=this.get(3).getData().getUTC();
			GIS_element gElem = this.get(3);
			
			return gElem.getData();

		}
		public MyGIS_layer(String csvFileName)
		{
			m_csvFileName = csvFileName;
			ArrayList<String[]> arr = CSVReader.ReadFile(csvFileName);
			for (String[] arrGisElemStrs : arr) {
				super.add(new MyGIS_element(arrGisElemStrs));
			}
		}
		public ArrayList<String[]> GetLayerGisElemsStrs()
		{
			 ArrayList<String[]> al = new ArrayList<>();
			 Iterator<GIS_element> itr =  this.iterator();
			 while(itr.hasNext())
			 {
				 GIS_element ge =  itr.next();
				 String[] arrStrs = ((MyGIS_element)ge).ToStringArr();
				 al.add(arrStrs);
			 }
				
			
			return  al;
		}
		public void WriteLayer2Kml()
		{
			String kmlFile =  m_csvFileName.split("[.]")[0]+".kml";
			ArrayList<String[]> layerGisElemsStrs =  GetLayerGisElemsStrs();
		    Path2Kml.writeFileKML(layerGisElemsStrs, kmlFile);
		}
		
}
