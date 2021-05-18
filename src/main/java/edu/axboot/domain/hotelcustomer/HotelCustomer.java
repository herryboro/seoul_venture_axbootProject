package edu.axboot.domain.hotelcustomer;

import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Entity
@Table(name = "PMS_GUEST")
public class HotelCustomer extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "GUEST_NM", length = 100, nullable = false)
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 100)
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "BRTH", length = 10)
	private String brth;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "LANG_CD", length = 20)
	private String langCd;

	@Column(name = "RMK", length = 500)
	private String rmk;

    @Override
    public Long getId() {
        return id;
    }

    @Builder
	public HotelCustomer(Long id, String guestNm, String guestNmEng, String guestTel, String email, String brth, String gender, String langCd, String rmk) {
    	this.id = id;
		this.guestNm = guestNm;
		this.guestNmEng = guestNmEng;
		this.guestTel = guestTel;
		this.email = email;
		this.brth = brth;
		this.gender = gender;
		this.langCd = langCd;
		this.rmk = rmk;
	}
}