package generics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@org.springframework.stereotype.Service
@Slf4j
public class PeopleService {

    @Autowired
    DataSource mySqlDataSource;


    public  List<PersonDTO>  getPeople() throws SQLException {
       Connection connection= mySqlDataSource.getConnection();
       PreparedStatement preparedStatement=connection.prepareStatement("select * from spitters");
       ResultSet resultSet=preparedStatement.executeQuery();
       List<PersonDTO> people=new ArrayList<>();
       while(resultSet.next()){
           people.add( PersonDTO.builder().id(resultSet.getInt("id"))
                     .username(resultSet.getString("username"))
                     .password(resultSet.getString("password")).build());
       }
       return people.stream().filter(p->p.getId()!=null && p.getPassword()!=null && p.getUsername()!=null).collect(toList());
    }

    public Map<PersonDTO,List<CommentDTO>> getCommentsPerSpitter() throws SQLException{
        Connection connection= mySqlDataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM comments LEFT JOIN" +
                " spitters ON comments.spitterid=spitters.ID");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<CommentDTO> people=new ArrayList<>();
        List<CommentPersonDTO> commentPerson=new ArrayList<>();
        Map<PersonDTO,List<CommentDTO>> output = new HashMap<>();
        while(resultSet.next()){
           CommentDTO commentDTO= CommentDTO.builder().idComment(resultSet.getInt("idcomments"))
                    .text(resultSet.getString("text"))
                    .build();
           PersonDTO personDTO=PersonDTO.builder()
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .id(resultSet.getInt("ID"))
                    .build();
            if(output.get(personDTO)!=null){
                output.get(personDTO).add(commentDTO);
            }
            else{
                List<CommentDTO> list=new ArrayList<>();
                list.add(commentDTO);
                output.put(personDTO, list);
            }
        }
        return output;
    }

    public Map<PersonDTO,List<CommentDTO>> getCommentsPerSpitterFunctional() throws SQLException{
        Connection connection= mySqlDataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM comments LEFT JOIN" +
                " spitters ON comments.spitterid=spitters.ID");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<CommentPersonDTO> list=new ArrayList<>();
        while(resultSet.next()) {
            list.add(CommentPersonDTO.builder()
                    .idcomments(resultSet.getInt("idcomments"))
                    .text(resultSet.getString("text"))
                    .ID(resultSet.getInt("ID"))
                    .USERNAME(resultSet.getString("USERNAME"))
                    .PASSWORD(resultSet.getString("PASSWORD"))
                    .FULLNAME(resultSet.getString("FULLNAME"))
                    .EMAIL(resultSet.getString("EMAIL"))
                    .build());
        }

       return list.stream().collect(groupingBy(object->buildPerson(object),mapping(obj->buildComment(obj),toList())));

    }

    private PersonDTO buildPerson(CommentPersonDTO commentPersonDTO){
        return    PersonDTO.builder()
                    .id(commentPersonDTO.getID())
                    .username(commentPersonDTO.getUSERNAME())
                    .password(commentPersonDTO.getPASSWORD())
                    .build();
    }

    private CommentDTO buildComment(CommentPersonDTO commentPersonDTO){
            return CommentDTO.builder()
                    .idComment(commentPersonDTO.getIdcomments())
                    .text(commentPersonDTO.getText())
                    .build();
    }

    public void createPerson(PersonDTO personDTO) throws SQLException{
        Connection connection= mySqlDataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO spitters(USERNAME,PASSWORD,FULLNAME,EMAIL)" +
                "               VALUES(?,?,?,?)");
        preparedStatement.setString(1,personDTO.getUsername());
        preparedStatement.setString(2,personDTO.getPassword());
        preparedStatement.setString(3,personDTO.getFullname());
        preparedStatement.setString(4,personDTO.getEmail());
        preparedStatement.executeUpdate();


    }

}
