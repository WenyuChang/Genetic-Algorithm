import java.awt.*;
import java.awt.event.*;
import java.math.*;


public class GeneticAlgorithm extends Frame implements WindowListener,ComponentListener,ActionListener
{
	int width=800,height=600;
	double o[]=new double[1000];
    double p[]=new double[1000];
    double ox[]=new double[500];
    double oy[]=new double[500];
    int wz;  //����λ��
    int bx[]=new int[8];
    int by[]=new int[8];
    double fito[]=new double[1000];
    double fitp[]=new double[1000];
    int i,j,k,l;
    String tq,tb,tj,tz,tbi;
    int qunti,baoliu,jinhua,zidai,bianyi;
    double bian;
	Color color=Color.blue;
	Label l1;
	Label l2;
	Label l3;
	Label l4;
	Label l5;
	TextField tf1;
	TextField tf2;
	TextField tf3;
	TextField tf4;
	TextField tf5;
	Button b1;
	Button b2;
	Graphics pen;
   	
	public GeneticAlgorithm()
	{
		setVisible(true);
		pen=getGraphics();
		setTitle("�Ŵ��㷨");
		setSize(width,height);
		setBackground(Color.white);
		setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		addWindowListener(this);
		addComponentListener(this);
		l1=new Label("Ⱥ���С(<=1000)");
		l2=new Label("����ʱ��������(<=�����Ⱥ���С)");
		l3=new Label("��������");
		l4=new Label("��ʾ�Ӵ�����(<=�����Ⱥ���С)");
		l5=new Label("������(�ٷ�֮��)");
		tf1=new TextField("",5);
		tf2=new TextField("",5);
		tf3=new TextField("",5);
		tf4=new TextField("",5);
		tf5=new TextField("",2);
		b1=new Button("ȷ��");
		b1.addActionListener(this);
		b2=new Button("����");
		b2.addActionListener(this);
		add(l1);
		add(tf1);
		add(l2);
		add(tf2);
		add(l3);
		add(tf3);
		add(l4);
		add(tf4);
		add(l5);
		add(tf5);
		add(b1);
	    add(b2);
	}
	
	
	
	public void paint(Graphics g)
	{
	 	g.setColor(Color.black);   //�̻��������SIN(X)��������
		g.drawLine(40,50,40,500);
		g.drawString("��",35,55);
		g.drawString("Y��",10,50);
		g.drawLine(10,380,580,380);
		g.drawString("��",575,385);
		g.drawString("X��",570,370);
		g.drawString("O",25,393);

		double si=0.0;
        for(i=40;i<440;i++)
		{
			g.setColor(Color.green);
			g.drawLine(i,(int)(382-100*Math.sin(si)),i+1,(int)(382-100*Math.sin(si+0.025)));
			si+=0.025;
		}
		
    }
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	
	public void componentResized(ComponentEvent e)
	{
		width=getWidth();
		height=getHeight();
	}
	
	public static void main(String arg[])
	{
		new GeneticAlgorithm().setVisible(true);
	}
	
	public void componentShown(ComponentEvent e){}
	
	public void componentMoved(ComponentEvent e){}
	
	public void componentHidden(ComponentEvent e){}
	
	public void windowOpened(WindowEvent e){}
	
	public void windowActivated(WindowEvent e){}
	
	public void windowDeactivated(WindowEvent e){}
	
	public void windowClosed(WindowEvent e){}
	
	public void windowIconified(WindowEvent e){}
	
	public void windowDeiconified(WindowEvent e){}
	
	
	void draw(int x,int y)  //����
	{
		pen.setColor(Color.red);
		pen.drawOval(40+x,380-y,3,3);
	}
	
	void drawC(int x,int y)  //���
	{
		pen.setColor(Color.white);
		pen.drawOval(40+x,380-y,3,3);
	}
	
	void binx(int b) //ת�ɶ�����
	{
		for(int m=7;m>=0;m--)
		{
			bx[m]=b%2;
			b=b/2;
		}
	}
	
	void biny(int b)
	{
		for(int m=7;m>=0;m--)
		{
			by[m]=b%2;
			b=b/2;
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			tq=tf1.getText();
			tb=tf2.getText();
			tj=tf3.getText();
			tz=tf4.getText();
			tbi=tf5.getText();
			qunti=Integer.parseInt(tq);
			baoliu=Integer.parseInt(tb);
			jinhua=Integer.parseInt(tj);
			zidai=Integer.parseInt(tz);
			bian=Integer.parseInt(tbi)/100.0*qunti;
			bianyi=(int)bian;
			//System.out.println("Ⱥ���С="+qunti);
			//System.out.println("����ʱ��������="+baoliu);
			//System.out.println("��������="+jinhua);
			//System.out.println("��ʾ�Ӵ�����="+zidai);
			//System.out.println("�������="+bianyi);
		
			double max,temp;
			
			for(i=0;i<zidai;i++)
		 	{
				drawC((int)(o[i]/255*400),(int)(100*fito[i]));
		 	}
			
			for(i=0;i<qunti;i++)  //��������Ⱥ��
			{
				o[i]=(int)(Math.random()*255);
				//System.out.println("o"+i+"="+o[i]);
			}
			
			for(j=0;j<jinhua;j++)  //��������
			{
				for(i=0;i<qunti;i++)
	   	 		{
		   			fito[i]=Math.sin(o[i]/255.0*10.0);
		   			//System.out.println("fito"+i+"="+fito[i]);
				}
		
				for(i=0;i<qunti;i++)  //��fitness�����ɴ�С����ͬʱ������������
				{
					max=fito[i];
					k=i;
		  			for(int l=i;l<qunti;l++)
		  			{
		  				if(fito[l]>max)
		    			{
		    				max=fito[l];
		    				k=l;
		    			}
		  			}
		  			fito[k]=fito[i];
		  			temp=o[k];
		  			o[k]=o[i];
		  			o[i]=temp;
		  			fito[i]=max;
				}
		  
		  		/*for(i=0;i<qunti;i++)
		  		{
		  			System.out.println("fito"+i+"="+fito[i]);
		  			System.out.println("o"+i+"="+o[i]);
		  		}*/
		 
		 		for(i=0;i<zidai;i++)  //����
		 		{
					draw((int)(o[i]/255*400),(int)(100*fito[i]));
		 		} 
		 		
		 		try
		       	{
		    		Thread.sleep(200);  //��ʱ200����
		       	}
		       	catch(Exception f)
		       	{
		       	}
		       	
		       	for(i=0;i<zidai;i++)   //��㣬����������˸Ч��
		 		{
					drawC((int)(o[i]/255*400),(int)(100*fito[i]));
		 		} 
		 		
		 		double si=0.0;
        		for(i=40;i<440;i++)  //�ػ����յ�SIN(X)��������Ϊ���ʱ�ѵ������
				{
					pen.setColor(Color.green);
					pen.drawLine(i,(int)(382-100*Math.sin(si)),i+1,(int)(382-100*Math.sin(si+0.025)));
					si+=0.025;
				}
		 		
		 		for(i=0;i<qunti;i++)  //��ʼ���Ӵ�Ⱥ��
		 		{
		 			p[i]=0;
		 		}
		  
		 	 	for(i=0;i<qunti/2;i++)   //���Ӵ���ֵ����
		  		{
		  			ox[i]=o[(int)(Math.random()*Math.random()*qunti)];
		  			oy[i]=o[(int)(Math.random()*Math.random()*qunti)];
		  			wz=(int)(Math.random()*6+1);
		  			binx((int)ox[i]);
		  			biny((int)oy[i]);
		   		 	for(l=0;l<wz;l++)  //����
		    		{
		    			p[2*i]=p[2*i]+bx[l]*Math.pow(2,l);
		    			p[2*i+1]=p[2*i+1]+by[l]*Math.pow(2,l);
		    		}
		    				    
		    		for(;l<8;l++)
		    		{
		    			p[2*i]=p[2*i]+by[l]*Math.pow(2,l);
		    			p[2*i+1]=p[2*i+1]+bx[l]*Math.pow(2,l);
		    		}
		    	}
	    		
	    		for(i=0;i<bianyi;i++)  //ѡȡĳЩ�Ӵ�����
	    		{
	    			wz=(int)(Math.random()*7);
	    			p[i]=(int)p[i]^(int)Math.pow(2,wz);
	       		}
	       		
		    	for(i=0;i<qunti;i++)
		    	{
		    		System.out.println("p"+i+"="+p[i]);
		    	}
		    	
		    	for(i=0;i<qunti;i++)
	   	 		{
		   			fitp[i]=Math.sin(p[i]/255.0*10.0);
		   			//System.out.println("fitp"+i+"="+fitp[i]);
				}
				
		    	for(i=0;i<qunti;i++)  //�Ա������Ӵ������ɴ�С����
				{
					max=fitp[i];
					k=i;
		  			for(int l=i;l<qunti;l++)
		  			{
		  				if(fitp[l]>max)
		    			{
		    				max=fitp[l];
		    				k=l;
		    			}
		  			}
		  			fitp[k]=fitp[i];
		  			temp=p[k];
		  			p[k]=p[i];
		  			p[i]=temp;
		  			fitp[i]=max;
				}
				
				if(j==jinhua-1)  //�������һ�εĽ��ͼ��
				{
					for(i=0;i<zidai;i++)
		 			{
						draw((int)(o[i]/255*400),(int)(100*fito[i]));
		 			}
		 		}
				
				else
				{
					for(i=baoliu;i<qunti;i++)  //���Ż��Ӵ��滻ĳЩ����
					{
						o[i]=p[i-baoliu];
					}
				}
		    
		  	} 
	
		}
		if(e.getSource()==b2)
		{
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
		}
	}

}