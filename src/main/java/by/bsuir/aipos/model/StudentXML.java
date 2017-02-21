package by.bsuir.aipos.model;

public class StudentXML {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String homeAddress;
    private StudentGroupXML studentGroup;

    public StudentXML(){
    }

    public StudentXML(String firstName, String lastName, String middleName, String dateOfBirth, String homeAddress, StudentGroupXML studentGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public StudentXML(long id, String firstName, String lastName, String middleName, String dateOfBirth, String homeAddress, StudentGroupXML studentGroup) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
        this.studentGroup = studentGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public StudentGroupXML getStudentGroupXML() {
        return studentGroup;
    }

    public void setStudentGroupXML(StudentGroupXML studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return "StudentXML{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", studentGroup='" + studentGroup + '\'' +
                '}';
    }
}
