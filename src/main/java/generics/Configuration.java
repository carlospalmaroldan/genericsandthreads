package generics;


/*import oracle.jdbc.replay.OracleConnectionPoolDataSourceImpl;
import oracle.jdbc.replay.OracleDataSourceImpl;*/
import org.springframework.context.annotation.Bean;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

/*import oracle.jdbc.datasource.*;*/

import javax.sql.DataSource;
import java.sql.SQLException;

@org.springframework.context.annotation.Configuration
public class Configuration {
  /*  @Bean
    DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSourceImpl() ;
        dataSource.setUser("admin");
        dataSource.setPassword("admin");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        return dataSource;
    }*/

    @Bean
    DataSource mySqlDataSource() throws SQLException{
        MysqlConnectionPoolDataSource mySqlDataSource= new MysqlConnectionPoolDataSource();
        mySqlDataSource.setUser("root");
        mySqlDataSource.setPassword("root");
        mySqlDataSource.setURL("jdbc:mysql://localhost:3306/spitter");
        mySqlDataSource.setUseSSL(false);
        return mySqlDataSource;
    }

}
