package project_504;

import java.util.ArrayList;
import java.util.List;

//import func.Vertex;

public class trie {
	private Vertex root = new Vertex();
    
	public trie(){  
    	Vertex root = new Vertex();   
    } 
 
    /**
     * get all the words in the trie
     * 
     * @return
     */
	
    public List<String> listAllWords() {
 
        List<String> words = new ArrayList<String>();
        Vertex[] edges = root.edges;
 
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String word = "" + (char) (' ' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }
        return words;
    }
 
    /**
     * 
     * @param words
     * @param vertex
     * @param wordSegment
     */
 
    private void depthFirstSearchWords(List words, Vertex vertex, String wordSegment) {
        if (vertex.words != 0) {
            words.add(wordSegment);
        }
        Vertex[] edges = vertex.edges;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String newWord = wordSegment + (char) (' ' + i);
                depthFirstSearchWords(words, edges[i], newWord);
            }
        }
    }
    
    /**
     * Given a string and get the most related word in the trie
     * 
     * @return
     */
    
    public List<String> listAllPossibleWords(String input_string) {
    	 
        List<String> words = new ArrayList<String>();   
        if (input_string == "") 
        	return words;     //if there is not input, return nothing
        
        char[] input = input_string.toCharArray(); //turn input string into array
        //input[0] = Character.toUpperCase(input[0]);
        Vertex[] edges = root.edges; //start at the root verdex 
        for (int j = 0; j < input_string.length(); j++) { 
        	if (edges[input[j] - ' '] != null) {    //if input_string.charAt(j) exists, down searching. 
        		edges = edges[input[j] - ' '].edges; 
        	} else return words;  //if there is not matched prefixes, nothing will be found, return nothing
        	
        }
        input[0] = Character.toUpperCase(input[0]);
        String prefix = String.valueOf(input);
        String maxMatch = getMaxMatchWord(input_string); //get the best matched string
        if (maxMatch != "") {     //if there exists a max matched string, then add to result list
        	char[] firstChar = maxMatch.toCharArray();
        	firstChar[0] = Character.toUpperCase(firstChar[0]);
        	String maxMatch1 = String.valueOf(firstChar);
        	words.add(maxMatch1);
        } 
        
        //after finding the prefixes, we start to find all the related words based on the new verdex, for example "all-"
        for (int i = 0; i < edges.length; i++) {
        	
        	if (edges[i] != null) {
        		String word = prefix + (char) (' ' + i); //we have to add the first characters we have typed in
        		depthFirstSearchWords(words, edges[i], word);
        	} 
        }
        	
        return words;
    }
    
    /**
     * count the number of prefiexes
     * 
     * @param prefix
     * @return
     */
    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }
 
    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (prefixSegment.length() == 0) { // reach the last character of the
                                            // word
            return vertex.prefixes;
        }
 
        char c = prefixSegment.charAt(0);
        int index = c - ' ';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
 
            return countPrefixes(vertex.edges[index],
                    prefixSegment.substring(1));
 
        }
 
    }
 
    /**
     * count the word which is best matched
     * 
     * @param word
     * @return
     */
    public int countWords(String word) {
        return countWords(root, word);
    }
 
    private int countWords(Vertex vertex, String wordSegment) {
        if (wordSegment.length() == 0) { // reach the last character of the word
            return vertex.words;
        }
 
        char c = wordSegment.charAt(0);
        int index = c - ' ';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));
 
        }
 
    }
 
    /**
     * add a word to trie
     * 
     * @param word
     * 
     */
 
    public void addWord(String word) {
        addWord(root, word);
    }
 
    /**
     * Add the word from the specified vertex.
     * 
     * @param vertex
     *            The specified vertex.
     * @param word
     *            The word to be added.
     */
 
    private void addWord(Vertex vertex, String word) {
        if (word.length() == 0) { // if all characters of the word has been
                                    // added
            vertex.words++;
        } else {
            vertex.prefixes++;
            char c = word.charAt(0);
            //c = Character.toLowerCase(c);
            int index = c - ' ';
            if (vertex.edges[index] == null) { // if the edge does NOT exist
                vertex.edges[index] = new Vertex();
            }
 
            addWord(vertex.edges[index], word.substring(1)); // go the the next
                                                                // character
        }
    }
 
    /**
     * given a word and return the max matched word
     * 
     * @param word
     * @return
     */
    public String getMaxMatchWord(String word) {
        String s = "";
        String temp = "";// record of the most recent max matched word
        char[] w = word.toCharArray();
        Vertex vertex = root;
        for (int i = 0; i < w.length; i++) {
            char c = w[i];
            //c = Character.toLowerCase(c);
            int index = c - ' ';
            if (vertex.edges[index] == null) {// if there is no child node
                if (vertex.words != 0)// if it is a word ,then return
                    return s;
                else
                    // if it is not a word, then return null
                    return null;
            } else {
                if (vertex.words != 0)
                    temp = s;
                s += c;
                vertex = vertex.edges[index];
            }
        }
        // there is a word that is longer than the given word (inclusive)
        if (vertex.words == 0)//
            return temp;
        return s;
    }
}
