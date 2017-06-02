import java.io.BufferedReader;
import java.io.FileReader;

/*


 */

public class Main {

    public static void main(String[] args) {

        //从文件中读取文本，采用Buffer缓冲全部读取

        try{

            FileReader fileReader=new FileReader("/Users/wangdong/test.pas");



            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String input=null;

            StringBuilder  text=new StringBuilder();

            while((input=bufferedReader.readLine())!=null)
            {


                text.append(input+" \\n");
            }


            //实例化对象，并调用分析函数
            MyAnalyse myAnalyse=new MyAnalyse(text);

            myAnalyse.analyse();




//            System.out.println(text.toString());

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }


    }
}
