package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TranslateResult implements Serializable {

    private List<_TranslateResult> trans_result;

    private String from;

    private String to;



}

@Data
@ToString
class _TranslateResult{
    private String dst;

    private String src;
}


