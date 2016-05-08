package project_504;
/*
 * This class is aiming to deal with playlists and sorting
 * It has 4 properties:
 * 1. A new 2D array of playlist and its popularity
 * 2. Each list is linked to its popularity by using hashmap
 * 3. Each song has many possibility of being in list
 * 
 * It also create a txt file within the top 8th playlists that will 
 * be showed to the UI directly without any further interface with user
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import func.Cell;

public class playlistTreatment {
		int[][] playlist = new int[1024][128]; //the first index refers to the total lines of playlist
	   //the second index means song number in the playlist	
		int[] playlist_popularity = new int[1024]; // it is the popularity of each song
		Map<String, ArrayList<Integer>> SongtoList = new HashMap<String, ArrayList<Integer>>(); 
		Map<Integer, Integer> playlistTopopularity = new HashMap<Integer, Integer>();
		public playlistTreatment() {
			pretreatment pre = new pretreatment();
			try {
				readInFile("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/1024playlist.txt", playlist, playlist_popularity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(playlist[7][1]);	
			for (String s : pre.SongName) {
				SongtoList.put(s, new ArrayList<Integer>());
			}
			for (int i = 0; i < 1024; i++) {
				playlistTopopularity.put(i, playlist_popularity[i]);
				for (int j = 0; j < playlist[i].length; j++) {
					if (SongtoList.containsKey(pre.SongIndextoSong.get(playlist[i][j]))) {
						SongtoList.get(pre.SongIndextoSong.get(playlist[i][j])).add(i);
					}
				}
			}		
		//use the minheap function we can get the most popular 8 songlist's index
		//which means we can get the songlists and iteration of the song's number and name in the play list
		int[] Top8thPlaylistIndex = new int[8];
		String[] output8thResult = {"","","","","","","",""};
		Top8thPlaylistIndex = find8thMinHeap(playlist_popularity, playlist, 8);
		for(int i = 0;i < 8; i++) {
			for (int j = 0; j < playlist[Top8thPlaylistIndex[i]].length; j++) {
				output8thResult[i] = output8thResult[i].concat(pre.SongName[playlist[Top8thPlaylistIndex[i]][j]]+", ");
			}	
			//System.out.println(output8thResult[i]);
		}
		String path = "/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/";
		String fileName = "test";
		String lastfix = ".txt";
		String con = path.concat(fileName);
		String wholepathof8playlisttxt = con.concat(lastfix);
		File f = new File(wholepathof8playlisttxt);
    	f.delete();
		MyFile myFile = new MyFile();
        try {
            for (int i = 0; i < 8; i++) {
                myFile.creatTxtFile(fileName);
                myFile.writeTxtFile(output8thResult[i]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
	/**
     * Minheap data structure to get the 8 most popular list 
     * the time complexity of this function is O(nlongk), which n means the total number of the playlists,
     * k means the total number of playlists you want(8 or 1024)
     * 
     * @return
     */
	public static int[] find8thMinHeap(int[] popularity, int[][] playlist, int k) {
		//We shall find the 8th most popular playlists 
		if (popularity.length < k) {
			k = popularity.length;
		}
		//initializing a minHeap to store the top 8 playlist
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
			@Override
			public int compare (Cell o1, Cell o2){
				if (o1.popularity == o2.popularity) return 0;
				else return o1.popularity < o2.popularity ? -1 : 1;
			}
		});
		//first of all, we offer 8 playlist to the minHeap
		//after that, when a new playlist offered, we will compare it with the peek
		//if the offered one is bigger than the peek, then we remove the peek and offer this playlist
		
		for (int i = 0; i < popularity.length; i++) {
			if (i < k) {
				minHeap.offer(new Cell(popularity[i], i ,playlist[i]));
			} else if (popularity[i] > minHeap.peek().popularity) {
				minHeap.poll();
				minHeap.offer(new Cell(popularity[i], i ,playlist[i]));
			}
		}
		int[] res = new int[k];
		for (int j = k - 1; j >= 0; j--) {
			res[j] = minHeap.poll().index;
			//System.out.println(res[j]);
		}
		return res;
		
	}
	
	public static void readInFile(String file, int[][] playlist, int[] playlist_popularity) throws Exception{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String[] wordsArray;
		String c;
		
		for (int i = 0; (c = br.readLine()) != null; i++){
			 wordsArray = c.split("\t");
			 
			 String[] song = wordsArray[0].split("\\s+");
			 
			 playlist[i] = new int[song.length];
			 for(int j = 0; j < song.length; j++) {
				 playlist[i][j] = Integer.parseInt(song[j]);
			 }
			 playlist_popularity[i] = Integer.parseInt(wordsArray[1]);
			 
		}
		br.close();
		fr.close();
		
	}

}
