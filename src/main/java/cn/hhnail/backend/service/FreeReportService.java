package cn.hhnail.backend.service;

import cn.hhnail.backend.vo.request.FreeReportReqVO;
import cn.hhnail.backend.vo.response.FreeReportRespVO;

import java.util.List;
import java.util.Map;

public interface FreeReportService {

    void saveFreeReport(FreeReportReqVO vo);

    FreeReportRespVO getFreeReport(String id);

    List<FreeReportRespVO> getFreeReportList();
}
