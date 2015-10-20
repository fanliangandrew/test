import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class HelloClient {
    static Hello helloImpl;

    public static void main(String args[]) {
        try {
            //����һ��ORBʵ��
            ORB orb = ORB.init(args, null);

            //��ȡ������������
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            //�������������л�ȡ�ӿ�ʵ�ֶ���
            String name = "Hello";
            helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

            //���ýӿڶ���ķ���
            System.out.println("Obtained a handle on server object: " + helloImpl);
            System.out.println(helloImpl.sayHello());
            helloImpl.shutdown();

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}