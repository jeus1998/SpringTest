package spring.test;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {

    @Test
    public void loadJsonTest(){
        var portsStream = new ClassPathResource("posts.json");
        assertThat(portsStream.getFilename()).isEqualTo("posts.json");
    }

    record Post(
          Integer userId,
          Integer id,
          String title,
          String body
    )
    {}

    @Test
    public void parseJsonTest() throws IOException {
        var portsStream = new ClassPathResource("posts.json");
        var docCtx = JsonPath.parse(portsStream.getInputStream());
        List<Post> posts = docCtx.json();
        assertThat(posts).hasSize(100);
    }

    @Test
    public void parseFilterJsonTest() throws IOException {
        var portsStream = new ClassPathResource("posts.json");
        var docCtx = JsonPath.parse(portsStream.getInputStream());
        List<Post> posts = docCtx.read("$[?(@.userId == 9)]");
        assertThat(posts).hasSize(10);
    }

    @Test
    public void hasPropertyTest() throws IOException {
        var usersStream = new ClassPathResource("users.json");
        var docCtx = JsonPath.parse(usersStream.getInputStream());

        String bs = docCtx.read("$.company.bs");
        assertThat(bs).isNotNull();
        assertThat(bs).contains("real-time");
    }

}
