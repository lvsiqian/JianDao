package com.lvsiqianlib_banner;
//指示器
public interface Indicator {
    void setRadio(int radio);
    void setCount(int count);
    void setCurrent(int index);
    void setUnSelectColor(int color);
    void setSelect(int color);
    void setMargin(int margin);
    void setId(int id);
    int getId();

}
