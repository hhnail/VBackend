package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.SysTable;
import cn.hhnail.backend.mapper.SysTableMapper;
import cn.hhnail.backend.service.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTableServiceImpl implements SysTableService {

	@Autowired
	SysTableMapper sysTableMapper;


	@Override
	public List<SysTable> getTables() {
		List<SysTable> sysTables = sysTableMapper.selectList(null);
		return sysTables;
	}
}
