/**
 * �ֻ�����
 * �����ֻ����ĸ�������
 * @author Administrator
 *
 */
public class MoblieCard 
{
	//����
	private String cardNumber;
	//�û���
	private String userName;
	//����
	private String passWord;
	//�����ײ�
	private ServicePackage serPackage;
	//�������ѽ��
	private double consumAmount;
	//�˻����
	private double money;
	//ʵ��ͨ��ʱ��
	private  int realTalktime;
	//ʵ�ʷ��Ͷ�������
	private int realSMSCount;
	//ʵ����������
	private int realFlow;
	
	/**
	 * ���췽��
	 */
	public MoblieCard( String userName, String passWord,String cardNumber,ServicePackage serPackage,double consumAmount, double money ) 
	{
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.cardNumber = cardNumber;
		this.serPackage = serPackage;
		this.consumAmount = consumAmount;
		this.money = money;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public ServicePackage getSerPackage()
	{
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage)
	{
		this.serPackage = serPackage;
	}

	public double getConsumAmount()
	{
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) 
	{
		this.consumAmount = consumAmount;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public int getRealSMSCount()
	{
		return realSMSCount;
	}

	public int getRealTalktime() {
		return realTalktime;
	}

	public void setRealTalktime(int realTalktime) {
		this.realTalktime = realTalktime;
	}

	public void setRealSMSCount(int realSMSCount)
	{
		this.realSMSCount = realSMSCount;
	}

	public int getRealFlow()
	{
		return realFlow;
	}

	public void setRealFlow(int realFlow)
	{
		this.realFlow = realFlow;
	}
	
	/**
	 *����ֻ�����Ϣ
	 */
	public void showMeg()
	{
		System.out.println("����:"+this.cardNumber+"   �û��� :  "+this.userName+"  ��ǰ���:  "+this.money+"Ԫ");
		this.serPackage.showInfo();
	}
	
	
	
}
