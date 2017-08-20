
public class NetPackage extends ServicePackage implements NetService
{
	 
	private int folw;
	@Override
	public void showInfo()
	{
		System.out.println("网虫套餐： 上网流量是是："+this.folw/1024+"GB/月,套餐资费为："+this.price+"元/月");//在这里this.flow和folw有什么区别？？？？？！
	}
	/**
	 * 无参
	 */
	public NetPackage()
	{
		super();
		this.folw=1024*3;
		this.price=68;
	}

	public int getFolw()
	{
		return folw;
	}
	public void setFolw(int folw)
	{
		this.folw = folw;
	}
	/**
	 * 有参
	 * @param price
	 * @param flow
	 */
	public NetPackage(double price, int flow)
	{
		super(price);
		this.folw=flow;
	}
	@Override
	public int netPlay(int flow, MoblieCard card) throws Exception
	{
		int temp=0;
		int reminFlow=this.folw-card.getRealFlow();   //卡中可支付的免费流量
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
				temp=folw;
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
		
		/*int temp=flow;
		for(int i=0;i<flow;i++)
		{
			if(this.folw-card.getRealFlow()>=1)
			{
				//套餐内剩余流量可以支持使用1M流量
				card.setRealFlow(card.getRealFlow()+1);//实际使用流量+1；
			}
			else if(card.getMoney()>=0.1)
			{
				card.setRealFlow(card.getRealFlow()+1);
				card.setMoney(Common.sub(card.getMoney(), 0.1));//账户消费0.1元
				card.setConsumAmount(card.getConsumAmount()+0.1);
			}
			else
			{
				temp=i;
				throw new Exception("本次已经使用流量"+i+"MB,您的余额不足，请充值后使用！");
					
			}
		}
		return temp;*/
	}
	/**
	 * 提供上网服务2
	 */
	public void netplay2(int flow,MoblieCard card) throws Exception
	
	{
		/*int reminFlow=this.folw-card.getRealFlow();   //卡中可支付的免费流量
		//判断上网流量是否足够支付本次上网服务
		if(this.folw<=reminFlow)
			//流量足够，修改该卡实际上网流量数据
		{
			card.setRealFlow(card.getRealFlow()+folw);
		}
		double externConsumeMoney=0.1*(flow-reminFlow);
		//该卡账户余额足够支付，修改该卡实际使用的上网流量，账户余额，当月消费金额
		if(card.getMoney()>=externConsumeMoney)
		{
			//消耗的流量增加
			card.setRealFlow(card.getRealFlow()+flow);
			//当前账户余额减去额外消费金额
			card.setMoney(card.getMoney()-externConsumeMoney);
			//消费金额增加
			card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
		}
		else
		{
			int temp=(int)(card.getMoney()/0.1);
			throw new Exception("您的余额不足，请充值后使用！");
		}*/
	}
	

	
	

}
