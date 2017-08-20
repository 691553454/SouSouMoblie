
public class SuperPackage	extends ServicePackage  implements NetService,SendService,CallService
{
	private int talkTime;
	private int flow;
	private int smsCount;
	@Override
	public void showInfo() 
	{
		System.out.println("超人套餐，通话时长为："+this.talkTime+"分钟/月，短信条数为："+this.smsCount+"条/月，上网流量为："+this.flow/1024+"GB/月");
	}
	
	public SuperPackage() 
	{
		super();
		this.price=78;
		this.flow=1*1024;
		this.smsCount=50;
		this.talkTime=200;
	}

	

	public SuperPackage(int talkTime, int flow, int smsCount,double price) {
		super(price);
		this.talkTime = talkTime;
		this.flow = flow;
		this.smsCount = smsCount;
	}

	public int getTalkTime() 
	{
		return talkTime;
	}
	public void setTalkTime(int talkTime) 
	{
		this.talkTime = talkTime;
	}
	public int getFlow()
	{
		return flow;
	}
	public void setFlow(int flow) 
	{
		this.flow = flow;
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
	}

	@Override
	public int sendMessage(int count, MoblieCard card) throws Exception
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
	}

	@Override
	public int netPlay(int flow, MoblieCard card) throws Exception 
	{
		int temp=0;
		int reminFlow=this.flow-card.getRealFlow();   //卡中可支付的免费流量
		//判断上网流量是否足够支付本次上网服务
		if(flow<=reminFlow)
			//流量足够，修改该卡实际上网流量数据
		{
			card.setRealFlow(card.getRealFlow()+flow);
			 temp=flow;
		}
		else
		{
			//套餐内余量不足，额外的需要按照0.1元/MB付费
			double externConsumeMoney=(flow-reminFlow)*0.1;
			//该卡账户余额足够支付，修改该卡实际使用的上网流量，账户余额，当月消费金额
			if(card.getMoney()>=externConsumeMoney)
			{
				//消耗的流量增加
				card.setRealFlow(card.getRealFlow()+flow);
				//当前账户余额减去额外消费金额
				card.setMoney(card.getMoney()-externConsumeMoney);
				//消费金额增加
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=flow;
			}
			//当该卡的账户余额不足以支付，设置余额时，本次消费减去剩余的，把余额设置为负数
			else
			{
				//消耗的流量增加
				card.setRealFlow(card.getRealFlow()+flow);
				//设置余额为负数
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp= flow;
				System.out.println("对不起，您的账户余额不足，已经欠费 "+Common.dataFormat(card.getMoney())+"元，"+"超出套餐流量："+(externConsumeMoney/0.1)+"MB "+"请及时充值！");
			}
		}
		return temp;
	}
	

}
