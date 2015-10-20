import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class HelloClient {
    static Hello helloImpl;

    public static void main(String args[]) {
        try {
            //创建一个ORB实例
            ORB orb = ORB.init(args, null);

            //获取根名称上下文
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            //从命名上下文中获取接口实现对象
            String name = "Hello";
            helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

            //调用接口对象的方法
            System.out.println("Obtained a handle on server object: " + helloImpl);
            System.out.println(helloImpl.sayHello());
            helloImpl.shutdown();

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}