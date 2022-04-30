package cn.hhnail.backend.vo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class SysTableRespVO {

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String name;

}
