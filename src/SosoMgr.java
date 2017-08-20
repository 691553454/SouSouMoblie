import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ҵ���� 2017/07/30
 * ʵ�����˵�
 * ʵ�ֶ����˵�
 * ʵ���û���¼�����֤
 * @author Administrator
 * ��֪����1����ֵ��¼�޷���ӡ�����⣡
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
		  System.out.println("ллʹ��");
	}
	
	public SosoMgr()
	{
		super();
	}

	/**
	 * ���˵�
	 * ��֪���⣺ѡ��ʱ���������������ʱ��������
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
			System.out.println("*****************************��ӭʹ�����ƶ�ҵ�����***********************************");
			System.out.println("\t1. �û���¼\t2. �û�ע�� \t3. ʹ���� \t 4. ���ѳ�ֵ\t 5. �ʷ�˵��\t 6.�ͻ���ѯ\t 7.�˳�ϵͳ");
			System.out.print("��ѡ��");
			Scanner input =new	Scanner(System.in);
		   choice=input.nextInt();
				switch(choice)
				{
				   case 1:
					   System.out.print("�������ֻ����ţ�");
					    num=input.next();
					   System.out.print("���������룺");
					    pwd=input.next();
					    if(utils.isExitCard(num, pwd))
					    {
					    	 cardMenu(num);
					    }
					    else
					    {
					    	System.out.println("�Բ����˺Ż��������");
					    }
					 continue;
				   case 2:
					   registCard();
					   continue;
				   case 3:
					   System.out.println("�������ֻ����ţ�");
					   num=input.next();
					   if(utils.isExistCard(num))
					   {
						   utils.userSoso(num);
					   }
					   else
					   {
						   System.err.println("�Բ��𣬸ÿ���δע�ᣡ");
					   }
					  continue;
				   case 4:
					   System.out.println("�������ֵ���ţ�");
					   num=input.next();
					   if(utils.isExistCard(num))
					   {
						   System.out.println("�������");
						   double money=input.nextDouble();
						   utils.chargeMoney(num, money);
					   }
					   else
					   {
						   System.out.println("�Բ�����Ҫ��ֵ�Ķ��δע�ᣬ�޷���ֵ");
					   }
					   continue;
				   case 5:
					   System.out.println("\n***********�ʷ�˵��***********");
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
				       	    System.out.println("ллʹ��");
				       	    System.exit(1);
				       	    continue;
				   default:
					   System.out.println("���벻�Ϸ� !");
					   break;
				}
      		break;
		}while(true);
	}
	
	/**
	 * �����˵�
	 * �����������ϼ��˵�
	 *����String���������ַ�����������α���---�뷨���󣡣���
	 */
	public int cardMenu(String num)
	{
		int choice=0;
		do
		{
		System.out.println("*********���ƶ��û��˵�**********");
		System.out.println("1.�����˵���ѯ");
		System.out.println("2.�ײ�������ѯ");
		System.out.println("3.��ӡ�����굥");
		System.out.println("4.�ײͱ��");
		System.out.println("5.��������");
		System.out.print("��ѡ��(����1-5ѡ���ܣ�������������һ��)��");
		Scanner input =new	Scanner(System.in);
		choice=input.nextInt();
				switch(choice)
				{
				   case 1:
					   System.out.println("\n***************�����˵���ѯ*****************");
					   utils.showAmountDetail(num);
					  continue;
				   case 2:
					   System.out.println("\n***************�ײ�������ѯ*****************");
					   utils.showRemainDetail (num);
					   continue;
				   case 3:
					   System.out.println("\n***************��ӡ�����굥*****************");
					   utils.printConsumInfo(num);
					   continue;
				   case 4:
					   System.out.println("\n***************�ײͱ��*****************");
					   System.out.println("1.�����ײ�  2.�����ײ� 3.�����ײ�    ��ѡ����ţ�");
					   utils.changingPack(num, input.nextInt());
					   continue;
				   case 5:
					   System.out.println("\n***************�������� ***************");
					   utils.delCard(num);
					   System.out.println("ллʹ��");
					   System.exit(1);
			    }
				break;
	}while(true);
		return choice;
	}
	/**
	 * ע���¿�����
	 */
	public void registCard()
	{
		String arr[]=utils.getNewNumbers(9);
		System.out.println("***************��ѡ��Ŀ���������ʾ*******************");
		//��ʽ���������
		for (int i = 0; i < arr.length; i++)
		{
				System.out.print((i+1)+"."+arr[i]+"\t    ");
				if((i+1)%3==0)
				{
					System.out.println();
				}
		}
		//ѡ���ֻ���,�洢��number��
		System.out.print("��ѡ�񿨺ţ�����1-9����ţ���   ");
		String number=arr[input.nextInt()-1];
		//ѡ���ײ�����
		   System.out.println("\n***************�ײ�ѡ��*****************");
		   System.out.println("1.�����ײ�  2.�����ײ� 3.�����ײ�    ��ѡ����ţ�");
		   //��ȡ�ײͶ���
		   ServicePackage pack=utils.createPack(input.nextInt());
		   System.out.print("�����û��� ");
		   String name=input.next();
		    //��������
		   System.out.print("����������:  ");
		   String password=input.next();
		   //����Ԥ�滰�ѽ��
		   double money=0;
		   System.out.print("������Ԥ�滨�ѽ�");
		   money=input.nextDouble();
		   while(money<pack.getPrice())
		   {
			   System.out.print("��Ԥ��Ļ��Ѳ�����֧�������ײ��ʷѣ������³�ֵ�� ");
			   money=input.nextDouble();
		   }
		   //�����¿��������
		  MoblieCard  newcard=new MoblieCard(name, password, number, pack, pack.getPrice(),money-pack.getPrice() );
		  utils.addCard(newcard);
	}
}
