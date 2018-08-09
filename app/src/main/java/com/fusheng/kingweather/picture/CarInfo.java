package com.fusheng.kingweather.picture;

import java.io.Serializable;

/**
 * author  LiXiaoWei
 * date  2017/12/5.
 * desc:车辆信息
 */

public class CarInfo implements Serializable {
    /**
     * id：车辆id
     * code:车牌号码
     * brandName:品牌
     * lineName：车系
     * modelName：车型
     * displacement：排量
     * produceYear：年款
     * name：车主姓名
     * phone:车主手机号
     * vinCode：车架号
     *registerDate：登记日期
     * url:车牌图片url
     */
    private int id;
    private String code;
    private String brandName;
    private String lineName;
    private String modelName;
    private String displacement;
    private String produceYear;
    private String productionYear;
    private String name;
    private String phone;
    private String vinCode;
    private String registerDate;
    private String url;

    private String recentMaintainDate ;
    private String nextMaintainDate ;
    private String mileage;
    private String maintainTime;


    private String brandId;
    private String lineId;

    private String createTime;
    private int count;
    private String model;
    private int status;

    public String getProductionYear() {
        return productionYear;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public void setProduceYear(String produceYear) {
        this.produceYear = produceYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getUrl() {
        return url;
    }

    public String getCode() {
        return code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getModelName() {
        return modelName;
    }

    public int getCount() {
        return count;
    }

    public String getProduceYear() {
        return produceYear;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getVinCode() {
        return vinCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getLineName() {
        return lineName;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getModel() {
        return model;
    }

    public int getId() {
        return id;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getLineId() {
        return lineId;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getRecentMaintainDate() {
        return recentMaintainDate;
    }

    public void setRecentMaintainDate(String recentMaintainDate) {
        this.recentMaintainDate = recentMaintainDate;
    }

    public String getNextMaintainDate() {
        return nextMaintainDate;
    }

    public void setNextMaintainDate(String nextMaintainDate) {
        this.nextMaintainDate = nextMaintainDate;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }
}
