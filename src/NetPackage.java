
public class NetPackage extends ServicePackage implements NetService
{
	 
	private int folw;
	@Override
	public void showInfo()
	{
		System.out.println("�����ײͣ� �����������ǣ�"+this.folw/1024+"GB/��,�ײ��ʷ�Ϊ��"+this.price+"Ԫ/��");//������this.flow��folw��ʲô���𣿣���������
	}
	/**
	 * �޲�
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
	 * �в�
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
		int reminFlow=this.folw-card.getRealFlow();   //���п�֧�����������
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
				temp=folw;
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
		
		/*int temp=flow;
		for(int i=0;i<flow;i++)
		{
			if(this.folw-card.getRealFlow()>=1)
			{
				//�ײ���ʣ����������֧��ʹ��1M����
				card.setRealFlow(card.getRealFlow()+1);//ʵ��ʹ������+1��
			}
			else if(card.getMoney()>=0.1)
			{
				card.setRealFlow(card.getRealFlow()+1);
				card.setMoney(Common.sub(card.getMoney(), 0.1));//�˻�����0.1Ԫ
				card.setConsumAmount(card.getConsumAmount()+0.1);
			}
			else
			{
				temp=i;
				throw new Exception("�����Ѿ�ʹ������"+i+"MB,�������㣬���ֵ��ʹ�ã�");
					
			}
		}
		return temp;*/
	}
	/**
	 * �ṩ��������2
	 */
	public void netplay2(int flow,MoblieCard card) throws Exception
	
	{
		/*int reminFlow=this.folw-card.getRealFlow();   //���п�֧�����������
		//�ж����������Ƿ��㹻֧��������������
		if(this.folw<=reminFlow)
			//�����㹻���޸ĸÿ�ʵ��������������
		{
			card.setRealFlow(card.getRealFlow()+folw);
		}
		double externConsumeMoney=0.1*(flow-reminFlow);
		//�ÿ��˻�����㹻֧�����޸ĸÿ�ʵ��ʹ�õ������������˻����������ѽ��
		if(card.getMoney()>=externConsumeMoney)
		{
			//���ĵ���������
			card.setRealFlow(card.getRealFlow()+flow);
			//��ǰ�˻�����ȥ�������ѽ��
			card.setMoney(card.getMoney()-externConsumeMoney);
			//���ѽ������
			card.setConsumAmount(card.getConsumAmount()+externConsumeMoney);
		}
		else
		{
			int temp=(int)(card.getMoney()/0.1);
			throw new Exception("�������㣬���ֵ��ʹ�ã�");
		}*/
	}
	

	
	

}
