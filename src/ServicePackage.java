/**
 * 嗖嗖移动卡套餐
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
	//显示套餐数据的抽象方法
	public abstract void showInfo();
	
}
