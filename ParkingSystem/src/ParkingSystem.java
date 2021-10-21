import java.io.*;
import java.util.ArrayList;

interface IParking {
    // 打印输出结果
    public void print(String[] s);
    // 检查是否有 carType 对应的停车位
    // 如果没有空车位，请返回 false ，否则将该车停入车位并返回 true
    public boolean addCar(int carType);
    // 解析命令行输入的参数（格式），如文档描述
    public static IParams parse() throws Exception{
        return null;
    };
}
interface IParams {
    // 获取大车位
    public int getBig();
    // 获取中车位
    public int getMedium();
    // 获取小车位
    public int getSmall();
    // 获取停车序列，例如 [1 2 2 3] 表示 依次停一辆大车，中车，中车，小车
    public ArrayList<Integer> getPlanParking();
}

public abstract class ParkingSystem implements IParking {
	int big,medium,small;
	String s2;
    public ParkingSystem(int big, int medium, int small) {
        this.big=big;
        this.medium=medium;
        this.small=small;
    }
    
	public String[] parse(String input) {
    	String[] s=new String[10];
    	String s1;
    	s1=input.substring(1);
    	s[0]=s1.substring(0,s1.indexOf("\""));
    	s1=s1.substring(s1.indexOf("\", \"")+4);
    	for(int i=1;i<5;i++) {
    		s[i]=s1.substring(0,s1.indexOf("\""));
        	if(i<4) s1=s1.substring(s1.indexOf("\", \"")+4);
    	}
    	
    	return s;
    }
    
    public static void main(String[] args) throws Exception {
    	InputStreamReader isr=new InputStreamReader(System.in);
    	BufferedReader br=new BufferedReader(isr);
    	String[] s=new String[10],s1=new String[10];
    	int[] a=new int[4];
    	int b=0;
    	
        IParams params = new IParams() {
            @Override
            public int getBig() { 
                return 1;
            }

            @Override
            public int getMedium() {
                return 1;
            }

            @Override
            public int getSmall() {
                return 0;
            }

            @Override
            public ArrayList<Integer> getPlanParking() {
            	
                return null;
            }
        };
        ParkingSystem ps = new ParkingSystem(params.getBig(), params.getMedium(), params.getSmall()) {
        	@Override
            public void print(String[] s1) {
                System.out.print("[");
                for(int i=0;i<5;i++) {
                	System.out.print(s1[i]);
                	if(i+1<5) System.out.print(",");
                }
                System.out.println("]");
            }

            @Override
            public boolean addCar(int carType) {
                if(carType==1) {
                	if(this.big>0) {
                		this.big--;
                		return true;
                	}
                	else {
                		return false;
                	}
                }
                else if(carType==2) {
                	if(this.medium>0) {
                		this.medium--;
                		return true;
                	}
                	else return false;
                }
                else {
                	if(this.small>0) {
                		this.small--;
                		return true;
                	}
                	else return false;
                }
            }
        };
        
        ArrayList<Integer> plan = new ArrayList<Integer>();
        
        s=ps.parse(br.readLine());     //拆分输入流
        for(int i=0;i<5;i++) {     //转化
        	if(s[i].equals("ParkingSystem")) {
        		s1[0]=null;
        	}
    		if(s[i].equals("addCar")) {
    			if(b<3) a[b]=b+1;
    			else a[b]=1;
    			b++;
    		}
    	}
        for(int i=0;i<4;i++) plan.add(a[i]);
        for (int i=0;i<plan.size();i++) {
           s1[i+1]=String.valueOf(ps.addCar(plan.get(i)));
        }
        ps.print(s1);
    }
}