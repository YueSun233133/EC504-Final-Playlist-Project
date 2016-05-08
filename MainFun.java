package project_504;
/*
 * This is the main body which combine all of other class and it also create some method used by UI directly.
 * First of all, every time an instance of this class is initialed, it will rerun the other class (pretreatment).
 * Method:
 * 1. SongToPlaylist function is used to show the most popular playlist that involves the song;
 * 2. Suggestion function is used to show the related song sharing the same prefix;
 * 3. buildMap is used to updated song's popularity;
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainFun {
	//initial the pretreatment
	pretreatment pre;
	playlistTreatment pt;
	
	public MainFun() {
		pre = new pretreatment();
		pt = new playlistTreatment();
	}
	//Because we have store the song's playlists, the only problem is to sort the whole list by playlist's popularity
	//I implemented an easy bubble sort because there is no obvious difference comparing with other sort algorithm as the list's size
	//is short enough.
	public String SongToPlaylist (String input) {
		while (input == null || input.length() == 0) {
			return "";
		}
		ArrayList<Integer> inLists = new ArrayList<Integer>();
		if (pt.SongtoList.containsKey(input)) {
			inLists = pt.SongtoList.get(input);
		} else return "";
		int[] array = new int[inLists.size()];
		for (int i = 0; i < inLists.size(); i++) {
			array[i] = inLists.get(i);
			System.out.println(array[i]);
		}
		
		helper (array,pt.playlistTopopularity);
		
		int[] nums = pt.playlist[array[0]];
		String res = "";
		for (int i = 0; i < nums.length; i++) {
			res =res.concat(pre.SongIndextoSong.get(nums[i]) + ", ");
		}
		return res;
	}
	public static void helper (int[] array, Map<Integer, Integer> mp) {
		if (array == null || array.length <= 1) return;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (mp.get(array[i]) < mp.get(array[j])) {
					helpSwap(array, i, j);
				}
			}
		}
		
	}
	public static void helpSwap (int[] array, int left, int right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}
	
	public String[] Suggestion(String prefix) {
		for (int i = 0; i < 1024; i++){
			buildMap(pre.SongIndextoPopularity, pt.playlist[i],pt.playlist_popularity[i]);
		}
		
		//this map is used to store the selected songs and their popularity, we use the popularity as the key and name as value
		Map<Integer,String> selectedSongs = new HashMap<Integer,String>(); 
		//this list is used to store each selected song's popularity 
		List<Integer> findpopularity = new ArrayList<Integer>();
		List<String> relatedstrings =  pre.Trie.listAllPossibleWords(prefix);  //*****!!!!
		// Iterator all the songs with the same prefix 
		Iterator listiterator = relatedstrings.listIterator(); 
		while (listiterator.hasNext()) {
			String s = (String) listiterator.next();
			for (int j = 0; j < 3168; j++) {
				if (pre.SongName[j].equals(s)) {
					//get their popularity
					//build up a map: popularity as the key and Song's name as the value
					findpopularity.add(pre.SongIndextoPopularity.get(j)); 
					selectedSongs.put(pre.SongIndextoPopularity.get(j),pre.SongName[j]); 
				}
			}
		}
		//set up an array in order to sort
		int[] array = new int[findpopularity.size()]; 
		for (int p = 0; p < findpopularity.size(); p++) {
			array[p] = findpopularity.get(p);
		}
		//***using quick sort algorithm to sort the popularity***
		array = quickSort(array);
		String[] res = new String[array.length];
		for (int p = 0; p < array.length; p++) {                 
			//System.out.println(array[p]);						  //*******!!!!!!
			//System.out.println(selectedSongs.get(array[p]));      //*******!!!!!!
			res[p] = selectedSongs.get(array[p]);
		}
		return res;
	}
	
	public static void buildMap(Map<Integer, Integer> mp, int[] song_list, int popularity) { 
		for (Integer song_num : song_list) {  
			if (mp.containsKey(song_num)) {  
				mp.put(song_num, mp.get(song_num) + popularity);  
	        } else {  
	            mp.put(song_num, 0);  
	        }  
	    }
	}	
	/**
     * Quicksort Algorithm
     * 
     * @return
     */
	public static int[] quickSort (int[] array) {
		if (array == null || array.length == 0) return new int[0];
		quicksort (array, 0, array.length - 1);
		return array;
	}
	
	public static void quicksort (int[] array, int left, int right) {
		if (left >= right) return;
		
		int pivotIndex = partition (array, left, right);
		quicksort (array, left, pivotIndex - 1);
		quicksort (array, pivotIndex + 1, right);
	}
	
	public static int partition (int[] array, int left, int right) {
		int pivotPos = pivotPosition (left, right);
		int pivot = array[pivotPos];
		swap (array, pivotPos, right);
		int leftbound = left;
		int rightbound = right - 1;
		while (leftbound <= rightbound) {
			if (array[leftbound] > pivot) {
				leftbound++;
			} else if (array[rightbound] <= pivot) {
				rightbound--;
			} else {
				swap(array, leftbound++, rightbound--);
			}
		}
		swap(array, leftbound, right);
		return leftbound;
		
		
	}
	
	public static int pivotPosition (int left, int right) {
		int pivot = left +  (int)(Math.random() * (right - left +1));
		return pivot;
	}
	
	public static void swap (int[] array, int left, int right) {
		int temp = array[right];
		array[right] = array[left];
		array[left] = temp;
	}
	

}


