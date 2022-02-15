package quoctuan.Employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="employee", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "TQT_UNIQUE")})
public class EmployeeEntity {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 100)
	private String name;
	@Column(length = 250)
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmployeeEntity(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public EmployeeEntity() {
		super();
	}
	
	
}
