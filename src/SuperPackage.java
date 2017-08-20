
public class SuperPackage	extends ServicePackage  implements NetService,SendService,CallService
{
	private int talkTime;
	private int flow;
	private int smsCount;
	@Override
	public void showInfo() 
	{
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ��"+this.talkTime+"����/�£���������Ϊ��"+this.smsCount+"��/�£���������Ϊ��"+this.flow/1024+"GB/��");
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
	}

	@Override
	public int sendMessage(int count, MoblieCard card) throws Exception
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
	}

	@Override
	public int netPlay(int flow, MoblieCard card) throws Exception 
	{
		int temp=0;
		int reminFlow=this.flow-card.getRealFlow();   //���п�֧�����������
		//�ж����������Ƿ��㹻֧��������������
		if(flow<=reminFlow)
			//�����㹻���޸ĸÿ�ʵ��������������
		{
			card.setRealFlow(card.getRealFlow()+flow);
			 temp=flow;
		}
		else
		{
			//�ײ����������㣬�������Ҫ����0.1Ԫ/MB����
			double externConsumeMoney=(flow-reminFlow)*0.1;
			//�ÿ��˻�����㹻֧�����޸ĸÿ�ʵ��ʹ�õ������������˻����������ѽ��
			if(card.getMoney()>=externConsumeMoney)
			{
				//���ĵ���������
				card.setRealFlow(card.getRealFlow()+flow);
				//��ǰ�˻�����ȥ�������ѽ��
				card.setMoney(card.getMoney()-externConsumeMoney);
				//���ѽ������
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp=flow;
			}
			//���ÿ����˻�������֧�����������ʱ���������Ѽ�ȥʣ��ģ����������Ϊ����
			else
			{
				//���ĵ���������
				card.setRealFlow(card.getRealFlow()+flow);
				//�������Ϊ����
				card.setMoney(card.getMoney()-externConsumeMoney);
				card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
				temp= flow;
				System.out.println("�Բ��������˻����㣬�Ѿ�Ƿ�� "+Common.dataFormat(card.getMoney())+"Ԫ��"+"�����ײ�������"+(externConsumeMoney/0.1)+"MB "+"�뼰ʱ��ֵ��");
			}
		}
		return temp;
	}
	

}
