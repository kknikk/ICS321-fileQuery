/**
 * Class for Line object.
 * @author kurtnikaitani
 *
 */

public class Line  implements java.io.Serializable{
  
 
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private char gender;
  private float fifth;
  private int fourth;
  private String date;
  
  //constructor
  public Line(String firstName, String lastName, char gender, int fourth, float fifth, String date) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.fourth = fourth;
    this.fifth = fifth;
    
    this.date = date;
  }
  
  public Line(){
    super();
    this.firstName = null;
    this.lastName = null;
    this.gender = 0;
    this.fourth = 0;
    this.fifth = 0;
    this.date = null;
  }
  
  //getter and setters
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public float getFifth() {
    return fifth;
  }
  public void setFifth(float fifth) {
    this.fifth = fifth;
  }
  public int getFourth() {
    return fourth;
  }
  public void setFourth(int fourth) {
    this.fourth = fourth;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  
  @Override
  public String toString() {
    return ""+ firstName + "," + lastName + "," + gender + "," + fourth
        + "," + fifth + "," + date + "";
  }
  

}
