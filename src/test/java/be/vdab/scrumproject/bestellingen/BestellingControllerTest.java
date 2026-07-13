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
@Sql({"/leveranciers.sql", "/artikelen.sql", "/bestellingen.sql", "/bestellijnen.sql"})
public class BestellingControllerTest {
    private final MockMvcTester mockMvcTester;
    private final JdbcClient jdbcClient;
    private final EntityManager entityManager;
    private static final String ARTIKELEN_TABLE = "artikelen";
    private static final String UITGAANDE_LEVERINGEN_TABLE = "uitgaandeleveringen";

    public BestellingControllerTest(MockMvcTester mockMvcTester, JdbcClient jdbcClient, EntityManager entityManager) {
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
    public void werkAfMaaktUitgaandeLevering() {
        var bestelId = idBestelling();
        var response = mockMvcTester.post().uri("/bestellingen/afwerken/{bestelId}", bestelId);

        assertThat(response)
                .hasStatusOk();

        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcClient,
                        UITGAANDE_LEVERINGEN_TABLE,
                        "bestelId = " + bestelId
                )
        ).isOne();
    }

    @Test
    public void werkAfVerminderVoorraad() {
        var bestelId = idBestelling();

        assertThat(
                mockMvcTester.post().uri("/bestellingen/afwerken/{bestelId}", bestelId)
        ).hasStatusOk();

        entityManager.flush();

        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcClient,
                        ARTIKELEN_TABLE,
                        "naam = 'naam1' and voorraad = 4"
                )
        ).isOne();
    }
}
