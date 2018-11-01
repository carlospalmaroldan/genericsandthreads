package generics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    PeopleService peopleService;

    @RequestMapping(value = "/")
    public List<PersonDTO> getPeople() throws SQLException {
        List<PersonDTO> person=peopleService.getPeople();
        return  person;
    }

    @RequestMapping(value="/commentsPerPerson")
    public Map<PersonDTO,List<CommentDTO>> getCommentsPerPerson() throws SQLException{
        return peopleService.getCommentsPerSpitter();
    }

    @RequestMapping(value="/commentsPerPersonFunctional")
    public Map<PersonDTO,List<CommentDTO>> getCommentsPerPersonFunctional() throws SQLException{
        return peopleService.getCommentsPerSpitterFunctional();
    }

    @RequestMapping(value = "/createPerson")
    public void createPerson(@RequestBody PersonDTO personDTO) throws SQLException{
        peopleService.createPerson(personDTO);
    }

    /*{
  "username":"adrian",
  "password":"356",
  "fullname":"adrian belew",
  "email": "adrian@gmail.com"
   }*/
}
