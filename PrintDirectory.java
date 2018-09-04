import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PrintDirectory {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter path of folder: ");
		String path = reader.nextLine();
		final File folder = new File(path);
		try {
			toCSV(folder);
			toTXT(folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
	}

	public static void toCSV(final File folder) throws IOException {
		File file = new File("/Users/" + System.getProperty("user.name") + "/" + folder.getName() + ".csv");
		file.createNewFile();
		Path out = Paths.get("/Users/" + System.getProperty("user.name") + "/" + folder.getName() + ".csv");
		PrintWriter pr= new PrintWriter(Files.newBufferedWriter(out), true);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				pr.println(fileEntry.getName());
				recursiveCSV(fileEntry, pr);
			} else {
				pr.println(fileEntry.getPath());
			}
			
		}
	}
	
	public static void recursiveCSV(final File folder, PrintWriter pr) throws IOException {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				pr.println(fileEntry.getName());
				recursiveCSV(fileEntry, pr);
			} else {
				pr.println(fileEntry.getPath());
			}
		}
		pr.println("End of " + folder.getName());
	}
	
	public static void toTXT(final File folder) throws IOException {
		File file = new File("/Users/" + System.getProperty("user.name") + "/" + folder.getName() + ".txt");
		file.createNewFile();
		Path out = Paths.get("/Users/" + System.getProperty("user.name") + "/" + folder.getName() + ".txt");
		PrintWriter pr= new PrintWriter(Files.newBufferedWriter(out), true);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				pr.println(fileEntry.getName());
				recursiveTXT(fileEntry, pr, 1);
			} else {
				pr.println(fileEntry.getName());
			}
			
		}
	}
	
	public static void recursiveTXT(final File folder, PrintWriter pr, int i) throws IOException {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				pr.println(new String(new char[i]).replace("\0", "  ") + fileEntry.getName());
				recursiveTXT(fileEntry, pr, i+1);
			} else {
				pr.println(new String(new char[i]).replace("\0", "  ")  + fileEntry.getName());
			}
		}
	}

}
