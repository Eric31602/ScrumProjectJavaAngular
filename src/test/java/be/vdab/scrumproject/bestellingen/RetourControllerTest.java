package be.vdab.scrumproject.bestellingen;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql("/bestellingen.sql")
public class RetourControllerTest {
    private final MockMvcTester mockMvcTester;
    private final JdbcClient jdbcClient;
    private final EntityManager entityManager;
    private final String BESTELLINGEN_TABLE = "bestellingen";

    public RetourControllerTest(MockMvcTester mockMvcTester, JdbcClient jdbcClient, EntityManager entityManager) {
        this.mockMvcTester = mockMvcTester;
        this.jdbcClient = jdbcClient;
        this.entityManager = entityManager;
    }

    private int idBestelling() {
        return jdbcClient.sql("""
select bestelId
from bestellingen
where betalingsCode = 'ABC123'
""").query(Integer.class).single();
    }

    @Test
    public void beginRetourUpdateCorrect() {
        var bestelId = idBestelling();

        var response = mockMvcTester.put().uri("/retours/start/{id}", bestelId);

        assertThat(response).hasStatusOk();

        entityManager.flush();

        assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, BESTELLINGEN_TABLE,
                "bestellingsStatusId = 9")
        ).isOne();
    }
}
