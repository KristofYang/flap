

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("doGet");
		
		String realPath = request.getServletContext().getRealPath("1.txt");
//		String resUri = request.getRequestURI();
//		System.out.println(resUri);
		
		System.out.println(realPath);
		
		CsvReader reader = new CsvReader(realPath,',',Charset.forName("UTF-8"));
		String[] header = {};
		while (reader.readRecord()) {
            //把头保存起来
           if (reader.getCurrentRecord()==0){
                header = reader.getValues();
            }
            //获取当前记录位置
            System.out.print(reader.getCurrentRecord() + ".");
            //读取一条记录
            System.out.println(reader.getRawRecord());
            String[] tmp = {reader.getValues()[0],reader.getValues()[1]};
        }
		System.out.println(header.toString());
        reader.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("ddd");
	}
	
public static void main(String[] args) throws Exception {
		
		String writerCsvFilePath = "src/resource/writer.csv";
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath, ',', Charset.forName("UTF-8"));
        String[] contents = {"Lily","五一","90","女"};
        csvWriter.writeRecord(contents);
        csvWriter.close();


        String readerCsvFilePath = "src/resource/reader.csv";
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        String[] head = csvReader.getHeaders(); //获取表头
        while (csvReader.readRecord())
        {
            for (int i = 0; i < head.length; i++)
            {
                System.out.println(head[i] + ":" + csvReader.get(head[i]));
            }

        }
        csvReader.close();
	}

}
