package com.imooc.mianshi.utils;

public enum CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩"),
    ;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    CountryEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static CountryEnum forEach(int index)
    {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(value.getCode().equals(index))
                return value;
        }
        return null;
    }
}
