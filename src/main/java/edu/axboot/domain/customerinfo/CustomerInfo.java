package edu.axboot.domain.customerinfo;

import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.SimpleJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Entity
@Table(name = "PMS_CHK_MEMO")
public class CustomerInfo extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RSV_NUM", length = 20)
	private String rsvNum;

	@Column(name = "SNO", precision = 10, nullable = false)
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	private String memoCn;

	@Column(name = "MEMO_DTTI", nullable = false)
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	private String delYn;

    @Override
    public Long getId() {
        return id;
    }

    @Builder
	public CustomerInfo(Long id, String rsvNum, Integer sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn, boolean isCreated, boolean isModified, boolean isDeleted) {
    	this.id = id;
		this.rsvNum = rsvNum;
		this.sno = sno;
		this.memoCn = memoCn;
		this.memoDtti = memoDtti;
		this.memoMan = memoMan;
		this.delYn = delYn;
		this.__created__ = isCreated;
		this.__modified__ = isModified;
		this.__deleted__ = isDeleted;
	}


}