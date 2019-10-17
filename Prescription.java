package Assignment9;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Prescription {
 private int id;
 private String desc;
 private ArrayList<String> meds;
 private String type;
 private DateFormat day=new SimpleDateFormat("dd/MM/yyyy");
 private String date;
 private int personid;
public int getPersonid() {
	return personid;
}
public void setPersonid(int personid) {
	this.personid = personid;
}
public Prescription(int id,ArrayList<String> meds, String type, String desc) {
	this.id = id;
	this.type = type;
	this.desc = desc;
	this.meds = meds;
	setDate();
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public ArrayList<String> getMeds() {
	return meds;
}
public void setMeds(String med) {
	try {
	if(this.meds.contains(med)) {throw new Exception();}
	else {this.meds.add(med);}
	}
	catch(Exception e) {
		System.err.println("THis meicine is already prescribed");
	}
}
public void upMeds(ArrayList<String> bb) {
	this.meds=new ArrayList<>(bb);
}
}
