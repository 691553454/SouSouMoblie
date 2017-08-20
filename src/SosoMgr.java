import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 业务类 2017/07/30
 * 实现主菜单
 * 实现二级菜单
 * 实现用户登录身份验证
 * @author Administrator
 * 已知问题1：充值记录无法打印的问题！
 *
 */

public class SosoMgr 
{ 
    EchoClient client;
     Scanner input=new Scanner(System.in);
    CardUtil utils=new CardUtil();
    boolean flag=false;
	
	public static void main(String[] args) 
	{
		  SosoMgr soso=new SosoMgr();
		  soso.mainShow();
		  System.out.println("谢谢使用");
	}
	
	public SosoMgr()
	{
		super();
	}

	/**
	 * 主菜单
	 * 已知问题：选择时在输入非整形数据时报错问题
	 */
	public    void mainShow()
	{
		String pwd="";
		String num="";
		int choice=0;
		utils.init();
		utils.initScenes();
		do
		{
			System.out.println("*****************************欢迎使用嗖嗖移动业务大厅***********************************");
			System.out.println("\t1. 用户登录\t2. 用户注册 \t3. 使用嗖嗖 \t 4. 话费充值\t 5. 资费说明\t 6.客户咨询\t 7.退出系统");
			System.out.print("请选择：");
			Scanner input =new	Scanner(System.in);
		   choice=input.nextInt();
				switch(choice)
				{
				   case 1:
					   System.out.print("请输入手机卡号：");
					    num=input.next();
					   System.out.print("请输入密码：");
					    pwd=input.next();
					    if(utils.isExitCard(num, pwd))
					    {
					    	 cardMenu(num);
					    }
					    else
					    {
					    	System.out.println("对不起，账号或密码错误！");
					    }
					 continue;
				   case 2:
					   registCard();
					   continue;
				   case 3:
					   System.out.println("请输入手机卡号：");
					   num=input.next();
					   if(utils.isExistCard(num))
					   {
						   utils.userSoso(num);
					   }
					   else
					   {
						   System.err.println("对不起，该卡号未注册！");
					   }
					  continue;
				   case 4:
					   System.out.println("请输入充值卡号：");
					   num=input.next();
					   if(utils.isExistCard(num))
					   {
						   System.out.println("请输入金额：");
						   double money=input.nextDouble();
						   utils.chargeMoney(num, money);
					   }
					   else
					   {
						   System.out.println("对不起，您要充值的额卡号未注册，无法充值");
					   }
					   continue;
				   case 5:
					   System.out.println("\n***********资费说明***********");
					   utils.showDescription();
					  continue;
				   case 6:
				    try
				    {
					new EchoServer().start();
					client=new EchoClient();
					client.talk();
				    } catch (IOException e)
				    {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    }
					continue;
				   case 7:
				       	    System.out.println("谢谢使用");
				       	    System.exit(1);
				       	    continue;
				   default:
					   System.out.println("输入不合法 !");
					   break;
				}
      		break;
		}while(true);
	}
	
	/**
	 * 二级菜单
	 * 其他键返回上级菜单
	 *利用String定义输入字符，避免非整形报错---想法错误！！！
	 */
	public int cardMenu(String num)
	{
		int choice=0;
		do
		{
		System.out.println("*********嗖嗖移动用户菜单**********");
		System.out.println("1.本月账单查询");
		System.out.println("2.套餐余量查询");
		System.out.println("3.打印消费详单");
		System.out.println("4.套餐变更");
		System.out.println("5.办理退网");
		System.out.print("请选择(输入1-5选择功能，其他键返回上一级)：");
		Scanner input =new	Scanner(System.in);
		choice=input.nextInt();
				switch(choice)
				{
				   case 1:
					   System.out.println("\n***************本月账单查询*****************");
					   utils.showAmountDetail(num);
					  continue;
				   case 2:
					   System.out.println("\n***************套餐余量查询*****************");
					   utils.showRemainDetail (num);
					   continue;
				   case 3:
					   System.out.println("\n***************打印消费详单*****************");
					   utils.printConsumInfo(num);
					   continue;
				   case 4:
					   System.out.println("\n***************套餐变更*****************");
					   System.out.println("1.话痨套餐  2.网虫套餐 3.超人套餐    请选择（序号）");
					   utils.changingPack(num, input.nextInt());
					   continue;
				   case 5:
					   System.out.println("\n***************办理退网 ***************");
					   utils.delCard(num);
					   System.out.println("谢谢使用");
					   System.exit(1);
			    }
				break;
	}while(true);
		return choice;
	}
	/**
	 * 注册新卡流程
	 */
	public void registCard()
	{
		String arr[]=utils.getNewNumbers(9);
		System.out.println("***************可选择的卡号如下所示*******************");
		//格式化输出数组
		for (int i = 0; i < arr.length; i++)
		{
				System.out.print((i+1)+"."+arr[i]+"\t    ");
				if((i+1)%3==0)
				{
					System.out.println();
				}
		}
		//选择手机号,存储在number中
		System.out.print("请选择卡号（输入1-9的序号）：   ");
		String number=arr[input.nextInt()-1];
		//选择套餐类型
		   System.out.println("\n***************套餐选择*****************");
		   System.out.println("1.话痨套餐  2.网虫套餐 3.超人套餐    请选择（序号）");
		   //获取套餐对象
		   ServicePackage pack=utils.createPack(input.nextInt());
		   System.out.print("输入用户名 ");
		   String name=input.next();
		    //输入密码
		   System.out.print("请输入密码:  ");
		   String password=input.next();
		   //输入预存话费金额
		   double money=0;
		   System.out.print("请输入预存花费金额：");
		   money=input.nextDouble();
		   while(money<pack.getPrice())
		   {
			   System.out.print("您预存的花费不足以支付本月套餐资费，请重新充值！ ");
			   money=input.nextDouble();
		   }
		   //创建新卡对象并添加
		  MoblieCard  newcard=new MoblieCard(name, password, number, pack, pack.getPrice(),money-pack.getPrice() );
		  utils.addCard(newcard);
	}
}
