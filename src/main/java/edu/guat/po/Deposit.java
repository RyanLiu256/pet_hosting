package edu.guat.po;

import java.text.SimpleDateFormat;
import java.util.*;

public class Deposit {

    private Integer id;
    private Integer userid;
    private String username;
    private String petname;
    private Date startTime;
    private String startTimeStr;
    private Date endTime;
    private String endTimeStr;
    private Integer dayCount;
    private Double totalPrice;
    private Breeds breeds;
    private PetFood petFood;
    private Keeper keeper;
    private Map<Integer,Service> services = new HashMap<>();
    private Integer state;
    private String stateStr;
    private Integer ispay;
    private String ispayStr;
    private Double age;
    private Double weight;
    private Integer isprove;
    private String isproveStr;
    private String reason;
    private Comment comment;

    public Map<Integer,Service> getServices() {
        return services;
    }

    public void setServices(Map<Integer,Service> services) {
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStr() {
        if(startTime!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            startTimeStr = sdf.format(startTime);
        }
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        if(endTime != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            endTimeStr = sdf.format(endTime);
        }
        return endTimeStr;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Breeds getBreeds() {
        return breeds;
    }

    public void setBreeds(Breeds breeds) {
        this.breeds = breeds;
    }

    public PetFood getPetFood() {
        return petFood;
    }

    public void setPetFood(PetFood petFood) {
        this.petFood = petFood;
    }

    public Keeper getKeeper() {
        return keeper;
    }

    public void setKeeper(Keeper keeper) {
        this.keeper = keeper;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateStr() {
        if (state != null) {
            if (state == 1) {
                stateStr = "已通过";
            } else if (state == 2) {
                stateStr = "未通过";
            } else if (state == 3) {
                stateStr = "已结束";
            }else if (state == 4) {
                stateStr = "审核中";
            }
        }
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Integer getIspay() {
        return ispay;
    }

    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    public String getIspayStr() {
        if (ispay!=null){
            if (ispay==1){
                ispayStr = "已付款";
            }else if(ispay==2){
                ispayStr = "未付款";
            }
        }
        return ispayStr;
    }

    public void setIspayStr(String ispayStr) {
        this.ispayStr = ispayStr;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getIsprove() {
        return isprove;
    }

    public void setIsprove(Integer isprove) {
        this.isprove = isprove;
    }

    public String getIsproveStr() {
        if (isprove!=null){
            if (isprove == 0){
                isproveStr = "否";
            }else if (isprove == 1){
                isproveStr = "是";
            }
        }
        return isproveStr;
    }

    public void setIsproveStr(String isproveStr) {
        this.isproveStr = isproveStr;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", petname='" + petname + '\'' +
                ", startTime=" + startTime +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", endTime=" + endTime +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", dayCount=" + dayCount +
                ", totalPrice=" + totalPrice +
                ", breeds=" + breeds +
                ", petFood=" + petFood +
                ", keeper=" + keeper +
                ", services=" + services +
                ", state=" + state +
                ", stateStr='" + stateStr + '\'' +
                ", ispay=" + ispay +
                ", ispayStr='" + ispayStr + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", isprove=" + isprove +
                ", isproveStr='" + isproveStr + '\'' +
                ", reason='" + reason + '\'' +
                ", comment=" + comment +
                '}';
    }
}
