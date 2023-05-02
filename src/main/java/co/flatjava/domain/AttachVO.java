package co.flatjava.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@Alias("attach")
public class AttachVO extends AttachFileDTO{
	// AttachFileDTO와 VO 구조가 비슷하기 때문에 extends를 사용하여 추가되는 필드만 추가해서 사용하면됨.
	// 이때 @Data 어노테이션이 필요. @ToString(callSuper=true)는 공부 필요.
	private Long bno;
}
