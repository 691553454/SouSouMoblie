import java.text.DecimalFormat;


public class Common 
{
	/**
	 * double ���͸�ʽ��
	 */
	public static String dataFormat(double data)
	{
		DecimalFormat formatData=new DecimalFormat("#.0");
		return formatData.format(data);
	}
	/**
	 * double�����������
	 */
	public static double sub(double n1,double n2)
	{
		return  (n1*10-n2-10)/10; 
	}
}
