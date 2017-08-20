/**
 * 手机卡类
 * 定义手机卡的各种属性
 * @author Administrator
 *
 */
public class MoblieCard 
{
	//卡号
	private String cardNumber;
	//用户名
	private String userName;
	//密码
	private String passWord;
	//所属套餐
	private ServicePackage serPackage;
	//当月消费金额
	private double consumAmount;
	//账户余额
	private double money;
	//实际通话时长
	private  int realTalktime;
	//实际发送短信条数
	private int realSMSCount;
	//实际上网流量
	private int realFlow;
	
	/**
	 * 构造方法
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
	 *输出手机卡信息
	 */
	public void showMeg()
	{
		System.out.println("卡号:"+this.cardNumber+"   用户名 :  "+this.userName+"  当前余额:  "+this.money+"元");
		this.serPackage.showInfo();
	}
	
	
	
}
