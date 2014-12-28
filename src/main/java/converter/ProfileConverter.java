package converter;

import bean.Connection;
import bean.Group;
import bean.Profile;
import com.google.code.linkedinapi.schema.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class converting Person object from LinkedIn to Profile object.
 */
public class ProfileConverter {

    /**
     * Converting Person object to Profile Object.
     *
     * @param person
     * @return Profile object
     */

    public Profile convertProfileOfPerson(Person person) {
        Profile profile = new Profile();
        profile.setId(person.getId());
        profile.setEmailAddress(person.getEmailAddress());
        profile.setFirstName(person.getFirstName());
        profile.setLastName(person.getLastName());
        profile.setFormattedName(person.getFormattedName());
        profile.setHeadLine(person.getHeadline());
        profile.setCurrentStatus(person.getCurrentStatus());
        profile.setIndustry(person.getIndustry());
        profile.setNumConnections(person.getNumConnections());
        profile.setInterests(person.getInterests());
        profile.setPictureUrl(person.getPictureUrl());
        profile.setSummary(person.getSummary());
        connectionConverter(person, profile);
        skillConverter(person, profile);
        languageConverter(person, profile);
        coursesConverter(person, profile);
        educationConverter(person, profile);
        groupConverter(person, profile);
        positionConverter(person, profile);
        return profile;
    }

    /**
     * Converting Positions Data.
     *
     * @param person
     * @param profile
     */
    private void positionConverter(Person person, Profile profile) {
        if (person.getPositions() != null) {
            List<bean.Position> positions = new ArrayList<bean.Position>();
            for (Position personPosition : person.getPositions().getPositionList()) {
                bean.Position position = new bean.Position();
                position.setId(personPosition.getId());
                position.setCurrent(personPosition.isIsCurrent());
                position.setDescription(personPosition.getDescription());
                position.setSummary(personPosition.getCompany().getName());
                position.setTitle(personPosition.getTitle());
                position.setImageUrl(personPosition.getCompany().getLogoUrl());
                bean.StartDate startDate = new bean.StartDate();
                if (personPosition.getStartDate() != null) {
                    startDate.setMonth(personPosition.getStartDate().getMonth());
                    startDate.setYear(personPosition.getStartDate().getYear());
                    startDate.setDay(personPosition.getStartDate().getDay());
                }
                bean.EndDate endDate = new bean.EndDate();
                if (personPosition.getEndDate() != null) {
                    endDate.setDay(personPosition.getEndDate().getDay());
                    endDate.setYear(personPosition.getEndDate().getYear());
                    endDate.setMonth(personPosition.getEndDate().getMonth());
                }
                position.setStartDate(startDate);
                position.setEndDate(endDate);
                positions.add(position);
            }
            profile.setPositions(positions);
        }
    }

    /**
     * Converting group data.
     *
     * @param person
     * @param profile
     */
    private void groupConverter(Person person, Profile profile) {
        if (person.getMemberGroups() != null) {
            List<Group> groups = new ArrayList<Group>();
            for (MemberGroup personGroup : person.getMemberGroups().getMemberGroupList()) {
                Group group = new Group();
                if (personGroup.getGroup() != null) {
                    group.setId(personGroup.getGroup().getId());
                    group.setName(personGroup.getGroup().getName());
                    group.setShortDescription(personGroup.getGroup().getShortDescription());
                    group.setSmallImageUrl(personGroup.getGroup().getSmallLogoUrl());
                    group.setLargeImageUrl(personGroup.getGroup().getLargeLogoUrl());
                }
                groups.add(group);
            }
            profile.setGroups(groups);
        }
    }

    /**
     * Converting education data.
     *
     * @param person
     * @param profile
     */
    private void educationConverter(Person person, Profile profile) {
        if (person.getEducations() != null) {
            List<bean.Education> educations = new ArrayList<bean.Education>();
            for (Education personEducation : person.getEducations().getEducationList()) {
                bean.Education education = new bean.Education();
                education.setId(personEducation.getId());
                education.setActivities(personEducation.getActivities());
                education.setDegree(personEducation.getDegree());
                bean.StartDate startDate = new bean.StartDate();
                if (personEducation.getStartDate() != null) {
                    startDate.setDay(personEducation.getStartDate().getDay());
                    startDate.setYear(personEducation.getStartDate().getYear());
                    startDate.setMonth(personEducation.getStartDate().getMonth());
                }
                education.setStartDate(startDate);
                bean.EndDate endDate = new bean.EndDate();
                if (personEducation.getEndDate() != null) {
                    endDate.setDay(personEducation.getEndDate().getDay());
                    endDate.setMonth(personEducation.getEndDate().getMonth());
                    endDate.setYear(personEducation.getEndDate().getYear());
                }
                education.setEndDate(endDate);
                education.setFieldOfStudy(personEducation.getFieldOfStudy());
                education.setDegree(personEducation.getDegree());
                education.setSchoolName(personEducation.getSchoolName());
                educations.add(education);
            }
            profile.setEducations(educations);
        }
    }

    /**
     * Coverting Courses data.
     *
     * @param person
     * @param profile
     */
    private void coursesConverter(Person person, Profile profile) {
        if (person.getCourses() != null) {
            List<bean.Course> courses = new ArrayList<bean.Course>();
            for (Course personCourse : person.getCourses().getCoursesList()) {
                bean.Course course = new bean.Course();
                course.setId(personCourse.getId());
                course.setName(personCourse.getName());
                course.setNumber(personCourse.getNumber());
                courses.add(course);
            }
            profile.setCourses(courses);
        }
    }

    /**
     * Converting Language data.
     *
     * @param person
     * @param profile
     */
    private void languageConverter(Person person, Profile profile) {
        if (person.getLanguages() != null) {
            List<bean.Language> languages = new ArrayList<bean.Language>();
            for (Language personLanguage : person.getLanguages().getLanguageList()) {
                bean.Language language = new bean.Language();
                language.setId(personLanguage.getId());
                if (personLanguage.getLanguage() != null)
                    language.setName(personLanguage.getLanguage().getName());
                if (personLanguage.getProficiency() != null)
                    language.setProficiency(personLanguage.getProficiency().getName());
                languages.add(language);
            }
            profile.setLanguages(languages);
        }
    }

    /**
     * Converting skills data.
     *
     * @param person
     * @param profile
     */
    private void skillConverter(Person person, Profile profile) {
        if (person.getSkills() != null) {
            List<bean.Skill> skills = new ArrayList<bean.Skill>();
            for (Skill personSkill : person.getSkills().getSkillList()) {
                bean.Skill skill = new bean.Skill();
                skill.setId(personSkill.getId());
                if (personSkill.getSkill() != null)
                    skill.setName(personSkill.getSkill().getName());
                skills.add(skill);
            }
            profile.setSkills(skills);

        }
    }

    /**
     * Converting Education data.
     *
     * @param person
     * @param profile
     */
    private void connectionConverter(Person person, Profile profile) {
        if (person.getConnections() != null) {
            List<Connection> Connections = new ArrayList<Connection>();
            for (Person personConnection : person.getConnections().getPersonList()) {
                Connection profileConnection = new Connection();
                profileConnection.setPictureUrl(personConnection.getPictureUrl());
                profileConnection.setFormatName(personConnection.getFormattedName());
                profileConnection.setFirstName(personConnection.getFirstName());
                profileConnection.setLastName(personConnection.getLastName());
                profileConnection.setHeadLine(personConnection.getHeadline());
                Connections.add(profileConnection);
            }
            profile.setConnections(Connections);
        }
    }


}
