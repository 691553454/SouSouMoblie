/**
 * ������Ϣ��
 * @author Administrator
 *
 */
public class ConsumInfo 
{
	//����
	private String cardNumber;  
	//�������� ͨ�������Ż�������
	private String type;      
	//���ѵ����ݴ�С
	private int consumDate;
	
	/**
	 * �޲�
	 */
	public ConsumInfo()
	{
		super();
	}

	/**
	 * �в�
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
