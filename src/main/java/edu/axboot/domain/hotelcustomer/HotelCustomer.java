package edu.axboot.domain.hotelcustomer;

import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PMS_GUEST")
@Comment(value = "")
@Alias("hotelCustomer")
public class HotelCustomer extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "GUEST_NM", length = 100, nullable = false)
	@Comment(value = "투숙객 명")
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 100)
	@Comment(value = "투숙객 명 영어")
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	@Comment(value = "투숙객 전화")
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	@Comment(value = "이메일")
	private String email;

	@Column(name = "BRTH", length = 10)
	@Comment(value = "생일")
	private String brth;

	@Column(name = "GENDER", length = 20)
	@Comment(value = "성별")
	private String gender;

	@Column(name = "LANG_CD", length = 20)
	@Comment(value = "언어 CD")
	private String langCd;

	@Column(name = "RMK", length = 500)
	@Comment(value = "비고")
	private String rmk;


    @Override
    public Long getId() {
        return id;
    }
}