package edu.axboot.domain.reservRegister;

import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@NoArgsConstructor
@Table(name = "PMS_CHK")
public class ReservRegister extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RSV_DT", length = 10, nullable = false)
	private String rsvDt;

	@Column(name = "SNO", precision = 10, nullable = false)
	private Integer sno;

	@Column(name = "RSV_NUM", length = 20, nullable = false)
	private String rsvNum;

	@Column(name = "GUEST_ID", precision = 19)
	private Long guestId;

	@Column(name = "GUEST_NM", length = 100)
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 200)
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "LANG_CD", length = 20)
	private String langCd;

	@Column(name = "ARR_DT", length = 10, nullable = false)
	private String arrDt;

	@Column(name = "ARR_TIME", length = 8)
	private String arrTime;

	@Column(name = "DEP_DT", length = 10, nullable = false)
	private String depDt;

	@Column(name = "DEP_TIME", length = 8)
	private String depTime;

	@Column(name = "NIGHT_CNT", precision = 10, nullable = false)
	private Integer nightCnt;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	private String roomTypCd;

	@Column(name = "ROOM_NUM", length = 10)
	private String roomNum;

	@Column(name = "ADULT_CNT", precision = 10, nullable = false)
	private Integer adultCnt;

	@Column(name = "CHLD_CNT", precision = 10, nullable = false)
	private Integer chldCnt;

	@Column(name = "SALE_TYP_CD", length = 20, nullable = false)
	private String saleTypCd;

	@Column(name = "STTUS_CD", length = 20, nullable = false)
	private String sttusCd;

	@Column(name = "SRC_CD", length = 20, nullable = false)
	private String srcCd;

	@Column(name = "BRTH", length = 10)
	private String brth;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "PAY_CD", length = 20)
	private String payCd;

	@Column(name = "ADVN_YN", length = 1, nullable = false)
	private String advnYn;

	@Column(name = "SALE_PRC", precision = 18, scale = 0)
	private BigDecimal salePrc;

	@Column(name = "SVC_PRC", precision = 18, scale = 0)
	private BigDecimal svcPrc;

    @Override
    public Long getId() {
        return id;
    }

    @Builder
	public ReservRegister(Long guestId, String guestNm, String guestNmEng,
						  String guestTel, String email, String langCd, String arrDt, String arrTime, String depDt, String depTime,
						  Integer nightCnt, String roomTypCd, String roomNum, Integer adultCnt, Integer chldCnt, String saleTypCd,
						  String sttusCd, String srcCd, String brth, String gender, String payCd, String advnYn, BigDecimal salePrc,
						  BigDecimal svcPrc) {

		// 예약 일자
		LocalDate date = LocalDate.now(); //오늘 날짜 LocalDate 객체 생성
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = date.format(dateTimeFormatter); //LocalDate 객체를 String 객체로 바꿈

		// 일렬번호 생성
		int serialNum = (int) (Math.random() * 100000);

		// 예약 번호
		int reservNum = 1;
		String convertNum = String.valueOf(reservNum++);
		reservNum = Integer.parseInt(convertNum);

		this.rsvDt = today;
		this.sno = serialNum;
		this.rsvNum = convertNum;
		this.guestId = guestId;
		this.guestNm = guestNm;
		this.guestNmEng = guestNmEng;
		this.guestTel = guestTel;
		this.email = email;
		this.langCd = langCd;
		this.arrDt = arrDt;
		this.arrTime = arrTime;
		this.depDt = depDt;
		this.depTime = depTime;
		this.nightCnt = nightCnt;
		this.roomTypCd = roomTypCd;
		this.roomNum = roomNum;
		this.adultCnt = adultCnt;
		this.chldCnt = chldCnt;
		this.saleTypCd = saleTypCd;
		this.sttusCd = sttusCd;
		this.srcCd = srcCd;
		this.brth = brth;
		this.gender = gender;
		this.payCd = payCd;
		this.advnYn = advnYn;
		this.salePrc = salePrc;
		this.svcPrc = svcPrc;
	}
}