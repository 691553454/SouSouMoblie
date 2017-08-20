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
	 * ���������ֻ������б�key���ֻ���  value��������Ϣ
	 * ���ݳ�ʼ��
	 */
	Map <String,MoblieCard> cards=new HashMap<String,MoblieCard>  ();
	//�����ֻ��������Ѽ�¼�б�valueΪ������Ϣ�б���һ������
	Map<String,List<ConsumInfo>>consumInfos=new HashMap<String, List<ConsumInfo>>();
	//����ʹ�ó����༯��
	List <Scene> scenes=new ArrayList<Scene>();
	//���๤�߶���
	//��ʼ������ ����
	public void init()
	{
	MoblieCard card1=new MoblieCard("Bang", "123456","13965756432",  new NetPackage(), 68 ,80);
	MoblieCard card2=new MoblieCard("������", "123456","13862302331",  new TalkPackage(), 58, 80);
	MoblieCard card3=new MoblieCard("��С��", "123456","13765864399",  new SuperPackage(), 78, 90);
	MoblieCard card4=new MoblieCard("ϴ�װ�", "123456","13622756430",  new TalkPackage(), 58, 100);
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
 * ��ʼ��ʹ�ó�������
 */
public void initScenes()
{
	scenes.add(new Scene("ͨ��",60,"����ͨ����60����"));
	scenes.add(new Scene("ͨ��",30,"��Ů��ͨ��30����"));
	scenes.add(new Scene("����",5,"������������5������"));
	scenes.add(new Scene("����",50,"֪ͨ�ֻ����ţ�������50��"));
	scenes.add(new Scene("����",1*1024,"������1G"));
	scenes.add(new Scene("����",2*1024,"������ 2G"));
}
	
	/**
	 * ��¼��֤
	 * ���κ�����µ�¼������false������ ---�߼����󣬸������Ѿ������ԭ�����ڵ��ø÷����ķ����ڲ�δ�����ʼ�������ݡ��������Ҽ��ϴ���ʱλ
	 * ʹ�÷��͵��²�����getPassWord�ķ���,(((MoblieCard) (cards.get(searchNum))).getPassWord()).equals(passWord))
	 * ��ȷ��ǿ������ת�����
	 */
	public boolean isExitCard(String number,String passWord)
	{
		//���ؼ��ļ���
		Set  <String>numbers=cards.keySet();
		//������������
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
	 *����11λ�ֻ��ţ�������
	 */
	public String createNumber()
	{
		String number="";
		Random random=new Random();
		//��¼�����Ƿ��Ѵ���
		boolean isExit=false;
		int temp=0;
		//����ѭ���ж����п����Ƿ���ڣ����������򷵻أ������ڳ����������
		do
		{
			isExit=false;
			do
			{
				 temp=random.nextInt(100000000);	
			}while(temp<10000000);
		   number="139"+temp;
		   //ȡ��ֵ���������ɿ��űȽϣ������ظ�
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
	 * ����һ������9����ѡ�ֻ��ŵ��б�
	 * �����ַ�������
	 */
	public String[] getNewNumbers(int count)
	{	
		String[] numbers=new String[count];
		for (int i = 0; i <count; i++)   //ΪʲôС��count;
		{
			numbers[i]=createNumber();
		}
		return numbers;
	}
	/**
	 * ����ָ�������Ƿ��Ѿ�ע��
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
	 * ����¿�
	 */
	public void addCard(MoblieCard card)
	{
		cards.put(card.getCardNumber(), card);
		card.showMeg();
	}
	/**
	 * �����µĶ���
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
	  * ��ѯָ�������»��������굥
	  */
	public void showAmountDetail(String searchNumber)
	{
		MoblieCard card;
		StringBuffer meg=new StringBuffer();
		card=cards.get(searchNumber);
		meg.append("���Ŀ��ţ�"+card.getCardNumber()+",�����˵���\n");
		meg.append("�ײ��ʷѣ�"+card.getSerPackage().getPrice()+"Ԫ\n");
		meg.append("�ײ����ʷѣ�"+(card.getConsumAmount()-card.getSerPackage().getPrice())+"Ԫ\n");
		meg.append("�������ѽ��ƣ�"+Common.dataFormat(card.getConsumAmount())+"Ԫ   ");
		meg.append("  �˻���"+Common.dataFormat(card.getMoney())+"Ԫ");
		System.out.println(meg);
	}
	
	/**
	 *ʵ���ײ�������ѯ�ķ��� 
	 */
	public void showRemainDetail(String searchNumber)
	{
		MoblieCard card;
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		StringBuffer meg=new StringBuffer();
		card=cards.get(searchNumber);
		meg.append("���Ŀ�����"+searchNumber+",�ײ���ʣ�ࣺ\n");
		ServicePackage pack=card.getSerPackage();
		if(pack instanceof TalkPackage)
		{
			//ʣ��ͨ��ʱ��
			TalkPackage cardPack=(TalkPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("ͨ��ʱ��"+remainTalkTime+"����");
			//ʣ���������
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("��������"+remainSmsCount+"��");
		}
		else if(pack instanceof NetPackage)
		{
			NetPackage cardPack=(NetPackage)pack;
			remainFlow=cardPack.getFolw()>card.getRealFlow()?cardPack.getFolw()-card.getRealFlow():0;
			meg.append("����������"+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		else if(pack instanceof SuperPackage)
		{
			SuperPackage cardPack=(SuperPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("ͨ��ʱ��"+remainTalkTime+"����");
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("��������"+remainSmsCount+"��");
			remainFlow=cardPack.getFlow()>card.getRealFlow()?cardPack.getFlow()-card.getRealFlow():0;
			meg.append("����������"+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		System.out.println(meg);
	}
	
	/**
	 *�����굥��ѯ����ӡ���Ѽ�¼ 
	 * @param number
	 */
	public void printConsumInfo(String number)
	{
		Writer fileWriter=null;
		try {
			fileWriter=new FileWriter(number+"���Ѽ�¼.txt");
			Set<String> numbers=consumInfos.keySet();
			Iterator<String> it=numbers.iterator();
			//�洢ָ�������е����Ѽ�¼
			List<ConsumInfo>infos=new ArrayList<ConsumInfo>();
			//���������б����Ƿ���ڴ˿������Ѽ�¼
			boolean isExit=false;
			while(it.hasNext())
			{
				if(it.next().equals(number))
				{
					//�����ֻ����Ż�ȡ�ֻ�������Ϣ���󼯺�
					infos=consumInfos.get(number);
					isExit=true;
					break;
				}
			}
			//�ж��Ƿ���ڴ����Ѽ�¼�����ҽ�����ʾ����
			if(isExit)
			{
				StringBuffer content=new StringBuffer("*********"+number+"���Ѽ�¼*********\n");
				content.append("���\t����\t����(ͨ��������/������MB��/���ţ�����)\n");
				//ѭ��ĳ�ռ���������Ӧ��������Ϣ���󼯺�
				for(int i=0;i<infos.size();i++)
				{
					//��ȡĳ��������Ϣ����
					ConsumInfo info=infos.get(i);
					content.append((i+1)+".\t         "+info.getType()+"\t"+info.getConsumDate()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				fileWriter.close();	//�Լ�
				System.out.println("���Ѽ�¼��ӡ��ϣ�");
			}
			else 
			{
				System.out.println("�����ڴ˿��ŵ����Ѽ�¼�����ܴ�ӡ��");
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
	 * ʹ����
	 */
	public void userSoso(String number)
	{
		MoblieCard card=cards.get(number);
		ServicePackage pack=card.getSerPackage();
		Random random=new Random();
		int ranNum=0;
		int temp=0;   //��¼��������
		
		do
		{
			ranNum=random.nextInt(6);
			Scene scene=scenes.get(ranNum);
			switch(ranNum)
			{
			//0-1Ϊͨ��
			case 0:
			case 1:
				//�ж��Ƿ�֧�ָù���Ȼ��ִ��
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
					//���һ�����Ѽ�¼
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}
				
				else
				{
					continue;//�˿�������֧��ͨ�����ܣ���������
				}
			case 2:
			case 3:
					//���Ͷ��ŷ���
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
						//���һ�����Ѽ�¼
						addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
						break;
					}
						else
						{
							continue;
						}
				case 4:
				case 5:
					//�жϸÿ��Ƿ�֧������
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
     * ���һ��ָ���������Ѽ�¼
     * @param number
     * 							���ѵĿ�
     * @param info
     * 							Ҫ��ӵļ�¼
     */
	public void addConsumInfo(String number,ConsumInfo info)
	{
		Set <String>numbers=consumInfos.keySet();
		Iterator<String> it=numbers.iterator();
		List<ConsumInfo>infos=new ArrayList<ConsumInfo>();
		boolean isExist=false;//���������б����Ƿ���ڴ˿������Ѽ�¼
		while(it.hasNext())
		{
					if(it.next().equals(number))
					{
						infos=consumInfos.get(number);//����иü��϶��󣬾��ҵ��ü��϶���
						infos.add(info);
						isExist=true;
						System.out.println("�Ѿ����һ�����Ѽ�¼");
						break;
					}
	     }
		//�ü�����û�д˿������Ѽ�¼
		if(!isExist)
		{
			infos.add(info);
			consumInfos.put(number, infos);
			System.out.println("�����ڴ˿������Ѽ�¼���Ѿ���ӣ�");
		}
	}
 
	
		
	
	public void  showDescription()
	{
		Reader rd=null;
		try {
			rd=new FileReader("�ײ��ʷ�˵��.txt");
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
	 * ��ָ�����ų�ֵ���
	 */
	public void chargeMoney(String number,double money)
	{
		MoblieCard card;
		if(money<50)
		{
			System.out.println("��ֵ������λ50Ԫ");
			return;
		}
		card=cards.get(number);
		card.setMoney(card.getMoney()+money);
		System.out.println("��ֵ�ɹ�����ǰ����ԼΪ��"+Common.dataFormat(card.getMoney())+"Ԫ") ;
	}
	
	
	/**
	 * �ƶ����Ű�������
	 */
	public void delCard(String delNumber)
	{
		if(isExistCard(delNumber))
		{
			cards.remove(delNumber);
			System.out.println("���ţ�"+delNumber+"���������ɹ���");
		}
		else
		{
			System.out.println("�Բ��𣬸ÿ�����δע�ᣬ���ܰ���������");
		}
	}
	
	
	/**
	 * �����ײ�
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
				System.out.println("������1-3�����֣�");
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
					System.out.println("��ϲ�����޸��ײͳɹ���");
					pack.showInfo();
				}
				else
				{
					System.out.println("�Բ����������㣡");
					return ;
				}
			}
			else
			{
				System.out.println("�����ײ�����˵Ҫ���ĵ��ײ���ͬ��");
			}
			
		}
		else 
		{
			System.out.println("���Ų����ڣ�����");
		}
	}
	
	
	/**
	 * �����ײ���������������Ҳ��ҷ���
	 */
	/*public  void  returnMoney(String num)
	{
		//1.�����ײ�����
		 * 
		MoblieCard card;
		int remainTalkTime;
		int remainSmsCount;
		int remainFlow;
		double	moneyTalk = 0;
		double	moneySmsCount = 0;
		StringBuffer meg=new StringBuffer();
		card=cards.get(num);
		meg.append("���Ŀ�����"+num+",�ײ���ʣ�ࣺ\n");
		ServicePackage pack=card.getSerPackage();
		if(pack instanceof TalkPackage)
		{
			//ʣ��ͨ��ʱ��
			TalkPackage cardPack=(TalkPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("ͨ��ʱ��  "+remainTalkTime+"����");
			//ʣ���������
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("��������   "+remainSmsCount+"��");
			//��������ʣ���۷��������
			if(remainTalkTime!=0)
			{
				 moneyTalk=	remainTalkTime/0.2;
				System.out.println("ʣ�໰�ѷ������Ϊ��"+moneyTalk);
			}
			
			if(remainSmsCount!=0)
			{
			 	moneySmsCount=	remainSmsCount/0.2;
				System.out.println("ʣ����ŷ������Ϊ��"+moneySmsCount);
			}
			System.out.println("�����ܽ��Ϊ��"+ moneySmsCount+moneyTalk);
		}
		else if(pack instanceof NetPackage)
		{
			NetPackage cardPack=(NetPackage)pack;
			remainFlow=cardPack.getFolw()>card.getRealFlow()?cardPack.getFolw()-card.getRealFlow():0;
			meg.append("����������"+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
		else if(pack instanceof SuperPackage)
		{
			SuperPackage cardPack=(SuperPackage)pack;
			remainTalkTime=cardPack.getTalkTime()>card.getRealTalktime()?cardPack.getTalkTime()-card.getRealTalktime():0;
			meg.append("ͨ��ʱ��"+remainTalkTime+"����");
			remainSmsCount=cardPack.getSmsCount()>card.getRealSMSCount()?cardPack.getSmsCount()-card.getRealSMSCount():0;
			meg.append("��������"+remainSmsCount+"��");
			remainFlow=cardPack.getFlow()>card.getRealFlow()?cardPack.getFlow()-card.getRealFlow():0;
			meg.append("����������"+Common.dataFormat(remainFlow*1.0/1024)+"GB");
		}
	}*/
}
