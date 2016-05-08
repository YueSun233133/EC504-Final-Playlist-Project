package project_504;


public class Vertex {
	protected int words; // number of words
	protected int prefixes; // number of prefix
	protected Vertex[] edges; // child nodes
 
	public Vertex() {
		this.words = 0;
		this.prefixes = 0;
		edges = new Vertex[95]; // not 26 because we should take into consideration of other characters like ''@/\*,etc
		for (int i = 0; i < edges.length; i++) {
			edges[i] = null;
        }
    }
}
