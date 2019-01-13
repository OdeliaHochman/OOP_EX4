package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
	/**
	 * The department represents a collection of paths
	 * @author Efrat Cohen and Odelia Hochman
	 *
	 */
	public class Solution {

		Map<Integer, ArrayList<Path>>pathListMapper;
		
	
		public Solution()
		{
			pathListMapper = new HashMap<>();	
		}
		public void Print()
		{
			 for (Entry<Integer, ArrayList<Path>> subSet: pathListMapper.entrySet()) 
			 {
				    final Integer idPac = subSet.getKey();
				    final ArrayList<Path> alPath = subSet.getValue();
		    		//Collections.sort(pathListMapper.get(alPath.get(path).GetIDPackman()), Path.PathDTimeComparator);

				   
				   // for (Path path : alPath) {
				    	for (int path = 0; path < alPath.size(); path++) 
			    	{
				    		//Collections.sort(pathListMapper.get(alPath.get(path).GetIDPackman()), Path.PathDTimeComparator);
							System.out.println("pc "+alPath.get(path).GetIDPackman()+" pc "+idPac+" frt "+alPath.get(path).GetIDFruit()+" T0 "+alPath.get(path).GetTime0()+" Tt "+alPath.get(path).GetTimeT()+" DelT "+alPath.get(path).GetDeltatime()); 

						}
					}
			 }
				    
		
		
		/**
		 *  The function checks whether the path has been added to the path collection
		 * @param path
		 * @return true if the path has been added to the path collection
		 */
		public boolean add (Path path) 
		{
			if(!pathListMapper.containsKey(path.GetIDPackman()))
			{
				ArrayList<Path> al = new ArrayList<>();
				pathListMapper.put(path.GetIDPackman(), al);
				pathListMapper.get(path.GetIDPackman()).add(path);// = al;
				//Collections.sort(pathListMapper.get(path.GetIDPackman()), Path.PathDTimeComparator);
				
				
			}
			else	
			{
				Path prev= GetLastPath(path.GetIDPackman());
				path.SetTime0(prev.GetTimeT());
				path.SetTimeT(prev.GetTimeT()+path.GetDeltatime());//*****
				//path.SetDeltatime(path.GetDeltatime()-prev.GetTimeT());
				pathListMapper.get(path.GetIDPackman()).add(path);
			
				
			}
			return true;
		}
		
		
		
		/**
		 * @return size of path collection
		 */
		public int size(int idPc)
		{
			if(pathListMapper.containsKey(idPc))
			{
			 return pathListMapper.get(idPc).size();
			}
			else return -1;
		}
		
//		public void comper() 
//		{
//			for (int i = 0; i < pathListMapper.size(); i++) 
//			{
//				comper();
//				
//			}
//		}
//		
		/**
		 * The function checks whether the requested path is deleted from the paths collection
		 * @param path
		 * @return true if the path has been removed from the path collection
		 */
		public boolean remove(int idPc,Path path)
		{
			if(pathListMapper.containsKey(idPc))
			{
			 return pathListMapper.get(idPc).remove(path);
			}
			else return false;
			//return pathList.remove(path);
		}

		/**
		 * The function checks whether the requested path is in the paths collection
		 * @param path
		 * @param idxPc-index packman
		 * @return true if the path is in the paths collection 
		 */
		public boolean contains(int idPc, Path path)
		{
			if(pathListMapper.containsKey(idPc))
			  return pathListMapper.get(idPc).contains(path);
			else
				return false;
		}
		
		/**
		 * The function checks whether the resulting object is the same as the path collection
		 * @param path
		 * @param idxPc-index packman
		 * @return true if Object path is equal to the path collection
		 */
		public boolean equals(Object path,int idPc)
		{
			return pathListMapper.get(idPc).equals(path);
		}
		
		/**
		 * The function checks whether the path collection is empty
		 * @param idxPc - index packman
		 * @return true if the path collection is empty
		 */
		public boolean isEmpty(int idPc)
		{
			return pathListMapper.get(idPc).isEmpty();
		}

		/**
		 * @param idxPc-index packman
		 * @return iterator
		 */
		public Iterator<Path> iterator(int idPc) 
		{
			
			return pathListMapper.get(idPc).iterator();
		}
		
		/**
		 * 
		 * @param idxPc-index packman
		 * @param idxPath-index path
		 * @return index path
		 */
		public Path GetPath(int idPc,int idxPath) 
		{
			ArrayList<Path> al = pathListMapper.get(idPc);
			if(al.size()>idxPath)
				return al.get(idxPath);
			
			else 
				return null;
		}
		public Path GetLastPath(int idPc) 
		{
			int idxPath = pathListMapper.get(idPc).size()-1;
			Path path = pathListMapper.get(idPc).get(idxPath);
			return path;
		}
	}



