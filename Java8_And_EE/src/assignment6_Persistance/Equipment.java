package assignment6_Persistance;

//Import java packages
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//Annotation to show this is an entity class
@Entity

//Tell server entity class is mapped to name equipment
@Table(name = "EQUIPMENT")

//Create Equipment class
public class Equipment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Generate unique primary key, declare equipment fields
	@Id @GeneratedValue
	private Long equipmentId;
	private String brand; 
	private Integer price;
	private String color;
	
	//Create getters and setters
	public Long EquipmentId() {
		return equipmentId;
	}
	
	public void setEquipmentId(Long equipmentId){
		this.equipmentId= equipmentId;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public Integer getPrice(){
		return price;
	}
	
	public void setPrice(Integer price){
		this.price = price;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
}
