/**
 * ���ƶ����ײ�
 * @author Administrator
 *
 */
public abstract class ServicePackage
{
	protected double price;

	public double getPrice() 
	{
		return price;
	}
 
	public ServicePackage() 
	{
		super();
	}
	public ServicePackage(double price)
	{
		super();
		this.price = price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
	//��ʾ�ײ����ݵĳ��󷽷�
	public abstract void showInfo();
	
}
