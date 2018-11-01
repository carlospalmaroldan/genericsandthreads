package generics;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class CommentDTO {
    Integer idComment;
    String text;
    Person person;
}
