import java.util.Scanner;
class StudentGrade
{   
    static char gradeCalculation(double avg)
    {
        if(avg>=75){
            return 'A';
        }
        else if(avg>=65){
            return 'B';
        }
        else if(avg>=55){
            return 'C';
        }
        else if(avg>=35){
            return 'S';
        }
        else {
            return 'W';
        }
    }


    public static void main(String args[])
    {   int subject;
        int sum=0;
        double avg=0;
        char grade;

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of subjects: ");
        subject=sc.nextInt();

        int marks[]=new int[subject];

        for(int i=0;i<subject;i++)
        {
            System.out.println("Enter the marks for "+(i+1)+":");
            marks[i]=sc.nextInt();
            sum=sum+marks[i];
        }

        avg=sum/subject;
        grade=gradeCalculation(avg);

        System.out.println("Total marks: "+sum);
        System.out.println("Average Percentage: "+avg+"%");
        System.out.println("Grade: "+grade);
    }
}