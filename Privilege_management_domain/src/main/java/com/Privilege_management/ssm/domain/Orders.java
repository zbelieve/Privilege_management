package com.Privilege_management.ssm.domain;

import com.Privilege_management.ssm.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Orders {
    private String id;
    private String orderNum;
    //这边是直接从数据库中读数据，不是页面传入数据，所以不需要转换，不需要@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date orderTime;
    //像这种str的是不写入数据库中的，只是作为呈现的时候呈现出来，所以在这儿要做一个转换
    private String orderTimeStr;
    private int orderStatus;
    //当前状态的字符串描述
    private String orderStatusStr;
    private int peopleCount;
    //旅游产品就只有一个所以不是list
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;

    public String getOrderStatusStr() {
        //订单状态（0未支付1已支付）
        if(orderStatus==0){
            orderStatusStr="未支付";
        }else if (orderStatus==1){
            orderStatusStr="已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if (orderTime!=null){
            //指定一个时间的样式，这样在获得了int的状态描述以后就可以获得一个相应的字符串描述,将页面上的转换为一个字符串形式
            orderTimeStr= DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        if(payType==0){
            payTypeStr="支付宝";
        }else if(payType==1){
            payTypeStr="微信";
        }else if(payType==2){
            payTypeStr="其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
