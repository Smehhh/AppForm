package credit.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Client
{
    @NotBlank(message = "Необходимо ввести фамилию")
    @Pattern(regexp = "([А-ЯЁ][а-яё]*(-[А-ЯЁ][а-яё]*)?)?", message = "Фамилия должна состоять только из букв русского алфавита" )
    @Size(min = 1, max = 20, message = "Длина фамилии от 1 до 200 букв")
    private String surname = "";
    
    @NotBlank(message = "Необходимо ввести имя")
    @Pattern(regexp = "([А-ЯЁ][а-яё]*)?",
            message = "Имя должно состоять только из букв русского алфавита" )
    @Size(min = 1, max = 20, message = "Длина имени от 1 до 200 букв")
    private String forename = "";
    
    
    @NotBlank(message = "Необходимо ввести отчество")
    @Pattern(regexp = "([А-ЯЁ][а-яё]*)?", message = "Отчество должно состоять только из букв русского алфавита или отсутствовать" )
    @Size(min = 0, max = 20, message = "Длина отчества от 0 до 200 букв")
    private String patronym = "";
    
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "" )
    @Size(min = 0, max = 20, message = "Длина адреса до 129 символов") 
    private String mail = "";
    
     @Pattern(regexp = "^7\\d{10}$", message = "Телефонный номер должен начинаться с 7 или +7 и содержать 11 цифр" )
    @Size(min = 0, max = 20, message = "") 
    private String number = "";
    
    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    @Max(value = 150, message = "Возраст должен быть не более 150 лет")
    private int age;
    
    @PositiveOrZero(message = "Доход не может быть отицательным")
    private int income;
    
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }
    public String getPatronym() {
        return patronym;
    }
    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
     public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }
    public void setIncome(int income) {
        this.income = income;
    }
}
