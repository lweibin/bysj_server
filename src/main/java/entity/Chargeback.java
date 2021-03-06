package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CHARGEBACK")
public class Chargeback extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", length = 4)
	private int id;
	@Column(name = "SHEET_ID")
	private int sheetId;
	@Column(name = "REASON", nullable = false)
	private int reason;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "STATE")
	private int state;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chargebackId")
	@JoinColumn(name = "ID", referencedColumnName = "CHARGEBACK_ID")
	List<ChargebackImge> imgeList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSheetId() {
		return sheetId;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ChargebackImge> getImgeList() {
		return imgeList;
	}

	public void setImgeList(List<ChargebackImge> imgeList) {
		this.imgeList = imgeList;
	}

}
