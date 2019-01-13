package GIS;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import GIS.GIS_project;
import Geom.Geom_element;
import Geom.Point3D;
import GIS.GIS_layer;
import Algorithms.MultiCSV;
import File_Format.CSVReader;


public class MyGIS_project extends ArrayList<GIS_layer> implements GIS_project{
	private static final long serialVersionUID = 1L;
	
	
     public MyGIS_project(String dirPath)
	{
		
		MultiCSV ms = new MultiCSV(dirPath);
		ArrayList<File>  csvFiles= ms.getFiles();
		for (File csvFile : csvFiles) 
		{
			MyGIS_layer gl=new MyGIS_layer(csvFile.getPath());
			this.add(gl);
		}
	}
	
	public void WriteLayers2Kml()
	{
	    for (int idx=0;idx<this.size();idx++) {
			((MyGIS_layer)this.get(idx)).WriteLayer2Kml();
		}
	}
    
	@Override
	public Meta_data get_Meta_data()
	{
		int idxLayer = 0;int idxGisElem = 0;
		return this.get(idxLayer).get(idxGisElem).getData();
		
	}
		
//	public static void main(String[] args)
//    {
//		String dirPath = "C:\\data\\";
//		MyGIS_project gp = new MyGIS_project(dirPath);
//		gp.WriteLayers2Kml();
//    }
//	

}
