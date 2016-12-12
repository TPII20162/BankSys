package banksys.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResetSQLiteDataBase {
	
	private static final String TEMPLATE_BANKSYS_SQLITE = "template_banksys.sqlite";
	private static final String BANKSYS_DB = "banksys.sqlite";

	public static void resetDataBase()
	{
		File dbFile = new File(BANKSYS_DB);
		if(dbFile.exists())
		{
			dbFile.delete();
		}
		
		copyFile(TEMPLATE_BANKSYS_SQLITE, BANKSYS_DB);
		
		//System.out.println("Database was reseted.");
	}

	private static void copyFile(String source, String destiny) {
		InputStream inStream = null;
		OutputStream outStream = null;

		File sourceFile = new File(source);
		File destinyFile = new File(destiny);
		try {
			inStream = new FileInputStream(sourceFile);
			outStream = new FileOutputStream(destinyFile);

			byte[] buffer = new byte[1024];

			int length;
			
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error on ResetSQLiteDataBase.copyFile: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error on ResetSQLiteDataBase.copyFile: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
