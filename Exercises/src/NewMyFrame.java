import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class NewMyFrame extends JFrame{
	JPanel personP = new JPanel();
	JPanel carP = new JPanel();
	JPanel rentaP = new JPanel();
	JPanel sprP = new JPanel();
	
	JTabbedPane tab = new JTabbedPane();
	
	
	public NewMyFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		tab.add(personP, "Клиенти");
		tab.add(carP, "Коли");
		tab.add(rentaP, "Наем");
		tab.add(sprP, "Справка по име и марка");
		
		this.add(tab);
		
		this.setVisible(true);
	}
}
