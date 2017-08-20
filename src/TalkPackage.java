

public class TalkPackage  extends ServicePackage implements CallService,SendService
	{
	
	 /**
	  * 通话时长
	  */
	private int talkTime;
		/**
		 * 短信条数
		 */
	 private int smsCount;
 
	   /**
	 	 * 有参构造
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
 * 无参构造
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
		System.out.println("话痨套餐：通话时长为："+this.talkTime+"分钟/月，短信条数为："+this.smsCount+"条/月,套餐资费为："+this.price);
	}

	@Override
	public int sendMessage(int smsCount,MoblieCard card) throws Exception 
	{
		int temp=0;
		//卡中剩余的短信条数
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
				System.out.println("对不起，您的账户余额不足："+Common.dataFormat(card.getMoney())+"元，超出套餐短信条数为："+(externConsumeMoney/0.1)+"条，请及时充值！");
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
			//套餐短信已经用完，账户余额可以制服一条短信，使用账户余额支付
			else if(card.getMoney()>=0.1)
			{
				card.setRealSMSCount (card.getRealSMSCount()+1);//实际使用条数+1
				card.setMoney(Common.sub(card.getMoney(), 0.1));//账户余额消费0.1元
				card.setConsumAmount(card.getConsumAmount()+0.1);
			}
			else 
			{
				temp=i; //记录短信条数	
				throw new Exception("本次已经发送短信"+i+"条，您的余额不足，请充值后再使用！");
			}
		}
		 
		return temp;*/
	}

	@Override
	public int call(int minCount, MoblieCard card) throws Exception 
	{
		int temp=0;
		//卡中剩余的通话时间数
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
				System.out.println("对不起，您的账户余额不足："+"已经欠费"+Common.dataFormat(card.getMoney())+"元，超出套餐通话时间数为："+(externConsumeMoney/0.2)+"分钟，请及时充值！");
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
			//套餐通话时长已经用完，账户余额可以制服一分钟通话，使用账户余额支付
			else if(card.getMoney()>=0.2)
			{
				card.setRealTalktime(card.getRealTalktime()+1);//时机使用通话时长1分钟
				card.setMoney(Common.sub(card.getMoney(), 0.2));//账户余额消费0。2元
				card.setConsumAmount(card.getConsumAmount()+0.2);
			}
			else 
			{
				temp=i; //记录分钟数
				throw new Exception("本次已经通话"+i+"分钟，您的余额不足，请充值后再使用！");
			}
		}
		return temp;*/
	}
}
