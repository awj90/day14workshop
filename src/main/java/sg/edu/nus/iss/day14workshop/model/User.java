package sg.edu.nus.iss.day14workshop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User implements Serializable {
    
    @NotBlank(message="Name cannot be blank")
    @Size(min=3, max=64, message="Name must have between 3 to 64 characters")
    private String name;

    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid Email")
    private String email;

    @NotBlank(message="Phone Number cannot be blank")
    @Size(min=7, message="Phone Number must have at least 7 digits")
    private String phoneNumber;

    @Past(message="Date of birth cannot be in the future")
    @NotNull(message="Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Min(value=10, message="User must be at least 10 years old")
    @Max(value=100, message="User cannot be more than 100 years old")
    private int age;

    private String id;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
