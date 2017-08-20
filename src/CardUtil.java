import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

                             
public class CardUtil 
{
	
	/**
	 * 定义所有手机卡的列表：key：手机号  value：卡号信息
	 * 数据初始化
	 */
	Map <String,MoblieCard> cards=new HashMap<String,MoblieCard>  ();
	//所有手机卡的消费记录列表，value为消费信息列表，是一个集合
	Map<String,List<ConsumInfo>>consumInfos=new HashMap<String, List<ConsumInfo>>();
	//定义使用场景类集合
	List <Scene> scenes=new ArrayList<Scene>();
	//本类工具对象
	//初始化集合 数据
	public void init()
	{
	MoblieCard card1=new MoblieCard("Bang", "123456","13965756432",  new NetPackage(), 68 ,80);
	MoblieCard card2=new MoblieCard("王滔滔", "123456","13862302331",  new TalkPackage(), 58, 80);
	MoblieCard card3=new MoblieCard("黄小文", "123456","13765864399",  new SuperPackage(), 78, 90);
	MoblieCard card4=new MoblieCard("洗白白", "123456","13622756430",  new TalkPackage(), 58, 100);
	MoblieCard card5=new MoblieCard("faker", "123456","13565756411",  new TalkPackage(),58, 80);
	cards.put("13965756432", card1);
	cards.put("13862302331", card2);
	cards.put("13765864399", card3);
	cards.put("13622756430", card4);
	cards.put("13565756411", card5);
 
	card4.setRealSMSCount(15);
	card4.setRealTalktime(200);
	}
/**
 * 初始化使用场景数据
 */
public void initScenes()
{
	scenes.add(new Scene("通话",60,"生意通话，60分钟"));
	scenes.add(new Scene("通话",30,"与女优通话30分钟"));
	scenes.add(new Scene("短信",5,"环境宣传，发5条短信"));
	scenes.add(new Scene("短信",50,"通知手机换号，发短信50条"));
	scenes.add(new Scene("上网",1*1024,"听音乐1G"));
	scenes.add(new Scene("上网",2*1024,"看韩剧 2G"));
}
	
	/**
	 * 登录验证
	 * 在任何情况下登录都返回false的问题 ---逻辑错误，该问题已经解决。原因在于调用该方法的方法内并未传入初始化的数据。。。并且集合创建时位
	 * 使用泛型导致不存在getPassWord的方法,(((MoblieCard) (cards.get(searchNum))).getPassWord()).equals(passWord))
	 * 正确的强制类型转换语句
	 */
	public boolean isExitCard(String number,String passWord)
	{
		//返回键的集合
		Set  <String>numbers=cards.keySet();
		//创建迭代对象
		Iterator<String> it=numbers.iterator();
		while(it.hasNext())
		{
			String searchNum=it.next();
			if(searchNum.equals(number)&&( cards.get(searchNum).getPassWord().equals(passWord)))
			{
				return true;
			}
		}
			 return false;
	}
	
	/**
	 *生成11位手机号，并返回
	 */
	public String createNumber()
	{
		String number="";
		Random random=new Random();
		//记录卡号是否已存在
		boolean isExit=false;
		int temp=0;
		//利用循环判断现有卡号是否存在，若不存在则返回，若存在持续输出。。
		do
		{
			isExit=false;
			do
			{
				 temp=random.nextInt(100000000);	
			}while(temp<10000000);
		   number="139"+temp;
		   //取键值集合与生成卡号比较，不可重复
		   Set<String> cardNumbers=cards.keySet();
		   for (String cardNumber : cardNumbers)
		   {
			   if(number.equals(cardNumber))
			   {
				   isExit=true;
				   break;
			   }
		   }
		}while(isExit);
		return  number;
	}
	
	/**
	 * 生成一个具有9个备选手机号的列表
	 * 利用字符串数组
	 */
	public String[] getNewNumbers(int count)
	{	
		String[] numbers=new String[count];
		for (int i = 0; i <count; i++)   //为什么小于count;
		{
			numbers[i]=createNumber();
		}
		return numbers;
	}
	/**
	 * 查找指定卡号是否已经注册
	 */
	public boolean isExistCard(String searchNumber)
	{
		Set <String> numbers=cards.keySet();
		for(String number:numbers)
		{
			if(number.equals(searchNumber))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 添加新卡
	 */
	public void addCard(MoblieCard card)
	{
		cards.put(card.getCardNumber(), card);
		card.showMeg();
	}
	/**
	 * 生成新的对象
	 */
	public ServicePackage createPack(int packId)
	{
		ServicePackage pack=null;
		switch(packId)
		{
		 case 1:
		 pack=new TalkPackage();
		 break;
		 case 2:
		 pack=new NetPackage();
		 case 3:
		 pack=new SuperPackage();
		}
		return pack;
	}
	
	 /**
	  * 查询指定卡当月话费消费详单
	  */
	public void showAmountDetail(String searchNumber)
	{
		MoblieCard card;
		StringBuffer meg=new StringBuffer();
		card=cards.get(searchNumber);
		meg.append("您的卡号："+card.getCardNumber()+",当月账单：\n");
		meg.append("套餐资费："+card.getSerPackage().getPrice()+"元\n");
		meg.append("套餐外资费："+(card.getConsumAmount()-card.getSerPackage().getPrice())+"元\n");
		meg.append("当月消费金额共计："+Common.dataFormat(card.getConsumAmount())+"元   ");
		meg.append("  账户余额："+Common.dataFormat(card.getMoney())+"元");
		System.out.println(meg);
	}
	
	/**
	 *实现套餐余量查询的方法 
	 */
	public void showRemainDetail(String searchNumber)
	{
		MoblieCard card;
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		StringBuffer meg=new StringBuffer();
		card=cards.get(searchNumber);
		meg.append("您的卡号是"+searchNumber+",套餐内剩余：\n");
		ServicePackage pack=card.getSerPackage();
		if(pack instanceof TalkPackage)
		{
			//剩余通话时长
			TalkPackage cardPack=(TalkPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("通话时长"+remainTalkTime+"分钟");
			//剩余短信条数
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("短信条数"+remainSmsCount+"条");
		}
		else if(pack instanceof NetPackage)
		{
			NetPackage cardPack=(NetPackage)pack;
			remainFlow=cardPack.getFolw()>card.getRealFlow()?cardPack.getFolw()-card.getRealFlow():0;
			meg.append("上网流量："+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		else if(pack instanceof SuperPackage)
		{
			SuperPackage cardPack=(SuperPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("通话时长"+remainTalkTime+"分钟");
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("短信条数"+remainSmsCount+"条");
			remainFlow=cardPack.getFlow()>card.getRealFlow()?cardPack.getFlow()-card.getRealFlow():0;
			meg.append("上网流量："+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		System.out.println(meg);
	}
	
	/**
	 *消费详单查询，打印消费记录 
	 * @param number
	 */
	public void printConsumInfo(String number)
	{
		Writer fileWriter=null;
		try {
			fileWriter=new FileWriter(number+"消费记录.txt");
			Set<String> numbers=consumInfos.keySet();
			Iterator<String> it=numbers.iterator();
			//存储指定卡所有的消费记录
			List<ConsumInfo>infos=new ArrayList<ConsumInfo>();
			//现有消费列表中是否存在此卡号消费记录
			boolean isExit=false;
			while(it.hasNext())
			{
				if(it.next().equals(number))
				{
					//根据手机卡号获取手机消费信息对象集合
					infos=consumInfos.get(number);
					isExit=true;
					break;
				}
			}
			//判断是否存在此消费记录，并且进行显示调用
			if(isExit)
			{
				StringBuffer content=new StringBuffer("*********"+number+"消费记录*********\n");
				content.append("序号\t类型\t数据(通话（条）/上网（MB）/短信（条）)\n");
				//循环某收集卡号所对应的消费信息对象集合
				for(int i=0;i<infos.size();i++)
				{
					//获取某条消费信息对象
					ConsumInfo info=infos.get(i);
					content.append((i+1)+".\t         "+info.getType()+"\t"+info.getConsumDate()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				fileWriter.close();	//自加
				System.out.println("消费记录打印完毕！");
			}
			else 
			{
				System.out.println("不存在此卡号的消费记录，不能打印！");
			}
		}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				if(fileWriter!=null)
				{
					try {
						fileWriter.close();
							}
					catch (IOException e)
							{
					 	e.printStackTrace();
							}
			   	}
			}
		
	}
	
	
		
	/**
	 * 使用嗖嗖
	 */
	public void userSoso(String number)
	{
		MoblieCard card=cards.get(number);
		ServicePackage pack=card.getSerPackage();
		Random random=new Random();
		int ranNum=0;
		int temp=0;   //记录消费数据
		
		do
		{
			ranNum=random.nextInt(6);
			Scene scene=scenes.get(ranNum);
			switch(ranNum)
			{
			//0-1为通话
			case 0:
			case 1:
				//判断是否支持该功能然后执行
				if(pack instanceof CallService)
				{
					System.out.println(scene.getDescription());
					CallService callService=(CallService)pack;
					try 
					{
						temp=callService.call(scene.getDate(), card);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					//添加一条消费记录
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}
				
				else
				{
					continue;//此卡所属不支持通话功能，重新生成
				}
			case 2:
			case 3:
					//发送短信方法
					if(pack instanceof SendService)
					{
						System.out.println(scene.getDescription());
						SendService sendService=(SendService)pack;
						try {
									temp=sendService.sendMessage(scene.getDate(), card);
					 			} 
						catch (Exception e)
								{
							// TODO Auto-generated catch block
									e.printStackTrace();
								}
						//添加一条消费记录
						addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
						break;
					}
						else
						{
							continue;
						}
				case 4:
				case 5:
					//判断该卡是否支持上网
					if(pack instanceof NetService)
					{
						System.out.println(scene.getDescription());
						NetService netService=(NetService)pack;
						try {
							temp=netService.netPlay(scene.getDate(), card);
								} 
						catch (Exception e) 
								{
							e.printStackTrace();
								}
						addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
						break;
				 	}
					else
					{
						continue;
					}
				}
			break;	
		}while(true);
	}
	
	
    /**
     * 添加一条指定卡的消费记录
     * @param number
     * 							消费的卡
     * @param info
     * 							要添加的记录
     */
	public void addConsumInfo(String number,ConsumInfo info)
	{
		Set <String>numbers=consumInfos.keySet();
		Iterator<String> it=numbers.iterator();
		List<ConsumInfo>infos=new ArrayList<ConsumInfo>();
		boolean isExist=false;//现有消费列表中是否存在此卡号消费记录
		while(it.hasNext())
		{
					if(it.next().equals(number))
					{
						infos=consumInfos.get(number);//如果有该集合对象，就找到该集合对象
						infos.add(info);
						isExist=true;
						System.out.println("已经添加一条消费记录");
						break;
					}
	     }
		//该集合中没有此卡号消费记录
		if(!isExist)
		{
			infos.add(info);
			consumInfos.put(number, infos);
			System.out.println("不存在此卡的消费记录，已经添加！");
		}
	}
 
	
		
	
	public void  showDescription()
	{
		Reader rd=null;
		try {
			rd=new FileReader("套餐资费说明.txt");
				}
		catch (FileNotFoundException e)
				{
			e.printStackTrace();
				}
		char[] content=new char[1024];
		int len=0;
		StringBuffer sb=new StringBuffer();
		try {
			while((len=rd.read(content))!=-1)
			{
				sb.append(content, 0, len);
			}
				}
		 catch (IOException e) 
		 	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		 	}
		System.out.println(sb);
	}
	
	
	/**
	 * 给指定卡号充值金额
	 */
	public void chargeMoney(String number,double money)
	{
		MoblieCard card;
		if(money<50)
		{
			System.out.println("充值金额最低位50元");
			return;
		}
		card=cards.get(number);
		card.setMoney(card.getMoney()+money);
		System.out.println("充值成功，当前花费约为："+Common.dataFormat(card.getMoney())+"元") ;
	}
	
	
	/**
	 * 制定卡号办理退网
	 */
	public void delCard(String delNumber)
	{
		if(isExistCard(delNumber))
		{
			cards.remove(delNumber);
			System.out.println("卡号："+delNumber+"办理退网成功！");
		}
		else
		{
			System.out.println("对不起，该卡号尚未注册，不能办理退网！");
		}
	}
	
	
	/**
	 * 更换套餐
	 */
	public void changingPack(String number,int packNum)
	{
		MoblieCard card;
		ServicePackage pack = null;
		if(isExistCard(number))
		{
			card=cards.get(number);
			switch(packNum)
			{
			case 1 :
					pack=new TalkPackage();
		 	   		break;
			case  2:
					pack=new NetPackage();
					break;
			case  3:
					pack=new SuperPackage();
					break;
			default:
				System.out.println("请输入1-3的数字！");
				break;
			}
			if(!(card.getSerPackage().getClass().getName().equals(pack.getClass().getName())))
			{
			  if(card.getMoney()>=pack.getPrice())
				{
					card.setMoney(card.getMoney()-pack.getPrice());
					card.setSerPackage(pack);
					card.setRealTalktime(0);
					card.setRealSMSCount(0);
					card.setRealFlow(0);
					card.setConsumAmount(pack.getPrice());
					System.out.println("恭喜您，修改套餐成功！");
					pack.showInfo();
				}
				else
				{
					System.out.println("对不起，您的余额不足！");
					return ;
				}
			}
			else
			{
				System.out.println("您的套餐与您说要更改的套餐相同！");
			}
			
		}
		else 
		{
			System.out.println("卡号不存在！！！");
		}
	}
	
	
	/**
	 * 根据套餐余量，折算人民币并且返还
	 */
	/*public  void  returnMoney(String num)
	{
		//1.计算套餐余量
		 * 
		MoblieCard card;
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		double	moneyTalk = 0;
		double	moneySmsCount = 0;
		StringBuffer meg=new StringBuffer();
		card=cards.get(num);
		meg.append("您的卡号是"+num+",套餐内剩余：\n");
		ServicePackage pack=card.getSerPackage();
		if(pack instanceof TalkPackage)
		{
			//剩余通话时长
			TalkPackage cardPack=(TalkPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("通话时长  "+remainTalkTime+"分钟");
			//剩余短信条数
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("短信条数   "+remainSmsCount+"条");
			//计算余量剩余折返的人民币
			if(remainTalkTime!=0)
			{
				 moneyTalk=	remainTalkTime/0.2;
				System.out.println("剩余话费返还余额为："+moneyTalk);
			}
			
			if(remainSmsCount!=0)
			{
			 	moneySmsCount=	remainSmsCount/0.2;
				System.out.println("剩余短信返还余额为："+moneySmsCount);
			}
			System.out.println("返还总金额为："+ moneySmsCount+moneyTalk);
		}
		else if(pack instanceof NetPackage)
		{
			NetPackage cardPack=(NetPackage)pack;
			remainFlow=cardPack.getFolw()>card.getRealFlow()?cardPack.getFolw()-card.getRealFlow():0;
			meg.append("上网流量："+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		else if(pack instanceof SuperPackage)
		{
			SuperPackage cardPack=(SuperPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("通话时长"+remainTalkTime+"分钟");
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("短信条数"+remainSmsCount+"条");
			remainFlow=cardPack.getFlow()>card.getRealFlow()?cardPack.getFlow()-card.getRealFlow():0;
			meg.append("上网流量："+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
	}*/
}
