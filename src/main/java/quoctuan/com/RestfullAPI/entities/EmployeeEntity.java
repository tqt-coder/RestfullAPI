package quoctuan.com.RestfullAPI.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name="name", length = 100, nullable = false)
	private String name;
	
	@Column(name="password", length = 150, nullable = false)
	private String password;


}
