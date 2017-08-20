

public class TalkPackage  extends ServicePackage implements CallService,SendService
	{
	
	 /**
	  * ͨ��ʱ��
	  */
	private int talkTime;
		/**
		 * ��������
		 */
	 private int smsCount;
 
	   /**
	 	 * �вι���
	     * @param talkTime
		 * @param smsCount
		 */
	public TalkPackage(double price,int talkTime, int smsCount) 
	{
		super(price);
		this.talkTime = talkTime;
		this.smsCount = smsCount;
	}

/**
 * �޲ι���
 */
	public TalkPackage()
	{
		 super();
		 this.price=58;
		 this.smsCount=30;
		 this.talkTime=500;
	}


	public int getTalkTime()
	{
	return talkTime;
	}

public void setTalkTime(int talkTime)
{
	this.talkTime = talkTime;
}

public int getSmsCount()
{
	return smsCount;
}

public void setSmsCount(int smsCount)
{
	this.smsCount = smsCount;
}

	@Override
	public void showInfo() 
	{
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ��"+this.talkTime+"����/�£���������Ϊ��"+this.smsCount+"��/��,�ײ��ʷ�Ϊ��"+this.price);
	}

	@Override
	public int sendMessage(int smsCount,MoblieCard card) throws Exception 
	{
		int temp=0;
		//����ʣ��Ķ�������
		int reminSMScount=this.smsCount-card.getRealSMSCount();
		if(smsCount<=reminSMScount)
		{
			card.setRealSMSCount(card.getRealSMSCount()+smsCount);
		}
		else
		{
			double externConsumeMoney=(smsCount-reminSMScount)*0.1;
			if(card.getMoney()>=externConsumeMoney)
			{
				card.setRealSMSCount(card.getRealSMSCount()+smsCount);
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=smsCount;
			}
			else
			{
				card.setRealSMSCount(card.getRealSMSCount()+smsCount);
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=smsCount;
				System.out.println("�Բ��������˻����㣺"+Common.dataFormat(card.getMoney())+"Ԫ�������ײͶ�������Ϊ��"+(externConsumeMoney/0.1)+"�����뼰ʱ��ֵ��");
			}
		}
		return  temp;
	
		
	/*	int temp=smsCount;
		for(int i=0;i<smsCount;i++)
		{
			if(this.smsCount-card.getRealSMSCount()>=1)
			{
				card.setRealSMSCount (card.getRealSMSCount()+1);
			}
			//�ײͶ����Ѿ����꣬�˻��������Ʒ�һ�����ţ�ʹ���˻����֧��
			else if(card.getMoney()>=0.1)
			{
				card.setRealSMSCount (card.getRealSMSCount()+1);//ʵ��ʹ������+1
				card.setMoney(Common.sub(card.getMoney(), 0.1));//�˻��������0.1Ԫ
				card.setConsumAmount(card.getConsumAmount()+0.1);
			}
			else 
			{
				temp=i; //��¼��������	
				throw new Exception("�����Ѿ����Ͷ���"+i+"�����������㣬���ֵ����ʹ�ã�");
			}
		}
		 
		return temp;*/
	}

	@Override
	public int call(int minCount, MoblieCard card) throws Exception 
	{
		int temp=0;
		//����ʣ���ͨ��ʱ����
		int reminTalktime=this.talkTime-card.getRealTalktime();
		if(minCount<=reminTalktime)
		{
			card.setRealTalktime(card.getRealTalktime()+minCount);
			temp=minCount;
		}
		else
		{
			double externConsumeMoney=(minCount-reminTalktime)*0.2;
			if(card.getMoney()>=externConsumeMoney)
			{
				card.setRealTalktime (card.getRealSMSCount()+minCount);
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=minCount;
			}
			else
			{
				card.setRealTalktime (card.getRealSMSCount()+minCount);
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=minCount;
				System.out.println("�Բ��������˻����㣺"+"�Ѿ�Ƿ��"+Common.dataFormat(card.getMoney())+"Ԫ�������ײ�ͨ��ʱ����Ϊ��"+(externConsumeMoney/0.2)+"���ӣ��뼰ʱ��ֵ��");
			}
		}
		return  temp;
		/*int temp=minCount;
		for(int i=0;i<minCount;i++)
		{
			if(this.talkTime-card.getRealTalktime()>=1)
			{
				card.setRealTalktime(card.getRealTalktime()+1);
			}
			//�ײ�ͨ��ʱ���Ѿ����꣬�˻��������Ʒ�һ����ͨ����ʹ���˻����֧��
			else if(card.getMoney()>=0.2)
			{
				card.setRealTalktime(card.getRealTalktime()+1);//ʱ��ʹ��ͨ��ʱ��1����
				card.setMoney(Common.sub(card.getMoney(), 0.2));//�˻��������0��2Ԫ
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}
			else 
			{
				temp=i; //��¼������
				throw new Exception("�����Ѿ�ͨ��"+i+"���ӣ��������㣬���ֵ����ʹ�ã�");
			}
		}
		return temp;*/
	}
}
