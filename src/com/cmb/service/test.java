package com.cmb.service;

import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class test {
	
	
	public static void main(String[] args) throws Exception {
		
		String writerCsvFilePath = "src/resource/writer.csv";
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath, ',', Charset.forName("UTF-8"));
        String[] contents = {"Lily","��һ","90","Ů"};
        csvWriter.writeRecord(contents);
        csvWriter.close();


        String readerCsvFilePath = "src/resource/reader.csv";
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders(); // ������ͷ   �����Ҫ��ͷ�Ļ�����Ҫд��䡣
        String[] head = csvReader.getHeaders(); //��ȡ��ͷ
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
