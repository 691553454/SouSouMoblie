
/**
 * ����ʹ�ó���
 * @author Administrator
 *
 */
public class Scene 
{
	private String type;               //��������
	private int date;						//��������
	private String description;//��������
	public Scene() 
	{
		super();
	}
	public Scene(String type, int date, String description)
	{
		super();
		this.type = type;
		this.date = date;
		this.description = description;
	}
	public String getType() 
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public int getDate()
	{
		return date;
	}
	public void setDate(int date)
{
		this.date = date;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
}
