package com.gmail.wandilenxumalo131;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class AppDisplay extends JFrame implements ActionListener, CaretListener {

	private JPanel nPan, sPan, cPan,leftCPan, rightCPan;
	private JButton openFile, saveFile, exit;
	private JTextField filePathJTF;
	private JLabel filePath, mostFreqTextF, mostFreq7TextF, highestScoringWord;
	private Font font;
	private JScrollPane scroll;
	private JTextArea txtArea;
	private BufferedReader br;
	private PrintWriter pw;
	private JOptionPane fileSaved;
	private JFileChooser fileChosen;

	public AppDisplay() {
		font = new Font("Arial", Font.BOLD, 28);
		NorthPanel();
		CenterPanel();
		SouthPanel();
		SetupFrame();

		fileSaved = new JOptionPane();
		fileSaved.setLocation(new Point(500, 500));
	}

	public void NorthPanel() {
		nPan = new JPanel();
		filePath = new JLabel("File Path: ");
		filePath.setFont(font);
		filePathJTF = new JTextField(20);
		filePathJTF.setFont(font);
		filePathJTF.addActionListener(this);
		nPan.add(filePath);
		nPan.add(filePathJTF);
	}

	public void CenterPanel() {
		cPan = new JPanel(new GridLayout(1, 2));
		
		leftCPan = new JPanel(new GridLayout(1,1));
		leftCPan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createTitledBorder("Enter Text")));
		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftCPan.add(scroll);
		
		rightCPan = new JPanel(new GridLayout(3, 1));
		rightCPan.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createTitledBorder("Results")));
		mostFreqTextF = new JLabel("Most frequent word");
		mostFreq7TextF = new JLabel("Most frequent 7 character word");
		highestScoringWord = new JLabel("Highest Scoring word");
		rightCPan.add(mostFreqTextF);
		rightCPan.add(mostFreq7TextF);
		rightCPan.add(highestScoringWord);
		
		cPan.add(leftCPan);
		cPan.add(rightCPan);
	}

	public void SouthPanel() {
		sPan = new JPanel();
		openFile = new JButton("Open File");
		openFile.setEnabled(false);
		openFile.setFont(font);
		openFile.addActionListener(this);
		saveFile = new JButton("Save File");
		saveFile.setEnabled(false);
		saveFile.setFont(font);
		saveFile.addActionListener(this);
		exit = new JButton("Exit");
		exit.setEnabled(true);
		exit.setFont(font);
		exit.addActionListener(this);
		sPan.add(openFile);
		sPan.add(saveFile);
		sPan.add(exit);
	}

	public void SetupFrame() {
		this.setLayout(new BorderLayout());
		this.setSize(1080, 1080);
		this.add(nPan, BorderLayout.NORTH);
		this.add(cPan, BorderLayout.CENTER);
		this.add(sPan, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppDisplay();
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object source = ae.getSource();
		String str = null;
		if (source == filePathJTF) {
			if (filePathJTF.getText() == " ") {
				return;
			}
			if(filePathJTF.getText() != "") {
				openFile.setEnabled(true);
				fileChosen = new JFileChooser(filePathJTF.getText());
				fileChosen.addActionListener(this);
				txtArea.setText("");
			}
//			openFile.setEnabled(true);
		}
		if (source == exit) {
			System.exit(0);
		}
		if (source == saveFile) {
			fileChosen.showSaveDialog(txtArea);
			try {
				pw = new PrintWriter(new FileWriter(fileChosen.getSelectedFile()));
				pw.write(txtArea.getText());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(txtArea, "File Saved!");
			txtArea.setText(" ");
			saveFile.setEnabled(false);
			openFile.setEnabled(true);
		}
		if (source == openFile) {
			fileChosen.showOpenDialog(txtArea);
			fileChosen.setVisible(true);

			try {
				br = new BufferedReader(new FileReader(fileChosen.getSelectedFile()));
				while ((str = br.readLine()) != null) {
					txtArea.setText(txtArea.getText() + str + "\n");
				}
				MostFrequentWord mfw = new MostFrequentWord(fileChosen.getSelectedFile());
				MostFrequent7CharWord char7word = new MostFrequent7CharWord(fileChosen.getSelectedFile());
				ScrabbleScoring scrabble = new ScrabbleScoring(fileChosen.getSelectedFile());
				
				mostFreqTextF.setText(mfw.toString());
				mostFreq7TextF.setText(char7word.toString());
				highestScoringWord.setText(scrabble.toString());
				
				br.close();
				txtArea.addCaretListener(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			saveFile.setEnabled(true);
			openFile.setEnabled(false);
		}
	}

}
