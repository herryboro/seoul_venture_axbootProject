package edu.axboot.domain.herryboroproject;

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
@Entity
@NoArgsConstructor
@Table(name = "PMS_ROOM")
public class HerryboroHotel extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ROOM_NUM", length = 10, nullable = false)
	private String roomNum;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	private String roomTypCd;

	@Column(name = "DND_YN", length = 1, nullable = false)
	private String dndYn;

	@Column(name = "EB_YN", length = 1, nullable = false)
	private String ebYn;

	@Column(name = "ROOM_STTUS_CD", length = 20)
	private String roomSttusCd;

	@Column(name = "CLN_STTUS_CD", length = 20)
	private String clnSttusCd;

	@Column(name = "SVC_STTUS_CD", length = 20)
	private String svcSttusCd;

	@Override
	public Long getId() {
		return id;
	}

	@Builder
	public HerryboroHotel(Long id, String roomNum, String roomTypCd, String dndYn, String ebYn, String roomSttusCd, String clnSttusCd, String svcSttusCd) {
		this.id = id;
		this.roomNum = roomNum;
		this.roomTypCd = roomTypCd;
		this.dndYn = dndYn;
		this.ebYn = ebYn;
		this.roomSttusCd = roomSttusCd;
		this.clnSttusCd = clnSttusCd;
		this.svcSttusCd = svcSttusCd;
	}
}