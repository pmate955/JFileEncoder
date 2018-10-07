package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Controller.Encoder;

public class MainFrame extends JFrame implements Runnable{
	
	private Encoder enc;
	private JLabel fileLabel;
	private JProgressBar progress;
	private JTextField keyField;
	private JCheckBox isEncode;
	
	public MainFrame() {
		this.setTitle("File Encoder");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		this.add(addInputPanel(), BorderLayout.NORTH);
		this.add(addWorkPanel(), BorderLayout.CENTER);
		this.enc = new Encoder();
		this.pack();
		this.setVisible(true);
	}

	private JPanel addWorkPanel() {
		JPanel out = new JPanel();
		out.setBorder(BorderFactory.createTitledBorder("Output options"));
		out.setLayout(new GridLayout(2,2));
		JButton start = new JButton("Start");
		JButton save = new JButton("Save");	
		save.addActionListener((l)->{
			this.save();
		});
		start.addActionListener((l)->{
			this.startEncoding();
		});
		this.progress = new JProgressBar();
		out.add(start);
		out.add(save);
		out.add(new JLabel("Progress:"));
		out.add(progress);
		return out;
	}


	private JPanel addInputPanel() {
		JPanel out = new JPanel();
		out.setLayout(new FlowLayout(FlowLayout.LEADING));
		out.setBorder(BorderFactory.createTitledBorder("Input options"));
		JButton readBtn = new JButton("Open file");
		fileLabel = new JLabel("Please open file!");
		readBtn.addActionListener((l)->{
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				if(enc.readFile(jfc.getSelectedFile().getAbsolutePath())) {
					fileLabel.setText(jfc.getSelectedFile().getName());
				} else {
					fileLabel.setText("Please try again!");
					JOptionPane.showMessageDialog(this, "Can't open the file!", "Error", JOptionPane.ERROR_MESSAGE);
				};
			}
		});
		keyField = new JTextField(10);
		isEncode = new JCheckBox("Encode");
		out.add(readBtn);
		out.add(fileLabel);
		out.add(new JLabel("Key"));
		out.add(keyField);
		out.add(isEncode);
		return out;
	}
	

	private void startEncoding() {
		if(keyField.getText().length() == 0){
			JOptionPane.showMessageDialog(this, "No keycode", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!enc.hasInput()) {
			JOptionPane.showMessageDialog(this, "No input file", "Error", JOptionPane.ERROR_MESSAGE);			
		} else {
			Thread mainThread = new Thread(this);
			mainThread.start();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf = new MainFrame();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		enc.setKey(keyField.getText());
		Thread t = new Thread(enc);
		t.start();
		progress.setMaximum(enc.getSize());
		while(t.isAlive()) {
			progress.setValue(enc.actual);
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		progress.setValue(enc.getSize());
	}

	private void save() {
		// TODO Auto-generated method stub
		if(enc.save(!isEncode.isSelected())) {
			System.out.println("Saved");
		} else {
			System.out.println("Error");
		}; 
	}
	
}
