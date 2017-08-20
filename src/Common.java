import java.text.DecimalFormat;


public class Common 
{
	/**
	 * double 类型格式化
	 */
	public static String dataFormat(double data)
	{
		DecimalFormat formatData=new DecimalFormat("#.0");
		return formatData.format(data);
	}
	/**
	 * double两个数组相减
	 */
	public static double sub(double n1,double n2)
	{
		return  (n1*10-n2-10)/10; 
	}
}
