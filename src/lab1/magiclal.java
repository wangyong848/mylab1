package lab1;
import java.util.*;
import java.io.*;
public class magiclal {

	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		String FileName="3.txt";
		String path = root+File.separator+"src"+File.separator+FileName;
        martix  first= new martix(path);
		first.builddata();
        if(first.ismagical())System.out.println("Yes");
        else System.out.println("No");
	}
}
 class martix
 {   
	 public class Errormaritx extends Exception
	 {
		 public String errorinf;
		 Errormaritx(String s)
		 {
			 super("Error martix");
			 errorinf=s;
		 }
	 }
	 private File fin;	 
	 private int[][] data;
	 private int col;
	 private BufferedReader reader;
	 martix(String path)
	 {
         fin = new File(path);
         FileReader a;
         try{
        	 a=new FileReader(path);
        	 reader = new BufferedReader(a);
        	 }
         catch(IOException s) {System.err.println("There is no such file");}
	 }
	   public boolean ismagical()
	   {
		 int rowsum=ismagicalrow();
		 int colsum=ismagicalcol();
		 int diasum=ismagicaldiagon();
		 if(rowsum!=-1&&rowsum==colsum&&rowsum==diasum)return true;
		 else return false;
	    }
	 public void builddata()
	 {
	     String str;
	     String toword[];
	     int lastcol=-1,col=-1,row = 0;
	     try {
			while((str=reader.readLine())!=null)
			 {
				toword = str.split("\\t");
				row++;
				lastcol=col;
				col=toword.length;
				if(lastcol!=-1&&col!=lastcol)throw new Errormaritx("That is not a martix");
				if(lastcol==-1)data = new int[col+1][col+1];
				for(int counter=1;counter<=col;counter++)
				{
					data[row][counter] = Integer.parseInt(toword[counter-1]);
				}
			 }
		     if(row!=col)throw  new Errormaritx("That is not a martix");//¼ÇµÃ¼Ì³Ð
		     else this.col=col;
		} catch (IOException e) {
			System.err.println("There is no such file");
			System.exit(1);
		} catch (Errormaritx e) {
			System.err.println(e.errorinf);      
		}
	     
	 }
	 private int ismagicalrow()
	 {
		 int sum=0,lastsum=0;
		 for(int counter=1;counter<=col;counter++)
		 {
			 lastsum=sum;
			 sum=0;
			 for(int counter2=1;counter2<=col;counter2++)
			 {
				 sum+=data[counter][counter2];
			 }
			 if(sum!=lastsum&&lastsum!=0)return -1;
		 }
		 return sum;
	 }
	 private int ismagicalcol()
	 {		 
     int sum=0,lastsum=0;
	 for(int counter=1;counter<=col;counter++)
	 {
		 lastsum=sum;
		 sum=0;
		 for(int counter2=1;counter2<=col;counter2++)
		 {
			 sum+=data[counter2][counter];
		 }
		 if(sum!=lastsum&&lastsum!=0)return -1;
	 }
	 return sum;
	 }
	 private int ismagicaldiagon()
	 {
		 int a=1;
		 int b=col;
		 int sum1=0,sum2=0;
		 while(a<=col)
		 {
			 sum1+=data[a][a];
			 sum2+=data[a][b];
			 b--;
			 a++;
		 }
		 if(sum1!=sum2)return -1;
		 else return sum1;
	 }
 }
 