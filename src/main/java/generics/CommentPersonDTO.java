package generics;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentPersonDTO {

    Integer idcomments;
    String text;
    String spitterid;
    Integer ID;
    String USERNAME;
    String PASSWORD;
    String EMAIL;
    String FULLNAME;


}
