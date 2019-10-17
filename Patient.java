package Assignment9;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Patient implements Comparable<Patient> {
	private  int id;
	private  String name;
	private  String email;
	private  String address;
	private  String phone;
	private DateFormat day=new SimpleDateFormat("dd/MM/yyyy");
	private String date;
	private  ArrayList<Integer> ids=new ArrayList<>();
	static  ArrayList<String> emails=new ArrayList<>();
	public Patient(int id, String name, String email, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		try {
			if(emails.contains(email)) {throw new Exception();}
			else {this.email=email;}
			}
			catch(Exception e) {
				System.err.println("Patient with this email already exists");
				return;
			}
		this.address = address;
		this.phone = phone;
		emails.add(email);
		setDate();
	}
	public void setDate() {
		this.date = day.format(new Date());
	}
	public String getDate() {
		return this.date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		try {
			if(emails.contains(email)) {throw new Exception();}
			else {this.email=email;}
			}
			catch(Exception e) {
				System.err.println("Patient with this email already exists");
			}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<Integer> getIds() {
		return ids;
	}
	public void setIds(int pid) {
		this.ids.add(pid);
	}
	@Override
	public int compareTo(Patient o) {
		String[] id=this.getDate().split("/");
		String[] fd=o.getDate().split("/");
		Date idate=new Date(Integer.parseInt(id[2]),Integer.parseInt(id[1]),Integer.parseInt(id[0]));
		Date fdate=new Date(Integer.parseInt(fd[2]),Integer.parseInt(fd[1]),Integer.parseInt(fd[0]));
		int iday=idate.getDate();
		int imonth=idate.getMonth();
		int iyear=idate.getYear();
		int fday=fdate.getDate();
		int fmonth=fdate.getMonth();
		int fyear=fdate.getYear();
		if(iyear-fyear>0) {return 1;}
		else if(iyear-fyear<0) {return -1;}
		else {
			if(imonth-fmonth>0) {return 1;}
			else if(imonth-fmonth<0) {return -1;}
			else {
				if(iday-fday>0) {return 1;}
				if(iday-fday<0) {return -1;}
				else {return 1;}
			}
		}
	}
}
