package lab1;
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
public class magiclal {

	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		String FileName="4.txt";
		String path = root+File.separator+"src"+File.separator+FileName;
        martix  first= new martix();
        if(first.ismagical(path))System.out.println("Yes");
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
	   public boolean ismagical(String path)
	   {
	    fin = new File(path);
	     FileReader a;
	     try{
	         a=new FileReader(path);
	          reader = new BufferedReader(a);
	         }
	     catch(IOException s) {System.err.println("There is no such file");}
	     builddata();
		 int rowsum=ismagicalrow();
		 int colsum=ismagicalcol();
		 int diasum=ismagicaldiagon();
		 if(rowsum!=-1&&rowsum==colsum&&rowsum==diasum)return true;
		 else return false;
	    }
	 private void builddata()
	 {
	     String str;
	     String toword[];
	     int lastcol=-1,col=-1,row = 0;
	     Pattern pattern = Pattern.compile("[0-9]*");
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
					if(pattern.matcher(toword[counter-1]).matches())data[row][counter] = Integer.parseInt(toword[counter-1]);
					else if(toword[counter-1].indexOf(".")==-1&&toword[counter-1].indexOf("-")==-1)throw new Errormaritx("Please use tab");
					else throw new Errormaritx("there is some number not a interger");
				}
			 }
		     if(row!=col)throw  new Errormaritx("That is not a martix");//¼ÇµÃ¼Ì³Ð
		     else this.col=col;
		} catch (IOException e) {
			System.err.println("There is no such file");
			System.exit(1);
		} catch (Errormaritx e) {
			System.err.println(e.errorinf);
			System.exit(1);
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
 