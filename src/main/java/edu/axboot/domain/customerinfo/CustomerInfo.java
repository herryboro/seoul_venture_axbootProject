package edu.axboot.domain.customerinfo;

import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PMS_CHK_MEMO")
@Comment(value = "")
@Alias("customerInfo")
public class CustomerInfo extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	private Long id;

	@Column(name = "RSV_NUM", length = 20)
	@Comment(value = "예약번호")
	private String rsvNum;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	@Comment(value = "메모 내용")
	private String memoCn;

	@Column(name = "MEMO_DTTI", nullable = false)
	@Comment(value = "메모 일시")
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	@Comment(value = "메모 자")
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	@Comment(value = "삭제 여부")
	private String delYn;

	@Transient
	private static int last_num; // 일렬번호

	@Transient
	private int serialNum;

    @Override
    public Long getId() {
        return id;
    }

    @Builder
	public CustomerInfo(String rsvNum, String memoCn, Timestamp memoDtti, String delYn) {

		// 일렬번호 생성
		if(last_num == 0) {
			last_num = 100;
			this.sno = last_num;
		} else {
			this.sno = this.last_num++;
		}

		// memoMan
		String memoMan = "작성자";

		this.rsvNum = rsvNum;
		this.memoCn = memoCn;
		this.memoDtti = memoDtti;
		this.memoMan = memoMan;
		this.delYn = delYn;
	}
}