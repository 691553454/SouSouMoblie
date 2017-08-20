/**
 * 消费信息类
 * @author Administrator
 *
 */
public class ConsumInfo 
{
	//卡号
	private String cardNumber;  
	//消费类型 通话发短信或者上网
	private String type;      
	//消费的数据大小
	private int consumDate;
	
	/**
	 * 无参
	 */
	public ConsumInfo()
	{
		super();
	}

	/**
	 * 有参
	 */
	public ConsumInfo(String cardNumber, String type, int consumDate) 
	{
		super();
		this.cardNumber = cardNumber;
		this.type = type;
		this.consumDate = consumDate;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getConsumDate()
	{
		return consumDate;
	}

	public void setConsumDate(int consumDate)
	{
		this.consumDate = consumDate;
	}
	
	
	
	
}
