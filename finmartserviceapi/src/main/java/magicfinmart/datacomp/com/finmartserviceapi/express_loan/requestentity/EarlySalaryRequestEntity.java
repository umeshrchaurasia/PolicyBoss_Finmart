package magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity;

/**
 * Created by Vinit Chindarkar on 05-10-2018.
 */

public class EarlySalaryRequestEntity {
    /**
     * FirstName : test
     * LastName : abc test
     * phoneNumber : 9019452735
     * Age : 32
     * email : ni7i7n@test.com
     * City : Mumbai
     * RefferalCode : 0
     * Employment : Salary
     * MonthlySalary : 50000
     * LoanAmount : 500000
     * brokerid : 0
     * BankId : 1
     * LoanType : car loan
     * FBAID : 12
     * ApplicationID : 0
     */

    private String FirstName;
    private String LastName;
    private String phoneNumber;
    private String Age;
    private String email;
    private String City;
    private String RefferalCode;
    private String Employment;
    private String MonthlySalary;
    private String LoanAmount;
    private String brokerid;
    private String BankId;
    private String LoanType;
    private String FBAID;
    private String ApplicationID;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getRefferalCode() {
        return RefferalCode;
    }

    public void setRefferalCode(String RefferalCode) {
        this.RefferalCode = RefferalCode;
    }

    public String getEmployment() {
        return Employment;
    }

    public void setEmployment(String Employment) {
        this.Employment = Employment;
    }

    public String getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(String MonthlySalary) {
        this.MonthlySalary = MonthlySalary;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String LoanAmount) {
        this.LoanAmount = LoanAmount;
    }

    public String getBrokerid() {
        return brokerid;
    }

    public void setBrokerid(String brokerid) {
        this.brokerid = brokerid;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String BankId) {
        this.BankId = BankId;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String LoanType) {
        this.LoanType = LoanType;
    }

    public String getFBAID() {
        return FBAID;
    }

    public void setFBAID(String FBAID) {
        this.FBAID = FBAID;
    }

    public String getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(String ApplicationID) {
        this.ApplicationID = ApplicationID;
    }
}
