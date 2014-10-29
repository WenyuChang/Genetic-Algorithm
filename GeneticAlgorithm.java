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
    int wz;  //变异位置
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
		setTitle("遗传算法");
		setSize(width,height);
		setBackground(Color.white);
		setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		addWindowListener(this);
		addComponentListener(this);
		l1=new Label("群体大小(<=1000)");
		l2=new Label("变异时保留个数(<=输入的群体大小)");
		l3=new Label("进化次数");
		l4=new Label("显示子代个数(<=输入的群体大小)");
		l5=new Label("变异率(百分之几)");
		tf1=new TextField("",5);
		tf2=new TextField("",5);
		tf3=new TextField("",5);
		tf4=new TextField("",5);
		tf5=new TextField("",2);
		b1=new Button("确定");
		b1.addActionListener(this);
		b2=new Button("清零");
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
	 	g.setColor(Color.black);   //刻画坐标轴和SIN(X)函数曲线
		g.drawLine(40,50,40,500);
		g.drawString("∧",35,55);
		g.drawString("Y轴",10,50);
		g.drawLine(10,380,580,380);
		g.drawString("＞",575,385);
		g.drawString("X轴",570,370);
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
	
	
	void draw(int x,int y)  //画点
	{
		pen.setColor(Color.red);
		pen.drawOval(40+x,380-y,3,3);
	}
	
	void drawC(int x,int y)  //清点
	{
		pen.setColor(Color.white);
		pen.drawOval(40+x,380-y,3,3);
	}
	
	void binx(int b) //转成二进制
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
			//System.out.println("群体大小="+qunti);
			//System.out.println("变异时保留个数="+baoliu);
			//System.out.println("进化次数="+jinhua);
			//System.out.println("显示子代个数="+zidai);
			//System.out.println("变异个数="+bianyi);
		
			double max,temp;
			
			for(i=0;i<zidai;i++)
		 	{
				drawC((int)(o[i]/255*400),(int)(100*fito[i]));
		 	}
			
			for(i=0;i<qunti;i++)  //生成祖先群体
			{
				o[i]=(int)(Math.random()*255);
				//System.out.println("o"+i+"="+o[i]);
			}
			
			for(j=0;j<jinhua;j++)  //进化过程
			{
				for(i=0;i<qunti;i++)
	   	 		{
		   			fito[i]=Math.sin(o[i]/255.0*10.0);
		   			//System.out.println("fito"+i+"="+fito[i]);
				}
		
				for(i=0;i<qunti;i++)  //对fitness数组由大到小排序，同时调换祖先数组
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
		 
		 		for(i=0;i<zidai;i++)  //画点
		 		{
					draw((int)(o[i]/255*400),(int)(100*fito[i]));
		 		} 
		 		
		 		try
		       	{
		    		Thread.sleep(200);  //延时200毫秒
		       	}
		       	catch(Exception f)
		       	{
		       	}
		       	
		       	for(i=0;i<zidai;i++)   //清点，这样就有闪烁效果
		 		{
					drawC((int)(o[i]/255*400),(int)(100*fito[i]));
		 		} 
		 		
		 		double si=0.0;
        		for(i=40;i<440;i++)  //重画对照的SIN(X)函数，因为清点时把点清掉了
				{
					pen.setColor(Color.green);
					pen.drawLine(i,(int)(382-100*Math.sin(si)),i+1,(int)(382-100*Math.sin(si+0.025)));
					si+=0.025;
				}
		 		
		 		for(i=0;i<qunti;i++)  //初始化子代群体
		 		{
		 			p[i]=0;
		 		}
		  
		 	 	for(i=0;i<qunti/2;i++)   //对子代赋值变异
		  		{
		  			ox[i]=o[(int)(Math.random()*Math.random()*qunti)];
		  			oy[i]=o[(int)(Math.random()*Math.random()*qunti)];
		  			wz=(int)(Math.random()*6+1);
		  			binx((int)ox[i]);
		  			biny((int)oy[i]);
		   		 	for(l=0;l<wz;l++)  //交叉
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
	    		
	    		for(i=0;i<bianyi;i++)  //选取某些子代变异
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
				
		    	for(i=0;i<qunti;i++)  //对变异后的子代重新由大到小排序
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
				
				if(j==jinhua-1)  //保留最后一次的结果图像
				{
					for(i=0;i<zidai;i++)
		 			{
						draw((int)(o[i]/255*400),(int)(100*fito[i]));
		 			}
		 		}
				
				else
				{
					for(i=baoliu;i<qunti;i++)  //用优化子代替换某些祖先
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