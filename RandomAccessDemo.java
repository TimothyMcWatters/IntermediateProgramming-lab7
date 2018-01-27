import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
This program:
Creates a simple Grade class and then writes Grade Object data to a RandomAccessFile object

@author Timothy McWatters
@version 1.0

COP3022    Lab 7
File Name: RandomAccessDemo.java
*/
public class RandomAccessDemo {
	
	public static void main(String[] args) throws IOException {
		// name of the RAF
		final String FILE_NAME = "Grades.dat"; 
		// read input from the keyboard
		Scanner keyboard = new Scanner(System.in).useDelimiter("\n|[\r\n]+");
		// creates a read/writable RAF 
		RandomAccessFile ioStream = new RandomAccessFile(FILE_NAME, "rw");
		
		// create 8 Grade objects
		Grade grade1 = new Grade((byte) 1, 45.6);
		Grade grade2 = new Grade((byte) 2, 88.6);
		Grade grade3 = new Grade((byte) 3, 98.3);
		Grade grade4 = new Grade((byte) 4, 45.5);
		Grade grade5 = new Grade((byte) 5, 78.0);
		Grade grade6 = new Grade((byte) 6, 89.1);
		Grade grade7 = new Grade((byte) 7, 71.9);
		Grade grade8 = new Grade((byte) 8, 90.0);
		
		// Write Grade objects to the RAF
		System.out.println("\nWriting the grades to file...");
		ioStream.writeUTF(String.format("%5d", grade1.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade1.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade2.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade2.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade3.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade3.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade4.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade4.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade5.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade5.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade6.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade6.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade7.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade7.getGrade())); //7
		ioStream.writeUTF(String.format("%5d", grade8.getStudentID())); //7
		ioStream.writeUTF(String.format("%5.1f", grade8.getGrade())); //7
		System.out.println("Done writing grades to file.\n");
		
		// used in calculations of the RAF file and record sizes
		final int STUDENT_ID_SIZE = 7;
		final int GRADE_SIZE = 7;
		final int RECORD_SIZE = STUDENT_ID_SIZE + GRADE_SIZE;
		long fileSize = ioStream.length();
		int numberOfRecords = (int)(fileSize / RECORD_SIZE);
		// used in reading records back from the RAF
		String output;
		// prints the RAF size & how many records are available for search
		System.out.println("File Size: " + ioStream.length());
		System.out.println("Number of records: " + numberOfRecords);
		
		// do while loop to continue to look up records until told to quit
		boolean again = false;
		do {
			again = false;
			int gradePlacementInFile = 0;
			
			// will ask for a record number to look up from (1 to number of records available), until a valid response is entered
			boolean inputNumberAgain = false;
			do{
				inputNumberAgain = false;
				try {
					System.out.printf("\nWhat # record in the file would you like to view? (1 though %d): ", numberOfRecords);
					gradePlacementInFile = keyboard.nextInt();
					if ((gradePlacementInFile < 1) || (gradePlacementInFile > numberOfRecords)) {
						throw new NumberFormatException("You have entered an invalid option, please try again.");
					} 
				} catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage());
					inputNumberAgain = true;
				}
			} while (inputNumberAgain);
			// reads the student ID from the record selected
			ioStream.seek((gradePlacementInFile - 1) * RECORD_SIZE);		
			output = ioStream.readUTF();
			String studentID = output.trim();
			// reads the grade from the record selected
			ioStream.seek(((gradePlacementInFile - 1) * RECORD_SIZE) + STUDENT_ID_SIZE);		
			output = ioStream.readUTF();
			String grade = output.trim();
			// prints out the record information
			System.out.printf("\nGrade #%d is %.1f and belongs to student %d\n", gradePlacementInFile, Double.parseDouble(grade), Byte.parseByte(studentID));
			// asks if you want another record or to quit
			System.out.println("\nType '1' to look up another grade, or '2' to quit.");
			int menuResponse = keyboard.nextInt();
			if (menuResponse == 1) {
				again = true;
			} else {
				System.out.println("You have elected to quit the program, Good Bye!");
				again = false;
			}
		} while (again);

		ioStream.close();
		
	} // end of main method
	
} // end of RandomAccessDemo class
