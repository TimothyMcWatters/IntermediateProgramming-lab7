/**
This program:
Creates a simple Grade class and then writes Grade Object data to a RandomAccessFile object

@author Timothy McWatters
@version 1.0

COP3022    Lab 7
File Name: Grade.java
*/

public class Grade {
	private byte studentID = 0;
	private double grade = 0.0;
	
	/**
	 * Default constructor for the Grade class
	 */
	public Grade() {
		setStudentID((byte) 0);
		setGrade(0.0);
	} // end of default constructor for the Grade class
	
	/**
	 * Parameterized constructor for the Grade class
	 * @param studentID = The students ID to set.
	 * @param grade = The grade to set.
	 */
	public Grade(byte studentID, double grade) {
		setStudentID(studentID);
		setGrade(grade);
	} // end of parameterized constructor for the Grade class

	/**
	 * @return the studentID
	 */
	public byte getStudentID() {
		return studentID;
	} // end of getStudentID method

	/**
	 * @param studentID = the studentID to set
	 */
	public void setStudentID(byte studentID) {
		this.studentID = studentID;
	} // end of setStudentID method

	/**
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	} // end of getGrade method

	/**
	 * @param grade = the grade to set
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	} // end of setGrade method

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Student %d grade is %.1f", getStudentID(), getGrade());
	} // end of toString method
	
} // end of Grade class
