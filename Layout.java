package project_504;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.BevelBorder;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.TextArea;

public class Layout {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_9;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout window = new Layout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Layout() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
 		frame.setBackground(Color.BLACK);
 		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
 		
 		JLabel lblNewLabel = new JLabel("");
 		lblNewLabel.setBounds(41, 217, 215, 16);
 		frame.getContentPane().add(lblNewLabel);
 		
 		JButton btnLoad = new JButton("load file");
 		btnLoad.setBounds(43, 169, 92, 24);
 		btnLoad.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				JFileChooser fileChooser = new JFileChooser();
 		        int returnValue = fileChooser.showOpenDialog(null);
 		        if (returnValue == JFileChooser.APPROVE_OPTION) {
 		          
 		          File filePath = fileChooser.getSelectedFile();
 		          
 		          
		        	try{
 		        		  BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
 		        		  StringBuffer stringBuffer = new StringBuffer();
 			        	  String line ;
 			        	  	
 			        	  File file =new File("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/pre.txt");
 			        	  if(!file.exists()){
 			        		  file.createNewFile();
 			        	  }
 		          	
 			        	  FileWriter fw = new FileWriter(file,true);
 			        	  BufferedWriter bw = new BufferedWriter(fw);
 			        	  while((line =bufferedReader.readLine())!=null){
 			        	 
 			        		  stringBuffer.append(line);
 			        		  System.out.println(line);
 			        		  bw.write(line);
 			        		  bw.write("\n");
 				           
 			        	  }
 			        	  bw.close();
 		          	  
 		        	} catch(IOException e1){
 		        		e1.printStackTrace();	
 		        	
 		        	}
 		        	try{
 						LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/pre.txt")));
 						lnr.skip(Long.MAX_VALUE);
 		      		  	System.out.print(String.valueOf(lnr.getLineNumber()-1));
 		      		  	int num=lnr.getLineNumber();
 		      		  	if(num<=1024){
 		      		  	lblNewLabel.setText("total "+String.valueOf(lnr.getLineNumber())+" playlist(s) in processing");
 		      		    lblNewLabel.setVisible(true);
 		      		    lnr.close();
 		      		  	}
 		      		  	else{
 		      		  	lblNewLabel.setText("total "+"1024"+" playlist(s) in processing");
 		      		    lblNewLabel.setVisible(true);
 		      		    lnr.close();
 		      		  	}
 		      		  		
 		      		
 						
 					}catch(IOException e3){
 		        		e3.printStackTrace();	
 		        	
 		        	}
 		            
 		         }
 		        
 		        }
 				
 			}
 		);
 		frame.getContentPane().setLayout(null);
 		
  		JLabel lblPlaylist = new JLabel("Playlist");
 		lblPlaylist.setBounds(305, 6, 176, 84);
 		lblPlaylist.setFont(new Font("Libian SC", Font.BOLD, 50));
 		frame.getContentPane().add(lblPlaylist);
 		frame.getContentPane().add(btnLoad);
 		     
 		
 		
 		JLabel lblFind = new JLabel("Find Your Song");
 		lblFind.setBounds(493, 82, 210, 24);
 		lblFind.setFont(new Font("Nanum Pen Script", Font.PLAIN, 20));
 		frame.getContentPane().add(lblFind);
 		
 		JLabel lblNameOfSong = new JLabel("Name of Song");
 		lblNameOfSong.setBounds(412, 108, 117, 24);
 		lblNameOfSong.setFont(new Font("Nanum Pen Script", Font.PLAIN, 20));
 		frame.getContentPane().add(lblNameOfSong);
 		
 		
 		
 		textField_9 = new JTextField();
 		textField_9.setBounds(516, 109, 144, 25);
 		textField_9.setColumns(10);
 		frame.getContentPane().add(textField_9);
 		
 		JTextArea txtrPleaseInputAs = new JTextArea();
 		txtrPleaseInputAs.setToolTipText("Please input as format: Playlist \\t Popularity(integer)");
 		txtrPleaseInputAs.setBounds(43, 124, 315, 21);
 		frame.getContentPane().add(txtrPleaseInputAs);
 		
 		JButton btnLoadSingle = new JButton("load single list");
 		btnLoadSingle.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				try{
 					  
 					  String single=txtrPleaseInputAs.getText();
 		        	  StringBuffer stringBuffer = new StringBuffer();
 		        	  
 		        	  File file =new File("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/pre.txt");
 		        	  if(!file.exists()){
 			          	   file.createNewFile();
 			          	}
 		        	  FileWriter fw = new FileWriter(file,true);
 		        	  BufferedWriter bw = new BufferedWriter(fw);
 		        	  
 		        	 
 		        		  stringBuffer.append(single);
 		        		  bw.write(single);
 		        		  bw.write("\n");
 		        		  bw.close();
 	        	} catch(IOException e2){
 	        		e2.printStackTrace();	
 	        	
 	        	}
 				
 				
 				try{
 					LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/pre.txt")));
 					lnr.skip(Long.MAX_VALUE);
 	      		  	System.out.print(String.valueOf(lnr.getLineNumber()-1));
 	      		  	int num=lnr.getLineNumber();
 	      		  	if(num<=1024){
 	      		  	lblNewLabel.setText("total "+String.valueOf(lnr.getLineNumber())+" playlist(s) in processing");
 	      		    lblNewLabel.setVisible(true);
 	      		    lnr.close();
 	      		  	}
 	      		  	else{
 	      		  	lblNewLabel.setText("total "+"1024"+" playlist(s) in processing");
 	      		    lblNewLabel.setVisible(true);
 	      		    lnr.close();
 	      		  	}
 	      		  		
 	      		
 					
 				}catch(IOException e3){
 	        		e3.printStackTrace();	
 	        	
 	        	}
 				
 				
 			}
 			
 		}
 		);
 		btnLoadSingle.setBounds(184, 167, 117, 29);
 		frame.getContentPane().add(btnLoadSingle);
		
		JLabel lblTopPlaylists = new JLabel("Top 8 Playlists");
		lblTopPlaylists.setBounds(349, 217, 110, 24);
		lblTopPlaylists.setFont(new Font("Nanum Pen Script", Font.PLAIN, 20));
		frame.getContentPane().add(lblTopPlaylists);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(387, 138, 361, 80);
		frame.getContentPane().add(textArea);
		
		JButton btnSuggestion = new JButton("Suggestion");
		btnSuggestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFun ma = new MainFun();
				String[] res = ma.Suggestion(textField_9.getText());
				textArea.setText("");
				if (res.length >= 4) {
					for (int i = 0; i < 4; i++) {
						textArea.append(res[i] + '\n');
						System.out.println(res[i]);
						
					}	
				} else {
					for (int i = 0; i < res.length; i++) {
						textArea.append(res[i] + '\n');
						
					}	
				}
				
			}
		});
		btnSuggestion.setBounds(674, 80, 88, 29);
		frame.getContentPane().add(btnSuggestion);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFun ma = new MainFun();
				String res = ma.SongToPlaylist(textField_9.getText());
				textArea.setText("");
				textArea.append(res + '\n');
				System.out.println(res);
					
					
			}
		});
		btnSearch.setBounds(674, 114, 88, 29);
		frame.getContentPane().add(btnSearch);
		
		FileReader fr = 
				new FileReader("/Users/Sony/Desktop/PlayListApp 2/PlaylistApp-Datasets/test.txt");
		BufferedReader br = new BufferedReader(fr);
		String[] song = new String[8];
		String c;
		for (int i = 0; i < 8 && (c = br.readLine()) != null;i++){
			song[i] = c;		
		}
		
		
		
		String[] columnNames = {"# of popularity","Name of Playlist"};
		Object[][] data = {
				{new Integer(1),song[0]},
				{new Integer(2),song[1]},
				{new Integer(3),song[2]},
				{new Integer(4),song[3]},
				{new Integer(5),song[4]},
				{new Integer(6),song[5]},
				{new Integer(7),song[6]},
				{new Integer(8),song[7]}};
		table = new JTable(data,columnNames);
		table.setEnabled(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(21, 245, 747, 125);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(table);
		
		
/*		
		Object[] LoadName = {"Category","Name of Playlist/Songs"};
		Object[][] loaddata = {
				{"Playlist","Name"},
				{"song#1","1"},
				{"song#2","2"},
				{"song#3","3"},
				{"song#4","4"},
				{"song#5","5"},
				{"song#6","6"},
				{"song#7","7"},
				};
		JTable table2 = new JTable(loaddata,LoadName);
		table2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table2.setBackground(Color.LIGHT_GRAY);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table2.setBounds(36, 242, 300, 128);
		table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table2.setFillsViewportHeight(true);
		TableColumn column20 = table.getColumnModel().getColumn(0);
		column20.setPreferredWidth(10);
		frame.getContentPane().add(table2);
*/		
		
		
		frame.setBounds(100, 100, 807, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


class AutoSuggestor {

    private final JTextField textField;
    private final Window container;
    private JPanel suggestionsPanel;
    private JWindow autoSuggestionPopUpWindow;
    private String typedWord;
    private final ArrayList<String> dictionary = new ArrayList<>();
    private int currentIndexOfSpace, tW, tH;
    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }
    };
    private final Color suggestionsTextColor;
    private final Color suggestionFocusedColor;

    public AutoSuggestor(JTextField textField, Window mainWindow, ArrayList<String> words, Color popUpBackground, Color textColor, Color suggestionFocusedColor, float opacity) {
        this.textField = textField;
        this.suggestionsTextColor = textColor;
        this.container = mainWindow;
        this.suggestionFocusedColor = suggestionFocusedColor;
        this.textField.getDocument().addDocumentListener(documentListener);

        setDictionary(words);

        typedWord = "";
        currentIndexOfSpace = 0;
        tW = 0;
        tH = 0;

        autoSuggestionPopUpWindow = new JWindow(mainWindow);
        autoSuggestionPopUpWindow.setOpacity(opacity);

        suggestionsPanel = new JPanel();
        suggestionsPanel.setLayout(new GridLayout(0, 1));
        suggestionsPanel.setBackground(popUpBackground);

        addKeyBindingToRequestFocusInPopUpWindow();
    }

    private void addKeyBindingToRequestFocusInPopUpWindow() {
        textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
        textField.getActionMap().put("Down released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {//focuses the first label on popwindow
                for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
                    if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                        ((SuggestionLabel) suggestionsPanel.getComponent(i)).setFocused(true);
                        autoSuggestionPopUpWindow.toFront();
                        autoSuggestionPopUpWindow.requestFocusInWindow();
                        suggestionsPanel.requestFocusInWindow();
                        suggestionsPanel.getComponent(i).requestFocusInWindow();
                        break;
                    }
                }
            }
        });
        suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
        suggestionsPanel.getActionMap().put("Down released", new AbstractAction() {
            int lastFocusableIndex = 0;

            @Override
            public void actionPerformed(ActionEvent ae) {//allows scrolling of labels in pop window (I know very hacky for now :))

                ArrayList<SuggestionLabel> sls = getAddedSuggestionLabels();
                int max = sls.size();

                if (max > 1) {//more than 1 suggestion
                    for (int i = 0; i < max; i++) {
                        SuggestionLabel sl = sls.get(i);
                        if (sl.isFocused()) {
                            if (lastFocusableIndex == max - 1) {
                                lastFocusableIndex = 0;
                                sl.setFocused(false);
                                autoSuggestionPopUpWindow.setVisible(false);
                                setFocusToTextField();
                                checkForAndShowSuggestions();//fire method as if document listener change occured and fired it

                            } else {
                                sl.setFocused(false);
                                lastFocusableIndex = i;
                            }
                        } else if (lastFocusableIndex <= i) {
                            if (i < max) {
                                sl.setFocused(true);
                                autoSuggestionPopUpWindow.toFront();
                                autoSuggestionPopUpWindow.requestFocusInWindow();
                                suggestionsPanel.requestFocusInWindow();
                                suggestionsPanel.getComponent(i).requestFocusInWindow();
                                lastFocusableIndex = i;
                                break;
                            }
                        }
                    }
                } else {//only a single suggestion was given
                    autoSuggestionPopUpWindow.setVisible(false);
                    setFocusToTextField();
                    checkForAndShowSuggestions();//fire method as if document listener change occured and fired it
                }
            }
        });
    }

    private void setFocusToTextField() {
        container.toFront();
        container.requestFocusInWindow();
        textField.requestFocusInWindow();
    }

    public ArrayList<SuggestionLabel> getAddedSuggestionLabels() {
        ArrayList<SuggestionLabel> sls = new ArrayList<>();
        for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
            if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                SuggestionLabel sl = (SuggestionLabel) suggestionsPanel.getComponent(i);
                sls.add(sl);
            }
        }
        return sls;
    }

    private void checkForAndShowSuggestions() {
        typedWord = getCurrentlyTypedWord();

        suggestionsPanel.removeAll();//remove previos words/jlabels that were added

        //used to calcualte size of JWindow as new Jlabels are added
        tW = 0;
        tH = 0;

        boolean added = wordTyped(typedWord);

        if (!added) {
            if (autoSuggestionPopUpWindow.isVisible()) {
                autoSuggestionPopUpWindow.setVisible(false);
            }
        } else {
            showPopUpWindow();
            setFocusToTextField();
        }
    }

    protected void addWordToSuggestions(String word) {
        SuggestionLabel suggestionLabel = new SuggestionLabel(word, suggestionFocusedColor, suggestionsTextColor, this);

        calculatePopUpWindowSize(suggestionLabel);

        suggestionsPanel.add(suggestionLabel);
    }

    public String getCurrentlyTypedWord() {//get newest word after last white spaceif any or the first word if no white spaces
        String text = textField.getText();
        String wordBeingTyped = "";
        if (text.contains(" ")) {
            int tmp = text.lastIndexOf(" ");
            if (tmp >= currentIndexOfSpace) {
                currentIndexOfSpace = tmp;
                wordBeingTyped = text.substring(text.lastIndexOf(" "));
            }
        } else {
            wordBeingTyped = text;
        }
        return wordBeingTyped.trim();
    }

    private void calculatePopUpWindowSize(JLabel label) {
        //so we can size the JWindow correctly
        if (tW < label.getPreferredSize().width) {
            tW = label.getPreferredSize().width;
        }
        tH += label.getPreferredSize().height;
    }

    private void showPopUpWindow() {
        autoSuggestionPopUpWindow.getContentPane().add(suggestionsPanel);
        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
        autoSuggestionPopUpWindow.setSize(tW, tH);
        autoSuggestionPopUpWindow.setVisible(true);

        int windowX = 0;
        int windowY = 0;

        windowX = container.getX() + textField.getX() + 5;
        if (suggestionsPanel.getHeight() > autoSuggestionPopUpWindow.getMinimumSize().height) {
            windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getMinimumSize().height;
        } else {
            windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getHeight();
        }

        autoSuggestionPopUpWindow.setLocation(windowX, windowY);
        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
        autoSuggestionPopUpWindow.revalidate();
        autoSuggestionPopUpWindow.repaint();

    }

    public void setDictionary(ArrayList<String> words) {
        dictionary.clear();
        if (words == null) {
            return;//so we can call constructor with null value for dictionary without exception thrown
        }
        for (String word : words) {
            dictionary.add(word);
        }
    }

    public JWindow getAutoSuggestionPopUpWindow() {
        return autoSuggestionPopUpWindow;
    }

    public Window getContainer() {
        return container;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void addToDictionary(String word) {
        dictionary.add(word);
    }

    boolean wordTyped(String typedWord) {

        if (typedWord.isEmpty()) {
            return false;
        }
        //System.out.println("Typed word: " + typedWord);

        boolean suggestionAdded = false;

        for (String word : dictionary) {//get words in the dictionary which we added
            boolean fullymatches = true;
            for (int i = 0; i < typedWord.length(); i++) {//each string in the word
                if (!typedWord.toLowerCase().startsWith(String.valueOf(word.toLowerCase().charAt(i)), i)) {//check for match
                    fullymatches = false;
                    break;
                }
            }
            if (fullymatches) {
                addWordToSuggestions(word);
                suggestionAdded = true;
            }
        }
        return suggestionAdded;
    }
}

class SuggestionLabel extends JLabel {

    private boolean focused = false;
    private final JWindow autoSuggestionsPopUpWindow;
    private final JTextField textField;
    private final AutoSuggestor autoSuggestor;
    private Color suggestionsTextColor, suggestionBorderColor;

    public SuggestionLabel(String string, final Color borderColor, Color suggestionsTextColor, AutoSuggestor autoSuggestor) {
        super(string);

        this.suggestionsTextColor = suggestionsTextColor;
        this.autoSuggestor = autoSuggestor;
        this.textField = autoSuggestor.getTextField();
        this.suggestionBorderColor = borderColor;
        this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

        initComponent();
    }

    private void initComponent() {
        setFocusable(true);
        setForeground(suggestionsTextColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                replaceWithSuggestedText();

                autoSuggestionsPopUpWindow.setVisible(false);
            }
        });

        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
        getActionMap().put("Enter released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                replaceWithSuggestedText();
                autoSuggestionsPopUpWindow.setVisible(false);
            }
        });
    }

    public void setFocused(boolean focused) {
        if (focused) {
            setBorder(new LineBorder(suggestionBorderColor));
        } else {
            setBorder(null);
        }
        repaint();
        this.focused = focused;
    }

    public boolean isFocused() {
        return focused;
    }

    private void replaceWithSuggestedText() {
        String suggestedWord = getText();
        String text = textField.getText();
        String typedWord = autoSuggestor.getCurrentlyTypedWord();
        String t = text.substring(0, text.lastIndexOf(typedWord));
        String tmp = t + text.substring(text.lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
        textField.setText(tmp + " ");
    }
}
