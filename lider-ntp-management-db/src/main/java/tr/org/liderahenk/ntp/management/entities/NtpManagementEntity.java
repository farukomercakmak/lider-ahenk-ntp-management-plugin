package tr.org.liderahenk.ntp.management.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "P_NtpManagement")
public class NtpManagementEntity {
	
	// This class is auto-generated.
	// Please follow the (table and column) naming conventions.
	
	@Id
	@GeneratedValue
	@Column(name = "${entity}_ID")
	private Long id;
	
	// Other database columns...
	
	// Getter setters
	
}
