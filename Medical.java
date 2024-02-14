import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class Mstore implements Serializable{
	int MedicineID;
	int MedicineQtyno;
	String Medicinename;
	Mstore(int MedicineID , int MedicineQtyno ,String Medicinename ){
		this.MedicineID = MedicineID;
		this.MedicineQtyno = MedicineQtyno;
		this.Medicinename=Medicinename;
	}
	public String toString() {
		return MedicineID+"\t\t"+MedicineQtyno+"\t\t"+Medicinename;
	}
}


public class Medical {
	public static int log(){
		int flag =1;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter user name ");
			String username = sc.nextLine();
			System.out.println("enter ur password ");
			String pass = sc.nextLine();
			if("admin".equals(username)&&"admin".equals(pass)) {
				System.out.println("Sucessfully enter into the application");
				See();
			}
			else {
				System.out.println("Unsucessful ur user id and passward is incorrect");
				log();
			}
			}catch(Exception e) {
				System.out.println(e);
			}
		return flag;
	}
	public static void See() throws FileNotFoundException, IOException, ClassNotFoundException {
		Scanner s = new Scanner (System.in);
		Scanner s1 = new Scanner (System.in);
		File file = new File("medical.txt");
		ArrayList<Mstore> al = new ArrayList<Mstore>();
		ObjectOutputStream oos = null; 
		ObjectInputStream ois = null;
		ListIterator li =null;
		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Mstore>)ois.readObject();
			ois.close();
		}
		
		while(true) {
			System.out.println("-----------------------------------");
			System.out.println("1.INSERT");
			System.out.println("2.SEARCH");
			System.out.println("3.DELETE");
			System.out.println("4.DISPLAY ALL PRODUCTS WITH DETAILS");
			System.out.println("5.SUMMERY ABOUT STORE");
			System.out.println("-----------------------------------");
			System.out.println("Enter ur choice");
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Number of Stocks to add");
				int n = s.nextInt();
				for(int i = 0;i<n;i++) {
				System.out.println("Enter Product ID");
				int MedicineID = s.nextInt();
				System.out.println("Enter Quantity: ");
				int MedicineQtyno = s.nextInt();
				System.out.println("Enter name of product ");
				String Medicinename = s1.nextLine();
				//System.out.println(Registration+" "+roomno+" "+name);
				al.add(new Mstore(MedicineID,MedicineQtyno,Medicinename));
				System.out.println(al);
				
				}
				oos= new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				break;
			case 2:
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Mstore>)ois.readObject();
					ois.close();
					
				System.out.println("Enter Product name:");
				String Medn = s1.nextLine();
                                s1.close();
                                //System.out.println(Medn);
				System.out.println("-----------------------------------");
				System.out.println("MedicineID"+"\t\t"+"MedicineQtyno"+"\t\t"+"Medicinename");
				li = al.listIterator();
				while(li.hasNext()) {
					Mstore st = (Mstore)li.next();
					if(st.Medicinename.equals(Medn)) {
				         System.out.println(st);
					}
                                       /* else{ System.out.println("ur r getting some error");
                                             }*/
				}
				System.out.println("-----------------------------------");
				}else {
					System.out.println("there is no file..........");
				}
				break;
			case 3:
                                 String Medn=" ";
                                boolean flag = false;
				boolean f = false;
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Mstore>)ois.readObject();
					ois.close();
			      try{
                                   System.out.println("Enter Product which u want to delete ");
				   Medn = s1.nextLine();
                                   s1.close();
                                   flag=true;
				
                                 }
                                  catch(Exception e){
				    System.out.println("there is an error u enter anyting else instead of string" + e);
				}
                                   System.out.println("-----------------------------------");
				System.out.println("MedicineID"+"\t"+"MedicineQtyno"+"\t"+"Medicinename");
				li = al.listIterator();
				while((li.hasNext())&& (!flag)) {
					Mstore st = (Mstore)li.next();
					if(st.Medicinename.equals(Medn)) {
				         System.out.println(st+"\n it is deleted");
				         li.remove();
				         f=true;
					}
					if(f) {
						oos= new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("RECORD DELETED SUCESSFULLY");
					}
					/*else {
						System.out.println("record not found");
					}*/
                                     }
				  System.out.println("-----------------------------------");
				}/*else {
					System.out.println("there is no file..........");
				}*/
				 break;
			case 4:
				if(file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Mstore>)ois.readObject();
					ois.close();
				System.out.println("-----------------------------------");
				System.out.println("MedicineID"+"\t"+"MedicineQtyno"+"\t"+"Medicinename");
				li = al.listIterator();
				while(li.hasNext())
				System.out.println(li.next());
				System.out.println("-----------------------------------");
				}
				break;
			case 5:
				summery();
				break;
			default :
			   return;
				
			}
		}

	}
	static void summery() {
		System.out.println("-----ABC Medicals-----");
		System.out.println("1.Medicens");
		System.out.println ("2.Masks");
		System.out.println ("3.Syringes ");
		System.out.println ("4.Santizers");
		System.out.println ("5.Gloves");
		System.out.println ("6.Bandgaes");
		System.out.println ("7.Salines");
		System.out.println ("8.Surgical Equipments");
	}

	public static void main(String[] args) {
		log();
		
	}

}