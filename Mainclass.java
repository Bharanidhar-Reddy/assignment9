package Assignment9;
import java.util.*;
import java.util.regex.Pattern;
public class Mainclass {
	int pid=0;
	int presid=1100;
	List<Patient> patients=new ArrayList<>();
	List<Prescription> prescriptions=new ArrayList<>();
	List<String> emails=new ArrayList<>();
	class NameException extends Exception {
		public NameException(String message) {
			super(message);
		}
	}
	class EmailException extends Exception {
		public EmailException(String message) {
			super(message);
		}
	}
	class PhoneException extends Exception {
		public PhoneException(String message) {
			super(message);
		}
	}
	public boolean validatename(String name) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
		try {
		if(p.matcher(name).matches()) {return false;}
		if (!(p.matcher(name).matches())) {
			throw new NameException("Name Should not contain numbers or special characters or be null");
		}
		else {return false;}
		}
		catch(NameException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	public boolean validatephone(String ph) {
		Pattern p = Pattern.compile("^\\d{10}$");
		try {
		if(p.matcher(ph).matches()) {return false;}
		if (!(p.matcher(ph).matches())) {
			throw new PhoneException("Phone Number must be all digits and of length 10");
		}
		else {return false;}
		}
		catch(PhoneException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	public boolean validateemail(String email) {
		Pattern p = Pattern.compile("^[a-zA-Z][\\w]+@[\\w]+[.][a-zA-Z]+");
		try {
		if((p.matcher(email).matches())) {return false;}
		if (!(p.matcher(email).matches())) {
			throw new EmailException("Email is not in correct format");
		}
		else {return false;}
		}
		catch(EmailException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	private void valid() {
		ArrayList<Patient> pp=new ArrayList<>();
		for(Patient p:patients) {
			if(p.getEmail()==null) {pp.add(p);}
		}
		patients.removeAll(pp);
	}
	private void addnew(String name, String email, String address, String phone) {
		Patient p=new Patient(pid,name,email,address,phone);
		pid++;
		patients.add(p);
		valid();
	}
	private void viewpatient(Patient p) {
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.printf("%70s\n","DETAILS OF PATIENT");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.printf("Patient ID : %-30s Patient Name : %-1s\n",String.valueOf(p.getId()),p.getName());
		System.out.printf("Patient Email : %-27s Patient Phone : %-1s\n",p.getEmail(),p.getPhone());
		System.out.println("Patient Address : "+p.getAddress());
		System.out.printf("Date and time : %-18s\n",p.getDate());
		System.out.println("----------------------------------------------------------------------------------------------------");
	}
	private void viewprescriptions(ArrayList<Integer> arr) {
		System.out.printf("%70s\n","Prescription OF PATIENT");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int x:arr) {
			for(Prescription pre:prescriptions) {
				if(pre.getId()==x) {
					System.out.printf("Prescription ID : %-15s\n",String.valueOf(pre.getId()));
					System.out.println("Medicines : ");
					for(String med:pre.getMeds()) {
						System.out.printf("%-20s|| %-20s ||\n","",med);
					}
					System.out.printf("Description : %-20s\n",pre.getDesc());
					System.out.printf("Type : %-27s\n",pre.getType());
					System.out.printf("Date and time : %-18s\n",pre.getDate());
					System.out.println("----------------------------------------------------------------------------------------------------");
				}
			}
		}
	}
	private void addprescription(int index, ArrayList<String> meds, String desc, String type) {
		for(Patient p:patients) {
			if(p.getId()==index) {p.setIds(presid);break;}
		}
		Prescription pre=new Prescription(presid,meds,type,desc);
		pre.setPersonid(index);
		prescriptions.add(pre);
		presid++;
	}
	private void removepatient(Patient p) {
		ArrayList<Integer> remove=new ArrayList<>(p.getIds());
		ArrayList<Prescription> pp=new ArrayList<>();
		patients.remove(p);
		for(int remid:remove) {
			for(Prescription pre:prescriptions) {
				if(pre.getId()==remid) {pp.add(pre);}
			}
		}
		prescriptions.removeAll(pp);
		System.out.println("Patient removed Successfully!!!");
	}
	private void removeprescription(int id) {
		ArrayList<Prescription> pp=new ArrayList<>();
		for(Prescription pres:prescriptions) {
			if(pres.getId()==id) {pp.add(pres);}
		}
		prescriptions.removeAll(pp);
		System.out.println("Prescription removed Successfully!!!");
	}
	private void updatepatient(int upp) {
		Scanner b=new Scanner(System.in);
		System.out.printf("%70s\n","ENTER DETAILS OF PATIENT");
		System.out.println("Name of patient : ");
		String name=b.nextLine();
		while(validatename(name)) {
			System.out.println("Please enter valid Name of patient : ");
			name=b.nextLine();
		}
		System.out.println("Email of patient : ");
		String email=b.nextLine();
		while(validateemail(email)) {
			System.out.println("Please enter valid Email of patient : ");
			email=b.nextLine();
		}
		System.out.println("Address of patient : ");
		String address=b.nextLine();
		System.out.println("Phone Number of Patient : ");
		String phone=b.nextLine();
		while(validatephone(phone)) {
			System.out.println("Please enter valid Phone Number : ");
			phone=b.nextLine();
		}
		for(Patient p:patients) {
			if(p.getId()==upp) {
				p.setName(name);
				p.setEmail(email);
				p.setAddress(address);
				p.setPhone(phone);
			}
		}valid();
		System.out.println("Details of patient updated successfully");
	}
	private void updateprescription(int uppr) {
		Scanner c=new Scanner(System.in);
		ArrayList<String> meds = new ArrayList<>();
		System.out.println("Enter Medicines with ',' inbetween");
		String a[]=c.nextLine().split(",");
		for(String med:a) {meds.add(med);}
		System.out.println("Description of Medicines : ");
		String desc=c.nextLine();
		System.out.println("Type of Medicines : ");
		String type=c.nextLine();
		for(Prescription prescr:prescriptions) {
			if(prescr.getId()==uppr) {
				prescr.upMeds(meds);
				prescr.setType(type);
				prescr.setDesc(desc);
			}
		}
		System.out.println("Details of prescription updated successfully");
	}
	public static void main(String[] args) {
		Mainclass m=new Mainclass();
		Scanner sc=new Scanner(System.in);
		int input=-1;
		do {
			System.out.println("\n");
			System.out.println("Press 1 to Enter new patient details");
			System.out.println("Press 2 to add a prescription to a patient");
			System.out.println("Press 3 to get data of patients");
			System.out.println("Press 4 to update patient/prescription details");
			System.out.println("Press 5 to delete patient/prescription details");
			System.out.println("Press 6 to search the data");
			System.out.println("Press 0 to exit (!!!ALL DATA WILL BE LOST!!!)");
			System.out.println("\n");
			System.out.println("Please enter your option");
			input=sc.nextInt();
			sc.nextLine();
			switch(input) {
			case 1: System.out.printf("%70s\n","ENTER DETAILS OF PATIENT");
					System.out.println("Name of patient : ");
					String name=sc.nextLine();
					while(m.validatename(name)) {
						System.out.println("Please enter valid Name of patient : ");
						name=sc.nextLine();
					}
					System.out.println("Email of patient : ");
					String email=sc.nextLine();
					while(m.validateemail(email)) {
						System.out.println("Please enter valid Email of patient : ");
						email=sc.nextLine();
					}
					System.out.println("Address of patient : ");
					String address=sc.nextLine();
					System.out.println("Phone Number of Patient : ");
					String phone=sc.nextLine();
					while(m.validatephone(phone)) {
						System.out.println("Please enter valid Phone Number : ");
						phone=sc.nextLine();
					}
					m.addnew(name,email,address,phone);
					break;
			case 2: System.out.println("Enter ID of the patient to add Prescription");
					int id=sc.nextInt();
					boolean contains=false;
					sc.nextLine();
					int index=0;
					outerloop:
					for(Patient pt:m.patients) {
						if(pt.getId()==id) {
							index=id;
							contains=true;
							break outerloop;
						}	
					}
					if(!contains) {System.err.println("Patient with that id do not exist . Please add Patient or check the id.");}
					if(contains) {
						ArrayList<String> meds = new ArrayList<>();
						System.out.println("Enter Medicines with ',' inbetween");
						String a[]=sc.nextLine().split(",");
						for(String med:a) {meds.add(med);}
						System.out.println("Description of Medicines : ");
						String desc=sc.nextLine();
						System.out.println("Type of Medicines : ");
						String type=sc.nextLine();
						m.addprescription(index,meds,desc,type);
					}
					break;
			case 3: System.out.printf("%70s\n","PATIENTS DATA");
					for(Patient pat:m.patients) {
						m.viewpatient(pat);
						if(pat.getIds().size()>0) {
						m.viewprescriptions(pat.getIds());}
					}
					break;
			case 4: System.out.println("Press 1. To update person details");
					System.out.println("Press 2. To update prescription details");
					System.out.println("Enter your option");
					int in=sc.nextInt();
					switch(in){
					case 1: System.out.println("Enter Id of person to update");
							int upp=sc.nextInt();
							sc.nextLine();
							m.updatepatient(upp);
							break;
					case 2: System.out.println("Enter Id of prescription to update");	
							int remper=sc.nextInt();
							sc.nextLine();
							m.updateprescription(remper);
							break;
					}
					
					break;
			case 5: System.out.println("Press 1. To delete person details");
					System.out.println("Press 2. To delete prescription details");
					System.out.println("Enter your option");
					int inremove=sc.nextInt();
					switch(inremove){
					case 1: System.out.println("Enter Id of person to delete");
							int remp=sc.nextInt();
							for(Patient pati:m.patients) {
								if(pati.getId()==remp) {m.removepatient(pati);}
							}
							break;
					case 2: System.out.println("Enter Id of prescription to delete");	
							int remper=sc.nextInt();
							m.removeprescription(remper);
							break;
					}
					break;
			case 6 :System.out.println("Press 1. To Search by person name");
					System.out.println("Press 2. To Search by medicine");
					System.out.println("Press 3. To Search by Date");
					System.out.println("Enter your option");
					int sear=sc.nextInt();
					sc.nextLine();
					switch(sear){
					case 1: System.out.println("Enter name of person to search");
							String names=sc.nextLine();
							boolean there=true;
							for(Patient pati:m.patients) {
								if(pati.getName().equals(names)) {m.viewpatient(pati);there=false;}
							}
							if(there) {System.out.println("There is no data of patient with that name");}
							break;
					case 2: System.out.println("Enter medicine name to search patients using it.");	
							String medi=sc.nextLine();
							boolean the=true;
							for(Prescription bkb:m.prescriptions) {
								if(bkb.getMeds().contains(medi)) {
									the=false;
									for(Patient mkb:m.patients) {
										if(bkb.getPersonid()==mkb.getId()) {m.viewpatient(mkb);}
									}
								}
							}
							if(the) {System.out.println("There is no patient using the given Medicine");}
							break;
							
					case 3: System.out.println("Enter Beginning date in DD/MM/YY Format");
							String[] sd=sc.nextLine().split("/");
							ArrayList<Patient> sortedp=new ArrayList<>();
							Date sdate=new Date(Integer.parseInt(sd[2]),Integer.parseInt(sd[1])-1,Integer.parseInt(sd[0]));
							System.out.println("Enter Ending date in DD/MM/YY Format");
							String[] ed=sc.nextLine().split("/");
							boolean found=false;
							Date edate=new Date(Integer.parseInt(ed[2]),Integer.parseInt(ed[1])-1,Integer.parseInt(ed[0]));
							for(Patient pp:m.patients) {
								String[] pd=pp.getDate().split("/");
								Date pdate=new Date(Integer.parseInt(pd[2]),Integer.parseInt(pd[1])-1,Integer.parseInt(pd[0]));
								if(sdate.before(pdate)&&pdate.before(edate)) {sortedp.add(pp);found=true;}
							}
							if(!found) {System.out.println("No Data is present in between thr given dates");}
							Collections.sort(sortedp);
							for(Patient sp:sortedp) {m.viewpatient(sp);}
					}
					break;
			case 0 : input=0; 
			default: System.out.println("Please enter valid option");
			}
			}while(input!=0);

	}
}
