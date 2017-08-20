
/**
 * 消费使用场景
 * @author Administrator
 *
 */
public class Scene 
{
	private String type;               //消费类型
	private int date;						//消费数据
	private String description;//场景描述
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
