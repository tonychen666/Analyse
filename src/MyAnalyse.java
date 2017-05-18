import java.io.FileWriter;
import java.util.Formatter;

/**
 * Created by wangdong on 2017/5/11.
 *
 * 具体分析代码
 */
public class MyAnalyse {


    //待处理的文本，字符数组存储
    private char[] test;

    public MyAnalyse(StringBuilder demo)
    {

        //对象初始化，将接收到的东西转为字符数组
        String temp=demo.toString();

        test=temp.toCharArray();


    }


    //分析的主函数，待完成
    public void analyse()
    {

        //对字符数组test进行分析，只需要循环一次就可以。
        int i=0;
        int line=1;

        StringBuilder stringBuilder=new StringBuilder();

        while(i<test.length)

        {
            switch (test[i])
            {
                case ' ':
                    i++;
                    continue;

                case '\\':
                    if(test[i+1]=='n')
                    {
                        line++;
                        logWrite("EOLN",24);
                        i=i+2;
                        continue;
                    }
                    else
                    {
                        //对错误的纪录

                        continue;
                    }



                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':

                    while(i<test.length&&(ifLetter(test[i])||ifDifit(test[i])))
                    {
                        stringBuilder.append(test[i]);
                        i++;

                    }

                    int kindNUmber=ifKeyString(stringBuilder.toString());
                    if(kindNUmber!=0)
                    {
                        logWrite(stringBuilder.toString(),kindNUmber);

                    }
                    else
                    {
                        logWrite(stringBuilder.toString(),10);

                    }

                    stringBuilder=new StringBuilder();
                    continue;

                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':

                    while(i<test.length&&ifDifit(test[i]))
                    {
                        stringBuilder.append(test[i]);
                        i++;
                    }

                    logWrite(stringBuilder.toString(),11);

                    stringBuilder=new StringBuilder();
                    continue;

                case '=':
                    logWrite(stringBuilder.toString(),12);
                    i++;
                    continue;

                case '<':
                    if(test[i+1]=='>')
                    {
                        logWrite("<>",13);
                        i=i+2;
                    }
                    else
                    if(test[i+1]=='=')
                    {
                        logWrite("<=",14);
                        i=i+2;
                    }
                    else
                    {
                        logWrite("<",15);
                        i++;

                    }
                    continue;

                case '>':
                    if(test[i+1]=='=')
                    {

                        logWrite(">=",16);
                        i=i+2;

                    }

                    else
                    {
                        logWrite(">",17);
                    }
                case '-':
                    logWrite("-", 18);
                    i++;
                    continue;

                case '*':
                    logWrite("*", 19);
                    i++;
                    continue;

                case ':':
                    if(test[i+1]=='=')
                    {

                        logWrite(":=",20);
                        i=i+2;
                    }
                    else
                    {

                        //输出错误，并在文件中纪录
                        System.out.println("error"+line);
                        i++;
                    }

                    continue;

                case '(':
                    logWrite("(", 21);
                    i++;
                    continue;
                case ')':
                    logWrite(")", 22);
                    i++;
                    continue;
                case ';':
                    logWrite(";", 23);
                    i++;
                    continue;
                default:
                    i++;
                    //打印错误的函数调用
                    logError("",line);
                    System.out.println("error++1");

            }



        }

        //在末尾打印end标志
        logWrite("END",25);





    }


    //错误纪录
    public  void logError(String error,int line)

    {
        try{

            //设定错误文件的路径
            String fileName="/Users/wangdong/test.err";

            FileWriter writer = new FileWriter(fileName, true);



            Formatter formatter1=new Formatter(writer);
            //执行写入的操作：
            formatter1.format("%16s %d\n",error,line);

            formatter1.close();




        }
        catch (Exception e)
        {

            e.printStackTrace();
        }




    }

    //字符类型的返回
    public  int ifKeyString(String str)
    {

        if(str.equals("begin"))
            return 1;
        if(str.equals("end"))
            return 2;
        if(str.equals("integer"))
            return 3;
        if(str.equals("if"))
            return 3;
        if(str.equals("then"))
            return 5;
        if(str.equals("else"))
            return 6;
        if(str.equals("function"))
            return 7;
        if(str.equals("read"))
            return 8;
        if(str.equals("write"))
            return 9;

        return 0;


    }


    //二元式的写入
    public  void logWrite(String str,int kindNumber)
    {


        //先打印出来，最后直接持久化到test.dyd文件里面

        System.out.printf("%16s %2d\n",str,kindNumber);
//
//        try{
//
//            Formatter f = new Formatter(System.out);
//
//            //设定生成二元式的文件路径
//            String fileName="/Users/wangdong/test.dyd";
//
//
//            //利用formatter包装器
//            FileWriter writer = new FileWriter(fileName, true);
//            Formatter formatter = new Formatter(writer);
//            // 这样也可以，加了个buffer缓冲包装 Formatter formatter1 = new Formatter(new BufferedOutputStream(writer));
//            formatter.format("%16s %2d\n",str,kindNumber);
//
//            //直接利用formatter来进行文件的关闭操作
//            formatter.close();
//
//
//
//
//        }
//        catch (Exception e)
//        {
//
//            e.printStackTrace();
//        }


    }


    public static boolean ifLetter(char character)
    {
        if ((character >= 'a'&&character <= 'z') || (character >= 'A'&&character <= 'Z'))
            return true;
        else
            return false;
    }

    //字母的判断
    public static boolean ifDifit(char character)
    {
        if((character>='0')&&(character<='9'))
            return true;
        else
            return false;

    }







}
