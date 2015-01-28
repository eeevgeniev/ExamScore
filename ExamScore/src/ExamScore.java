import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ExamScore {
	
	private static ArrayList<Exam> exams = new ArrayList<Exam>();
	
	public static void main(String[] args) {
		
		int counter = 1;
		
		String firstLine = "";
		
		boolean toReadNext = true;
		
		Scanner consoleReader = new Scanner(System.in);
		
		firstLine = consoleReader.nextLine();
		
		while (toReadNext) {
			
			if (counter < 3) {
				
				consoleReader.nextLine();
				
				counter +=1;
				
			}
			else {
				
				String line = consoleReader.nextLine();
					
				if (!line.equals(firstLine)) {
					
					addNewGrade(line);
					
				}
				else{
					
					toReadNext = false;
					
				}
				
			}
			
		}
		
		consoleReader.close();
		
		for (int a = 0; a < exams.size(); a++) {
			
			for (int b = a  + 1; b < exams.size(); b++) {
				
				Exam firstExam = exams.get(a);
				Exam secondExam = exams.get(b);
				
				if (firstExam.examScore > secondExam.examScore) {
					
					exams.set(a, secondExam);
					exams.set(b, firstExam);
					
				}
				
			}
			
		}
		
		for (int i = 0; i < exams.size(); i++) {
			
			System.out.println(exams.get(i).toString());
			
		}
		
	}
	
	private static void addNewGrade(String line) {
		
		boolean isAdded = false;
		
		String[] results = line.split("\\s*\\|\\s*");
	
		String name = results[1];
		int score = Integer.parseInt(results[2]);
		double grade = Double.parseDouble(results[3]);
		
		for (int i = 0; i < exams.size(); i++) {
			
			Exam currentExam = exams.get(i);
			
			if (score == currentExam.examScore) {
				
				isAdded = true;
				
				currentExam.names.add(name);
				currentExam.grades.add(grade);
				
			}
			
		}
		
		if (!isAdded) {
			
			Exam newExam = new Exam(score, name, grade);
			exams.add(newExam);
			
		}
		
	}
	
}

class Exam {
	
	public int examScore;
	public ArrayList<String> names = new ArrayList<String>();
	public ArrayList<Double> grades = new ArrayList<Double>();
	
	public Exam(int newScore, String newName, double newGrade) {
		
		this.examScore = newScore;
		this.names.add(newName);
		this.grades.add(newGrade);
		
	}
	
	public void sortNames() {
		
		Collections.sort(names);

	}
	
	public double avgGrade() {
		
		double gradeAvg = 0D;
		
		for (int i = 0; i < grades.size(); i++) {
			
			gradeAvg += grades.get(i);
			
		}
		
		double count = grades.size();
		
		return gradeAvg / count;
		
	}
	
	@Override public String toString() {
		
		this.sortNames();
		
		double gradeAVG = this.avgGrade();
		
		String stringToReturn = Integer.toString(examScore) + " -> [";
		
		for (int i = 0; i <names.size(); i ++) {
			
			if ( i != names.size() - 1) {
				
				stringToReturn += names.get(i) + ", ";
				
			}
			else {
				
				stringToReturn += names.get(i);
				
			}
		}
		
		stringToReturn += "]; avg=" + Double.toString(gradeAVG);
		
		return stringToReturn;
	}
	
}


