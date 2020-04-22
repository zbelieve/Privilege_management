# spring mvc绑定参数之类型转换有三种方式:

## 1.实体类中加日期格式化注解

@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
private Date creationTime;

## 2.属性编辑器

spring3.1之前

在Controller类中通过@InitBinder完成

/**
     * 在controller层中加入一段数据绑定代码
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        simpleDateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class , new CustomDateEditor(simpleDateFormat , true));
    }
   备注：自定义类型转换器必须实现PropertyEditor接口或者继承PropertyEditorSupport类
写一个类 extends propertyEditorSupport（implements PropertyEditor）{
     public void setAsText(String text){
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy -MM-dd hh:mm");
        Date date = simpleDateFormat.parse(text);
        this.setValue(date);
     }
     public String getAsTest(){
      Date date = (Date)this.getValue(); 
      return this.dateFormat.format(date);
     }
}

## 3. 类型转换器Converter

全局类型转换

参考前期课程代码