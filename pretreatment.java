package project_504;
/*
 * Class preatreatment has defined some necessary data:
 * 1. stored all of the song and their number and author
 * 2. read the all_playlists.txt to get each playlist and its number
 * 3. Used minHeap to sort all playlists and get the 1024 playlists and write it to a new $PATH/1024playlists.txt
 * 4. initializing a trie in order to prepare for searching 
 * 5. Two hashmap SongIndextoPopularity is used to store the song's index and its popularity which will be updated later
 *    SongIndextoSong is used to keep song and its popularity. And then Song's properties are all linked
 * 
 * NOTE:
 * all_playlists.txt will be added therefore its size will increase, everytime user upload a txt file or single playlist
 * the preatreatment should be updated.
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import func.trie;

public class pretreatment {
	public static void main(String[] args) {
		pretreatment pre = new pretreatment();
	}
	
	public int[] SongNumber = new int[8162];   
    public String[] SongName = new String[8162];
    public String[] Author = new String[8162];
    int[][] playlist = new int[8162][128]; //the first index refers to the total lines of playlist
	   //the second index means song number in the playlist	
	int[] playlist_popularity = new int[8162]; // it is the popularity of each song
	Map<Integer,Integer> SongIndextoPopularity = new HashMap<Integer,Integer>();
	Map<Integer, String> SongIndextoSong = new HashMap<Integer, String>();
	trie Trie = new trie();
	
    public pretreatment() {
    	readFileByLines_song("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/song_list.txt", SongNumber, SongName, Author);
		try {
			readInFile("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/pre.txt", playlist, playlist_popularity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 3168; i++) { 
			SongIndextoPopularity.put(i, 0);
			SongIndextoSong.put(i, SongName[i]);
		} 
		for (int i = 0; i < 3168; i++) { 
			//System.out.println("Song's name: " + SongName[i] + " Song's popularity: " + mp.get(i));
			Trie.addWord(SongName[i]);
		}
		int[] Top1024thPlaylistIndex = find1024thMinHeap(playlist_popularity, playlist, 1024);
		String[] sorted1024thResult = new String[1024];
		
		for(int i = 0;i < 1024; i++) {
			sorted1024thResult[i] = "";
			for (int j = 0; j < playlist[Top1024thPlaylistIndex[i]].length; j++) {
				sorted1024thResult[i] =sorted1024thResult[i].concat(playlist[Top1024thPlaylistIndex[i]][j]+" ");
			}
			char mid = '\t';
			String s = Character.toString(mid);
			sorted1024thResult[i] = sorted1024thResult[i].concat(s);
			sorted1024thResult[i] = sorted1024thResult[i].concat(Integer.toString(playlist_popularity[Top1024thPlaylistIndex[i]]));
			
		}
		//System.out.println(sorted1024thResult[0]);
		
		String path = "/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/";
		String fileName = "1024playlist";
		String lastfix = ".txt";
		String con = path.concat(fileName);
		String wholepathof1024playlisttxt = con.concat(lastfix);
		File f = new File(wholepathof1024playlisttxt);
    	f.delete();
		MyFile sorted1024Playlists = new MyFile();
        try {
            for (int i = 0; i < 1024; i++) {
            	sorted1024Playlists.creatTxtFile(fileName);
            	sorted1024Playlists.writeTxtFile(sorted1024thResult[i]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
		
    }
    
    public static void readFileByLines_song(String fileName, int[] SongNumber, String[] SongName, String[] Author) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int i = 0;
            while ((tempString = reader.readLine()) != null) {
                int j = 0;
                while(tempString.charAt(j) != '\t') {
                	j++;
                }
                String num = tempString.substring(0, j);
                SongNumber[i] = Integer.parseInt(num);
                j++;
                int n = j;
                while(tempString.charAt(n) != '\t') {
                	n++;
                }
                SongName[i] = tempString.substring(j, n);
                n++;
                int m = n;
                Author[i] = tempString.substring(m);
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
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
    
    public static int[] find1024thMinHeap(int[] popularity, int[][] playlist, int k) {
		//We shall find the 8th most popular playlists 
		if (popularity.length < k) {
			k = popularity.length;
		}
		//initializing a minHeap to store the top 1024 playlist
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
}
