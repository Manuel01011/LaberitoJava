package gui;
import java.awt.*;
import java.util.Vector;
import javax.swing.JPanel;
public class Tablero extends JPanel {
	private Vector<Linea> lineas;
	private Vector<Paso> pasos;
	private Circulo inicio;
	private Circulo fin;
	public Tablero(){
		super();
		lineas=new Vector<Linea>(0);
		pasos=new Vector<Paso>(0);
		setBackground(Color.white);
	}
	
	public void agregarLinea(int x1,int y1,int x2,int y2){
		lineas.add(new Linea(x1,y1,x2,y2));
	}
	public void agregarPaso(int x1,int y1,int x2,int y2){
		pasos.add(new Paso(x1,y1,x2,y2));
	}
	
	public void paint(Graphics g){
		this.setBackground(Color.blue.darker());
		super.paint(g);
		g.setColor(Color.gray);
		for(Paso p:pasos){
			g.fillRect(p.x1, p.y1, p.x2, p.y2);
		}
		g.setColor(Color.WHITE);
		for(Linea l:lineas){
			((Graphics2D) g).setStroke(new BasicStroke(l.grosor));
			g.drawLine(l.x1, l.y1, l.x2, l.y2);
		}
		if(inicio!=null){
			g.setColor(Util.inicioColor);
			g.fillArc(inicio.xcentro,inicio.ycentro,2*inicio.radio,2*inicio.radio,0,360);
		}
		if(fin!=null){
			g.setColor(Util.finColor);
			g.fillArc(fin.xcentro,fin.ycentro,2*inicio.radio,2*inicio.radio,0,360);
		}
	}
	public void setInicio(Circulo inicio) {
		this.inicio = inicio;
	}
	public void setFin(Circulo fin) {
		this.fin = fin;
	}
	public void limpiar() {
		lineas.removeAllElements();
		pasos.removeAllElements();
		setInicio(null);
		setFin(null);
	}
}

class Paso extends Linea{
	public Paso(int x1,int y1,int ancho,int alto){
		super(x1, y1, ancho, alto);
	}
}

class Circulo{
	public int xcentro,ycentro,radio;
	public Circulo(int xc,int yc,int radio){
		this.xcentro=xc;
		this.ycentro=yc;
		this.radio=radio;
	}
}