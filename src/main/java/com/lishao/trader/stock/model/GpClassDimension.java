package com.lishao.trader.stock.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.lishao.system.core.base.BaseModel;

@TableName("GP_CLASS_DIMENSION")
public class GpClassDimension extends BaseModel{
	private String dimensionCode;

    private String dimensionName;

    private String closeReadClass;

    private String openReadClass;

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode == null ? null : dimensionCode.trim();
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName == null ? null : dimensionName.trim();
    }

    public String getCloseReadClass() {
        return closeReadClass;
    }

    public void setCloseReadClass(String closeReadClass) {
        this.closeReadClass = closeReadClass == null ? null : closeReadClass.trim();
    }

    public String getOpenReadClass() {
        return openReadClass;
    }

    public void setOpenReadClass(String openReadClass) {
        this.openReadClass = openReadClass == null ? null : openReadClass.trim();
    }
}