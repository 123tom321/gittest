import java.io.*;
import java.util.ArrayList;

interface IParking {
    // ��ӡ������
    public void print(String[] s);
    // ����Ƿ��� carType ��Ӧ��ͣ��λ
    // ���û�пճ�λ���뷵�� false �����򽫸ó�ͣ�복λ������ true
    public boolean addCar(int carType);
    // ��������������Ĳ�������ʽ�������ĵ�����
    public static IParams parse() throws Exception{
        return null;
    };
}
interface IParams {
    // ��ȡ��λ
    public int getBig();
    // ��ȡ�г�λ
    public int getMedium();
    // ��ȡС��λ
    public int getSmall();
    // ��ȡͣ�����У����� [1 2 2 3] ��ʾ ����ͣһ���󳵣��г����г���С��
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
        
        s=ps.parse(br.readLine());     //���������
        for(int i=0;i<5;i++) {     //ת��
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