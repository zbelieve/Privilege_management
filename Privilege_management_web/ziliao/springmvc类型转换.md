# spring mvc�󶨲���֮����ת�������ַ�ʽ:

## 1.ʵ�����м����ڸ�ʽ��ע��

@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
private Date creationTime;

## 2.���Ա༭��

spring3.1֮ǰ

��Controller����ͨ��@InitBinder���

/**
     * ��controller���м���һ�����ݰ󶨴���
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        simpleDateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class , new CustomDateEditor(simpleDateFormat , true));
    }
   ��ע���Զ�������ת��������ʵ��PropertyEditor�ӿڻ��߼̳�PropertyEditorSupport��
дһ���� extends propertyEditorSupport��implements PropertyEditor��{
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

## 3. ����ת����Converter

ȫ������ת��

�ο�ǰ�ڿγ̴���