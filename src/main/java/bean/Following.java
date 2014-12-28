package bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Following

{
    private List<Industry> industries;
    private List<Company> companies;
    private List<Profile> people;


    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Profile> getPeople() {
        return people;
    }

    public void setPeople(List<Profile> people) {
        this.people = people;
    }
}
